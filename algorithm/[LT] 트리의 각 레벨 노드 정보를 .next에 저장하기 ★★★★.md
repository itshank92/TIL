# 116. Populating Next Right Pointers in Each Node

from: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/





### 문제 이해가 어렵다

일단 완전 이진트리가 주어진다.(모두 자식이 2명인 트리) 이렇게 주어진 트리의 각 노드들에는 `.next`라는 속성이 존재하는데 이 값은 모두 `None`으로 설정되어 있다. 

이렇게 설정된 `.next`의 값을 각 레벨의 노드를 왼쪽부터 오른쪽 순서대로 `.next`로 순차적으로 가리키도록 변경하는 것이 문제의 과제다.

​     

### 문제 해결 방법

각 레벨을 순회하면서 다음 레벨의 자식들을 `.next`로 연결시킨다. 

모든 자식 연결이 끝나면 다음 레벨의 시작점 (이전 레벨의 최초 노드의 `.left`가 여기에 해당)으로 넘어간다.

​    

### 코드

```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""

class Solution:
    def connect(self, root: 'Node') -> 'Node':
        start = root
        
        # 현재 노드가 존재하고 .next로 연결할 자식이 존재하는 레벨인 경우에만 진행
        while root and root.left:
        
        	# 다음 레벨 시작점인 root.left를 next에 저장
            next = root.left
            
            # 해달 레벨의 노드의 자식 노드들을 순차적으로 .next로 계속 연결
            while root:
                root.left.next = root.right
                root.right.next = root.next.left if root.next else None
                root = root.next
            root = next
        
        # 최초 시작 노드 반환
        return start    
```



