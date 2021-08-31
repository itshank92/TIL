# 카드 앞뒤 제거해서 연속적인 최고 점수 만들기

from: 



> 풀이

**슬라이딩 윈도우로 풀이**

카드를 뽑는 것이 아닌 카드를 뽑고나서 남은 형태를 생각 ▶ 연속적인 카드 배열 형태 ▶ 슬라이딩 윈도우로 해당 배열의 합이 최소값이 되는 것을 찾는다.     



슬라이딩 윈도우의 크기는 양쪽에서 k개를 뽑은 나머지이기에 `전체길이 - k`가 된다.     



전체길이가 `n`일때, `t` 크기의 슬라이딩 윈도우는 총 `n-t+1`개가 존재한다.
(길이가 `8`인(마지막 인덱스는 `7`)  배열에서 `3`크기의 window는 총 `6`개 존재 → `0,1,2`부터 `5,6,7`까지 )     



따라서 슬라이딩 윈도우를 순회할 때, `전체 길이 - 슬라이딩 윈도우 크기 + 1`번 순회해줘야 한다.

하지만 순회에 들어가기 전, 슬라이딩 윈도우를 생성할 때 **초기값으로 최초의 슬라이딩 윈도우 값으로 설정**하는 것이 일반적이다.

따라서 순회전에 슬라이딩 윈도우 횟수가 `1`번 이미 계산되었기에 순회는 `전체 길이 - 슬라이딩 윈도우 크기` 만큼 순회한다.





> 코드

※ 해당 문제를 dfs로 풀면 안풀린다(시간초과)

```python
## 슬라이딩 윈도우 방식1
## 장점: 직관적, 단점: 마지막에 전체 합을 다시 구해줘야 한다.

class Solution:
    def maxScore(self, cardPoints: List[int], k: int) -> int:
        # ws: window size 
        ws = len(cardPoints) - k
        
        # 최초 window값으로 window 생성
        curr_sum = min_sum = sum(cardPoints[:ws])
        
        # window 크기는 ws이기에 '전체길이 - ws'만큼 순회해야 한다.
        for idx in range(len(cardPoints)-ws):
            curr_sum += cardPoints[idx+ws] - cardPoints[idx]
            min_sum = min(curr_sum,min_sum)
        return sum(cardPoints) - min_sum
            
```

​    

```python
## 슬라이딩 윈도우 방식2
## 장점: 전체 합을 쉽게 구할 수 있다. 단점: 덜 직관적
class Solution:
    def maxScore(self, cardPoints: List[int], k: int) -> int:
        # ws: window size 
        ws = len(cardPoints) - k
        curr_sum = min_sum = 0
        for idx, val in enumerate(cardPoints):
            # 무조건 현재 탐색하고 있는 값은 윈도우에 더해준다.
            curr_sum += val
            
            # 최초의 슬라이딩 윈도우 생성시
            if idx == ws-1:
                min_sum = curr_sum
                
            # 이후 슬라이딩 윈도우 크기 업데이트 후 비교
            if idx >= ws:
                curr_sum -= cardPoints[idx-ws]
                min_sum = min(min_sum, curr_sum)
        return sum(cardPoints) - min_sum
```

