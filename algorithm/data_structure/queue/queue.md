# 큐

출처: https://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/    





> 기본 

선형자료구조인 큐는 **FIFO**를 보장한다. 



> 큐의 기능(명세)

* **Enqueue**
  * 큐에 값을 넣는 기능으로, 만약 큐가 꽉 찼다면(full) Overflow가 발생한다.
  * Enqueue의 핵심은 **큐가 꽉찼는지 확인** ▶ (안찼다면) **마지막에 넣은 위치 +1에 넣기**
* **Dequeue**
  * 큐에서 값을 빼는 기능으로 가장 먼저 넣은 값부터 빠진다. 
    (만약 큐가 비었다면(empty) Underflow가 발생한다.)
  * Dequeue의 핵심은 **큐가 비었는지 확인** ▶ (안비었다면) **가장 오래전에 넣은 요소 제거**
* **Front**: 큐의 가장 앞에 있는 값을 가져온다.
* **Rear**: 큐의 가장 뒤에 있는 값을 가져온다.

![queue](queue.assets/Queue.png)

​     



> 큐 구현을 위한 고려사항

* 큐를 구현하기 위해서는 우리는 큐의 가장 앞부분과 뒷부분을 가리키는 각각의 포인터가 필요하다.

* 앞부분을 가리키는 큐(`front`)의 용도: 큐에서 값을 빼낼때(`dequeue`) 사용된다.

* 뒷부분을 가리키는 큐(`rear`)의 용도: 큐에 값을 넣을 때(`enqueue`) 사용된다.

* 하지만 선형적으로 큐를 구현하는 경우 앞에 빈 공간이 생겼음에도 불구하고 이를 활용하지 못하게 된다. 

  ▶ 이 문제를 해결하기 위해 원형큐가 개발되었다.

  ▶ 선형큐에서 이문제를 해결하기 위해서는 **큐의 크기를 나타내는 attribute을 별도로 만들어야 한다**.

  ​	→ **큐의 크기를 확인하여 큐가 empty인지, full인지 확인하는 방식**을 취한다.  

  ​	→ 아래 코드에서는 큐의 크기를 `.size`라는 attribute로 만들어서 이 문제를 해결했다.

  





> 코드 

```python
# array implementation of queue 
  
class Queue: 
  
    #1 큐의 포인터들, 큐자체, 큐의크기를 attribute로 구현한다.
    def __init__(self, capacity): 
        self.front = self.size = 0  #★
        self.rear = capacity -1
        self.Q = [None]*capacity 
        self.capacity = capacity 
      
  	#2 큐의 크기(사이즈)가 큐의 용량과 같은 경우 큐는 full 상태다.
    def isFull(self): 
        return self.size == self.capacity 
      
    #3 큐의 크기(사이즈)가 0인 경우 큐는 empty 상태이다.
    def isEmpty(self): 
        return self.size == 0
  
    #4 큐에 값을 넣는 메소드이다.
    # .rear값과 .size값을 변경시킨다.
    def EnQueue(self, item): 
        #4-1 예외상황 처리
        if self.isFull(): 
            print("Full") 
            return

        #4-2 rear포인터를 다음 위치로 바꾸고 size를 수정해준다.
        self.rear = (self.rear + 1) % (self.capacity) 
        self.Q[self.rear] = item 
        self.size = self.size + 1
        print("% s enqueued to queue"  % str(item)) 
  
    #5 큐에서 값을 제거하는 메소드
    # .front값과 .size값을 변경시킨다.
    def DeQueue(self): 
        #5-1 예외상황 처리
        if self.isEmpty(): 
            print("Empty") 
            return
        #5-2 front 포인터를 다음 위치로 바꾸고 size를 수정해준다.
        print("% s dequeued from queue" % str(self.Q[self.front])) 
        self.front = (self.front + 1) % (self.capacity) 
        self.size = self.size -1
          
    #6 큐에서 가장 오래전에 입력된 값을 보여준다. (가장 앞쪽에 있는 값)
    def que_front(self): 
        if self.isEmpty(): 
            print("Queue is empty") 
  			return
        print("Front item is", self.Q[self.front]) 
          
    #7 큐에서 가장 최근에 입력된 값을 보여준다. (가장 뒤쪽에 있는 값)
    def que_rear(self): 
        if self.isEmpty(): 
            print("Queue is empty")
            return
        print("Rear item is",  self.Q[self.rear]) 
  
  
# 실제 코드 수행
if __name__ == '__main__': 
  
    queue = Queue(30) 
    queue.EnQueue(10) 
    queue.EnQueue(20) 
    queue.EnQueue(30) 
    queue.EnQueue(40) 
    queue.DeQueue() 
    queue.que_front() 
    queue.que_rear() 
    
### 결과값 ###
"""
10 enqueued to queue    ▶  [10]
20 enqueued to queue    ▶  [10,20]
30 enqueued to queue    ▶  [10,20,30]
40 enqueued to queue    ▶  [10,20,30,40]
10 dequeued from queue  ▶  [20,30,40]
Front item is 20
Rear item is 40

"""
```

