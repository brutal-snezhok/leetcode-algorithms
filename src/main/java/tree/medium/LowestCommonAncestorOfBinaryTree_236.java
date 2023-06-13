package tree.medium;

import tree.TreeNode;

import java.util.*;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
public class LowestCommonAncestorOfBinaryTree_236 {
    // solution1
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // iterative with saving to parent node
        // time O(n)
        // space O(n)

        Map<TreeNode, TreeNode> parent = new HashMap<>();  // child -> parent
        parent.put(root, null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode curr = queue.poll();
            if(curr.left != null) {
                parent.put(curr.left, curr);
                queue.add(curr.left);
            }

            if(curr.right != null) {
                parent.put(curr.right, curr);
                queue.add(curr.right);
            }
        }

        Set<TreeNode> path1 = new HashSet<>();
        while(p != null) {
            path1.add(p);
            p = parent.get(p);
        }

        while(q != null) {
            if(path1.contains(q))
                return q;
            q = parent.get(q);
        }

        return null;
    }

    // solution2
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // time O(n)
        // space O(n)

        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        dfs(root, p, new ArrayList<>(), pathP);
        dfs(root, q, new ArrayList<>(), pathQ);

        int i = 1;
        int j = 1;
        TreeNode prev = pathP.get(0);
        while(i < pathP.size() && j < pathQ.size()) {
            if(pathP.get(i) != pathQ.get(j))
                break;
            prev = pathP.get(i);
            i++;
            j++;
        }

        return prev;
    }

    private void dfs(TreeNode curr, TreeNode target, List<TreeNode> currPath, List<TreeNode> res) {
        if(curr == null)
            return;

        if(curr == target) {
            currPath.add(target);
            res.addAll(currPath);
            return;
        }

        currPath.add(curr);
        dfs(curr.left, target, currPath, res);
        dfs(curr.right, target, currPath, res);
        currPath.remove(currPath.size() - 1);
    }

    // solution3
    TreeNode res = null;

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        // recursive
        // time O(n)
        // space O(n)

        dfs(root, p, q);
        return res;
    }

    private boolean dfs(TreeNode curr, TreeNode p, TreeNode q) {
        if(curr == null)
            return false;

        boolean mid = curr == p || curr == q;
        boolean l = dfs(curr.left, p, q);
        boolean r = dfs(curr.right, p, q);

        if((mid && l) || (mid && r) || (l && r))
            res = curr;

        return mid || l || r;
    }

    // solution4
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        // recursive
        // time O(n)
        // space O(n)

        if(root == null || root == p || root == q)
            return root;

        TreeNode l = lowestCommonAncestor4(root.left, p, q);
        TreeNode r = lowestCommonAncestor4(root.right, p, q);

        if(l == null)
            return r;
        if(r == null)
            return l;

        return root;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4)));
        TreeNode right = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        TreeNode root = new TreeNode(3, left, right);

        LowestCommonAncestorOfBinaryTree_236 lowest = new LowestCommonAncestorOfBinaryTree_236();
        lowest.lowestCommonAncestor2(root, left, right);
    }
}
