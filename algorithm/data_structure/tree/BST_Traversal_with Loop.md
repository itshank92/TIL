## 반복구조를 사용해서 BST를 순회하는 코드를 작성하자

​    

> 순회를 위한 BST 만들기

```PYTHON
class Node:
  def __init__(self,val=0):
    self.val = val
    self.left = None
    self.right = None
  

def insert(root,key):
  if root is None:
    return Node(key)
  if root.val > key:
    root.left = insert(root.left,key) 
  elif root.val < key:
    root.right = insert(root.right,key)
  return root

root = Node(5)
insert(root,3)
insert(root,1)
insert(root,4)
insert(root,7)
insert(root,6)
insert(root,8)
#######
"""
      5
   3      7
 1   4  6   8
"""
```

   



> 전위순회(preorder) 코드

```python
def preorder_loop(root):
  stack = [root]
  
  while stack:
    # pop을 해서 해당 노드를 방문한다.
    node = stack.pop()
    print(node.val)

    # right child가 존재하는 경우부터 확인해서 stack에 넣는다.
    # ▶ 전위순회는 노드-왼쪽-오른쪽 순서로 순회하기에 stack의 뒤쪽에 left child를 넣어야 한다.
    # ▶ stack은 뒤에서부터 요소를 뽑아서 순회하기 때문
    if node.right:
      stack.append(node.right)
    if node.left:
      stack.append(node.left)

preorder_loop(root)
#결과값
"""
5
3
1
4
7
6
8
"""
```

​    

> 중위순회(inorder)코드

```python
def inorder_loop(root):
  stack = []
  node = root
  
  # stack와 node 모두의 조건을 넣는다.
  while stack or node:
    # node가 존재하는 경우 left child를 끝까지 넣어준다.
    # ▶ 어떤 노드든 left child부터 탐색하게 된다.
    while node:
      stack.append(node)
      node = node.left
    # pop을 하고 탐색
    node = stack.pop()
    print(node.val)

    # 탐색 종료후에는 right child로 node를 바꿔준다.
    # 현재 노드에 대한 탐색 후 right child를 탐색 대상으로 하는 것은 중위순회의 특징
    # right child가 없는 경우 자동으로 stack.pop()으로 넘어가서 다음 탐색 대상이 나온다.
    node = node.right
    
inorder_loop(root)
## 결과값
"""
1
3
4
5
6
7
8
"""
```

   

* BST에서 중위순회(inorder)를 수행하는 경우 **오름차순 순서로 순회**하는 것과 같다.

​     

> 후위순회(postorder) 코드

```python
# 중위순회(inorder)와 다른 점은 stack에 (모든노드에 대해) right child를 끝까지 넣어준다는 것이다.
def postorder_loop(root):
  stack = []
  node = root
  while stack or node:
    while node:
      stack.append(node)
      node = node.right
    node = stack.pop()
    print(node.val)
    node = node.left

  
postorder_loop(root)
## 결과값
"""
8
7
6
5
4
3
1
"""
```

   

* 후위순회로 BST를 탐색하면 **내림차순으로 정렬된 배열을 탐색하는 것**과 같다. 