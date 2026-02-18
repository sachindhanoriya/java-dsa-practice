package currency_conversion;

import java.util.*;

public class CurrencyConversion {
    public static void main(String[] args) {
        List<Object[]> rates = new ArrayList<>();
        rates.add(new Object[] { "USD", "JPY", 100.0 });
        rates.add(new Object[] { "JPY", "CHN", 20.0 });
        rates.add(new Object[] { "CHN", "THAI", 200.0 });
        List<Object[]> queries = new ArrayList<>();
        queries.add(new Object[] { "USD", "CHN" });
        queries.add(new Object[] { "JPY", "THAI" });
        queries.add(new Object[] { "USD", "AUD" });
        Solution sol = new Solution();
        System.out.println(sol.getConversionRates(rates, queries));
    }

}

class Solution {

    public List<Double> getConversionRates(List<Object[]> rates, List<Object[]> queries) {
        // Build the graph
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (Object[] rate : rates) {
            String from = (String) rate[0];
            String to = (String) rate[1];
            Double val = (Double) rate[2];

            graph.putIfAbsent(from, new HashMap<>());
            graph.get(from).put(to, val);

            graph.putIfAbsent(to, new HashMap<>());
            graph.get(to).put(from, 1 / val);
        }

        // Perform DFS on each query
        List<Double> res = new ArrayList<>();
        for (Object[] query : queries) {
            String from = (String) query[0];
            String to = (String) query[1];
            Set<String> visited = new HashSet<>();
            double rate = dfs(graph, visited, from, to, 1.0);
            res.add(rate);
        }

        return res;
    }

    private double dfs(Map<String, Map<String, Double>> graph, Set<String> visited, String from, String end,
            Double value) {
        if (!graph.containsKey(from) || visited.contains(from))
            return -1.0;
        if (from.equals(end))
            return value;
        visited.add(from);
        Map<String, Double> neighbours = graph.get(from);
        for (String neighbour : neighbours.keySet()) {
            Double rate = dfs(graph, visited, neighbour, end, value * neighbours.get(neighbour));
            if (rate != -1.0)
                return rate;
        }
        return -1.0;
    }
}
