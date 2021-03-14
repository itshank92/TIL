## Abstract data type _ Minimum Heap

출처: 파이썬 알고리즘 인터뷰 *454page*    



> 최소힙 작동 과정 생각

* 최소힙은 배열로 구현한다.

* 배열의 가장 앞에는 가장 작은 값이 온다.

* **최소힙에 값을 입력할 때** (`push` or `insert`)

  1. 값을 최소힙 배열의 가장 뒤에 넣는다.
  2. 계속 부모와 비교작업을 수행하면서 **자신보다 부모가 작은 경우** 위치를 스왑한다.
     * **위로** 올라가면서 자신이 가장 높이 올라갈 수 있는 위치까지 올라간다.
  3. 부모보다 자신이 더 크거나 자신이 루트가 될때까지 2번 작업을 계속해서 반복한다.    

  

* **최소힙에서 값을 추출할 때** (`pop` or `extract`)

  1. 최소힙 배열의 가장 앞의 값을 뽑는다.
2. 최소힙에서 가장 뒤에 있는 값을 가장 앞에 넣는다.
  3. 가장 앞에 넣은 값을 자식노드와 비교해가면서 **자신보다 작은 자식이 있는 경우** 자신과 위치를 바꿔준다.

     * 두 자식(`left`, `right`)모두 자신보다 작은 경우, 더 작은 놈과 교체한다. (이를 통해 상하관계 보장됨)

     * **아래로** 내려가면서 모든 직계 자식들보다 자신이 더 작은 경우까지 수행한다.
4. 자신이 자식들보다 작거나 자신이 리프가 될 때까지 3번 작업을 수행한다.
  5. 1번에 뽑은 값을 반환한다.     

  

* 힙의 원소들은 **배열에 저장**하지만 실제 삽입이나 추출과정에서는 **이진 트리 구조로 접근**한다.

  * 이를 가능하게 하기 위해 힙의 배열 구성시 `[parent , child1 , child2]`로 구성한다. 
  * `parent`에서 `child`에 접근하거나, `child`에서 `parent`에 접근할 때, `index`를 사용한다.
  * 그런데 위의 경우 `parent`의 인덱스는 `0`, `child1`과 `child2`의 인덱스는 `1, 2`다.
  * 보통 두개씩 묶어서 표현할 때는 `(1,2), (3,4), ...`보다는 `(0,1), (2,3), (4,5)`가 편한다.
    
    * 이는 후자의 경우 `//2`를 하면 같은 묶음 안의 요소들은 같은 값을 가지게 되기 때문이다.    
    
    

* 따라서 힙 배열을 구성할 때, 인덱스 `0`번은 `None`으로 padding해준다.

  * 이렇게 구성하면 `root`값의 인덱스는 `1`이 되고 자식들을 `(2,3)`부터 2개씩 묶이게 된다.





> 최소힙 구현 명세서

* `Heap 클래스`
  * 힙 요소들을 보관할 배열을 속성에 할당한다.
  * (예시) `self.items = [None]`
    * 이때, 인덱스 `0`번에는 `None`을 넣어서 루트노드의 인덱스가 `1`번이 되도록한다.
    * 이를 통해서 모든 자식노드는 부모노드를 찾아갈 때, 자신의 인덱스에 `//2`를 하면 된다. 
* `__len__`
  * 가장 마지막 요소

* `insert`
  * 힙에 새로운 요소를 넣는 것으로 `heapq` 라이브러리의 `push`에 해당하는 기능이다.
  * 작동과정은 다음과 같다.







> 최소힙을 파이썬으로 구현해보자

```python
# 파이썬의 최소힙을 직접 구현하자
class BinaryHeap(object):
  def __init__(self):
    #1 각 요소의 인덱스는 1부터 시작한다.
    self.items = [None] 
  
  #2 __로 묶인 메소드는 class내부에서 사용하는 메소드로
  #  사용시에는 __ 없이 '메소드명(self)'로 사용한다.
  def __len__(self):
    return len(self.items)-1 # 마지막 요소를 가리키는 인덱스로 사용하기위해 len-1 를 반환한다.


  #3 삽입 시 실행, 반복 구조로 표현
  def _percolate_up(self):
    i = len(self)
    parent = i // 2
    while parent > 0:
      if self.items[i] < self.items[parent]:
        self.items[parent], self.items[i] = self.items[i], self.items[parent]
      i = parent
      parent = i//2
    
  #4  
  def insert(self, k):
    self.items.append(k)
    self._percolate_up()

  
  #5 추출시 실행, 재귀 구조로 표현   
  def _percolate_down(self,idx):
    left = idx * 2
    right = idx * 2 + 1
    smallest = idx

    if left <= len(self) and self.items[left] < self.items[smallest]:
      smallest = left
    
    if right <= len(self) and self.items[right] < self.items[smallest]:
      smallest = right

    if smallest != idx:
      self.items[smallest] , self.items[idx] = self.items[idx] , self.items[smallest]
      self._percolate_down(smallest)
    
  #6
  def extract(self):
    extracted = self.items[1] #루트값을 저장
    self.items[1] = self.items[len(self)]
    self.items.pop()
    self._percolate_down(1)
    return extracted
```

