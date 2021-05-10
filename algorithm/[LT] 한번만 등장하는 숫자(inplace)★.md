# 136. Single Number

from: https://leetcode.com/problems/single-number/



## XOR의 천재적인 풀이

> XOR의 기능

두 수를 이진수로 바꿔서 둘 중 자리가 다른 수만 1로 계산.

1010과 1111을 XOR 연산하면 0101이 나온다.      



즉 배열에 있는 모든 숫자들을 XOR 연산한다는 것은 모든 숫자들을 이진수로 바꿔서

짝수번 등장하는 1은 0으로, 홀수번 등장하는 1은 1로 만든다는 것이다.

우리가 찾는 것은 1번만 등장하는 숫자이고

나머지 숫자는 모두 2번 등장하기에

2번 등장하는 숫자를 이진수로 바꾸었을 때 나오는 1들은 모두 2번 등장해서 XOR 연산의 결과 0이 될 것이다.

1번 등장하는 숫자만 XOR 연산의 결과로 남을 것이다. 



### 코드

```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        result = 0
        for num in nums:
            result = result^num
        return result
```







