# DFS & BDF & Topological sort

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





