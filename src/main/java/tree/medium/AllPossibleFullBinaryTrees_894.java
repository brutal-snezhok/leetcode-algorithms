package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/all-possible-full-binary-trees/description/
public class AllPossibleFullBinaryTrees_894 {
    Map<Integer, List<TreeNode>> map = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        // with memo
        // time O(2^n)
        // space O(2^n)

        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        if (map.containsKey(n))
            return map.get(n);

        for (int l = 0; l < n; l++) {
            int r = n - l - 1;

            List<TreeNode> leftNodes = allPossibleFBT(l);
            List<TreeNode> rightNodes = allPossibleFBT(r);

            for (TreeNode left : leftNodes) {
                for (TreeNode right : rightNodes) {
                    res.add(new TreeNode(0, left, right));
                }
            }
        }

        if (!map.containsKey(n))
            map.put(n, res);

        return res;
    }
}
