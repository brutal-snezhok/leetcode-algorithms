package stack.monotonic;

// https://leetcode.com/problems/max-chunks-to-make-sorted/description/
public class MaxChunksToMakeSorted_769 {
    // solution2
    public int maxChunksToSorted(int[] arr) {
        // greedy
        // time O(n)
        // space O(1)

        int localMax = 0;
        int numOfChunks = 0;

        for(int i = 0; i < arr.length; i++) {
            localMax = Math.max(localMax, arr[i]);

            if(localMax == i)
                numOfChunks++;
        }

        return numOfChunks;
    }
}
