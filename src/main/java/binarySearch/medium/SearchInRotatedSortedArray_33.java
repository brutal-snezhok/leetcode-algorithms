package binarySearch.medium;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
public class SearchInRotatedSortedArray_33 {
    // solution1
    public int search1(int[] nums, int target) {
        // time O(logn)
        // space O(n)

        // determine in left or right part are you now
        // then check num[mid] ><= target and determine where move a pointer

        int l = 0;
        int r = nums.length - 1;
        int res = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(nums[mid] == target) {
                res = mid;
                break;
            }

            if(nums[mid] >= nums[l]) {
                // in left part
                if(target > nums[mid] || target < nums[l])
                    l = mid + 1;
                else
                    r = mid - 1;
            } else {
                // in right part
                if(target < nums[mid] || target > nums[r])
                    r = mid - 1;
                else
                    l = mid + 1;
            }
        }

        return res;
    }

    // solution2
    public int search2(int[] arr, int target) {
        // time O(logn)
        // space O(1)

        /*
         1. find pivot - the smallest value in arr
         2. do binary search from 0 to pivot - on first part of arr
         3. do binary search from pivot + 1 to n - 1 - on second part of arr
        */

        int n = arr.length;
        int pivot = findPivot(arr);
        int res1 = binarySearch(arr, 0, pivot - 1, target);
        if(res1 != -1)
            return res1;

        return binarySearch(arr, pivot, n - 1, target);
    }

    private int findPivot(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(arr[mid] <= arr[r])
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    private int binarySearch(int[] arr, int l, int r, int target) {
        while(l < r) {
            int mid = l + (r - l) / 2;

            if(arr[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }

        return arr[l] == target ? l : -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray_33 search = new SearchInRotatedSortedArray_33();
        search.search2(new int[]{4,5,6,7,0,1,2}, 0);
    }
}
