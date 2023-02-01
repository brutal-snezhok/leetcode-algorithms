package stack.easy;

import java.util.*;

// https://leetcode.com/problems/next-greater-element-i/description/
public class NextGreaterElementI_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // time O(n + m)
        // space O(n)

        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        Map<Integer, Integer> map = new HashMap<>(); // {val, index}
        for(int i = 0; i < nums1.length; i++)
            map.put(nums1[i], i);

        Deque<Integer> stack = new ArrayDeque<>(); // mono decreasing
        for(int num : nums2) {
            while(!stack.isEmpty() && stack.peek() < num) {
                int val = stack.removeFirst();
                if(map.containsKey(val))
                    res[map.get(val)] = num;
            }
            stack.addFirst(num);
        }

        return res;
    }
}
