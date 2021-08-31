# Binary Tree Inorder Traversal

from: https://leetcode.com/problems/binary-tree-inorder-traversal/







### 풀이

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        if root is None:
            return None
        
        # 1. 왼쪽으로 최대한 간다. stack에 저장
        # 2. 더 이상 갈 수 없다면, 결과값에 append한 다음 오른쪽으로 한칸씩 가면서 매번 왼쪽으로 갈 수 있는지 확인한다. stack에 저장
        # 3. 1번을 다시 수행한다. stack에 저장 
        
        stack = []
        res = []
        while root != None or stack:            
            
            # [ 가장 먼 .left를 stack 쌓는다. ]
            # 일단 대상 노드가 유효하면 stack에 저장
            # 대상 노드의 모든 .left를 stack에 저장
            while root:
                stack.append(root)
                root = root.left
                
            # [ 모든 .left를 탐색했다면 마지막 .left를 결과값에 넣어준다. ]
            # stack에서 마지막 .left를 뽑는다.
            # 뽑힌 대상은 res에 append
            # 대상 노드는 그 다음 탐색 대상인 .right로 재할당
            root = stack.pop()
            res.append(root.val)
            root = root.right
            
        return res
```

