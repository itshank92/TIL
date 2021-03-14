# 노드
class Node:

    def __init__(self,key):
        self.key = key
        self.left = None
        self.right = None

# A utility function to do inorder traversal of BST
def inorder(root):
    if root:
        inorder(root.left)
        print(root.key)
        inorder(root.right)

# A utility function to insert a
# new node with given key in BST
# 재귀 형식으로 작성 ▶ 결국 최종 반환값은 최초의 insert에 입력된 node
def insert(node,key):
    # If tree is empty, return a new node
    if node is None:
        return Node(key)
    
    # Otherwise recur down the tree
    else:
        if node.key > key:
            node.left = insert(node.left,key)
        elif key > node.key:
            node.right  = insert(node.right,key)
        return node

# Given a non empty binary
# search tree, return the node
# with minimum key value
# found in that tree. Note that the 
# entire tree dose not need to be searched
# 위 영어 설명에서 return the node with minimum key value는 
# 가장 작은 값을 가진 노드를 반환하라는 것이지 가장 작은 값을 반환하라는 말이 아니다.
# node.key가 아닌 node를 반환하자
def minValueNode(root):
    # current라는 변수를 생성해서 포인터로 사용한다.
    # root를 포인터로 사용해서 계속 변경해도 함수 밖의 root 변수의 참조값이 변하지는 않는다.
    # ▶ 즉 root를 그냥 사용해도 상관없지만 좀 더 직관성을 위해 root가 current를 사용했다
    current = root

    while current:
        if current.left is None:
            return current
        else:
            current = current.left
    
# Given a binart search tree and a key, this function 
# delete the key and returns the new root ★ 삭제를 수행하고 root를 반환한다 ★
def deleteNode(root,key):
    
    #예외처리(tree 자체가 없는 경우)
    if root is None:
        return root
    
    # 탐색 ★ 멋진재귀
    if root.key > key:
        # [Q] root.left나 root.right를 계속 할당하는 형식으로 할 필요가 있을까?
        # 위의 insert같은 경우는 새로운 노드를 만들어서 넣는 것이기때문에
        # 부모노드에서 left나 right로 할당해주는 코드가 반드시 필요했다.
        # 하지만 delete의 경우 원래 존재하던 공간에 None이나 새로운 값을 넣어주는 것이기에
        # 연결을 신경쓰지 않아도 될것 같다고 생각한다.
        # [A] 지워야 하는 노드의 값만 바꿔주는 코드로 작성한다면
        # 이렇게 부모 노드의 화살표를 계속 재귀로 받아올 필요는 없다.
        # 하지만 아래 코드를 보면 삭제 대상 노드의 값을 바꾸는 형식이 아닌
        # 새로 대체할 노드를 반환하는 형태로 되어 있다.
        # [WHY]이는 실제 트리 형태의 데이터구조에서 특정 노드를 삭제할 때, 기존의 노드틀에
        # 값 하나만 바꿔서 삭제를 수행하는 경우가 거의 없기 때문이다.
        # 실제는 노드에는 수많은 값들이 있기에 값을 바꿔줌으로서 삭제 노드를 대체하는 것은
        # 쉽지 않다.
        root.left = deleteNode(root.left,key)
    
    elif key > root.key:
        root.right = deleteNode(root.right,key)
    
    # 삭제값의 노드를 찾은 경우
    else:
        # no child or only one child case (주의: 왼쪽만 있을수도, 오른쪽만 있을 수도 있다)
        # 자식이 한명 있으면 누가 되었든 그놈을 return한다(재귀구조로 인해 그놈은 조부모노드와 연결된다.).
        # 자식이 없으면 저절로 None이 반환되는 구조다.
        if root.left is None:
            temp = root.right
            root = None
            return temp
        elif root.right is None:
            temp = root.left
            root = None
            return temp
        
        # two child case
        # 해당 노드를 삭제한 후 그 노드에 대신 넣을 값을 찾자.
        # ▶▶▶ 오른쪽 subtree의 가장 작은 값을 찾는다 (or 왼쪽 subtree의 가장 큰 값을 찾아도 된다)
        temp = minValueNode(root.right)  # 가장 작은 값을 가진 노드가 반환된다.

        # 현재 노드(삭제하려는 값의 노드)의 값을 찾은 값(오른쪽 subtree중 가장 작은 값)으로 바꾼다.
        root.key = temp.key

        # 찾은 값(오른쪽 subtree중 가장 작은 값)을 deleteNode를 사용해서 삭제한다
        root.right = deleteNode(root.right,temp.key)
    
    return root
        

# 작동을 확인해보자
# Driver code
""" Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 """

root = None
root = insert(root,50)
# print(root.key) ▷ 50 (insert는 입력노드가 없으면 새로운 노드를 만든다)
root = insert(root,30)
# print(root.key) ▷ 50 (insert는 항상 입력노드를 반환한다)
root = insert(root,20)
root = insert(root,40)
root = insert(root,70)
root = insert(root,60)
root = insert(root,80)

print("BST 중위(inorder)순회")
inorder(root)

print("-------------")
print("BST에서 20을 값으로 가진 노드를 삭제하고 나서 중위(inorder) 순회")
deleteNode(root,20)
inorder(root)

print("-------------")
print("BST에서 30을 값으로 가진 노드를 삭제하고 나서 중위(inorder) 순회")
deleteNode(root,30)
inorder(root)

print("-------------")
print("BST에서 50을 값으로 가진 노드를 삭제하고 나서 중위(inorder) 순회")
deleteNode(root,50)
inorder(root)