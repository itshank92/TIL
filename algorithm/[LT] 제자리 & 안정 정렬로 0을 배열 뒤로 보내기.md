# 제자리 & 안정 정렬로 0을 배열 뒤로 보내기 ★★

FROM: https://leetcode.com/problems/move-zeroes/

​     



< 다른 사람의 코드 찾아보기 현재 49% beat > 



> 풀이

* nums의 각 숫자를 순차적으로 순회하기 위한 포인터 idx 생성
* 자연수를 찾기 위한 포인터인 i_r을 생성
* idx로 순회하면서 0이 나오면 i_r을 사용해서 자연수를 찾음
  * 자연수를 찾으면 idx와 i_r의 요소를 swap
  * 못찾으면 모든 과정 종료
* idx와 i_r의 값을 +=1로 업데이트
  * 이 업데이트는 0이 나와서 위 과정을 모두 수행했거나, 정수가 나온 경우 수행한다.



핵심은 이미 idx가 지나친 정수는 바꿀 필요가 없다는 것. 

따라서 i_r도 계속해서 업데이트 해줄 수 있다. 





> 내 코드

```python
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        if len(nums) <= 1:
            return nums
        
        idx = 0
        i_r = 0
        
        while idx < len(nums):
            num = nums[idx]
            # 0 발견시 정수 찾아서 바꿔줌
            if num == 0:
                while i_r < len(nums):
                    i_num = nums[i_r]
                    if i_num == 0:
                        i_r += 1
                        continue
                    else:
                        break
                # break없이 끝남 == 자연수 못찾음
                else:
                    break
                nums[idx],nums[i_r] = nums[i_r],nums[idx]
            
            # 0발견 후 swap 끝났거나 자연수 발견시
            idx += 1
            i_r += 1
            
                
        return nums           
```

