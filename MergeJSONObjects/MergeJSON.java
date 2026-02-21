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
                            "skills" [true, false, "engineering", 23]
                        },
                        {
                            "name": "Zeha Dhanoriya",
                            "age": 53,
                            "income": 55.5,
                            "married": true,
                            "usingProsperIO": false,
                            "skills": null,
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
                        "skills": null,
                    }
                ]
            }

        """;
        jsonString = "{\"key1\" : \"value1\"}";
        JSON json = JSONProcessor.jsonify(jsonString);
        System.out.println(json);
    }
}
