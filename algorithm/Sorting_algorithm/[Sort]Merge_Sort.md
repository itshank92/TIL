# 병합정렬

출처: https://www.geeksforgeeks.org/merge-sort/    



> 기본

퀵소트와 마찬가지로 병합정렬은 분할과 정복 방식으로 정렬하는 알고리즘이다. 입력받은 배열을 2등분하고 각 조각에 대해 자기 자신을 재귀로 호출한다. 그리고 나서 결과값으로 얻은 두 조각덩어리를 병합한다.    



> 병합 정렬의 작동 과정

* `arr`: 정렬하려는 배열,  `right`: 정렬하려는 범위의 끝,  `left`: 정렬하려는 범위의 시작

* `if right > left:`
  1. `right`와 `left`의 중간 지점(`middle`)을 찾는다.
     * `middle = (right-left)/2 + left`
  2.  중간 지점(`middle`)을 기준으로 나눈 두 덩어리에 대해 병합정렬을 재귀로 호출한다.
     * `mergeSort(arr, left, middle)`
     * `mergeSort(arr, middle+1, right)`
  3. 두 덩어리에 대해 재귀호출한 결과를 하나로 통합해준다.
     * `merge(arr, left, middle, right)`      



아래의 그림은 병합정렬의 과정을 잘 보여준다. 그림을 자세히 보면, 배열의 길이가 1이 될 때까지 계속해서 재귀적으로 배열을 두 덩어리로 나누는 것을 알 수 있다. 그리고 덩어리의 길이가 1이 되면 병합(merge)과정이 수행된다. 

![Merge-Sort-Tutorial]([Sort]Merge_Sort.assets/Merge-Sort-Tutorial.png)

   

> 병합정렬 코드

```python
def mergeSort(arr):
    if len(arr) > 1:
        #1 절반을 나눌 인덱스를 구한다(//2 사용)
        mid = len(arr)//2
        
        #2 L:왼쪽 덩어리, R:오른쪽 덩어리
        L = arr[:mid]
        R = arr[mid:]
        
        #3 각 덩어리에 대해 재귀 호출
        mergeSort(L)
        mergeSort(R)
        
        #4 i: L을 탐색할 포인터, j: R을 탐색할 포인터, k: 배열의 포인터
        # (★ k는 매 재귀마다 0부터 시작하는 이유)
        i = j = k = 0
        
        #5 L과 R에 대해 크기 비교할 값들이 남아 있는 경우
        while (i < len(L)) and (j < len(R)):
            # 오른쪽 덩어리의 값이 왼쪽 덩어리의 값보다 큰 경우, 
            # 배열의 k번째 위치에 왼쪽 덩어리의 값을 넣어주고 
            # i(왼쪽포인터) += 1, k(배열포인터) += 1
            if L[i] < R[j]:
                arr[k] = L[i]
                i += 1
            # 왼쪽 덩어리의 값이 오른쪽 덩어리의 값보다 큰 경우,
            # 배열의 k번째 위치에 왼쪽 덩어리의 값을 넣어주고 
            # j(오른쪽포인터) += 1, k(배열포인터) += 1
            else:
                arr[k] = R[j]
                j += 1
            k += 1
         
         # L에 아직 넣을 것이 남아 있는 경우   
         while i < len(L):
            arr[k] = L[i]
            i += 1
            k += 1
            
         # R에 아직 넣을 것이 남아 있는 경우   
         while j < len(R):
            arr[k] = R[j]
            j += 1
            k += 1
```

​    



> 코드 설명

* **#1**
  * `//2`를 사용하면 길이가 짝수든 홀수든 가운데를 나눌 수 있는 인덱스를 얻을 수 있다.
  * 길이가 짝수인 경우 큰 중앙인덱스값이 나온다. (길이가 6인 경우, //2의 결과는 3)
  * 길이가 홀수인 경우 정확한 중앙인덱스 값이 나온다. (길이가 5인 경우, //2의 결과는 2)
* **#2**
  * 왼쪽과 오른쪽 덩어리를 나눈다.
  * 이때, 주의해야 하는 것은, 재귀로 입력받은 `arr`은 이미 **쪼개진 arr**이라는 것이다. 
  * 즉, 왼쪽 덩어리 `arr[:mid]`도 재귀 내에서는 인덱스 `0`부터 시작하고
  * 오른쪽 덩어리 `arr[mid:]`도 재귀 내에서는 인덱스 `0`부터 시작한다.
* **#3**
  * 나눈 덩어리에 대해서 `mergeSort`를 재귀로 호출한다.
  * 병합정렬은 입력값을 직접 정렬하기에 따로 `return`값이 없다.
    * 입력받은 배열의 부분(덩어리)에 대해 직접 정렬한다. 
* **#4**
  * ★ 배열 포인터인 `k`가 매 재귀시마다 `0`부터 시작하는 이유
  * 병합정렬은 쪼개진 덩어리를 하나의 arr로 보고 각 arr은 인덱스 0부터 시작한다.
* **#5**
  * 작은것부터 앞에서 넣어준다.





## 병합정렬 다른 코드

제자리 정렬은 아니지만 직관적으로 이해가 되는 코드

아직까지 병합정렬이 쪼갠 arr에 대해서 쪼갠 arr의 인덱스로 arr를 바꾸는 것이 이해가 안된다. 

그래서 일단 이렇게 직관적으로 구현했다.



나누는 함수와, 병합하는 함수를 각각 구현했다.

```python
# 병합하는 함수
def merge(left,right):
  result = []
  lp = rp = p = 0
  while lp < len(left) and rp < len(right):
    if left[lp] < right[rp]:
      result.append(left[lp])
      lp += 1
    else:
      result.append(right[rp])
      rp += 1
  
  if lp < len(left):
    result.extend(left[lp:])
  if rp < len(right):
    result.extend(right[rp:])

  return result
    

# 나누는 함수
def mergesort(arr):
  if len(arr) > 1:
    left = mergesort(arr[:len(arr)//2])
    right = mergesort(arr[len(arr)//2:])
    return merge(left,right)
  return arr


temp = [10,40,2,3,40,666,52,88,12,96,5,9,11,86,1852,74,115,35]

temp = mergesort(temp)
temp

```





---

​       

# 병합정렬 이해하기

```python
def mergesort(arr):
  if len(arr) > 1:
    mid = len(arr)//2
    left = arr[:mid]
    right = arr[mid:]
    mergesort(left)
    mergesort(right)
    print("-------------------------")
    print(f" ■ arr = {arr}")
    print(f" ■ left = {left}")
    print(f" ■ right = {right}")
    print(f" ■ left + rifht = {left + right}")
    print("-------------------------")
    arr[0] = "♣"

arr = [1,2,3,4]
mergesort(arr)

"""
<결과값>
-------------------------
 ■ arr = [1, 2]
 ■ left = [1]
 ■ right = [2]
 ■ left + rifht = [1, 2]
-------------------------
-------------------------
 ■ arr = [3, 4]
 ■ left = [3]
 ■ right = [4]
 ■ left + rifht = [3, 4]
-------------------------
-------------------------
 ■ arr = [1, 2, 3, 4]
 ■ left = ['♣', 2]
 ■ right = ['♣', 4]
 ■ left + rifht = ['♣', 2, '♣', 4]
-------------------------

"""
```

