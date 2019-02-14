package classic;

/**
 * 最短路径算法
 * create by stephen on 2018/4/19
 */
public class ShortPath {

    /**
     * Dijkstra算法 无负权回路，边权必须非负，单源最短路 时间复杂度：优化前O(n2)
     */
    public void Dijkstra(int[][] e) {
        int[] dis = new int[e.length];
        System.arraycopy(e[0], 0, dis, 0, e.length);

        //记录到该节点的路径有没有确定最短 每次循环会确定一个
        boolean[] finished = new boolean[e.length];

        for (int i = 1; i < e.length; ++i) {
            int min = Integer.MIN_VALUE, cur = -1;
            for (int j = 1; j < e.length; ++j) {
                if (!finished[j] && dis[j] < min) {
                    min = dis[j];
                    cur = j;
                }
            }

            if (cur == -1) {
                break;
            }

            finished[cur] = true;

            //查看是否要调整
            for (int k = 1; k < e.length; ++k) {
                if (e[cur][k] < Integer.MAX_VALUE && dis[k] < e[cur][k] + dis[cur]) {
                    dis[k] = e[cur][k] + dis[cur];
                }
            }
        }

        //最短路径
        for (int di : dis) {
            System.out.println(di);
        }
    }

    /**
     * Floyds算法：动态规划思想
     * 适用范围：无负权回路即可，边权可正可负，运行一次算法即可求得任意两点间最短路
     * 时间复杂度：O(n^3)
     */
    public void Floyds(int[][] e) {
        for (int k = 1; k < e.length; ++k) {
            for (int i = 1; i < e.length; ++i) {
                for (int j = 1; j < e.length; ++j) {
                    e[i][j] = Math.min(e[i][j], e[i][k] + e[k][j]);
                }
            }
        }

        //最短路径
        for (int i = 0; i < e.length; ++i) {
            System.out.println(e[0][i]);
        }
    }

}
