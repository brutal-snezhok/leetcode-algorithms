package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/delete-nodes-and-return-forest/description/
public class DeleteNodesAndReturnForest_1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // time O(n)
        // space O(n)

        // if node is root and not in to_delete -> add to res
        // if parent node was deleted then child node is a root
        // need to have flag isRoot in dfs function

        List<TreeNode> res = new ArrayList<>();
        Set<Integer> deleteSet = new HashSet<>();
        for(int num : to_delete)
            deleteSet.add(num);

        dfs(root, deleteSet, res, true);

        return res;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> deleteSet, List<TreeNode> res, boolean isRoot) {
        if(node == null)
            return null;

        boolean isDeleted = deleteSet.contains(node.val);
        if(isRoot && !isDeleted)
            res.add(node);

        node.left = dfs(node.left, deleteSet, res, isDeleted);
        node.right = dfs(node.right, deleteSet, res, isDeleted);

        return isDeleted ? null : node;
    }
}
