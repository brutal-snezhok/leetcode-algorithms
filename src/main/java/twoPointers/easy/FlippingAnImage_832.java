package twoPointers.easy;

// https://leetcode.com/problems/flipping-an-image/description/
public class FlippingAnImage_832 {
    public int[][] flipAndInvertImage(int[][] image) {
        // time O(n * n)
        // space O(1)

        for (int[] row : image) {
            reverse(row);
            invert(row);
        }

        return image;
    }

    private void reverse(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    private void invert(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = arr[i] == 1 ? 0 : 1;
    }
}
