package MergeJSONObjects;

import java.util.Map;
import java.util.HashMap;

public class JSONProcessor {
    static int idx;
    static int n;
    static char[] chars;

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
        char cur = chars[idx];
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
            //
            cur = chars[++idx];

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
            else if (cur > '1' && cur < '9')
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

            //
            cur = chars[++idx];
            
            while (cur == ' ')
                cur = chars[++idx];
            
            if (cur == ',')
                cur = chars[++idx];
        }

        return curJson;
    }

    private static String getString() {
        StringBuilder key = new StringBuilder();
        char cur = chars[++idx];
        while (cur != '"') {
            if (cur == '\\') {
                cur = chars[++idx];
                if (cur != '\\' && cur != '"')
                    throw new JSONParseException(String.format("Invalid character at %d", idx));
            }
            key.append(cur);
            cur = chars[++idx];
        }
        return key.toString();
    }

    private static Object getNumber() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStringValue'");
    }

    private static Object getBooleanTrue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBooleanTrue'");
    }

    private static Object getBooleanFalse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBooleanFalse'");
    }

    private static Object getNull() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBooleanFalse'");
    }

    private static Object getArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBooleanFalse'");
    }
}
