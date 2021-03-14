# 트리를 구성하는 노드 클래스를 만들자
class Node:
    
    def __init__(self,data):
        self.data = data
        self.left = None
        self.right = None
    
    # self.data를 출력하는 함수
    def PrintTree(self):
        print(self.data)


# 이진 트리의 root를 만들자
root = Node(10)

# 노드 생성 및 값이 잘 들어갔는지 출력해서 확인해보기
root.PrintTree()  #=> 10이 잘 출력된다.

##############################################################################################
# 트리를 구성하기 위해서는 Node에 Child Node를 연결해줘야 한다. 
# #이 과정을 insert 라는 내장함수로 만들어보자 (기존의 Node클래스 재사용)

class Node:
    
    def __init__(self,data):
        self.data = data
        self.left = None
        self.right = None
    

    # 현재노드의 값보다 크면 right child로, 작으면 left child로 삼는 방식으로 구현해보자
    def insert(self,data):
        # 일단 부모노드가 값이 들어있는 정상노드인지 확인
        if self.data:
            # 입력데이터가 부모노드의 값보다 작은 경우
            if self.data > data:
                if self.left is None:
                    self.left = Node(data)
                else:
                    # self.left가 있는 경우, self.left를 가지고 insert를 수행한다.
                    self.left.insert(data)
            # 입력데이터가 부모노드의 값보다 큰 경우
            elif self.data < data:
                if self.right is None:
                    self.right = Node(data)
                else:
                    # self.right가 있는 경우, self.right를 가지고 insert를 수행하다.
                    self.right.insert(data)
            
        # self.data가 비어있는 경우(값을 넣어줌)
        else:    
            self.data = data
            

    # 노드의 자식노드들도 보여줄 수 있게 PrintTree함수 수정
    # left child - self.data - right child 순서로 작성하였다.
    def PrintTree(self):
        if self.left:
            self.left.PrintTree()
        print(self.data)
        if self.right:
            self.right.PrintTree()

# 이진트리를 만들어보자
root = Node(12)
root.insert(6)
root.insert(14)
root.insert(3)

root.PrintTree()

################################################################################
# 




