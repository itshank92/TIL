# Furthest Building You Can Reach

from: https://leetcode.com/problems/furthest-building-you-can-reach/





# 문제 풀이의 핵심

풀이: https://leetcode.com/problems/furthest-building-you-can-reach/discuss/918515/JavaC%2B%2BPython-Priority-Queue



* 가장 기본적인 접근자세는 bricks는 작은 높이 차이에, ladders는 큰 높이 차이에 사용하자라는 생각이다.

* ladders의 갯수 만큼의 높이 차이 갯수는 아무런 문제 없이 지나갈 수 있다.
  * 예를들어 ladders가 2개가 남았다면, 높이 차이 갯수가 2개까지는 아무런 문제없이 지나갈 수 있다.
  * 하지만 2개를 초과하는 순간부터는 bricks의 도움을 받아야 한다.
  * 2개를 초과했는데 bricks의 도움을 받지 못하는 경우, 순회중이던 index를 리턴하면 된다.

​      

​       

* **즉, 핵심은 ladders 갯수를 초과하는 높이 차이에 대해서 bricks를 사용하는 것이다.**
* **힙을 사용**해서 높이 차이를 보관하고 힙의 길이가 ladders의 갯수를 초과할 때 bricks를 사용한다.
* **사용 후 bricks가 음수가 되면 순회를 종료**한다.
* **순회가 모두 이상없이 끝났으면 마지막 인덱스를 반환**한다.

​     

​       

# 코드

```python
# 풀이의 핵심
# 1. 힙 사용해서 높이 차 보관
# 2. laaders의 갯수 초과시 heappop으로 나온 높이를 bricks로 매꾸기
# 3. ladders는 실제 사용하는 것이 아니라, 힙에 최소로 저장될 높이 차이 갯수를 정해주는 용도

import heapq

class Solution:
    def furthestBuilding(self, heights: List[int], bricks: int, ladders: int) -> int:
        heap = []
        for idx in range(len(heights)-1):
            d = heights[idx+1] - heights[idx]
            if d > 0:
                heapq.heappush(heap,d)
            if len(heap) > ladders:
                bricks -= heapq.heappop(heap)
            if bricks < 0:
                return idx
        return len(heights)-1
```

