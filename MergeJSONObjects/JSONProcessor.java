package MergeJSONObjects;

import java.util.Map;
import java.util.HashMap;

public class JSONProcessor {
    static int idx;
    static int n;
    static char[] chars;
    static char cur;

    public static String stringify(JSON json) {
        return json.toString();
    }

    public static JSON jsonify(String jsonString) {
        chars = jsonString.toCharArray();
        n = jsonString.length();
        idx = 0;
        Map<String, Object> json = null;
        try {
            json = getJSONObject();
        } catch (NullPointerException e) {
            throw new JSONParseException("Invalid input JSON");
        } catch (Exception e) {
            throw new JSONParseException("JSON Parse Error");
        }
        return new JSON(json);
    }

    private static Map<String, Object> getJSONObject() {
        cur = chars[idx];
        if (cur != '{')
            throw new JSONParseException(String.format("Invalid character at %d", idx));
        //
        cur = chars[++idx];

        Map<String, Object> curJson = new HashMap<>();
        while (cur != '}') {
            // Parse Key
            while (cur == ' ')
                cur = chars[++idx];

            if (cur != '"')
                throw new JSONParseException(String.format("Invalid character at %d", idx));

            String key = getString();
            curJson.put(key, null);

            // Parse Value
            while (cur == ' ')
                cur = chars[++idx];

            if (cur != ':')
                throw new JSONParseException(String.format("Invalid character at %d", idx));

            cur = chars[++idx];
            while (cur == ' ')
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

            while (cur == ' ')
                cur = chars[++idx];

            if (cur == ',')
                cur = chars[++idx];
        }

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNull'");
    }

    private static Object getArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArray'");
    }
}
