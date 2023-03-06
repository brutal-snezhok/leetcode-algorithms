package graphs.medium;

import graphs.Node;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/clone-graph/description/
public class CloneGraph_133 {
    Map<Node, Node> map = new HashMap<>(); // oldNode-> newNode
    public Node cloneGraph(Node node) {
        // recursive dfs
        // time O(V + E)
        // space O(V)

        if(node == null)
            return node;

        Node newNode = new Node(node.val);
        map.put(node, newNode);

        for(Node neighbor : node.neighbors) {
            Node newNeighbor = map.containsKey(neighbor) ? map.get(neighbor) : cloneGraph(neighbor);
            newNode.neighbors.add(newNeighbor);
        }

        return newNode;
    }
}
