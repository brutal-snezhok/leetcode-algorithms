package arraysHashing.prefix_sum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/continuous-subarray-sum/description/
public class ContinuousSubarraySum_523 {
    // solution1
    public boolean checkSubarraySum1(int[] nums, int k) {
        // time O(n)
        // space O(n)

        int n = nums.length;
        int[] prefixSum = new int[n + 1];

        for(int i = 1; i <= n; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];

        Set<Integer> reminderSet = new HashSet<>();
        for(int i = 2; i <= n; i++) {
            reminderSet.add(prefixSum[i - 2] % k);

            if(reminderSet.contains(prefixSum[i] % k))
                return true;
        }

        return false;
    }

    // solution2
    public boolean checkSubarraySum2(int[] nums, int k) {
        // time O(n)
        // space O(n)

        int n = nums.length;
        int[] prefixSum = new int[n + 1];

        for(int i = 1; i <= n; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];

        Map<Integer, Integer> reminderMap = new HashMap<>(); // {reminder, index}
        for(int i = 0; i <= n; i++) {
            int reminder = prefixSum[i] % k;

            if(reminderMap.containsKey(reminder) && reminderMap.get(reminder) - i >= 2)
                return true;

            // update only if absent
            // reminderMap.put(reminder, reminderMap.getOrDefault(reminder, i)); // for [5,0,0,0], k = 3
            reminderMap.putIfAbsent(reminder, i);
        }

        return false;
    }

    // solution3
    public boolean checkSubarraySum3(int[] nums, int k) {
        // time O(n)
        // space O(min(n, k)), there are only k possible reminders

        int n = nums.length;
        Map<Integer, Integer> reminderMap = new HashMap<>(Map.of(0, 0)); // {reminder, index}
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            int reminder = sum % k;

            if(reminderMap.containsKey(reminder) && i - reminderMap.get(reminder) >= 1)
                return true;

            reminderMap.putIfAbsent(reminder, i + 1); // put i + 1, for [5,0,0,0], k = 3
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{23,6,9};
        int[] arr2 = new int[]{23,2,4,6,7};
        int[] arr3 = new int[]{2,4,3};
        int[] arr4 = new int[]{1,0};
        ContinuousSubarraySum_523 cont = new ContinuousSubarraySum_523();
        //cont.checkSubarraySum1(arr1, 6); // false
        cont.checkSubarraySum3(arr4, 2); // false
    }
}
