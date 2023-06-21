package arraysHashing.cyclic_sort;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-all-duplicates-in-an-array/
public class FindAllDuplicatesInArray_442 {
    // solution1
    public List<Integer> findDuplicates1(int[] nums) {
        // cyclic sort
        // time O(n)
        // space O(1)

        /*  0 1 2 3 4 5 6 7
           [4,3,2,7,8,2,3,1]
           []
         */

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
            if(nums[i] - 1 != i)
                res.add(nums[i]);

        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // solution2
    public List<Integer> findDuplicates2(int[] nums) {
        // using multiply by -1
        // time O(n)
        // space O(1)

        /*  0 1 2 3 4 5 6 7
           [4,3,2,7,8,2,3,1]
         */

        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int ind = Math.abs(nums[i]) - 1;
            if(nums[ind] > 0)
                nums[ind] *= -1;
            else
                res.add(Math.abs(nums[i]));
        }

        return res;
    }

    public static void main(String[] args) {
        FindAllDuplicatesInArray_442 findAllDuplicatesInArray_442 = new FindAllDuplicatesInArray_442();
        findAllDuplicatesInArray_442.findDuplicates1(new int[]{4,3,2,7,8,2,3,1});
    }
}
