# bst(binary search tree)
# binary tree 중 왼쪽 자식 노드 값 <= 부모 노드 값 <= 오른쪽 자식 노드 값 을 만족하는 트리가 bst

# 이전에 binary tree를 만들 때 사용했던 Node 클래스에 값을 찾기 위한 search 메소드를 만들어보자
# (이전 코드: binary.py 참고)

class Node:
    
    def __init__(self,data):
        self.data = data
        self.left = None
        self.right = None
    

    # 값을 넣는 메소드
    def insert(self,data):
        if self.data:
            if self.data > data:
                if self.left is None:
                    self.left = Node(data)
                else:
                    self.left.insert(data)
            elif self.data < data:
                if self.right is None:
                    self.right = Node(data)
                else:
                    self.right.insert(data)
            
        else:    
            self.data = data      

    ## 여기에 값을 탐색하는 findval 메소드를 작성한다.
    def findval(self,val):
        if val < self.data:
            if self.left is None:
                return str(val) + " Not Found"
            return self.left.findval(val)
        elif val > self.data:
            if self.right is None:
                return str(val) + " Not Found"
            return self.right.findval(val)
        else:
            return str(self.data) +  ' is found'

    # 해당 노드 기준으로 아래 Tree 보여주는 함수
    def PrintTree(self):
        if self.left:
            self.left.PrintTree()
        print(self.data)
        if self.right:
            self.right.PrintTree()

# 이진트리 만들기
root = Node(12)
root.insert(6)
root.insert(14)
root.insert(3)

print(root.findval(7))
print(root.findval(14))