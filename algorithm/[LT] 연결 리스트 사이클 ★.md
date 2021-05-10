# 연결 리스트 사이클

FROM: https://leetcode.com/problems/linked-list-cycle



I don't quite get your explanation, especially this:
*"the cycle could only happen at the end of the list"*?
If there's a cycle, then there's no end.



Anyway, I'd say why I did it is speed. I could use [LBYL](https://docs.python.org/3/glossary.html#term-lbyl) instead, i.e., use explicit extra tests checking whether next nodes actually exist before trying to access them, but here I chose EAFP. Python will check for access errors **anyway**, so additionally checking it myself would be a waste of time.



And a meta-reason I did that: Probably the LBYL-way had already been posted, and I don't post when I have nothing new to add to the discussion.

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def hasCycle(self, head: ListNode) -> bool:
        try:
            slow, fast = head, head.next
            while slow is not fast:
                slow = slow.next
                fast = fast.next.next
            return True
        # 이게 에러가 났다는 것은 반복이 발생하기 전에 Nonetype에러 발생했다는 것
        except:
            return False
        
        
        # slow = fast = head
        # while fast and fast.next:
        #     fast = fast.next.next
        #     slow = slow.next
        #     if fast == slow:
        #         return True
        # return False
        
```

