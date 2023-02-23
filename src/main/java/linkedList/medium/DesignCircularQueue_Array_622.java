package linkedList.medium;

// https://leetcode.com/problems/design-circular-queue/description/
public class DesignCircularQueue_Array_622 {
    // solution2: based on array
    // all operations work with time O(1)
    int[] arr;
    int size;
    int front;
    int tail;

    public DesignCircularQueue_Array_622(int k) {
        arr = new int[k];
        size = 0;
        front = 0;
        tail = 0;
    }

    public boolean enQueue(int value) {
        if (size == arr.length)
            return false;

        // for case when insert first element
        if (size == 0) {
            front = 0;
            tail = 0;
            arr[front] = value;
            size++;

            return true;
        }

        int newIndex = (tail + 1) % arr.length;
        arr[newIndex] = value;
        tail = newIndex;
        size++;

        return true;
    }

    public boolean deQueue() {
        if (size == 0)
            return false;

        int newIndex = (front + 1) % arr.length;
        front = newIndex;
        size--;

        return true;
    }

    public int Front() {
        if (size == 0)
            return -1;

        return arr[front];
    }

    public int Rear() {
        if (size == 0)
            return -1;

        return arr[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }
}
