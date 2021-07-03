# DFS & BDF

## DFS template

See [Problem079](Problem079.java).

```java
for (int i = 0; i < n; ++i) {
	DFS();  // do something
}
```

## BFS template

See [Problem127](Problem127.java).

```java
while (!queue.isEmpty()) {
	Element e = queue.poll();
	BFS();  // do something
	// add new element to queue
}
```

## Tips

- 很多题目直接用DFS/BFS会Time limit exceed，整体方法是避免不必要的遍历开销。
  - 用memorization，记录之前已经遍历过的节点，典型例题参考[Problem329](Problem329.java) 。
  - 记录当前节点的访问状态（访问过与否），参考[Problem210](Problem210.java)的DFS解法（利用额外的space）和[Problem079](Problem079.java) （原数组更新，不使用额外的space）。
  - 注意遍历的边界条件，如 [Problem301](Problem301.java)的方法二的终止条件，一旦找到一个合法的字符串，就不继续遍历新的可能了。

