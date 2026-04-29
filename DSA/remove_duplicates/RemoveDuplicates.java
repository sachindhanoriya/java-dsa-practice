package remove_duplicates;

import java.util.*;


public class RemoveDuplicates {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] testCases = new String[]{"abbaca", "azxxzy"};
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(sol.removeDuplicates(testCases[i]));
        }
    }
}

class Solution {
    public String removeDuplicatesBrute(String str) {
        String[] dupes = new String[]{"aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj", "kk", "ll", "mm", "nn", "oo", "pp", "qq", "rr", "ss", "tt", "uu", "vv", "ww", "xx", "yy", "zz"};
        int prev = -1;
        int curr = str.length();
        while (prev != curr) {
            prev = curr;
            for (String dupe: dupes)
                str = str.replaceAll(dupe, "");
            curr = str.length();
        }
        return str;
    }

    public String removeDuplicates(String str) {
        Stack<Character> stk = new Stack<>();
        for (Character ch: str.toCharArray()) {
            if (stk.isEmpty() || stk.peek() != ch)
                stk.push(ch);
            else
                stk.pop();
        }
        return stk.toString().replaceAll(", |\\[|\\]", "");
    }

}
