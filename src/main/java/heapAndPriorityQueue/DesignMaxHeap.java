package heapAndPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// info: https://habr.com/ru/post/112222/
public class DesignMaxHeap {
    private List<Integer> list;

    public DesignMaxHeap() {
        list = new ArrayList<>();
    }

    // time O(logn)
    private void add(int val) {
        list.add(val);
        int i = list.size() - 1;
        int parentInd = (i - 1) / 2;

        while(i > 0 && list.get(parentInd) < list.get(i)) {
            swap(i, parentInd);
            i = parentInd;
            parentInd = (i - 1) / 2;
        }
    }

    // time O(logn)
    public void heapify(int i) {
        int size = list.size();

        while(true) {
            int leftChildInd = 2 * i + 1;
            int rightChildInd = 2 * i + 2;
            int largestChildInd = i;

            if(size > leftChildInd && list.get(leftChildInd) > list.get(largestChildInd))
                largestChildInd = leftChildInd;
            if(size > rightChildInd && list.get(rightChildInd) > list.get(largestChildInd))
                largestChildInd = rightChildInd;
            if(largestChildInd == i)
                break;

            swap(i, largestChildInd);
            i = largestChildInd;
        }
    }

    // time O(n)
    // create binary tree from arr, then invoke heapify for every vertex which has children
    public void buildHeap(int[] arr) {
        list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        for(int i = list.size() / 2; i >= 0; i--)
            heapify(i);
    }

    // O(1)
    // O(logn) - for rebuild tree heapify
    public int getMax() {
        int res = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        heapify(0);

        return res;
    }

    // time O(nlogn)
    public void heapSort(int[] arr) {
        buildHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = getMax();
            heapify(0);
        }
    }

    // time O(1)
    public int getSize() {
        return list.size();
    }

    private void swap(int ind1, int ind2) {
        int temp = list.get(ind1);
        list.set(ind1, list.get(ind2));
        list.set(ind2, temp);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,6,3,8,9,5,15,11,7,8};
        DesignMaxHeap maxHeap = new DesignMaxHeap();
        maxHeap.buildHeap(arr);
    }
}
