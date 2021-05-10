# 연결리스트를 BST로 바꾸기

FROM: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/



---

> 내 풀이

* **fast runner 방식**으로 중간지점을 찾는다. 
  * 이때, 정확한 중간은 **middle이 아닌 slow에 위치**한다.
  * 전체 길이가 짝수인 경우 중간이 2개이기에 middle을 중간으로 설정해도 문제가 없지만, 홀수인 경우 slow가 중간이다.
* fast runner 방식으로 중간을 찾고 중간값을 가지고 TreeNode를 만든다.
* 그리고 중간값을 기준으로 양쪽을 자르고, 양쪽에 대해 재귀를 호출한다. 



※  ★★ **fast runner 방식**을 사용함에 있어 예외처리가 매우 중요하다. 예외처리를 잘 못해서 시간이 오래걸리고 문제 풀이가 어려워진다. ★★

예외처리는 **1번이라도 fast runner 방식이 돌아가는 경우가 아닌 경우**, 즉 **1번도 fast runner가 실행되지 않는 경우**를 생각하면 된다.

그 경우는 head가 None이거나 head.next가 None인 경우다.

두 경우 어떤 값을 return해줘야 하는지 생각해서 코드 최상단에서 처리한다. 





> 내 코드

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def sortedListToBST(self, head: ListNode) -> TreeNode:
        
        # 따로 외부 변수를 사용하는 것이 아닌데 굳이 중첩함수를 만들 필요가 없었다.
        def finding_middle_position(head):
            
            # ★ 예외처리가 매우 중요 ▶ fast runner를 안정적으로 수행하기 위해서
            
            # base case는 head가 0개인 경우, 1개인 경우로 나뉜다.
            # 0개인 경우, head 리턴(== null 리턴)
            if head is None:
                return head
            
            # 1개인 경우, head.val을 가지고 TreeNode를 만들어서 리턴 
            if head.next is None:
                return TreeNode(head.val)
            
            # middle: 중간 지점을 기점으로 이전끝 포인터 
            middle = None
            # slow: 중간 지점 포인터, fast: fast runner
            slow = fast = head
            while fast and fast.next:
                middle,slow,fast = slow,slow.next, fast.next.next
                
            # 이전 부분 연결 끊기
            middle.next = None
            
            # 중간값(slow)을 가지고 TreeNode를 생성
            # left, right에 양쪽 영역을 할당해서 재귀 호출
            # 반환값은 이번에 생성한 TreeNode
            node = TreeNode(slow.val)
            node.left = finding_middle_position(head)
            node.right = finding_middle_position(slow.next)
            return node
        return finding_middle_position(head)
```

