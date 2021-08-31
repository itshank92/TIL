# 대칭 트리 확인하기

from : https://leetcode.com/problems/symmetric-tree/

​     

> 풀이

`root`와 `root` 이후의 노드의 대칭을 확인하는 방식이 다르다.

* `root`는 `root.left`와 `root.right`의 동일 여부를 확인
* `root` 이후 노드는 두 노드가 하나의 쌍으로 묶여`(node1, node2)`, `node1.left`와 `node2.right `, `node1.right`와 `node2.left`를 비교해야 한다.     

따라서 최초의 **<u>`root` 레벨에서의 대칭 확인 코드</u>**와 **<u>이후 두 노드의 대칭 여부 확인하는 코드</u>**를 작성해야 한다.



> 두 가지 방법

재귀 구조로 작성하는 방법과 반복구조 방법이 존재한다.



> 코드_재귀

```python
class Solution:
    # 최종 메소드
    # >> root 레벨 비교 여부 직접수행
    # >> root 이후 레벨은 isMirror()로 수행
    def isSymmetric(self, root: TreeNode) -> bool:
        # 예외처리
        if root is None:
            return True
        # 최초의 비교는 left와 right의 비교
        return self.isMirror(root.left,root.right)
    
    # 두 대상의 일치 여부 판단
    def isMirror(self, node1, node2):
        # [둘 다 None인 경우] True
        if node1 is None and node2 is None:
            return True
        # [둘 중 하나가 None인 경우] or [두 값이 다른 경우] False
        if (node1 is None or node2 is None) or node1.val != node2.val:
            return False
        # 각자 대칭되는 자식들을 재귀 호출
        return self.isMirror(node1.left, node2.right) and self.isMirror(node1.right, node2.left)
```

​      

> 코드_ 반복

```python
from collections import deque

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        # 예외 처리
        if root is None:
            return True
        
        # 최초의 비교 대상을 q에 삽입
        q = deque([root.left, root.right])
        
        while q:
            n1, n2 = q.popleft(), q.popleft()
            # 둘 다 None이면 continue
            if n1 is None and n2 is None:
                continue
            # 둘 중 하나만 None이거나 두 값이 다른 경우 return False
            if (n1 is None or n2 is None) or n1.val != n2.val:
                return False
            # 다음 비교 대상을 q에 삽입
            # ※ 순차적으로 뽑아서 비교하기에 비교 대상이 옆에 존재하도록 삽입
            q += [n1.left, n2.right, n1.right, n2.left]
        return True
```

