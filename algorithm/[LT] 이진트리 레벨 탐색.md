# 이진트리 레벨 탐색

from: https://leetcode.com/problems/binary-tree-level-order-traversal/



> 풀이

1. **큐**를 사용해서 노드를 저장 (`deque`)

2. 탐색시 해당 시점의 **큐 길이** 정보를 통해서 레벨 탐색 수행

3. 레벨 탐색을 위해 `popleft()`한 `node`는 `None`이 아닌 경우 `temp`(임시 리스트)에 저장

4. 한번의 레벨 탐색이 종료되면 최종 결과값(`ans`)에 `temp`를 `append`

   ※    이 때, `temp`가`[]`(빈 리스트)라면 `append()` 수행 X





> 코드

```python
from collections import deque


class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        """
        q = 레벨 단위로 순회하면서 node넣은 deque
        lq = 레벨 단위 순회를 위한 해당 시점의 q길이를 저장한 값
        temp = q에서 popleft한 동일레벨 노드 모음
        ans = temp들을 레벨 순서대로 저장한 최종 결과값
        """
        q = deque([root])
        ans = []
        while q:
            lq = len(q)
            temp = []
            for _ in range(lq):
                node = q.popleft()
                # 빈 노드는 pass
                if node is None:
                    continue
                temp.append(node.val)
                q.append(node.left)
                q.append(node.right)
            # 빈 level(=leaf 다음에 오는 level)은 pass
            if temp:
                ans.append(temp)
        return ans
            
```

