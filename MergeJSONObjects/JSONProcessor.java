package MergeJSONObjects;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class JSONProcessor {
    static int idx;
    static int n;
    static char[] chars;
    static char cur;

    public static String stringify(JSON json) {
        return json.toString();
    }

    public static JSON jsonify(String jsonString) {
        jsonString = jsonString.trim();
        n = jsonString.length();
        chars = jsonString.toCharArray();
        idx = 0;
        Map<String, Object> json = null;
        try {
            json = getJSONObject();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            throw new JSONParseException("Invalid input JSON");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw new JSONParseException("Invalid input JSON");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new JSONParseException("JSON Parse Error");
        }
        return new JSON(json);
    }


    public static JSON mergeJSONObjects(JSON json1,JSON json2) {
        Map<String, Object> mergedJson = mergeJSONData(json1.getData(), json2.getData());
        return new JSON(mergedJson);
    }

    private static Map<String, Object> getJSONObject() {
        cur = chars[idx];
        while (cur == ' ' || cur == '\n' || cur == '\t')
            cur = chars[++idx];
        
        if (cur != '{')
            throw new JSONParseException(String.format("Invalid character at %d", idx));
        //
        cur = chars[++idx];

        Map<String, Object> curJson = new HashMap<>();
        while (cur != '}') {
            // Parse Key
            while (cur == ' ' || cur == '\n' || cur == '\t')
                cur = chars[++idx];

            if (cur != '"')
                throw new JSONParseException(String.format("Invalid character at %d", idx));

            String key = getString();
            curJson.put(key, null);

            // Parse Value
            while (cur == ' ' || cur == '\n' || cur == '\t')
                cur = chars[++idx];

            if (cur != ':')
                throw new JSONParseException(String.format("Invalid character at %d", idx));

            cur = chars[++idx];
            while (cur == ' ' || cur == '\n' || cur == '\t')
                cur = chars[++idx];

            Object value = null;
            if (cur == '"')
                value = getString();
            else if (cur >= '0' && cur <= '9')
                value = getNumber();
            else if (cur == 't')
                value = getBooleanTrue();
            else if (cur == 'f')
                value = getBooleanFalse();
            else if (cur == 'n')
                value = getNull();
            else if (cur == '{')
                value = getJSONObject();
            else if (cur == '[')
                value = getArray();
            else
                throw new JSONParseException(String.format("Invalid character at %d", idx));
            curJson.put(key.toString(), value);

            while (cur == ' ' || cur == '\n' || cur == '\t')
                cur = chars[++idx];

            if (cur == ',')
                cur = chars[++idx];
        }

        if (idx < n - 1)
            cur = chars[++idx];
        return curJson;
    }

    private static String getString() {
        StringBuilder key = new StringBuilder();
        cur = chars[++idx];
        while (cur != '"') {
            if (cur == '\\') {
                cur = chars[++idx];
                if (cur != '\\' && cur != '"')
                    throw new JSONParseException(String.format("Invalid character at %d", idx));
            }
            key.append(cur);
            cur = chars[++idx];
        }

        cur = chars[++idx];
        return key.toString();
    }

    private static Object getNumber() {
        StringBuffer number = new StringBuffer();
        while (cur >= '0' && cur <= '9') {
            number.append(cur);
            cur = chars[++idx];
        }
        if (cur != '.')
            return Integer.parseInt(number.toString());
        if (cur == '.')
            number.append(cur);
        cur = chars[++idx];
        while (cur >= '0' && cur <= '9') {
            number.append(cur);
            cur = chars[++idx];
        }
        return Double.parseDouble(number.toString());
    }

    private static boolean getBooleanTrue() {
        char[] boolTrue = new char[] { 't', 'r', 'u', 'e' };
        for (char ch : boolTrue) {
            if (ch != cur)
                throw new JSONParseException(String.format("Invalid character at %d", idx));
            cur = chars[++idx];
        }
        return true;
    }

    private static boolean getBooleanFalse() {
        char[] boolTrue = new char[] { 'f', 'a', 'l', 's', 'e' };
        for (char ch : boolTrue) {
            if (ch != cur)
                throw new JSONParseException(String.format("Invalid character at %d", idx));
            cur = chars[++idx];
        }
        return false;
    }

    private static Object getNull() {
        char[] boolTrue = new char[] { 'n', 'u', 'l', 'l' };
        for (char ch : boolTrue) {
            if (ch != cur)
                throw new JSONParseException(String.format("Invalid character at %d", idx));
            cur = chars[++idx];
        }
        return null;
    }

    private static List<Object> getArray() {
        List<Object> array = new ArrayList<>();
        cur = chars[++idx];
        while (cur != ']') {
            while (cur == ' ' || cur == '\n' || cur == '\t')
                cur = chars[++idx];

            Object value = null;
            if (cur == '"')
                value = getString();
            else if (cur >= '0' && cur <= '9')
                value = getNumber();
            else if (cur == 't')
                value = getBooleanTrue();
            else if (cur == 'f')
                value = getBooleanFalse();
            else if (cur == 'n')
                value = getNull();
            else if (cur == '{')
                value = getJSONObject();
            else if (cur == '[')
                value = getArray();
            else
                throw new JSONParseException(String.format("Invalid character at %d", idx));
            
            array.add(value);
            
            while (cur == ' ' || cur == '\n' || cur == '\t' || cur == ',')
                cur = chars[++idx];
        }
        cur = chars[++idx];
        return array;
    }

    private static Map<String, Object> mergeJSONData(Map<String, Object> dataMap1, Map<String, Object> dataMap2) {
        if (dataMap1 == null)
            return dataMap2;
        if (dataMap2 == null)
            return dataMap1;

        Map<String, Object> resultMap = new HashMap<>();

        for (Map.Entry<String, Object> entry : dataMap1.entrySet()) {
            String key = entry.getKey();
            Object value1 = entry.getValue();
            Object value2 = dataMap2.get(key);
            if (!dataMap2.containsKey(key) || value1.equals(dataMap2.get(key)))
                resultMap.put(key, value1);
            else {
                if (value1 instanceof Map && value2 instanceof Map) {
                    Map<String, Object> resultSubMap = mergeJSONData((Map<String, Object>) value1, (Map<String, Object>) value2);
                    resultMap.put(key, resultSubMap);
                }
                else {
                    List<Object> listValue = new ArrayList<>();
                    listValue.add(value1);
                    listValue.add(value2);
                    resultMap.put(key, listValue);
                }
            }
        }

        for (Map.Entry<String, Object> entry : dataMap2.entrySet())
            if (!dataMap1.containsKey(entry.getKey()))
                resultMap.put(entry.getKey(), entry.getValue());

        return resultMap;
    }
}
