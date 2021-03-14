# 새로운 bst 코드

# 노드
class Node:
    def __init__(self,data):
        self.val = data
        self.left = None
        self.right = None



# 탐색 함수
def search(root,key):
    # 노드가 None이거나 해당 노드의 값이 key과 같다면 ▶ 노드 반환
    if root is None or root.val == key:
        return root
    # key가 노드의 값보다 큰 경우
    if key > root.val:
        return search(root.right,key)
    # key가 노드의 값보다 작은 경우
    return search(root.left,key)
    
# 삽입 함수 
# 일단 leaf 노드를 찾고 해당 leaf 노드의 child로 삽입한다.

def insert(root,key):
    # 노드가 비어있거나 노드의 값이 key와 같은 경우 
    if root is None:
        return Node(key) 
    # key가 노드의 값보다 작은 경우    
    if root.val > key:
        root.left = insert(root.left,key)
    # key가 노드의 값보다 큰 경우
    elif key > root.val:
        root.right = insert(root.right,key)
    # key가 노드의 값과 같은 경우 or 위의 과정 수행완료한 경우
    return root


# inorder traversal하며 출력하는 함수
def inorder(root):
    if root:
        inorder(root.left)
        print(root.val)
        inorder(root.right)

#     50
#   /     \
#  30     70
#  / \    / \
# 20  40 60  80
 
# 트리만들기
r = Node(50)
insert(r,30)
insert(r,70)
insert(r,20)
insert(r,40)
insert(r,60)
insert(r,80)

inorder(r)