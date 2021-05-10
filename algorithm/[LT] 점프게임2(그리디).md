# 점프 게임2

FROM: https://leetcode.com/problems/jump-game-ii/



> 풀이

```python
class Solution:
    def jump(self, nums: List[int]) -> int:
        # base case
        if len(nums) <= 1:
            return 0
        j_cnt = 1
        idx = 0
        # 매번 탐색범위 설정
        # 탐색 범위내에서 가장 멀리갈 수 있는 좌표로 이동
        # 탐색 종료시 j_cnt += 1
        while True:
            s_range = nums[idx] + idx
            if s_range >= len(nums)-1:
                return j_cnt
            # 인덱스 0번: 가장 멀리갈 수 있는 범위, 시작 좌표 인덱스
            max_reach = [0,0]
            for n_idx in range(idx,s_range+1):
                if n_idx < len(nums) and nums[n_idx] + n_idx > max_reach[0]:
                    max_reach = [nums[n_idx] + n_idx, n_idx]
            idx = max_reach[1]
            j_cnt += 1
```



# ★★★

> Most Voted 풀이

* 필요 변수 생성

  * `Jump_cnt` = 점프 횟수
  * `Curend` = 현재 탐색 가능 범위 인덱스(초기값 0 )
  * `Curfarthest` = 이번 탐색 회차 중 발견한 가장 멀리 갈 수 있는 인덱스(초기값 0 ) 

* 풀이 과정

  0. `Curend`가 `len(nums)-1` 이상이 되면 종료

  1. for 문으로 전체 탐색

  2. 탐색하면서 `Curfarthest` 업데이트

  3. 탐색 중간에 해당 인덱스가 `Curend`가 되면 

     * 결과값(점프 횟수) += 1

     * `Curend`를 `Curfarthest`로 바꾸고 탐색 재개

```python
class Solution:
    def jump(self, nums: List[int]) -> int:
        Jump_cnt = Curend = Curfarthest = 0
        # 전체 for문 탐색
        for i in range(len(nums)):
            # 현재 탐색 가능 범위가 마지막 인덱스를 포함하는 경우 종료
            if Curend >= len(nums)-1:
                return Jump_cnt
            # 가장 멀리 갈 수 있는 인덱스 업데이트
            Curfarthest = max(Curfarthest,nums[i] + i)
            # 현재 회차의 탐색 범위 종료되었다면, Curend를 Curfarthest로 업데이트, 점프횟수 += 1
            if i == Curend:
                Curend = Curfarthest
                Jump_cnt += 1
```

