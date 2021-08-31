# rotate된 정렬 배열에서 특정 값 존재 확인하기

from: https://leetcode.com/problems/search-in-rotated-sorted-array/



> ### 생각

```python
[4,5,6,7,0,1,2]

target = 0
```

로테이션 기준이 되는 인덱스 `k`는 3이다.

모든 기존의 인덱스에 `-k` 즉 `-3`이 적용되어서 위와 같은 배열이 나왔다.

문제에서 `log(n)`으로 탐색을 수행하라고 하였기 때문에 **이진 탐색**으로 값을 탐색해야 한다.

그런데 이진 탐색을 수행하기 위해서는 일단, 가장 작은 값과 가장 큰 값의 위치를 알아야 한다.



> #### 1. 가장 큰 값의 위치 파악하기



가장 큰 값의 위치를 알아내면, 가장 작은 값의 위치도 알아낼 수 있다.

* 가장 큰 값의 위치를 알아내면 `k`를 알 수 있다.
  * 가장 큰 값의 위치 ==  `len(Array)- 1 - k`의 위치
  * 위 예시에서 `7`의 위치는 `3`이다. 
  * 이를 파악하면 `7 - 1 - k = 3` , 즉 `k`는 `3`인 것을 알 수 있다.

* `k`를 알아내면 가장 작은 값의 위치도 알 수 있다. 
  * 가장 작은 값의 위치 == `len(Array) - k` 
  * 위 예시에서 `0`의 위치는 7 - 3, 즉 4 인 것을 알 수 있다.  



**[ 실제로 가장 큰 값의 위치를 찾는 방법 ]**

배열의 가장 첫번째 값이 바로 원래의 `k`번째의 값이다. 

로테이션의 경우의 수

1) `k`가 `0`인경우(로테이션이 발생하지 않은 경우)

배열의 가장 첫번째 값보다 가장 마지막 값이 더 크다.

```
Array[0] < Array[-1]
```

이 경우 `k`는 바로 `0`으로 알 수 있다.



2) `k`가 `0`보다 큰 경우

배열의 가장 첫번째 값이 가장 마지막 값보다 더 크다.

```
Array[0] > Array[-1]
```

이 경우 이진 탐색을 수행한다. 

* `low = 0` , `high = len(Array) - 1`
  
  

1) 이진 탐색 대상 값이 `Array[0]` 보다 큰 경우 왼쪽 포인터(`low`)를 이진탐색 대상 위치 ~~`+ 1`~~로 옮긴다.

* ~~`low = mid + 1`~~

* `low = mid `

  

2) 이진 탐색 대상 값이 `Array[0]` 보다 작거나 같은 경우 오른쪽 포인터(`high`)를 이진 탐색 대상 위치로 옮긴다.

* `high = mid`
* `Array[0]`보다 작은 값의 경우, 모두 첫번째 시작값이 될 수 있는 가능성이 있기 때문에 `mid-1`이 아닌 `mid`로 해당 값을 계속 탐색 대상에 포함시킨다.



3) `high > low` 일 때까지만 이진 탐색 작업을 계속 수행한다. 

* 종료되는 경우 `high == low`이고 해당 위치가 바로 최대값의 위치가 된다. 







> #### 2. 이진 탐색으로 Target 찾기

이렇게 위치를 파악했으면 

가장 작은 값의 인덱스를 `low`로 가장 큰 값의 인덱스를 `high`로 설정해서 이진 탐색을 수행한다.

이때 현실의 인덱스는 rotation이 적용된, 즉 인코딩된 값이기에 이를 가지고 `mid`를 만들 수는 없다. 

따라서 `mid`를 만들기 위해서는 `low`를 원래 값으로 바꾸고 `high`도 원래 값으로 바꾸는 것이 필요하다. 

즉, `low`와 `high` 둘 다 `- k`가 되었기 때문에 `+ k`를 해준다. 

그러면 (최초에는) `low`는 `0`이 되고 `high`는 `len(Array) - 1`이 된다. 

이렇게 한 상태에서 `mid`를 구해준다.

* `mid = low + (high-low)//2`



`mid`는 이상세계의 `mid`이기에 이를 현실세계의 위치로 인코딩해준다. 

즉, `mid`에 `- k`를 해준다. 

그리고 나서 해당 값과 `target`을 비교한다. 

1) `target` 값 찾은 경우, `mid`를 반환

2) 탐색한 값이 `target` 값보다 큰 경우

* `high`를 `mid-1`로 바꾼다.

3) 탐색한 값이 `target` 값보다 작은 경우

* `low`를 `mid + 1`로 바꾼다.



위 작업은 `high >= low`일 때까지만 수행한다. 

만약 수행이 끝났는데도 함수가 종료되지 않은 경우,` -1`을 반환한다. 



> ### 코드 

```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        # 가장 큰 값 찾기 위한 이진 탐색
        # 가장 큰 값 찾는 이유: k를 파악하기 위해서
        # (k: rotate의 기준이 되는 값)
        low = 0
        high = len(nums) - 1
        
        # rotate가 되지 않은 경우, k는 0
        if nums[low] <= nums[high]:
            k = 0
            
        # rotate가 된 경우, 이진 탐색을 통해 가장 큰 값 찾기
        else:
            while high > low:
                mid = low + (high - low)//2
                # print(f"------------------")
                # print(f"low:{low}, high:{high}, mid:{mid}")
                
                # 가운데 값이 num[low]보다 크다면 low를 mid로 설정
                if nums[mid] > nums[low]:
                    low = mid
                
                # 가운데 값이 num[high]보다 크거나 같다면 high를 mid로 설정 
                else:
                    high = mid
			
            # while문 종료시(high와 low가 같아짐)
            # high(or low)가 바로 가장 큰 값의 위치
            # 해당 위치를 통해 k값 파악 가능(가장 큰 값의 원래 위치는 len(nums)라는 사실을 바탕으로 역접근)
            max_loc = high
            k = len(nums)-1 -max_loc 
       
    	
        # print(k)  ## 중간에 한 번 k값 확인(디버깅 용도)

        # target값 이진탐색을 위한 right, left 설정
        right = len(nums)-1
        left = 0
        
        # left가 right보다 작거나 같다면 계속 수행
        while left <= right:
            
            # cen(=center) 설정 후 -k로 현재 위치로 인코딩
            cen = left + (right-left)//2
            cen -= k    
            
            # target값과 nums[cen]을 비교
            # 같으면 현재 cen위치 반환
            # 이 때, 인덱스위치가 음수일 수 있기에 양수화 작업 후 반환
            # 위에서 cen -= k를 할 때, 음수가 나올 수 있음
            if nums[cen] == target:
                return (cen + len(nums)) % len(nums)
            
            # target 값보다 nums[cen]이 크면, right를 cen - 1로 바꾸고 초기 위치로 인코딩
            elif nums[cen] > target:
                right = cen -1
                right += k
            
            # target 값보다 nums[cen]이 크면 left를 cen + 1로 바꾸고 초기 위치로 인코딩
            else:
                left = cen + 1
                left += k
        return -1
            
```





