package graphs.unionFind;

import java.util.*;

// https://leetcode.com/problems/accounts-merge/description/
public class AccountsMerge_721 {
    // solution1 union find
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // time O(nk*log(nk)), n - number of accounts, k - max length of account
        // space O(nk)

        UnionFind uf = new UnionFind(accounts.size());
        Map<String, Integer> emailToAcc = new HashMap<>(); // {email, account}

        // populate emailToAcc map
        for(int i = 0; i < accounts.size(); i++) {
            List<String> currAcc = accounts.get(i);

            for(int j = 1; j < currAcc.size(); j++) {
                String currEmail = currAcc.get(j);
                if(emailToAcc.containsKey(currEmail)) {
                    uf.union(i, emailToAcc.get(currEmail));
                } else {
                    emailToAcc.put(currEmail, i);
                }
            }
        }

        Map<Integer, List<String>> emailGroup = new HashMap<>(); // {index of Acc, [emails]}

        // populate emailGroup map
        for(Map.Entry<String, Integer> entry : emailToAcc.entrySet()) {
            String email = entry.getKey();
            Integer accountNum = entry.getValue();

            Integer rootOfComponent = uf.find(accountNum);
            emailGroup.computeIfAbsent(rootOfComponent, v -> new ArrayList<>()).add(email);
        }

        // populate result List
        List<List<String>> mergedAccounts = new ArrayList<>();
        for(Map.Entry<Integer, List<String>> entry : emailGroup.entrySet()) {
            Integer indexOfAcc = entry.getKey();
            String name = accounts.get(indexOfAcc).get(0);
            List<String> emails = entry.getValue();

            Collections.sort(emails);
            List<String> bunch = new ArrayList<>();
            bunch.add(name);
            bunch.addAll(emails);

            mergedAccounts.add(bunch);
        }

        return mergedAccounts;
    }

    class UnionFind {
        int[] root;
        int[] rank; // depth of tree

        public UnionFind(int n) {
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

            if(rank[rootP] > rank[rootQ]) {
                root[rootQ] = rootP;
            } else if(rank[rootP] < rank[rootQ])
                root[rootP] = rootQ;
            else {
                root[rootP] = rootQ;
                rank[rootP] += 1;
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> acc = new ArrayList<>(Arrays.asList(
                Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
                Arrays.asList("John","johnsmith@mail.com","john00@mail.com"),
                Arrays.asList("John","johnnybravo@mail.com"),
                Arrays.asList("Mary","mary@mail.com")));

        AccountsMerge_721 accountsMerge_721 = new AccountsMerge_721();
        System.out.println(accountsMerge_721.accountsMerge(acc));
    }
}
