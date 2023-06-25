package pramp;

public class ArrayIndexElementEquality {
    /*
    * Given a sorted array arr of distinct integers, write a function indexEqualsValueSearch
    * that returns the lowest index i for which arr[i] == i. Return -1 if there is no such index.
    *  Analyze the time and space complexities of your solution and explain its correctness.
    *
    * input: arr = [-8,0,2,5]
      output: 2 # since arr[2] == 2

      input: arr = [-1,0,3,6]
      output: -1
    * */

    // solution1
    static int indexEqualsValueSearch1(int[] arr) {
        /*    0 1 2 3
             [-8,0,2,5]
        arr[m]!=m
        arr[index]+a>index+a  -> ignore right side
        */
        int l = 0;
        int r = arr.length - 1;
        int res = -1;

        while(l <= r) {
            int mid = (l + r) / 2;
            if(arr[mid] == mid) {
                res = mid;
                r = mid - 1;
            }
            else if(arr[mid] < mid)
                l = mid + 1;
            else
                r = mid - 1;
        }

        return res;
    }

    // solution2
    static int indexEqualsValueSearch2(int[] arr) {
        int l = 0;
        int r = arr.length;
        int res = -1;

        while(l < r) {
            int mid = l + (r - l) / 2;

            if(arr[mid] == mid) {
                res = mid;
                r = mid;
            } else if(arr[mid] > mid)
                r = mid;
            else
                l = mid + 1;
        }

        return res;
    }
}
