package tree.medium;

import tree.TreeNode;

import java.util.*;

// https://leetcode.com/problems/delete-nodes-and-return-forest/description/
public class DeleteNodesAndReturnForest_1110 {
    // solution1
    public List<TreeNode> delNodes1(TreeNode root, int[] to_delete) {
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

    // solution2
    public List<TreeNode> delNodes2(TreeNode root, int[] to_delete) {
        // time O(n)
        // space O(n)

        /*
            1. traverse tree and pass parent at each step of dfs
            2. if node exists in to_delete -> remove connection parent -- this child
            3. if no just go forward

            note: we should put root of tree in res list only if parent-child rel removed
        */

        Set<Integer> toDelete = new HashSet<>();
        for(int num : to_delete)
            toDelete.add(num);

        List<TreeNode> res = new ArrayList<>();
        dfs(root, null, toDelete, res, true);

        return res;
    }

    private void dfs(TreeNode curr, TreeNode parent, Set<Integer> toDelete, List<TreeNode> res, boolean isRoot) {
        if(curr == null)
            return;

        if(toDelete.contains(curr.val)) {
            if(parent != null) {
                if(parent.left == curr)
                    parent.left = null;
                else
                    parent.right = null;
            }

            dfs(curr.left, null, toDelete, res, true);
            dfs(curr.right, null, toDelete, res, true);
        } else {
            if(isRoot)
                res.add(curr);

            dfs(curr.left, curr, toDelete, res, false);
            dfs(curr.right, curr, toDelete, res, false);
        }
    }
}
