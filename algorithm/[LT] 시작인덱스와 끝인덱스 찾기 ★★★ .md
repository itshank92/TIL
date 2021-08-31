## 오름차순 배열에서 target 숫자에 대한 시작 위치와 끝 위치 찾기

from: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

​     

​     



## 문제 풀이

* `n log(n)` 시간의 제약 조건 존재 ▶ 이진 탐색 수행
  * 이진탐색은 `low`, `high`를 매개변수로 받는 다는 것을 명심
    

* 최초의 이진탐색 수행 ▶ 위치값 반환
  * 만약 존재하지 않는 경우 `-1`을 반환
    
* 위치값을 기준으로 왼쪽 영역과 오른쪽 영역에 대해서 최소 인덱스와 최대 인덱스 찾기 수행
  * 왼쪽영역의 경우
    * 왼쪽영역의 경우 계속 binary search를 통해 `target`을 발견하면서 그 위치를 기준으로 다시 왼쪽 영역에 대해 binary search를 수행하는 방식으로 처리 ▶`target`의 가장 왼쪽 인덱스를 찾기 위함
    * binary search의 `결과값`을 다음번의 binary search의 `high`에 넣는 방식으로 수행
      * 정확히 말하면 결과값이 아닌 `결과값 -1`을 다음번 binary search의 `high`에 넣음
    * `결과값`이 `-1`이 나오기 전까지 계속해서 변수 `low_index`의 값을 `결과값`으로 업데이트
    * `결과값`이 `-1`이 나오는 경우 종료(break)
  * 오른쪽 영역의 경우
    * 오른쪽영역의 경우 계속 binary search를 통해 `target`을 발견하면서 그 위치를 기준으로 다시 오른쪽 영역에 대해 binary search를 수행하는 방식으로 처리 ▶`target`의 가장 오른쪽 인덱스를 찾기 위함
    * binary search의 `결과값`을 다음번의 binary search의 `low`에 넣는 방식으로 수행
      * 정확히 말하면 `결과값`이 아닌 `결과값 +1`을 다음번 binary search의 `low`에 넣음
    * `결과값`이 `-1`이 나오기 전까지 계속해서 변수 `high_index`의 값을 `결과값`으로 업데이트
    * `결과값`이 `-1`이 나오는 경우 종료(break)







## 코드

```python
class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        """
        binary search 수행
        발견시 해당 지점을 기준으로 양 옆의 영역에 대해 binary search 수행
        왼쪽 영역은 가장 작은 인덱스 발견 탐색, 오른쪽 영역은 가장 큰 인덱스 발견 탐색
        """
        def binary_search(nums,target,low,high):
            while high >= low:
                mid = low + (high-low)//2
                if nums[mid] > target:
                    high = mid - 1
                elif nums[mid] < target:
                    low = mid + 1
                else:
                    return mid
            # 발견하지 못하는 경우 -1을 반환
            return -1
        
        
        ## pi는 최초 이진탐색의 결과
        pi = binary_search(nums,target,0,len(nums)-1)
		## ★★★ low_idx와 high_idx를 pi로 초기값 설정 ★★★★
        low_idx = pi
        high_idx = pi
        
        # (pi!= 1) == (값이 존재하는 경우)
        if pi != -1:
            # temp의 초기값을 pi로 설정(이를 통해 아래 binary search의 매개변수로 temp 계속 사용가능) ★★★
            temp = pi
            while True:
                temp = binary_search(nums,target,0,temp-1)
                if temp != -1:
                    low_idx = temp
                else:
                    break
            temp = pi
            while True:
                temp = binary_search(nums,target,temp+1,len(nums)-1)
                if temp != -1:
                    high_idx = temp
                else:
                    break
        
        return low_idx,high_idx
                
        
```

