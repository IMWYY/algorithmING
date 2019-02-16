package classic;

import java.util.Arrays;

/**
 * 并查集 parent节点保存的值是该集合的元素个数的相对数
 */
public class UFset {

    private int[] parent;

    UFset(int n) {
        this.parent = new int[n];
        Arrays.fill(this.parent, -1);
    }

    /**
     * 查找并返回节点所属集合的根节点
     *
     * @param x 搜索节点
     * @return 根节点
     */
    public int find(int x) {
        int s; //查找位置
        for (s = x; parent[s] >= 0; s = parent[s]) ;
        while (s != x) { //优化方案―压缩路径，使后续的查找操作加速。
            int tmp = parent[x];
            parent[x] = s;
            x = tmp;
        }
        return s;
    }

    /**
     * 将两个不同集合的元素进行合并，使两个集合中任两个元素都连通
     */
    public void union(int x1, int x2) {
        int r1 = find(x1), r2 = find(x2); //r1 为 x1 的根结点，r2 为 x2 的根结点
        int tmp = parent[r1] + parent[r2]; //两个集合结点个数之和(负数)

        //如果 R2 所在树结点个数 > R1 所在树结点个数(注意 parent[r1]是负数)
        if (parent[r1] > parent[r2]) { //优化方案――加权法则
            parent[r1] = r2;
            parent[r2] = tmp;
        } else {
            parent[r2] = r1;
            parent[r1] = tmp;
        }
    }
}