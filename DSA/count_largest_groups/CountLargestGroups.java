package count_largest_groups;


public class CountLargestGroups {
    public static void main(String[] args) {
        var sol = new Solution();
        System.out.println(sol.countLargestGroup(13));
        System.out.println(sol.countLargestGroup(2));
        System.out.println(sol.countLargestGroup(10000));
    }
}


class Solution {
    public int countLargestGroup(int n) {
        int[] groupSizes = new int[37];
        int maxGroupSize = 0;
        
        for (int i = 1; i <= n; i++) {
            int groupNumber = getGroupNumber(i);
            groupSizes[groupNumber]++;
            maxGroupSize = Math.max(maxGroupSize, groupSizes[groupNumber]);
        }
        
        int noOfGroupsOfMaxSize = 0;
        for (int i = 1; i < groupSizes.length; i++)
            if (groupSizes[i] == maxGroupSize)
                noOfGroupsOfMaxSize++;
       
        return noOfGroupsOfMaxSize;
    }

    private int getGroupNumber(int n) {
        int sumOfDigits = 0;
        while (n > 0) {
            sumOfDigits += n % 10;
            n = n / 10;
        }
        return sumOfDigits;
    }
}