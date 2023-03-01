package tree.hard;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class SerializeAndDeserializeBinaryTree_297 {
    // use dfs preorder traverse to serialize
    // and dfs preorder traverse to deserialize

    int ind = 0;

    // Encodes a tree to a single string.
    // time O(n), traverse all nodes one time
    // space O(n), save all nodes to list
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        serializeDFS(root, list);

        return list.stream().collect(Collectors.joining(","));
    }

    private void serializeDFS(TreeNode node, List<String> list) {
        if (node == null) {
            list.add("N");
            return;
        }

        list.add(String.valueOf(node.val));
        serializeDFS(node.left, list);
        serializeDFS(node.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] parts = data.split(",");

        return deserializeDFS(parts);
    }

    private TreeNode deserializeDFS(String[] parts) {
        String curr = parts[ind++];
        if (curr.equals("N") || ind >= parts.length)
            return null;

        TreeNode node = new TreeNode(Integer.parseInt(curr));
        node.left = deserializeDFS(parts);
        node.right = deserializeDFS(parts);

        return node;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));

        SerializeAndDeserializeBinaryTree_297 codec = new SerializeAndDeserializeBinaryTree_297();
        codec.serialize(node);
    }
}
