# 유효한 BST 찾기

출처: https://leetcode.com/problems/validate-binary-search-tree/  (98번)      





> 생각

* 처음에는 각 노드별로 왼쪽 오른쪽 비교하고 재귀로 넘기는 구조로 작성했다.
* 그렇게 한 결과 `[5,4,6,null,null,3,7]`에서 틀렸다고 나왔다. (정답은 `False`, 내 코드결과는 `True`)
  * `3`의 경우 부모노드인 `6`보다는 작지만 조부모 노드인 `5`보다는 커야 하는데 작기때문
* 그래서 매번 부모 노드 뿐만아니라 최소값과 최대값과의 비교를 해주는 구조로 코드를 다시 작성했다.
  * 트리를 그려가며 최소값과 최대값이 언제 바뀌는지 파악했다.
  * `left`로 내려갈 때, 최대값이 변하고, `right`로 내려갈 때 최소값이 변한다.
  * 최소값과 최대값의 초기값은 문제에서 제시된 범위로 설정했다.









> 코드

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def isValidBST(self, root: TreeNode) -> bool:
        # 최소값과 최대값 비교 재귀
        def dfs(min_,node,max_):
            # 노드가 없는 경우 True반환
            if not node:
                return True
            
            # 왼쪽과 오른쪽이 유효한 경우, 현재 노드 유효성 검사하고 통과시 True
            if dfs(min_,node.left,node.val) and dfs(node.val,node.right,max_):
                if min_ < node.val < max_:
                    return True
               
            # 나머지 경우는 모드 False
            return False

        
        min_,max_ = -(2**31)-1, (2**31) + 1 
        return dfs(min_, root, max_)
```

