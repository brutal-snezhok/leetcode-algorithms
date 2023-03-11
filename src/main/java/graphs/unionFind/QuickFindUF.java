package graphs.unionFind;

public class QuickFindUF {

    private int[] id;    // id[i] = component identifier of i
    private int count;   // number of components

    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for(int i = 0; i < n; i++)
            id[i] = i;
    }

    public int find(int p) {
        validate(p);
        return id[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        // p and q are already in the same component
        if (pID == qID) return;

        for(int i = 0; i < id.length; i++)
            if(id[i] == pID)
                id[i] = qID;
       count--;
    }

    private void validate(int p) {
        int n = id.length;
        if(p < 0 || p >= n)
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
    }

    public int count() {
        return count;
    }
}