​    

> 코드 설명★

* **#1**
  * 힙은 내부적으로 배열로 저장되지만 논리 구조는 **트리**로 구성된다.
  * 힙은 **상하 관계**가 항상 보장된다.(위쪽에 있는 것은 아래에 있는 것보다 작다_최소힙의 경우)
    * 이진검색트리의 경우 **좌우 관계**가 항상 보장된다.
    * 왼쪽은 부모 노드보다 항상 작고 오른쪽 노드는 부모보다 항상 크다.
  * 힙 트리의 
  * 요소의 인덱스를 1부터 시작하는 경우
  * 이진트리이기에 2,3 / 4,5 / 6,7 순서로 묶을 수 있고 이는 //2를 공통점으로 가진 숫자다.

* **#2**

  * `__`(언더바 2개)로 시작한 메소드는 다른 메소드 내부에서 사용되는 메소드다. (called as `Magic Method`)
  * `__메소드명__(self)` 는 `메소드명(self)`으로 사용할 수 있다. 
  * `__`(언더바 2개)로 작성된 메소드는 외부에서 호출할 수 없다.    

  ```python
  # __(언더바 2개) 시작되는 메소드 사용해보기
  
  class Test:
    def __init__(self):
      self.array = [1,2,3,4]
    
    # __len__ 이라는 내부메소드를 만든다
    def __len__(self):
      return 4999
    
    # insert라는 메소드에 __len__을 사용해보자
    def insert(self):
      print(f"len(self) ▶ {len(self)}")
      print(f"len(self.array) ▶ {len(self.array)}")
      return
  
  s = Test()  # 객체 생성
  s.insert()  # 메소드 수행
  
  ## 결과값
  # len(self) ▶ 4999 
  # len(self.array) ▶ 4
  
  -------------------------------------------------------------------------------------
  ## 추가적으로 
  ## ▶ s.len()은 사용이 불가능하다.(__len__은 내부메소드로서 외부에서 접근할 수 없다.)
  ```

  * 결론적으로 내부 메소드는 오직 **클래스의 다른 메소드 안에서만 호출(사용)** 할 수 있다.
  * 클래스의 다른 메소드에서 내부 메소드를 사용할 때는 `내부메소드(self) `형태로 사용한다.



* **#3**
  * 



> 실제 사용 결과

```python
b = BinaryHeap()
b.insert(10)
b.insert(1)
b.insert(5)
b.insert(737)
b.insert(4)
b.insert(67)
b.insert(25)
b.insert(12)
b.insert(9906)


for _ in range(9):
  print(b.extract())

## 결과값 ##
"""
1
4
5
10
12
25
67
737
9906
"""
```

​     



> 최대 힙 만들기(perlocate_down을 재귀가 아닌 while문으로 바꿈)

```python
class Heapqueue:
    def __init__(self):
        self.items = [None]
        
    def __len__(self):
        return len(self.items) - 1
        
    def _perlocate_up(self):
        idx = len(self)
        parents = idx//2
        while parents > 0:
            if self.items[idx] > self.items[parents]:
                self.items[idx],self.items[parents] = self.items[parents],self.items[idx]
            idx = parents
            parents = idx//2    
    
    def insert(self,key):
        self.items.append(key)
        self._perlocate_up()
        return

    def _perlocate_down(self,idx):
        left = idx*2
        right =  idx*2 + 1
        larger = idx
        while True:
            if left <= len(self) and self.items[left] > self.items[larger]:
                larger = left
            if right <= len(self) and self.items[right] > self.items[larger]:
                larger = right
            if larger != idx:
                self.items[larger],self.items[idx] = self.items[idx],self.items[larger]
                idx = larger
                left = idx*2
                right = idx*2 + 1
            else:
                break
                       
    def extract(self):
        root = self.items[1]
        self.items[1] = self.items[len(self)]
        self.items.pop()
        self._perlocate_down(1)
        return root
```

