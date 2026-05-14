package DSA.balanced_parenthesis_with_wildcards;

import java.util.Arrays;
import java.util.Stack;

public class BalancedParanthesisWithWildcards {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] testCases = new String[]{"***((*", "****", "***)))", "()", "(*)", "((*)", "(*))", "*(", "*)", ")*", "(*))*", "(*)*((**", "**))"};
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(sol.checkValidString(testCases[i]));
        }
    }
}


class Solution {
    private String str;
    private int n;
    private int[][] cache;

    public boolean checkValidString(String s) {
        this.str = s;
        this.n = s.length();
        this.cache = new int[n + 1][n + 1];
        for (int[] row: cache) 
            Arrays.fill(row, -1);
        // return recurseDPMemo(0, 0);
        // return dpUsingTabulation();
        // return greedyUsingStacks();
        return greedyUsingTwoPointers();
    }

    private boolean recurseDPMemo(int idx, int openBraces) {
        // DP Top-Down using Memoization
        if (idx == n)
            return openBraces == 0;

        if (this.cache[idx][openBraces] > -1)
            return cache[idx][openBraces] == 1;
        
        char curChar = this.str.charAt(idx);
        var isValid = false;
        
        if (curChar == '(') {
            isValid |= recurseDPMemo(idx + 1, openBraces + 1);
        } else if (curChar == '*') {
            isValid |= recurseDPMemo(idx + 1, openBraces + 1);
            if (openBraces > 0)
                isValid |= recurseDPMemo(idx + 1, openBraces - 1);
            isValid |= recurseDPMemo(idx + 1, openBraces);
        } else if (openBraces > 0) {
            isValid |= recurseDPMemo(idx + 1, openBraces - 1);
        }
        
        cache[idx][openBraces] = isValid ? 1: 0;
        return isValid;
    }

    private boolean dpUsingTabulation() {
        // DP Bottom-Up using Tabulation
        var dpTable = new boolean[n + 1][n + 1];
        dpTable[n][0] = true;

        for (int i = n - 1; i >= 0; i--) {
            for (int o = 0; o < n; o++) {
                boolean isValid = false;
                char curChar = this.str.charAt(i);
                
                if (curChar == '(') {
                    isValid |= dpTable[i + 1][o + 1];
                } else if (curChar == '*') {
                    isValid |= dpTable[i + 1][o + 1];
                    if (o > 0)
                        isValid |= dpTable[i + 1][o - 1];
                    isValid |= dpTable[i + 1][o];
                } else {
                    if (o > 0)
                        isValid |= dpTable[i + 1][o - 1];
                }
                dpTable[i][o] = isValid;
            }
        }

        return dpTable[0][0];
    }

    private boolean greedyUsingStacks() {
        var openBraces = new Stack<Integer>();
        var asterisks = new Stack<Integer>();

        for (int i = 0; i < n; i++) {
            char curChar = this.str.charAt(i);
            if (curChar == '(')
                openBraces.push(i);
            else if (curChar == '*')
                asterisks.push(i);
            else {
                if (!openBraces.isEmpty())
                    openBraces.pop();
                else if (!asterisks.isEmpty())
                    asterisks.pop();
                else
                    return false;
            }
        }
        while (!openBraces.isEmpty()) {
            if (asterisks.isEmpty() || openBraces.peek() > asterisks.peek())
                return false;
            openBraces.pop();
            asterisks.pop();
        }
        return true;
    }

    private boolean greedyUsingTwoPointers() {
        var openBraces = 0;
        var closeBraces = 0;

        for (int i = 0; i < n; i++) {
            if (this.str.charAt(i) == '(' || this.str.charAt(i) == '*')
                openBraces++;
            else
                openBraces--;
            
            if (this.str.charAt(n - i - 1) == ')' || this.str.charAt(n - i - 1) == '*')
                closeBraces++;
            else
                closeBraces--;

            if (openBraces < 0 || closeBraces < 0)
                return false;
        }

        return true;
    }
}