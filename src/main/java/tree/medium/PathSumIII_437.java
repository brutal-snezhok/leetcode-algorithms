package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/path-sum-iii/
public class PathSumIII_437 {
    // TODO: check better solutions
    // solution1
    public int pathSum(TreeNode root, int targetSum) {
        // time O(n^2)
        // space O(n)

        // when add node to path
        // check all sums that ending in this node

        return pathSum(root, targetSum, new ArrayList<>());
    }

    private int pathSum(TreeNode root, int targetSum, List<Integer> list) {
        if(root == null)
            return 0;

        list.add(root.val);
        long sum = 0; // pay attention!
        int pathCount = 0;
        for(int i = list.size() - 1; i >= 0; i--) {
            sum += list.get(i);
            if(targetSum == sum)
                pathCount++;
        }

        pathCount += pathSum(root.left, targetSum, list);
        pathCount += pathSum(root.right, targetSum, list);

        list.remove(list.size() - 1);

        return pathCount;
    }
}
