# Rotate Array 189

from : https://leetcode.com/problems/rotate-array/



## 문제풀이 핵심

풀이 출처: https://leetcode.com/problems/rotate-array/discuss/54250/Easy-to-read-Java-solution

제자리정렬로 추가 메모리 사용없이 배열 회전시키는 문제



### 1번 제자리 정렬방식으로 배열 뒤집는 함수 구현

(start < end 조건 걸어놓고) 투포인터 방식으로 swap을 수행



### 2번 k번 회전한 것을 3번의 reverse로 구현할 수 있다는 것을 생각

1) 일단 전체 배열 reverse 수행

2) 처음부터 k-1번까지 reverse 수행

3) k번부터 끝까지 reverse 수행



### 3번 k가 배열보다 큰 경우 처리를 생각

처음부터 이러한 상황을 생각하면 좋았겠지만, 문제 풀이도 급급해서 마지막에 생각함

k가 배열과 길이가 같은 경우 배열은 변화가 없음

따라서 k를 배열의 길이로 나눈 나머지로 설정하면 됨



이를 수행 안하면 reverse 수행단계에서 k를 포인터중 하나로 사용하는데 이때 out of range가 발생함



## 실제 코드

```python
class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        # k가 nums의 길이보다 큰 경우를 처리하기 위해서 작업
        k = k%len(nums)
        
        # swap방식(in-place)으로 배열 역순 정렬하기
        def reverse(array,start,end):
            while start < end:
                array[start],array[end] = array[end],array[start]
                start += 1
                end -= 1
        
        # 3번의 reverse 수행
        reverse(nums,0,len(nums)-1)
        reverse(nums,0,k-1)
        reverse(nums,k,len(nums)-1)
```

