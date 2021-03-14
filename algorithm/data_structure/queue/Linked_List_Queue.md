# 연결리스트를 사용해 만드는 큐

출처: https://www.geeksforgeeks.org/queue-linked-list-implementation/     



> 기본

연결리스트를 사용해서 큐를 만든다.  

* **Enqueue**

  ▶ 새로운 노드를 `rear`에 해당하는 노드의 뒤에 만들어서 연결한다. 

  ▶ 새로운 노드에 `rear`를 할당해준다.

* **Dequeue**

  ▶ `front`에 해당하는 노드를 제거한다.

  ▶ `front`에 해당하는 노드의 `.next` 노드에 `front`를 할당해준다.     

  

연결리스트라는 특성에 힘입어 **큐의 사이즈(capacity)에 제한이 없다**.

* 큐의 사이즈(capacity)를 초기 큐 생성시 인자로 받지 않는다.
* 큐가 꽉 차있는지를 확인하는`isfull()`이라는 메소드가 필요가 없다.      



연결리스트이기에 `front`와 `rear`포인터를 `(+1) % capacity` 할 필요가 없다. 

* `front`와 `rear`는 일방향으로 작동한다.

​      

★ 연결리스트로 큐를 만드는 경우 **<u>큐가 비어있는 경우</u>**와 큐에 **<u>노드가 하나밖에 없는 경우</u>**를 주의해서 코드로 작성해야 한다.

* **큐가 비어있는 경우** ▶ **enqueue**를 할 때, `rear`뿐만 아니라, `front`에도 새 노드를 할당해줘야 한다.
* **큐에 노드가 하나밖에 없는 경우** ▶ **dequeue**를 할 때, `front`뿐만 아니라 `rear`도 `None`으로 바꿔줘야 한다.



> 코드

``` python
# 연결리스트를 사용해서 큐만들기
  
    
#1 연결리스트의 노드 클래스 
class Node: 
      
    def __init__(self, data): 
        self.data = data 
        self.next = None
  

 
class Queue: 
      
    #2 큐의 포인터 생성
    def __init__(self): 
        self.front = self.rear = None
  
	#3 큐가 비었는지 확인
    def isEmpty(self): 
        return self.front == None
      
    #4 큐에 값을 넣는 메소드
    def EnQueue(self, item): 
        #4-1 노드 생성
        temp = Node(item) 
        
        #4-2 rear가 없다면(=큐에 노드가 없다면)
        if self.rear == None: 
            self.front = self.rear = temp 
            return
       	#4-3 rear가 있다면(=큐에 노드가 있다면)
        self.rear.next = temp 
        self.rear = temp 
  
    #5 큐에 값을 빼는 메소드
    def DeQueue(self): 
        #5-1 큐가 비었다면
        if self.isEmpty(): 
            return
        #5-2 큐가 비어있지 않다면
        temp = self.front 
        self.front = temp.next
  		
        # 5-3 큐가 한개의 요소였을 경우(현재 0개)
        if(self.front == None): 
            self.rear = None
  


if __name__== '__main__': 
    q = Queue() 
    q.EnQueue(10) 
    q.EnQueue(20)
    print("Queue Front " + str(q.front.data))  #=> Queue Front 10
    print("Queue Rear " + str(q.rear.data))    #=> Queue Rear 20
    q.DeQueue() 
    q.DeQueue() 
    q.EnQueue(30) 
    q.EnQueue(40) 
    q.EnQueue(50)      
    print("Queue Front " + str(q.front.data))  #=> Queue Front 30
    print("Queue Rear " + str(q.rear.data))    #=> Queue Rear 50
```

​     

> 코드 설명

* **#1**

  * 연결리스트의 노드를 클래스로 선언한다.
  * 노드는 기본적인 형태로 값을 담는 부분(`.data`)과 다음 노드를 가리키는 부분(`.next`)으로 이뤄진다.

* **#2**

  * 큐의 포인터를 생성한다.
  * `.front`: 가장 앞의 노드를 가리키는 포인터, 초기값 `None` 
  * `.rear`: 가장 뒤의 노드를 가리키는 포인터, 초기값 `None`

* **#3**

  * 큐가 비었는지 확인하는 메소드
  * 큐가 비어있는 경우(=노드가 없다) `.front`와 `.rear`모두 `None`이다.
  * 위 코드에서는 두 포인터 중 `.front`를 사용해서 큐의 empty 여부를 판별하였다.

* **#4**

  * **enqueue**의 핵심은 **큐가 꽉찼는지 확인** & **(안찼다면) 큐의 가장 마지막 위치 다음에 넣어주기** 이다.
    * 연결리스트로 구현한 큐의 경우 capacity에 제한이 없기에 큐가 꽉찼는지 확인할 필요가 없다.
  * 연결리스트로 구현한 큐의 enqueue는 **큐가 비어있는지는 확인**해야 한다.
    * **큐가 비어있다면** 새 노드를 `rear`와 `front`에 모두 할당해야 한다.
    * **큐가 비어있지 않다면** 일반적인 큐의 euqueue처럼 `rear`의 `.next`에만 새 노드를 할당하면 된다.
  * **#4-1**
    * 입력받은 값으로 노드 만들기
  * **#4-2**
    * 큐가 비었다면 새로운 노드를 `front`와 `rear`에 모두 할당 
  * **#4-3**
    * 큐가 비어있지 않다면 새로운 노드를 `rear.next`에만 할당
    * `rear`를 새로운 노드로 설정

* **#5**

  * dequeue의 핵심은 **큐가 비었는지 확인** & (비어있지 않다면) **front에 위치한 값을 추출**

  * 연결리스트의 dequeue역시 큐가 비어있는 경우와 그렇지 않은 경우로 나눠서 접근

    * 다만, **큐가 하나의 노드로 이뤄진 경우** `front`뿐만아니라 `rear`도 `None`으로 바꾼다.
      (`None`은 `front.next`로 접근한다.)

  * **#5-1**

    * 큐가 비어있는 경우 ▶ 추출할게 없으니 안내메세지를 내보내던가 하고 종료

  * **#5-2**

    * 큐가 비어있지 않으면 `front`에 있는 값을 뽑고 `front.next`를 새로운 `front`로 등극시킴

  * **#5-3**

    * 새로 등극한 `front`의 값이 `None`이라면(=노드가 비었다면) `rear`도 `None`으로 설정해준다.

      → (노드 하나를 추출한 후) 새로 등극한 `front`가 `None`이라는 것은 큐에 노드가 1개뿐이었다는 것

    