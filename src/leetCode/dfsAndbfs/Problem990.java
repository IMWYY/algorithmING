package leetCode.dfsAndbfs;

/**
 * Given an array equations of strings that represent relationships between variables, each string equations[i]
 * has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase
 * letters (not necessarily different) that represent one-letter variable names.
 * <p>
 * Return true if and only if it is possible to assign integers to variable
 * names so as to satisfy all the given equations.
 * <p>
 * Example 1:
 * Input: ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
 * There is no way to assign the variables to satisfy both equations.
 * <p>
 * Example 2:
 * Input: ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 * <p>
 * create by stephen on 2019/2/10
 */
public class Problem990 {

    public static void main(String[] args) {
        Problem990 p = new Problem990();
        System.out.println(p.equationsPossible(new String[]{"c==c", "b==d", "x!=z"}));
        System.out.println(p.equationsPossible(new String[]{"a==b","b!=c","c==a"}));
    }

    public boolean equationsPossible(String[] equations) {
        if (equations.length == 1) return true;
        UnionFind unionFind = new UnionFind();
        for (String s : equations) {
            if (s.charAt(1) == '=') {
                unionFind.union(s.charAt(0) - 'a', s.charAt(3) - 'a');
            }
        }
        for (String s : equations) {
            if (s.charAt(1) == '!') {
                if (unionFind.find(s.charAt(0) - 'a') == unionFind.find(s.charAt(3) - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }


    private class UnionFind {
        int[] father;

        UnionFind() {
            father = new int[26];
            for (int i=0; i<father.length; ++i) {
                father[i] = i;
            }
        }

        public void union(int node1, int node2) {
            int find1 = find(node1);
            int find2 = find(node2);
            if (find1 != find2) {
                father[find1] = find2;
            }
        }

        public int find(int node) {
            if (father[node] == node) {
                return node;
            }
            father[node] = find(father[node]);
            return father[node];
        }
    }
}
