package DSA.balanced_parenthesis_with_wildcards;


public class BalancedParanthesisWithWildcards {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] testCases = new String[]{"()", "(*)", "((*)", "(*))", "*(", "*)", ")*", "(*))*", "(*)*((**", "**))"};
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(sol.checkValidString(testCases[i]));
        }
    }
}

/*
 ( ( * )
 1 2 2 2
 1 1 1 1

 ( * ) )
 1 1 1 1
 2 2 2 1

 ( ( ( * * ) ( * * ) )
 1 2 3 3 3 2 3 3 3 2 1
-1 0 1 2 2 2 1 2 2 2 1
*/

class Solution {
    private String str;
    private int strLen;
    public boolean checkValidString(String s) {
        this.str = s;
        this.strLen = s.length();
        return recurse(0, 0);
    }

    private boolean recurse(int curIdx, int openBraces) {
        if (curIdx == strLen)
            return openBraces == 0;
        
        char curChar = this.str.charAt(curIdx);
        boolean isValid = false;
        
        if (curChar == '(') {
            isValid |= recurse(curIdx + 1, openBraces + 1);
        }
        else if (curChar == '*') {
            isValid |= recurse(curIdx + 1, openBraces + 1);
            if (openBraces > 0)
                isValid |= recurse(curIdx + 1, openBraces - 1);
            isValid |= recurse(curIdx + 1, openBraces);
        } else if (openBraces > 0) {
            isValid |= recurse(curIdx + 1, openBraces - 1);
        }
        return isValid;
    }
}