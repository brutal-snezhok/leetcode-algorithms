package arraysHashing.bucket_sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/top-k-frequent-elements/description/
public class TopKFrequentElements_347 {
    public int[] topKFrequent(int[] nums, int k) {
        // time O(n)
        // space O(n)
        // bucket sort

        int[] res = new int[k];
        // populate freq map
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<List<Integer>> buckets = new ArrayList<>(nums.length);
        for (int i = 0; i <= nums.length; i++){
            buckets.add(new ArrayList<>());
        }

        // populate buckets
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            int key = entry.getKey();

            List<Integer> currList = buckets.get(value);
            currList.add(key);
        }

        int count = 0;
        for(int i = buckets.size() - 1; i > 0; i--) {
            if(buckets.get(i) != null) {
                List<Integer> curr = buckets.get(i);
                int p = 0;
                for(int j = count; j < k && p < curr.size(); j++) {
                    res[j] = curr.get(p++);
                    count++;
                }

                if(count == k)
                    break;
            }


        }

        return res;
    }
}
