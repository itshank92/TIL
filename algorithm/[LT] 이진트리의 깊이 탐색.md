# 이진트리의 깊이 탐색

From: https://leetcode.com/problems/maximum-depth-of-binary-tree



> 풀이법 두 가지 



1. bfs를 사용해서 깊이 탐색 수행 가능

2. 재귀를 사용해서 1줄로 가능



> bfs 코드

```python
# BFS
from collections import deque

class Solution:
    def maxDepth(self, root: TreeNode) -> int:
        ans = 0
        if not root:
            return ans
        q = deque([])
        q.append(root)
        while q:
            q_l = len(q)
            ans += 1
            for _ in range(q_l):
                node = q.popleft()
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
        return ans
```

​      



> 재귀 코드

```python
# 재귀
class Solution:
    def maxDepth(self, root: TreeNode) -> int:
        if not root:
            return 0
        return 1 + max(self.maxDepth(root.left), self.maxDepth(root.right))
    
## 한줄로 하려면 아래와 같이 구현
return 1 + max(self.maxDepth(root.left), self.maxDepth(root.right)) if root else 0
```

