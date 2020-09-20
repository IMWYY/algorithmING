package classic;

import java.util.Arrays;

/**
 * Disjoint-set data structure
 */
public class UFset {

    private int[] parent;

    UFset(int n) {
        this.parent = new int[n];
        Arrays.fill(this.parent, -1);
    }

    /**
     * fine node x and return the root node of x
     */
    public int find(int x) {
        int s;
        for (s = x; parent[s] >= 0; s = parent[s]); // the value of the root node is negetive, it stores the number of its decendents
        while (s != x) { // fast path for following 'find(x)': directly link all descendents of s as the child of s
            int tmp = parent[x];
            parent[x] = s;
            x = tmp;
        }
        return s;
    }

    /**
     * union two different sets
     */
    public void union(int x1, int x2) {
        int r1 = find(x1), r2 = find(x2); //r1 is the root of x1,r2 is the root of x2
        int tmp = parent[r1] + parent[r2]; // the sum of elements two sets

        // the # of element in r2 is larger than # of element in r1 (parent[r1] is negetive)
        if (parent[r1] > parent[r2]) {
            parent[r1] = r2;
            parent[r2] = tmp;
        } else {
            parent[r2] = r1;
            parent[r1] = tmp;
        }
    }
}