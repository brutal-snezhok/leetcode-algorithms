package arraysHashing.easy.cyclic_sort;

public class CyclicSort {
    /*
     We are given an array containing ‘n’ objects. Each object, when created,
     was assigned a unique number from 1 to ‘n’ based on their creation sequence.
     This means that the object with sequence number ‘3’ was created just before the object with sequence number ‘4’.
    * */
    public void sort(int[] nums) {
        int n = nums.length;
        int i = 0;
        while(i < n) {
            int j = nums[i] - 1;
            if(nums[i] != nums[j])
                swap(nums, i, j);
            else
                i++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