​       

> 코드 설명

* **#1**
  * 입력값에서 `capacity`는 생성할 큐의 크기를 의미한다. (이론적으로 큐는 동적할당이 안되는 자료구조)
  * `.front`: **가장 오래전에 입력된 값의 위치**를 가리키는 포인터 →  `dnqueue`에 사용 & 초기값 `0`
  * `.rear`:  **가장 최근에 값이 입력된 위치**를 가리키는 포인터 →  `enqueue`에 사용 & 초기값 `capacity-1`
    * **`rear`의 초기값이 `capacity-1`인 이유**는 다음과 같다.
      1. 우리가 정의한 `rear`는 가장 최근에 입력된 값의 위치다. 
         * 즉, `enqueue`에서 값을 입력할 위치는 언제나 `rear`의 다음 위치가 된다. 
      2. 따라서 `enqueue`에서 값을 입력할 때 입력할 위치값은 `(rear + 1)%capacity`가 된다.
  * `.Q`: 실제 큐를 가리키는 속성값으로 초기의 요소들은 `None`으로 한다.
  * `.size`: 큐의 크기를 가리키는 속성값★
    * `size` 속성값을 통해서 **선형 큐의 한계를 쉽게 극복**할 수 있다. 
    * 정확히 말하자면 `size`가 없으면 `front`와 `rear`를 통해 큐가 `full`인지 `empty`인지 판단해야 한다.
    * 이 경우 각각의 경우를 고려하여 작성해야 한다.
    * 하지만 `size`라는 속성이 있다면 직관적으로 `full`인지 `empty`인지 판별할 수 있다.
    * 다시 말해, **`rear`와 `front`라는 변수를 그 원래 목적인 포인터로만 사용**할 수 있게되는 것이고, **더 직관적인 코드 작성이 가능**해진다.
  * `.capacity` : 큐에게 할당된 메모리 공간값을 가리키는 속성으로 포인터를 이동할 때 분모로서 사용된다.     



* **#2 #3**
  * 2번과 3번 함수는 큐가 `full`인지 `empty`인지 판별하는 함수로 앞서 만든 `.size` 값을 사용하여 쉽게 구현할 수 있다.    
  * 함수 이름에서 알 수 있듯이 해당 함수에서 알아보고자 하는 것이 `True`인지 `False`인지를 반환해야 하기에 조건식을 `return`으로 전달한다. (*간결* )



* **#4**
  * **#4-1** 예외상황
    * 큐에 값을 더이상 넣을 수 없는 경우(full)인지 확인한다.
  * **#4-2** 
    * 큐에 값을 넣을 때는 `rear` 포인터를 한칸 앞으로 옮긴 위치에 값을 넣는다.
      * `rear` 포인터는 **마지막에 넣은 값의 위치를 가리키기 때문**이다.
    * rear 포인터를 옮길 때는 `(+1) % capacity`를 해준다.
    * 값을 넣고나서 `.size` 를 `+1`해준다.
* **#5**
  * **#5-1** 예외상황
    * 큐가 비어있는 경우(empty)인지 확인한다.
  * **#5-2**
    * 큐에서 값을 추출할 때는 `front` 포인터가 가리키는 값을 뽑는다.
    * 뽑고나서 `front` 포인터는 `(+1) % capacity`를 통해 앞으로 한칸 이동한다.
    * 실제 `front` 위치의 값을 없앨 필요는 없다 ▷ 어짜피 우리는 `.size`를 통해 큐의 상태를 관리한다.
    * 값을 추출하고 나서 `.size` 를 `-1`해준다.
* **#6**
  * 큐에서 가장 앞에 있는 값을 보여주는 것으로 일단 `empty`가 아닌지 확인한다.
  * 큐가 `empty`가 아니면 `front`위치의 값을 보여준다.
  * 값을 추출하는 것이 아니기에 `.size`에는 변화가 없다.
* **#7**
  * 큐에서 가장 뒤에 있는 값을 보여주는 것으로 일단 `empty`가 아닌지 확인한다.
  * 큐가 `empty`가 아니면 `rear`위치의 값을 보여준다.
  * 값을 추출하는 것이 아니기에 `.size`에는 변화가 없다.



> 큐의 시간복잡도

**Time Complexity:**

```
Operations              Complexity
Enque(insertion)           O(1)
Deque(deletion)            O(1)
Front(Get front)           O(1)
Rear(Get Rear)             O(1)             
```

​    

> 선형 큐를 구현시 장점과 단점

* 장점
  1. 구현이 쉽다
* 단점
  1. 고정된 크기의 데이터 구조로 확장이 불가능하다.
  2. 많은 수의 enqeueu와 dequeue가 발생하는 경우, 큐가 비어있지 않음에도 불구하고 더이상 값을 추가하지 못하는 경우가 발생할 수 있다. ▷ 이는 원형큐 or `size` 속성값으로 해결가능