> ㅅㅈ님 코드

```python
# 가로 회문 체크 함수
def pelin_h(str_map, r, c, M):
    for i in range(M//2):
        if str_map[r][c+i] != str_map[r][c+M-1-i]:
            return False
    else:
        return True
 
# 세로 회문 체크 함수
def pelin_v(str_map, r, c, M):
    for i in range(M // 2):
        if str_map[r+i][c] != str_map[r+M-1-i][c]:
            return False
    else:
        return True
 
for tc in range(1, 11):
    T = int(input())
 
    # String은 배열처럼 인덱스로 접근할 수 있기 때문에 굳이 다시 char로 쪼개서 2차원 배열을 만들지 않고 넣어줌
    str_map = [input() for _ in range(100)]
 
    ans = 0
    for M in range(100, 0, -1):
        for i in range(100):
            for j in range(100-M+1):
                if pelin_h(str_map, i, j, M) or pelin_v(str_map, j, i, M):
                    ans = M
                    break
            if ans: break
        if ans: break
 
    print(f'#{tc} {ans}')
```



> 코드 이해

* 최장 팰린드롬을 찾는 문제이기에 탐색하려는 문자열의 길이는 최장 길이부터 -1씩 줄여간다(M)
* 가로문자열과 세로문자열의 길을 찾는 함수를 만든다.
* 탐색하려는 길이 M을 사용하여 양쪽 끝을 확인하는 형태로 함수가 작동한다.

* 만약 팰린드롬을 찾으면 그길로 바로 모든 탐색을 종료한다.
  * 최장길이부터 -1씩 줄여가면 탐색하였기에 가능 





> 선생코드_좀더 직관적

```python
## 회문2 선생코드

def my_find(M):
  for i in range(N):
    for j in range(N-M+1):
      # 가로검사 
      for k in range(M//2):
        if words[i][j+k] != words[i][j+M-1-k]:
          break 
        elif k == M//2-1:
          return M
        
      # 세로검사
      for k in range(M//2):
        if words[j+k][i] != words[j+M-1-k]:
          break
        elif k == M//2-1:
          return M
        
N = 100

words = [input() for i in range(N)]

for i in range(N,1,-1):
  ans = my_find(i)
  if ans != 0:
    break
```

