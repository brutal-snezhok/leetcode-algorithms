package slidingWindow.medium;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/find-k-closest-elements/description/
// article: https://leetcode.com/problems/find-k-closest-elements/discuss/1311197/Find-K-Closest-Elements-or-All-Possible-Approaches-Explained-w-Simple-Solutions!
public class FindKClosestElements_658 {
    // solution1
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        // two pointers
        // time O(n - k)
        // space O(1)

        int l = 0;
        int r = arr.length - 1;

        while(r - l >= k) {
            if(x - arr[l] <= arr[r] - x)
                r--;
            else
                l++;
        }

        List<Integer> res = new ArrayList<>();
        for(int i = l; i <= r; i++)
            res.add(arr[i]);

        return res;
    }

    // solution2
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        // time O(nlogk + klogk)
        // space O(k)

        // int[] point = new int[2];
        // point[0] - val
        // point[1] - diff

        // compare by distance, then by val
        Queue<int[]> maxHeap = new PriorityQueue<>(k, (p1, p2) -> p2[1] - p1[1] != 0 ?  p2[1] - p1[1] : (p2[0] - p1[0]));

        for(int num : arr) {
            maxHeap.add(new int[]{num, Math.abs(x - num)});

            if(maxHeap.size() > k)
                maxHeap.poll();
        }

        List<Integer> res = new ArrayList<>();
        while(!maxHeap.isEmpty())
            res.add(maxHeap.poll()[0]);

        Collections.sort(res);

        return res;
    }

    // solution3
    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        // binary search + two pointers
        // time O(logn + k)
        // space O(1)

        // find the smallest val >= x, let's make this index as r
        // l = r - 1

        int r = binarySearch(arr, x);
        int l = r - 1;

        for(int i = 0; i < k; i++) {
            if(l >= 0 && r < arr.length) {
                if(x - arr[l] <= arr[r] - x)
                    l--;
                else
                    r++;
            } else if(l >= 0) {
                l--;
            } else {
                r++;
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int i = l + 1; i < r; i++)
            res.add(arr[i]);

        return res;
    }

    // solution4
    public List<Integer> findClosestElements4(int[] arr, int k, int x) {
        // binary search + two pointers
        // time O(logn + k)
        // space O(1)

        // find the smallest val >= x, let's make this index as r
        // l = r - 1
        // Now, [l, r] forms our window of elements closest to x. We have to expand this window to fit k
        // elements. We will keep picking either arr[l] or arr[r] based on whichever's closer to x and expand
        // our window till it contains k elements.

        int r = binarySearch(arr, x);
        int l = r - 1;

        while(r - l <= k) {
            if(r >= arr.length || l >= 0 && arr[r] - x >= x - arr[l])
                l--;
            else
                r++;
        }

        List<Integer> res = new ArrayList<>();
        for(int i = l + 1; i < r; i++)
            res.add(arr[i]);

        return res;
    }

    private int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;

        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(arr[mid] == target)
                return mid;
            else if(arr[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }

        // since the loop is running until l <= r, at the end of the while loop 'l == r + 1'
        // we are not able to find the element in the given array,
        // so the next big number will be arr[l]
        // so the next small number will be arr[r]
        return l;
    }

    public static void main(String[] args) {
        FindKClosestElements_658 closest = new FindKClosestElements_658();
        System.out.println(closest.binarySearch(new int[]{1,2,3,5,6}, 4));
    }
}
