package DSA.balanced_parenthesis_with_wildcards;

import java.lang.reflect.Array;
import java.util.Arrays;

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
    private int strLen;
    private int[][] cache;

    public boolean checkValidString(String s) {
        this.str = s;
        this.strLen = s.length();
        this.cache = new int[strLen + 1][strLen + 1];
        for (int[] row: cache) 
            Arrays.fill(cache, -1);
        return recurseDPMemo(0, 0);
    }

    private boolean recurseDPMemo(int curIdx, int openBraces) {
        if (curIdx == strLen)
            return openBraces == 0;

        if (this.cache[curIdx][openBraces] > -1)
            return cache[curIdx][openBraces] == 1;
        
        char curChar = this.str.charAt(curIdx);
        boolean isValid = false;
        
        if (curChar == '(') {
            isValid |= recurseDPMemo(curIdx + 1, openBraces + 1);
        } else if (curChar == '*') {
            isValid |= recurseDPMemo(curIdx + 1, openBraces + 1);
            if (openBraces > 0)
                isValid |= recurseDPMemo(curIdx + 1, openBraces - 1);
            isValid |= recurseDPMemo(curIdx + 1, openBraces);
        } else if (openBraces > 0) {
            isValid |= recurseDPMemo(curIdx + 1, openBraces - 1);
        }
        
        cache[curIdx][openBraces] = isValid ? 1: 0;
        return isValid;
    }
}