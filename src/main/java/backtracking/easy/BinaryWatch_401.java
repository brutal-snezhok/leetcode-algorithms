package backtracking.easy;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-watch/description/
public class BinaryWatch_401 {
    public List<String> readBinaryWatch(int turnedOn) {
        // backtracking
        // time O(1), since turnedOn bound with 10
        // space O(1)

        List<String> res = new ArrayList<>();
        if(turnedOn >= 9)
            return res;

        int[] nums1 = new int[]{8, 4, 2, 1};
        int[] nums2 = new int[]{32, 16, 8, 4, 2, 1};

        for(int i = 0; i <= turnedOn; i++) {
            List<Integer> hours = generateDigits(nums1, i);
            List<Integer> minutes = generateDigits(nums2, turnedOn - i);

            for(int h : hours) {
                if(h > 11)
                    continue;

                for(int m : minutes) {
                    if(m > 59)
                        continue;

                    res.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }

        return res;
    }

    private List<Integer> generateDigits(int[] nums, int count) {
        List<Integer> list = new ArrayList<>();
        generate(list, 0, 0, nums, count);

        return list;
    }

    private void generate(List<Integer> list, int pos, int sum, int[] nums, int count) {
        if(count == 0) {
            list.add(sum);
            return;
        }

        for(int i = pos; i < nums.length; i++)
            generate(list, i + 1, sum + nums[i], nums, count - 1);

    }

    public static void main(String[] args) {
        BinaryWatch_401 binary = new BinaryWatch_401();
        binary.readBinaryWatch(1);
    }
}
