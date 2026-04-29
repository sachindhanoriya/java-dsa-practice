package DSA.currency_conversion;

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
        System.out.println(sol.getConversionRates2(rates, queries));
    }

}

class Solution {

    private Map<String, Map<String, Double>> conversionRates;
    private Set<String> visited;

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

    public List<Double> getConversionRates2(List<Object[]> conversionRatesList, List<Object[]> queries) {
        this.conversionRates = new HashMap<>();
        this.visited = new HashSet<>();
        for (Object[] conversionRate : conversionRatesList) {
            String to = (String) conversionRate[0];
            String from = (String) conversionRate[1];
            Double rate = (Double) conversionRate[2];
            
            this.conversionRates.putIfAbsent(to, new HashMap<>());
            this.conversionRates.get(to).put(from, rate);
            
            this.conversionRates.putIfAbsent(from, new HashMap<>());
            this.conversionRates.get(from).put(to, 1/rate);
        }

        List<Double> result = new ArrayList<>();
        for (Object[] query : queries) {
            String from = (String) query[0];
            String to = (String) query[1];
            visited.clear();
            Double conversionRate = dfs2(from, to, 1);
            result.add(conversionRate);
        }
        return result;
    }

    private Double dfs2(String from, String to, double rate) {
        if (from.equals(to))
            return rate;
        if (!this.conversionRates.containsKey(from) || visited.contains(from))
            return -1.0;

        visited.add(from);
        for (Map.Entry<String, Double> conversion : this.conversionRates.get(from).entrySet()) {
            Double conversionRate = dfs2(conversion.getKey(), to, rate * conversion.getValue());
            if (conversionRate > -1)
                return conversionRate;
        }
        return -1.0;
    }
}
