package MergeJSONObjects;


public class MergeJSON {
    public static void main(String[] args) {
        String jsonString = 
        """

            {
                "name": "Sachin Dhanoriya",
                "age": 53,
                "income": 55.5,
                "married": true,
                "usingProsperIO": false,
                "siblings": null,
                "spouse": {
                    "name": "Sachin Dhanoriya",
                    "age": 53,
                    "income": 55.5,
                    "married": true,
                    "usingProsperIO": false,
                    "siblings": null,
                    "kids": [
                        {
                            "name": "Ira Dhanoriya",
                            "age": 53,
                            "income": 55.5,
                            "married": true,
                            "usingProsperIO": false,
                            "skills": [true, false, "engineering", 23]
                        },
                        {
                            "name": "Zeha Dhanoriya",
                            "age": 53,
                            "income": 55.5,
                            "married": true,
                            "usingProsperIO": false,
                            "skills": null
                        }
                    ]
                },
                "skills": [
                    "Java", 
                    21, 
                    23.5,
                    true, 
                    ["ele1", "ele2"],
                    {
                        "name": "Zeha Dhanoriya",
                        "age": 53,
                        "income": 55.5,
                        "married": true,
                        "usingProsperIO": false,
                        "skills": null
                    }
                ]
            }

        """;
        // jsonString = "{\"key8\": [\"stringVal\", 12, 34.56, true, false, null, {\"key9\": null }, [1, true] ] , \"key7\":{\"key6\":null,\"key4\" : true,\"key5\":false ,\"key1\" : \"value1\"    ,    \"key2\"  :  123,    \"key3\"  :  456.345 },\"key6\":null,\"key4\" : true,\"key5\":false ,\"key1\" : \"value1\"    ,    \"key2\"  :  123,    \"key3\"  :  456.345 }";
        JSON json = JSONProcessor.jsonify(jsonString);
        System.out.println(json);
        System.out.println(json.toString());
    }
}
