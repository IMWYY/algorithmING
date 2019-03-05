# DFS & BDF 

### DFS算法

参考一个用DFS的典型题目：Problem079

```java
for (int i = 0; i < n; ++i) {
	DFS();  // do something
}
```

### BFS算法

参考一个用DFS的典型题目：Problem127

```java
while (!queue.isEmpty()) {
	Element e = queue.poll();
	BFS();  // do something
	// add new element to queue
}
```

### Topological sort

拓扑排序（Topological Sorting）是一个有向无环图（DAG, Directed Acyclic Graph）的所有顶点的线性序列。且该序列必须满足

1. 每个顶点出现且只出现一次。

2. 若存在一条从顶点 A 到顶点 B 的路径，那么在序列中顶点 A 出现在顶点 B 的前面。

拓补排序可以很好的解决前后有依赖关系的问题，在工程实践中，各种包复杂依赖管理，如govendor，apt-get，homebrew，makefile，都会用到这个算法。

- BFS解法（Kahn算法）可以参见[Problem207](Problem207.java), [Problem210](Problem210.java)

  利用一个数组记录入度，每次访问入度为0的节点（将其入队），访问完后，更新该节点所指向节点的入度。

- DFS解法 可以参见[Problem207](Problem207.java), [Problem210](Problem210.java)

  DFS过程中利用一个数组保存节点是否杯访问过，避免重复访问。一旦出现重复访问，即不是一个DAG。同时需要注意，访问节点的顺序与最终拓补排序的顺序恰好相反，所以需要用栈来reverse一下。


### Union Find

所谓并查集，主要包括两个操作：合并和查找，所以对于**需要合并元素，并查找元素是否在同一个集合的场景**（只需要看他们的代表元是否相同即可），并查集都适用。

一类元素可以利用一个“代表元”来表示，一个集合内的所有元素组织成以代表元为根的树形结构，

很多DFS场景可以利用Union Find来解，参见[Problem200](Problem200.java), [Problem990](Problem990.java)

- java实现：

```java
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
```



### Tips

- 很多题目直接用DFS/BFS会Time limit exceed，整体方法是避免不必要的遍历开销。
  - 用memorization，记录之前已经遍历过的节点，典型例题参考[Problem329](Problem329.java) 。
  - 记录当前节点的访问状态（访问过与否），参考[Problem210](Problem210.java)的DFS解法（利用额外的space）和[Problem079](Problem079.java) （原数组更新，不使用额外的space）。
  - 注意遍历的边界条件，如 [Problem301](Problem301.java)的方法二的终止条件，一旦找到一个合法的字符串，就不继续遍历新的可能了。

  