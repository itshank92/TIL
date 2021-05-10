# Brick Wall

from: https://leetcode.com/problems/brick-wall/



## 풀이 생각

각 라인의 벽돌 크기를 누적합을 적용시켜 공통된 위치값으로 바꾼다.

이 위치값은 선을 그을 수 있는 곳으로, 가장 많이 등장한 위치값으로 선을 그으면 된다.(counter만들어서 사용)

★ 누적합을 통해 공통된 위치값을 구해서 counter를 만들어야 하는 이유

→ 그냥 counter를 만들면 `[1,2,3]`의 `1`과 `[3,2,1]`의 `1`이 같은 위치가 아닌데도 불구하고 counter상에서 같이 카운트된다.



### 풀이

```python
from collections import defaultdict


class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        # 벽돌 끝나는 지점을 key로 해당 지점이 나온 횟수를 value로 기록할 counter
        counter = defaultdict(int)
        # 가장 많이 나온 지점은 인덱스 0번에, 해당 지점의 횟수를 인덱스 1번에 기록할 리스트
        max_vc = [0,0]
        
        # 각 벽을 돌면서 해당 벽돌의 크기를 누적합을 적용해서
        # 끝나는 지점의 인덱스를 찾고 counter에 업데이트 & max_vc와 비교를 해준다.
        for line in wall:
            accum = 0
            # 가장 마지막 요소의 w는 벽을 가리키게 된다.
            # 따라서 빼준다.
            # >> 이렇게 안하면 [[1],[1],[1]]의 경우 벽에 그어지는 선을 정답으로 반환한다.
            for w in line[:-1]:
                w = w + accum
                counter[w] += 1
                if counter[w] > max_vc[1]:
                    max_vc = [w,counter[w]]
                accum = w
        return len(wall)-max_vc[1]
```

