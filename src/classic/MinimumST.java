package classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 最小生成树算法
 * create by stephen on 2018/4/19
 */
public class MinimumST {

    /**
     * prim算法 加点法
     * 时间复杂度为O(n2)，适合于求边稠密的最小生成树。
     *
     * @return 返回最小的路径和
     */
    public int prim(int[][] e) {
        int mst = 0;

        boolean[] chosen = new boolean[e.length];       //记录该点是否被加入已经连通的集合
        Arrays.fill(chosen, false);

        int[] dis = new int[e.length];  //记录当前已经选择的点到其他没选择到点的最短距离
        for (int i = 0; i < e.length; ++i) {
            dis[i] = e[0][i];
        }

        for (int i = 1; i < e.length; ++i) {
            //找出当前的最短路径
            int min = Integer.MAX_VALUE, cur = -1;
            for (int j = 1; j < e.length; ++j) {
                if (!chosen[j] && dis[j] < min) {
                    min = dis[j];
                    cur = j;
                }
            }

            chosen[cur] = true;
            if (cur == -1) return -1;
            mst += min;

            //查看是否要更新最短路径
            for (int k = 1; k < e.length; ++k) {
                if (!chosen[k] && dis[k] > e[cur][k]) {
                    dis[k] = e[cur][k];
                }
            }

        }
        return mst;
    }


    /****************************************************************************/
    /********************************Kruskal算法**********************************/
    /****************************************************************************/

    /**
     * Kruskal算法 利用并交集
     * https://www.cnblogs.com/yoke/p/6697013.html
     */
    public int Kruskal(int[][] e) {
        int mst = 0;
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e.length; ++i) {
            for (int j = i; j < e.length; ++j) {
                edges.add(new Edge(i, j, e[i][j]));
            }
        }

        edges.sort(Comparator.comparingInt(o -> o.w));

        UFset uFset = new UFset(e.length);
        int sum = 0;

        //每次选不再同一个集合中的两个边 将两个集合相连
        for (int i = 0; i < edges.size(); ++i) {
            Edge edge = edges.get(i);
            if (uFset.find(edge.u) != uFset.find(edge.v)) {
                uFset.union(edge.u, edge.v);
                sum++;
                mst += edge.w;
            }

            if (sum >= e.length - 1) break;
        }

        return mst;
    }
    public class Edge {
        public int u;
        public int v;
        public int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }


}
