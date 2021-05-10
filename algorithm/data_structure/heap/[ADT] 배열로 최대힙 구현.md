# 배열로 최대힙 구현

​    

> 중요한 설정

힙을 배열로 구현할 때, 인덱스를 통해 자식과 부모를 비교하게 된다. 

자식과 부모 관계를 쉽게 파악하기 위해 힙의 첫자리를 임의의 수로 padding해놓는다. 

**이렇게 해서 힙의 인덱스가 1부터 시작할 수 있게 만든다.**

[예시]

```python
h = [0]  #생성시 0번인덱스를 채워서 생성한다. → 1부터 입력 가능
```

​     

​          



> 최대힙 입력 구현

* 힙의 규칙은 딱 하나
* 부모는 자식보다 작거나 크다(작다→ 최소힙 // 크다 → 최대힙)
* 입력시 가장 아랫단에 입력해서 위로 올라올 수 있는 만큼 올라온다.

```python

#최대힙 입력 구현
def heap_insert(value, heap):
    # 가장 뒤에 입력
    heap.append(value)
    # index를 통해 부모와 비교작업을 수행함
    idx = len(heap) - 1
    while idx > 1:
        parent = idx // 2
        # 부모보다 자식이 큰 경우 swap
        if heap[idx][0] > heap[parent][0]:
            heap[idx], heap[parent] = heap[parent], heap[idx]
            idx = parent
        else:
            break
    return
```





> 최대힙 pop 구현

* 일단 반환할 값(인덱스1번)을 변수에 담아둔다.
* 힙에서 가장 뒤에 있는 값을 루트 노드(인덱스1번)로 불러온다.
* 자식들과 비교하면서 더 큰 자식이 있는 경우 해당 자식과 swap
* 위 과정을 자식들보다 자신이 더 클 때까지 반복한다.

```python

def heap_pop(heap):
    # 아무 인자가 없는 경우 None반환(비어있는 힙은 기본 길이가 1 >> padding때문)
    if len(heap) == 1:
        return None
    # 인자가 하나만 있는 경우 바로 pop()
    if len(heap) == 2:
        return heap.pop()

    # 가장 큰 값을 popnum에 저장 
    popnum = heap[1]
    # 가장 뒤에 있던 값을 루트로 불러옴
    heap[1] = heap.pop()
    idx = 1
    # 자식들과 비교하면서 더 큰 자식과 swap하는 과정을 지속한다.
    while True:
        largest = idx
        if idx*2 < len(heap) and heap[idx*2][0] > heap[largest][0]:
            largest = idx*2
        if idx*2+1 < len(heap) and heap[idx*2+1][0] > heap[largest][0]:
            largest = idx*2+1
        # 자신이 자식들보다 더 큰 경우 종료
        if largest == idx:
            break
        else:
            heap[idx],heap[largest] = heap[largest],heap[idx]
        idx = largest
    return popnum
```

