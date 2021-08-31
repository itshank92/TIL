# 3가지 수 더해서 0만드는 고유한 쌍 모두 찾기

from: https://leetcode.com/problems/3sum



### ★ 풀이 기본 ★

> 정렬 + 투포인터

- 정렬을 한 다음 앞에서부터 기준값을 잡고 나머지 영역에 대해서 left, right 포인터로 합을 구한다.
- 합의 결과에 따라 left나 right를 조정하거나 결과값을 담을 배열에 해당 조합을 추가한다.



> 주의사항

* 고유한 각 숫자는 중복해서 입력으로 주어질 수 있으나 중복해서 처리되면 안된다.
  * 즉, -1이 두 번 나오는 경우 `[-1,-1, ... ] ` 기준점이 되는 -1은 앞의 -1 한번만 수행되어야 한다.
  * -1을 기준으로 나올 수 있는 모든 조합은 앞의 -1이 기준점이 되는 경우가 뒤의 -1이 기준점이 되는 경우를 모두 **포함**하기 때문
* 하나의 기준점에 대해서 정답은 여러개일 수 있으니 정답이 나왔다고 해서 바로 다음 기준점으로 넘어가면 안된다. 
  * 예를 들면 `[-1,-1,0,1,2]`가 주어지고 가장 앞의 `-1`이 기준점일 때, `-1, -1, 2`와 `-1, 0, 1`이 모두 가능하다.
  * 따라서 정답을 얻은 다음에도 투포인터 탐색이 가능하다면 (불가능할 때까지) 계속 수행해야 한다.







```python
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        # 정렬
        nums.sort()
        # 결과값 담을 빈 리스트
        res = []
        
        
        for i in range(len(nums)-2):
            # 중요 코드(코드채 외우기) → 앞과 동일한 경우 있으면 pass
            if i > 0 and nums[i] == nums[i-1]:
                continue
            lp = i + 1
            rp = len(nums)-1
            while lp < rp:
                s = nums[i] + nums[lp] + nums[rp]
                if s > 0:
                    rp -= 1
                elif s < 0:
                    lp += 1
                else:
                    res.append([nums[i],nums[lp],nums[rp]])
                    # 위의 조합과 다른 조합을 찾기 위해
                    # 다른 값이 나올 때까지 포인터 계속 이동
                    while lp < rp and nums[lp] == nums[lp+1]:
                        lp += 1
                    while lp < rp and nums[rp] == nums[rp-1]:
                        rp -= 1
                    # 최종적으로 다른 값으로 포인터 이동
                    lp += 1
                    rp -= 1
        return res
```

