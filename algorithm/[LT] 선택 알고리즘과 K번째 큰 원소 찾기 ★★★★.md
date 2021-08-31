# 선택 알고리즘과 k번째 큰 원소 찾기 



선택알고리즘 출처: https://dad-rock.tistory.com/351

k번째 큰 원소찾기 문제: https://leetcode.com/problems/kth-largest-element-in-an-array/

k번째 큰 원소찾기 풀이: https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60294/Solution-explained     





## 선택 알고리즘이란

* 입력값들 중 `i` 번째로 큰(작은) 원소를 찾는 알고리즘
* `n`개의 입력값에 대해 전체 순회가 필요하기에 `O(n)`만큼의 시간이 소요된다. (최악의 경우 `O(N^2)`)
* **선택 알고리즘**은 기본적으로 **퀵 정렬의 `partition` 함수를 응용(사용)해서 수행하는 탐색**이기에 퀵정렬을 알아야 한다.        

​      



## [배경지식] 퀵 정렬의 `partition` 함수

퀵 정렬은 입력 배열에 대해서 기준 원소를 중심으로 기준값보다 작은 원소들은 왼쪽에, 큰 원소들은 오른쪽에 배치시킨다.  
▶ 이 작업을 수행하는 함수가 바로 `partition` 함수다. 

기준 원소는 배열 내의 어떤 원소도 될 수 있고 여기서는 배열의 가장 마지막 원소를 기준점으로 삼아서 설명을 진행한다.     



#### `partition` 함수의 작동 방식

(0) 기준 값보다 작은 값을 왼쪽으로 배치시키기 위한 포인터를 만든다(`lli`)

(1) 입력된 배열의 임의 위치의 값을 기준 값으로 설정한다. (여기에서는 가장 마지막 값으로 설정한다)

(2) 전체 값들을 순회하면서 기준 값보다 작은 값은 왼쪽으로, 큰 값은 오른쪽으로 재배치 시킨다.

(3) 모든 값들에 대해 재배치가 끝나며 기준값의 최종 인덱스를 반환한다.

```python
def partition(arr, low, high):
    # lli: last_low_index ▶ pivot보다 작은 요소의 가장 최우측(마지막) 인덱스 (초기값은 -1)
    lli = low - 1
    pivot = arr[high]
    
    # pivot전까지 모두 순회
    for j in range(low, high):
        # pivot보다 작은 경우 lli에 + 1을해서 가장 작은 값의 최우측 인덱스 바로 다음 위치를 가리키게 함
        # arr[j](피벗보다 작은 값)와 arr[lli](피벗보다 작은 값의 최우측 인덱스 다음값) SWAP
        if arr[j] <= pivot:
            lli += 1
            arr[lli], arr[j] = arr[j], arr[lli]
            
    # 순회가 끝나면 lli + 1의 위치(피벗보다 큰 요소의 첫번째 위치)와 pivot을 SWAP
    arr[lli+1], arr[high] = arr[high], arr[lli+1]
    
    # pivot의 최종 위치를 반환
    return (lli+1)
            
```

( cf ) 퀵정렬 코드

```python
def quickSort(arr,low, high):
    ## 배열의 길이가 1이면 배열 그대로 반환
    if len(arr) == 1:
        return arr
    
    # 퀵정렬 대상이 되는 영역은 low와 high 포인터로 설정된다.
    if low < high:
        # pi: 이번에 정렬된 pivot의 정렬위치(인덱스)
        pi = partition(arr,low,high)
        
        # 정렬된 값을 기준으로 영역 분리해서 퀵정렬 수행
        quickSort(arr,low,pi-1)
        quickSort(arr,pi+1,high)
```

(기준값을 배열의 가장 오른쪽의 값으로 설정하는 퀵정렬을 가정)
※ `partition`함수의 **입력 배열이 이미 정렬된 경우, 퀵정렬의 시간복잡도가 최악으로 되는 경우 `O(N^2)`가 나온다**.

▶ 이를 피하기 위해 **기준 원소를 매번 랜덤하게 선정**하는 방법이 있다.          







## 선택 알고리즘의 매커니즘

0) 사용자는 입력 배열에서 `i`번째로 큰(작은) 값을 알고자 한다.

1) 입력배열에 대해서 `partition()`함수를 한 번 실행시킨다.

2) **`partition()`의 결과값(기준 값의 인덱스)를 통해서 찾고자 하는 값이 왼쪽에 있는지, 오른쪽에 있는지 판단**할 수 있다.

3) 찾는 원소가 속한 부분을 파악했다면, 해당 부분 배열에 대해 다시 1번 과정을 수행한다.

4) **찾는 원소의 위치와 기준 원소의 위치가 일치할 때까지 재귀적으로 원소를 찾는다**.    



| 요약 | `i`번째 큰 원소가 존재할 수 있는 영역에 대해서만 재귀적으로 `partition`함수를 사용한다.<br />(모두 정렬할 필요가 없기 때문에) |
| ---- | ------------------------------------------------------------ |

​     



## 선택 알고리즘 코드

* 배열 `arr`에서 `i`번쨰로 작은 원소를 찾아야 하는 상황 
* 퀵정렬의 `partition` 코드를 통해 해당 `pivot`이 몇 번째로 작은지(인덱스+1로 파악가능) 판단한다.
* `pivot`이 `i`보다 작은 경우 오른쪽 영역에 대해서 다시 `partition` 함수를 수행한다.
* `pivot`이 `i`보다 큰 경우 왼쪽 영역에 대해서 다시 `partition` 함수를 수행한다.

```python
## partition 함수 생략

## arr에서 i번째로 작은 값을 찾는 문제
def selectSort(arr, low, high, i):
    
    while low < high:
        
        # pivot_loc은 arr[high]가 배열에서 몇 번째로 작은지를 보여준다.
        # ▶ pivot을 배열의 가장 끝에 있는 원소로 설정할 것이기에 arr[high]에 대한 정렬 위치를 반환
    	pivot_loc = partition(arr, low, high)    
        
        
        # pivot의 위치가 i보다 작은 경우
        # ▶ low를 pivot_loc + 1로 설정해서 오른쪽을 탐색 대상으로 한다.
        if pivot_loc < i:
            low = pivot + 1
            
            
        # pivot의 위치가 i보다 큰 경우
        # ▶ high의 위치를 pivot_loc - 1로 설정해서 왼쪽 영역을 탐색 대상으로 한다.
        elif pivot_loc > i:
            high = pivot - 1
            
        # 찾았을 때는 바로 break    
        else:
            break

    return arr[i]
```



