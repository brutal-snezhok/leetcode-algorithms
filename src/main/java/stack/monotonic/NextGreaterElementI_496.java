package stack.monotonic;

import java.util.*;

// https://leetcode.com/problems/next-greater-element-i/description/
public class NextGreaterElementI_496 {
    // solution1
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        // first solution, brute force
        // time O(m*n)
        // space O(m)
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }

        for(int i = 0; i < nums2.length; i++) {
            if(map.containsKey(nums2[i])) {
                int index = map.get(nums2[i]);
                int value = -1;

                for(int j = i + 1; j < nums2.length; j++) {
                    if(nums2[j] > nums2[i]) {
                        value = nums2[j];
                        break;
                    }
                }

                res[index] = value;
            }
        }

        return res;
    }

    // solution2
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        // monotonic stack
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
