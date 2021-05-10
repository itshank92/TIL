# 퀵정렬

https://colab.research.google.com/drive/1_n6dqMjuirpRf2PGkzIdCuVDHUsGksTi#scrollTo=2GyphccZy4gZ

https://www.geeksforgeeks.org/quick-sort/

> 기본

퀵소트는 기본적으로 분할정복의 정렬 기법이다. 

배열에서 피벗을 정하고 구역을 나눈다. 

배열에서 어떤 위치의 요소를 피벗으로 정할 것인지에 따라 퀵소트는 종류가 다음과 같이 나뉜다.    



* 가장 앞의 요소를 피벗으로 정하는 경우
* 가장 뒤의 요소를 피벗으로 정하는 경우 
* 랜덤한 위치의 요소를 피벗으로 정하는 경우 (가장 성능이 좋다고 알려져있다.)
* 중간값을 피벗으로 정하는 경우    



퀵소트의 핵심은 바로 **구역을 나누는 것**이다. 피벗을 기준으로 왼쪽과 오른쪽의 구역을 나눈다. 

그리고 나서 **피벗보다 작은 값은 왼쪽으로, 피벗보다 큰 값은 오른쪽에 위치시키는 것**이다. 

이 작업은 선형시간에 수행된다.

   

> 퀵소트의 수도코드

```
/* low: 시작인덱스,  high: 종료인덱스 */

quickSort(arr[], low, high)
{
    if (low < high)
    {
        /* pi: 구역나누는 기준점 인덱스 */
        pi = partition(arr, low, high); // 구역나누기

        quickSort(arr, low, pi - 1);  // pi왼쪽에 대한 퀵소트
        quickSort(arr, pi + 1, high); // pi오른쪽에 대한 퀵소트
    }
}
```

![quicksort]([Sort]Quick_Sort.assets/QuickSort2.png)

​     

> 구역나누는 알고리즘(Partition Algorithm)

많은 종류의 Partition 알고리즘이 존재한다.  여기서는 CLRS book\*에서 나온 Partition 알고리즘 수도코드를 바탕으로 작성해볼 것이다.

*(CLRS book: 책 Introduction to Algorithms를 지칭하는 말로 4명의 저자의 첫글자만 따서 CLRS책이라 불린다)* 

로직은 간단한다. **배열의 가장 오른쪽 요소를 피벗으**로 삼고 앞의 **요소들을 피벗과 하나씩 비교**한다. 

비교를 통해 **피벗보다 작은값이 발견되면 가장 왼쪽의 요소부터 스왑**한다. 

▶ 이를 위해 **가장 왼쪽을 가리키는 포인터**를 따로 생성한다.

이렇게 피벗 이전의 모든 요소에 대한 비교가 끝나면 **<u>왼쪽 포인터 +1</u>의 위치의 요소와 피벗을 스왑**한다. 

왼쪽 포인터의 의미는 해당 포인터까지의 요소가 피벗보다 작다는 것이다. 

따라서 우리는 피벗의 위치를 왼쪽포인터 + 1로 교환해주고 이로서 피벗을 기준으로 왼쪽은 피벗보다 작은 요소들, 오른쪽은 피벗보다 큰 요소들로 정렬이 된다. 

피벗위치의 요소는 자신의 자리를 찾았기에 피벗 인덱스를 기준으로 그 앞부분과 그 뒷부분에 대해서 다시 퀵소트를 수행한다. 

수도 코드를 바탕으로 파이썬 함수를 작성하였다.

★ `quicksort`와 `partition` 모두 `arr, left, right`를 인자로 넘겨받는다.

```python
#  partition: 피벗을 정하고 좌우 정렬을 수행한 다음 해당 피벗 위치를 반환하는 함수

def partition(arr, low, high):
    # 피벗을 오른쪽 가장 끝의 요소로 선택
    pivot = arr[high]
    
    # 피벗보다 작은 요소의 인덱스 위치값
    # ▶ 초기 값은 -1로 아직 그 어떤 요소도 피벗보다 작다고 발견되지 않았기에!
    i = low-1
    
    # 입력받은 탐색 범위의 처음부터 피벗의 직전까지 탐색 수행
    # ▶ 본 코드에서는 피벗이 가장 오른쪽 요소(arr[high])로 설정되었기에 이러한 방식으로 수행
    for j in range(low,high):  # ★범위는 low to high!
        # 해당 요소가 피벗보다 작은 경우
        if arr[j] < pivot:
			# 피벗보다 작은 요소의 인덱스 위치값(i)+1의 요소와 해당 요소를 스왑(swap)
            # (i는 피벗보다 작은 요소를 왼쪽으로 쌓았을 때 마지막 위치값)
            i += 1
            arr[i],arr[j] = arr[j],arr[i]
            
    # 모든 탐색이 끝났다면, i+1(피벗보다 작은 요소들의 마지막 위치 + 1)와 피벗을 스왑한다.
    # 즉 pivot의 위치는 i+1이 된다. 
    # 이러한 방식을 통해서 피벗의 왼쪽(low ~ i)은 피벗보다 작은 요소들로
    # 피벗의 오른쪽(i+2~high)은 피벗보다 큰 요소들로 구성된다.
    # ▶ 결론적으로 피벗이 위치한 i+1위치는 정렬이 끝났다.
    # ▶ 정렬이 끝난 위치인 i+1을 반환한다.
    arr[i+1], arr[high] = arr[high], arr[i+1]
    return i + 1


# partition함수를 통해 매번 제대로 된 자리를 찾은 피벗을 제외하고 양옆의 영역을 분할정복
def quicksort(arr, low, high):
    # low와 high는 탐색할 배열의 시작과 끝을 가리키는 인덱스
    # 따라서 탐색할 배열이 크기가 1인 경우에는 low와 high가 같아짐
    # 탐색할 배열의 크기가 1이 아닌 경우에만 quicksort 수행
    if low < high:
        # 현재 탐색 범위에서 피벗을 정하고 해당 피벗을 정렬된 위치로 이동시킴
        # (정렬된 위치를 반환값으로 받음)
        pi = partition(arr,low,high)
        # 정렬된 위치를 제외하고 그 양옆 영역에 대해 quicksort 수행
        quicksort(arr,low,pi-1)  #★ arr 통째로 넣는 것 주의(병합정렬과 다름)
        quicksort(arr,pi+1,high)
```

