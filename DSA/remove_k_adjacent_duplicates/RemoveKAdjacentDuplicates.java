package DSA.remove_k_adjacent_duplicates;

import java.util.Stack;
import java.util.function.Function;

public class RemoveKAdjacentDuplicates {

    record Inputs(String str, int k){}

    public static void main(String[] args) {
        var solution = new Solution();
        // var inputA = new Inputs("abcd", 2);
        // System.out.println(solution.removeDuplicates(inputA.str(), inputA.k()));
        // var inputB = new Inputs("deeedbbcccbdaa", 3);
        // System.out.println(solution.removeDuplicates(inputB.str, inputB.k));
        // var inputC = new Inputs("pbbcggttciiippooaais", 2);
        // System.out.println(solution.removeDuplicates(inputC.str, inputC.k));
        var inputC = new Inputs("aaaa", 2);
        System.out.println(solution.removeDuplicates(inputC.str, inputC.k));
    }
}

class Solution {
    record Element(char c, int cnt){}

    Function<Stack<Element>, Element> stackTop = stk -> stk.size() > 0 ? stk.peek() : null;
    
    public String removeDuplicates(String s, int k) {
        var stack = new Stack<Element>();
        char[] str = s.toCharArray();
        
        stack.push(new Element(str[0], 1));
        int n = str.length;
        int i = 1;

        while (i < n) {
            // Element top = stack.peek();
            // Element top = stackTop(stack);
            Element top = stackTopOrNull(stack);

            if (top.cnt == k)
                for (int j = 0; j < k; j++)
                    stack.pop();

            // top = stack.peek();
            // top = stackTop(stack);
            top = stackTopOrNull(stack);
            char curElem = str[i];
            int curCnt = 1;

            if (top != null && curElem == top.c) {
                curCnt = top.cnt + 1;
            }

            stack.push(new Element(curElem, curCnt));
           
            i++;
        }

        if (!stack.isEmpty()) {
            Element top = stackTopOrNull(stack);
            if (top.cnt == k)
                for (int j = 0; j < k; j++)
                    stack.pop();
        }

        var res = stack.stream()
                    .map(ele -> ele.c)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
        return res;
    }

    private Element stackTopOrNull(Stack<Element> stack) {
        return stack.size() > 0 ? stack.peek() : null;
    }
}