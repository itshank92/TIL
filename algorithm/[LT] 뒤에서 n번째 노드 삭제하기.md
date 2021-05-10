# 뒤에서 n번째 노드 삭제하기 ★★★

from: https://leetcode.com/problems/remove-nth-node-from-end-of-list/



> 풀이

**투 포인터로 탐색**을 수행한다.

* 앞에 가는 포인터와 뒤에 가는 포인터 사이에 `n`칸을 두고 탐색을 수행한다.
* 앞에 가는 포인터가 마지막에 위치하는 경우, 뒤에 있는 포인터는 `n+1`칸에 위치하기에 `.next`를 `.next.next`로 바꾼다.

**투 포인터**를 수행하기 앞서 **비어 있는 노드인 `root`를 만들고** `.next`를 `head`로 연결한다.

* 뒤에서 `n`번째가 `head`인 경우, `root`를 만들지 않고는 제거가 불가능하다.
  * 우리의 제거 방식은 `back포인터.next` = `back포인터.next.next`로 할 것이기에





> 코드

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        root = ListNode()
        root.next = head
        start = 0
        # 초기값은 root
        back = front = root
        while front.next:
            if start >= n:
                back = back.next
            front = front.next
            start += 1
        back.next = back.next.next

        # head를 반환하는 것이 아니다.
        # 뒤에서 n번째(제거대상)이 head일 수도 있기에 root.next를 반환한다.
        return root.next
```

