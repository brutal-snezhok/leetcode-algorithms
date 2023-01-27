package stack.medium;

import java.util.*;

// https://leetcode.com/problems/simplify-path/
public class SimplifyPath_71 {
    public static String simplifyPath(String path) {
        // time O(n)
        // space O(n)

        Deque<String> stack = new ArrayDeque<>(); // add only name of directory
        Set<String> skip = new HashSet<>(Arrays.asList("", "..", "."));
        for(String str : path.split("/")) {
            if(!stack.isEmpty() && "..".equals(str))
                stack.removeFirst();
            else if(!skip.contains(str))
                stack.addFirst(str);
        }

        if(stack.size() == 0) return "/";

        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()) {
            res.append("/").append(stack.removeLast());
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/../"));
    }

}
