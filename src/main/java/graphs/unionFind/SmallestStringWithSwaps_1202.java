package graphs.unionFind;

import java.util.*;

// https://leetcode.com/problems/smallest-string-with-swaps/description/
public class SmallestStringWithSwaps_1202 {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // time O(nlogn??)
        // space O(n)

        int n = s.length();
        UF uf = new UF(n);

        for(List<Integer> list : pairs)
            uf.union(list.get(0), list.get(1));

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int root = uf.find(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).add(s.charAt(i));
        }

        StringBuilder res = new StringBuilder();
        for(int i = 0; i < n; i++)
            res.append(map.get(uf.find(i)).poll());

        return res.toString();
    }

    class UF {
        int[] root;
        int[] rank;

        public UF(int n) {
            root = new int[n];
            rank = new int[n];

            for(int i = 0; i < n; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int p) {
            while(p != root[p]) {
                root[p] = root[root[p]];
                p = root[p];
            }

            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if(rootP == rootQ)
                return;

            if(rank[rootP] > rank[rootQ])
                root[rootQ] = rootP;
            else if(rank[rootP] < rank[rootQ])
                root[rootP] = rootQ;
            else {
                root[rootP] = rootQ;
                rank[rootQ] += 1;
            }
        }
    }

    public static void main(String[] args) {
        SmallestStringWithSwaps_1202 smallestStringWithSwaps_1202 = new SmallestStringWithSwaps_1202();
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(0, 3));
        list.add(Arrays.asList(1, 2));

        smallestStringWithSwaps_1202.smallestStringWithSwaps("dcab", list);
    }
}
