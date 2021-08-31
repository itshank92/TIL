

# 두 연결리스트의 교차노드 찾기

from: https://leetcode.com/problems/intersection-of-two-linked-lists/

​     



---

​     



### 문제 이해 ★★★

1. `노드A`와 `노드B`의 값(`.val`)이 같다고 해서 두 노드가 같은 것은 아니다.
   * 예를 들면 `노드A`의 값이 `1`이고 `노드B`의 값이 `1`이라고 해서 두 노드가 같은 것은 아니다.

​    

2. 그래서 교차점(`Intersection`) 노드가 있다면 `노드A.val == 노드B.val`가 아닌 `노드A == 노드B`로 판별해야 한다.     



3. 문제에서 두 연결리스트에 동일한 노드가 존재한다면, 해당 노드는 바로 교차점(`Intersection`)이 된다.

​        



---

​      

### 문제 풀이 ★★★



풀이 링크: https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!



이 문제 풀이에 있어서 노드의 값의 차이를 신경쓸 필요가 없다. 어떤 노드가 같은 노드인 경우 값이 아닌 `노드 == 노드`로서 판별 가능하기 때문이다.

풀이에 있어서 주의해야 하는 것은 단지 같은 노드(intersection)에 동일한 시점에 도착해야 한다는 것이다. 
(두 포인터가 **동일한 회차에 해당 교차점 노드를 비교**해야 한다는 것)

최대 두 번의 순회(iteration)를 통해 두 연결리스트에 대해서 동일한 시점에 교차점(intersection) 노드를 비교할 수 있다. 

교차점을 찾기 위한 순회가 실제 어떻게 수행되는지는 아래 예시 상황을 통해서 이해할 수 있다. 



**Visualization of this solution:**
**Case 1 (Have Intersection & Same Len):**

`a`: 연결리스트A에서 출발하는 포인터

`b`: 연결리스트B에서 출발하는 포인터

[순회 종료 조건이 아닌데, 어떤 하나의 포인터가 `None`을 가리키는 경우 (= 끝까지 순회한 경우)]

* 해당 포인터가 순회하지 않은 다른 연결리스트의 `head`로 포인터를 이동시킨다.



[순회 종료 조건]

- 두 포인터가 가리키는 대상이 같을 때
- 종료시 포인터`a`가 가리키는 대상을 `return`(반환)한다.

```
       a
A:     a1 → a2 → a3
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
       b
      
--------------------------------------
            a
A:     a1 → a2 → a3
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
            b
            
--------------------------------------
                 a
A:     a1 → a2 → a3
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
                 b
                 
--------------------------------------
                 
A:     a1 → a2 → a3
                   ↘ a
                     c1 → c2 → c3 → null
                   ↗ b            
B:     b1 → b2 → b3
```



Since `a == b` is true, end loop `while(a != b)`, return the intersection node `a = c1`.



**Case 2 (Have Intersection & Different Len):**



```
            a
A:          a1 → a2
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
       b
       
-----------------------------------------
       
                 a
A:          a1 → a2
                   ↘
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
            b
            
-----------------------------------------
            
A:          a1 → a2
                   ↘ a
                     c1 → c2 → c3 → null
                   ↗            
B:     b1 → b2 → b3
                 b
                 
-----------------------------------------
                 
A:          a1 → a2
                   ↘      a
                     c1 → c2 → c3 → null
                   ↗ b           
B:     b1 → b2 → b3

-----------------------------------------

A:          a1 → a2
                   ↘           a
                     c1 → c2 → c3 → null
                   ↗      b           
B:     b1 → b2 → b3

-----------------------------------------

A:          a1 → a2
                   ↘                a = null, then a = b1
                     c1 → c2 → c3 → null
                   ↗           b           
B:     b1 → b2 → b3

-----------------------------------------

A:          a1 → a2
                   ↘ 
                     c1 → c2 → c3 → null
                   ↗                b = null, then b = a1 
B:     b1 → b2 → b3
       a
       
-----------------------------------------
       
            b         
A:          a1 → a2
                   ↘ 
                     c1 → c2 → c3 → null
                   ↗
B:     b1 → b2 → b3
            a
            
-----------------------------------------
            
                 b         
A:          a1 → a2
                   ↘ 
                     c1 → c2 → c3 → null
                   ↗ 
B:     b1 → b2 → b3
                 a
                 
-----------------------------------------
                 
A:          a1 → a2
                   ↘ b
                     c1 → c2 → c3 → null
                   ↗ a
B:     b1 → b2 → b3
```



Since `a == b` is true, end loop `while(a != b)`, return the intersection node `a = c1`.



**Case 3 (Have No Intersection & Same Len):**



```
       a
A:     a1 → a2 → a3 → null
B:     b1 → b2 → b3 → null
       b
       
-----------------------------------------
       
            a
A:     a1 → a2 → a3 → null
B:     b1 → b2 → b3 → null
            b
            
-----------------------------------------
            
                 a
A:     a1 → a2 → a3 → null
B:     b1 → b2 → b3 → null
                 b
                 
-----------------------------------------
                 
                      a = null
A:     a1 → a2 → a3 → null
B:     b1 → b2 → b3 → null
                      b = null
```



Since `a == b` is true (both refer to null), end loop `while(a != b)`, return `a = null`.



**Case 4 (Have No Intersection & Different Len):**



```
       a
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
       b
       
-----------------------------------------
       
            a
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
            b
            
-----------------------------------------
            
                 a
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
                 b
                 
-----------------------------------------
                 
                      a
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
                      b = null, then b = a1
                      
-----------------------------------------
                      
       b                   a = null, then a = b1
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null

-----------------------------------------

            b            
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
       a
       
-----------------------------------------
       
                 b
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
            a
            
-----------------------------------------
            
                      b
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
                 a
                 
-----------------------------------------
                 
                           b = null
A:     a1 → a2 → a3 → a4 → null
B:     b1 → b2 → b3 → null
                      a = null
```



Since `a == b` is true (both refer to null), end loop `while(a != b)`, return `a = null`.

​     



---

### 결론

만약 두 연결리스트의 길이가 같다면 한 번의 순회를 통해서 교차점 여부를 확인할 수 있다.

두 연결리스트의 길이가 다른 경우 역시 2번의 순회를 통해서 교차점 여부를 확인할 수 있다.

​     



---

### 원리

* 두 연결리스트를 연결(concatenate)한 다음, 각 연결리스트의 `head`부터 순회하면 둘 다 같은 길이의 연결리스트를 순회하게 된다.
* 연결리스트에 교차점이 존재한다면  해당 교차점을 같은 시점에 조회하게 되고, 교차점이 없는 경우 연결리스트의 마지막인 `None`에서 두 포인터가 가리키는 대상이 같게 된다. 



---

### 풀이

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        a = headA
        b = headB
        while a != b:
            if a == None:
                a = headB
            else:
                a = a.next
            if b == None:
                b = headA
            else:
                b = b.next
        return a
```

