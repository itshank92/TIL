# Best Time to Buy and Sell Stock II

from : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/



## 풀이

* 핵심: 문제에서 주어진 예시에서 주식을 사고 팔았던 날들의 공통 특징을 찾는다. (낮은곳>높은곳)
* 공통 특징을 적용해서 사고 판다.



```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        res = 0
        if len(prices) > 1:
            for idx in range(len(prices)-2,-1,-1):
                if prices[idx] < prices[idx+1]:
                    res += prices[idx+1] - prices[idx]
        return res
```

