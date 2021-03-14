# 삽입정렬

출처: https://www.geeksforgeeks.org/insertion-sort/    



> 기본

삽입정렬은 단순한 정렬 방식 중 하나로 다음과 같이 작동한다.

* n개의 요소를 오름차순 정렬한다고 하자

1. 인덱스 1번 요소부터 n-1번 요소까지 순회한다.
2. 매번 이전의 값과 비교해서, 이전값보다 작은 경우 swap하고 다시 직전의 값과 비교한다.     



* 시간 복잡도는 O(n^2)가 소요되고, 공간 복잡도는 O(1)로 기존의 배열을 그대로 이용한다.(swap)
* 동일 값의 경우 기존의 순서를 보장하는 stable한 방식의 정렬이다

> 작동과정

![insertion-sort]([Sort]Insertion_Sort.assets/insertionsort.png)



> 코드

```python
def insertionSort(arr):    
    for i in range(1,len(arr)):
        # 앞의 값보다 현재값이 큰 경우까지 계속 바꿔준다.
        while i > 0 and arr[i-1] > arr[i]:
            arr[i-1],arr[i] = arr[i],arr[i-1] 
            i = i-1


### 입력값이 [4, 3, 2, 10, 12, 1, 5, 6]인 경우(위 그림과 동일)
"""
#0번째 수행결과: [4, 3, 2, 10, 12, 1, 5, 6]

#1번째 수행결과: [3, 4, 2, 10, 12, 1, 5, 6]

#2번째 수행결과: [2, 3, 4, 10, 12, 1, 5, 6]

#3번째 수행결과: [2, 3, 4, 10, 12, 1, 5, 6]

#4번째 수행결과: [2, 3, 4, 10, 12, 1, 5, 6]

#5번째 수행결과: [1, 2, 3, 4, 10, 12, 5, 6]

#6번째 수행결과: [1, 2, 3, 4, 5, 10, 12, 6]

#7번째 수행결과: [1, 2, 3, 4, 5, 6, 10, 12]
"""
```


