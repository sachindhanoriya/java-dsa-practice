package balanced_parenthesis_with_wildcards;

import java.util.Stack;


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
    public boolean checkValidString(String s) {
        Stack<Character> stk = new Stack<>();
        return false;
    }
}