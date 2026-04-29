package DSA.merge_intervals;

import java.util.*;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        Solution sol = new Solution();
        System.out.println(sol.merge(intervals));
    }
}

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        var prevInt = intervals[0];
        List<int[]> auxRes = new ArrayList<>();
        var n = intervals.length;
        var i = 1;
        while (i < n) {
            var curInt = intervals[i];
            if (curInt[0] <= prevInt[1]) {
                prevInt[1] = Math.max(prevInt[1], curInt[1]);
            }
            else {
                auxRes.add(prevInt);
                prevInt = curInt;
            }
            i++;
        }
        auxRes.add(prevInt);
        // return auxRes.stream().toArray(int[][]::new);
        return auxRes.toArray(new int[auxRes.size()][]);
    }
}