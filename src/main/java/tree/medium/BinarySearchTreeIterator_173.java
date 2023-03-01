package tree.medium;

import tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/binary-search-tree-iterator/description/
public class BinarySearchTreeIterator_173 {
    Deque<TreeNode> stack;

    public BinarySearchTreeIterator_173(TreeNode root) {
        stack = new ArrayDeque<>();
        TreeNode curr = root;
        while(curr != null) {
            stack.addFirst(curr);
            curr = curr.left;
        }
    }

    public int next() {
        TreeNode curr = stack.removeFirst();
        int res = curr.val;
        curr = curr.right;
        while(curr != null) {
            stack.addFirst(curr);
            curr = curr.left;
        }

        return res;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
