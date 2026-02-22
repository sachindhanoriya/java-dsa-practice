package MergeJSONObjects;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * `\` :- escape character
 * `\` & `"` :- characters to be escaped
 * Keys (field names) must be strings, enclosed in double quotes " "
 * Values can be:
 * - String (in double quotes)
 * - Number (integer or floating point)
 * - Boolean (true or false)
 * - null
 * - Object (another JSON object)
 * - Array (a list of values)
 * Sample JSON :-
 * {
 * "name": "Sachin Dhanoriya",
 * "age": 53,
 * "income": 55.5,
 * "married": true,
 * "usingProsperIO": false,
 * "siblings": null,
 * "spouse": {
 * "name": "Sachin Dhanoriya",
 * "age": 53,
 * "income": 55.5,
 * "married": true,
 * "usingProsperIO": false,
 * "siblings": null,
 * "kids": [
 * {
 * "name": "Ira Dhanoriya",
 * "age": 53,
 * "income": 55.5,
 * "married": true,
 * "usingProsperIO": false,
 * "skills": [true, false, "engineering", 23]
 * },
 * {
 * "name": "Zeha Dhanoriya",
 * "age": 53,
 * "income": 55.5,
 * "married": true,
 * "usingProsperIO": false,
 * "skills": null
 * }
 * ]
 * },
 * "skills": [
 * "Java",
 * 21,
 * 23.5,
 * true,
 * ["ele1", "ele2"],
 * {
 * "name": "Zeha Dhanoriya",
 * "age": 53,
 * "income": 55.5,
 * "married": true,
 * "usingProsperIO": false,
 * "skills": null
 * }
 * ]
 * }
 *
 */
class JSON implements Comparable<JSON>, Cloneable, Serializable, Iterable<Map.Entry<String, Object>> {

    private Map<String, Object> data;

    public JSON(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public int size() {
        return this.data.size();
    }

    @Override
    public Iterator<Entry<String, Object>> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public int compareTo(JSON json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    public boolean equals(JSON json) {
        return this.data.equals(json.getData());
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

}