​     



> 코드 설명

* 큰 틀
  * 퀵소트(`def quicksort`)는 재귀의 구조를 관장한다
    * **재귀의 실행조건(`low < high`)을 퀵소트 레벨에서 관리**한다.
    * 재귀의 대상이 되는 인자를 퀵소트 레벨에서 관리한다.
  * 분할함수(`def partition`)는 재귀를 수행만 한다.
    * 분할함수를 통해 피벗에 해당하는 수의 정렬된 위치를 찾아서 정렬한다.
    * 분할함수의 반환값으로 피벗의 위치값을 받아서 그 위치를 기준으로 왼쪽 오른쪽 구역을 나눈다.
    * 양쪽 구역에 대해 분할함수를 수행한다.
  * ※ 분할함수의 인자는 배열, 탐색시작위치, 탐색끝위치 이다
  * ※ 퀵소트 역시 내부적으로 분할함수를 재귀적으로 호출하기에 분할함수와 같은 요소를 인자로 받는다.
  * ※ 퀵소트의 수행조건은 탐색 범위가 2이상인경우로, `탐색끝위치 > 탐색시작위치` 인 경우다.
* 분할함수(`def partition`)  작동과정
  * 탐색 대상의 가장 마지막 요소를 피벗으로 설정한다.
  * 앞으로 탐색을 수행하면서 해당 요소가 피벗보다 작은 경우 가장 왼쪽의 요소부터 스왑한다.
  * 스왑위치를 가리킬 포인터를 변수로 만들어서 사용한다.
  * 탐색이 끝나면 스왑위치를 가리킬 포인터 +1과 피벗을 스왑한다.
    * 스왑위치를 가리킬 포인터는 항상 피벗보다 작은 요소 중 끝에 있는 요소 위치를 가리킨다.



#  중간값을 피벗으로 설정하기

* 말 그대로 가운데 값을 피벗으로 설정하기

* 원래 피벗으로 설정하던 곳(나의 경우 right)와 중간 값을 swap해주는 것 만 수행하고  나머지는 기존의 퀵소트와 동일하다.

  

```python
def partition(left,right,arr):
    arr[(left+right)//2],arr[right] = arr[right],arr[(left+right)//2]
    pivot = arr[right]
    lp = left
    for idx in range(left,right):
        if pivot > arr[idx]:
            arr[idx], arr[lp] = arr[lp], arr[idx]
            lp += 1
    arr[right], arr[lp] = arr[lp], arr[right]
    return lp
```

이렇게 가운데 값을 피벗으로 설정하는 경우 성능이 좋아질거라는 기대가 있지만 사실 성능이 개선된 퀵소트는 단순히 left와 right의 가운데 값을 피벗으로 설정하는 것이 아닌, left, right, mid를 미리 정렬하고 정렬된 결과 중간에 위치한 값을 피벗으로 설정하는 것이다.

이를 전문용어?로 **Median of Three(left,right,mid)**라고 부른다. 



## Median of Three

코드는 다음과 같다. 

```python
def partition(left,right,arr):
    if right-left >= 2:
        mid = left + ((right-left)//2)
        if arr[left] > arr[mid]:
            arr[left],arr[mid] = arr[mid],arr[left]
        if arr[mid] > arr[right]:
            arr[right], arr[mid] = arr[mid], arr[right]
        if arr[left] > arr[mid]:
            arr[left], arr[mid] = arr[mid], arr[left]
        arr[mid],arr[right-1] = arr[right-1],arr[mid]
        left += 1
        right -= 1
    pivot = arr[right]
    lp = left
    for idx in range(left,right):
        if pivot > arr[idx]:
            arr[idx], arr[lp] = arr[lp], arr[idx]
            lp += 1
    arr[right], arr[lp] = arr[lp], arr[right]
    return lp
```

* 정렬 대상 범위의 값이 3개 이상일 때( `right-left >= 2` ) **Median of Three**를 수행한다. 
* left, right mid를 비교해서 순서대로 정렬하고 mid값을 right-1로 이동시킨다.
* left, right 값을 수정해서 퀵정렬 범위를 줄인 다음, 피봇을 설정하고 퀵정렬을 수행한다.









> 기타



퀵소트는 기존 배열을 사용하여 정렬하는 내부정렬 방법으로 메모리 사용이 효율적이다.