### 연결리스트 이해하기



> 코드

```python
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# ★ root와 포인터를 동시 생성(같은 연결리스트)   
test_node = root = ListNode()

# 연결리스트에 값 입력(※next는 같은 객체여야한다)
for i in range(1,6):
  # 다음 값으로 입력
  test_node.next = ListNode(i)
  # 포인터를 다음으로 이동
  test_node = test_node.next

# 출력을 위해 root.next 포인터 생성    
node = root.next
for i in range(5):
  # 해당 차례의 값을 출력
  print(node.val)
  # 출력했으면 포인터 다음으로 이동
  node = node.next
    
###### 출력값 ######
# 1
# 2
# 3
# 4
# 5
```

