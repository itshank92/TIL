# 시작점에서 점프하면서 도착지에 도달할 수 있을까

from: https://leetcode.com/explore/interview/card/top-interview-questions-medium/111/dynamic-programming/807/



## 문제 풀이 생각

        도착점에서부터 출발점으로의 탐색 수행
        
        탐색 과정은 목표지점으로 가는 가장 가까운 점프지점
        
        	* 찾으면 목표지점 = 점프지점으로 하고 탐색 계속 수행
        
        탐색은 OUT OF RANGE(탐색 위치가 0 미만)면 종료
        
        종료 시 
        
        	* 목표지점이 시작지점(인덱스0)이면 True
        
        	* 아니면 False
  

## 문제 풀이

```python
class Solution:
    def canJump(self, nums: List[int]) -> bool:
        destination = len(nums)-1
        for idx in range(len(nums)-1,-1,-1):
            if nums[idx] + idx >= destination:
                destination = idx
        if destination == 0:
            return True
        else:
            return False
```

