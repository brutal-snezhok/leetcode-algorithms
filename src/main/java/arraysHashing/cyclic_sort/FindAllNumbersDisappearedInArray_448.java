package arraysHashing.cyclic_sort;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
public class FindAllNumbersDisappearedInArray_448 {
    // solution1
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        // multiply by -1
        // time O(n)
        // space O(1), do not count res list

        int n = nums.length;
        for(int i = 0; i < n; i++) {
            int ind = Math.abs(nums[i]) - 1;
            nums[ind] = nums[ind] < 0 ? nums[ind] : nums[ind] * -1;
        }

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++)
            if(nums[i] > 0)
                res.add(i + 1);

        return res;
    }

    // solution2
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        // cyclic sort
        // time O(n)
        // space O(1), do not count res list

        int n = nums.length;
        int i = 0;
        while(i < n) {
            int j = nums[i] - 1;
            if(nums[i] != nums[j])
                swap(nums, i, j);
            else
                i++;
        }

        List<Integer> res = new ArrayList<>();
        for(i = 0; i < n; i++)
            if(nums[i] != i + 1)
                res.add(i + 1);

        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
