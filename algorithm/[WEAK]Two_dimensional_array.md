2차원 배열을 이리자르고 저리자르는 서칭이 어렵다.



문제중

회문 (2차원 배열 탐색하는 능력 떨어짐)

글자수(dict에 get할 때 0이 오는 경우 처리하는 방법, 즉 0과 None 구분 방법)

> 회문

```python
## 선생코드

def my_find():
  # 전체 크기가 N
  for i in range(N):
    # 가로 검사
    for j in range(N-M+1):
      # 부분문자열을 위한 빈리스트
      tmp = []
      # 부분문자열 완성
      for k in range(M):
        tmp.append(words[i][j+k])  #가로문자열이니까 행고정
      
      # 회문검사
      if tmp == tmp[::-1]:
        return tmp

    #세로검사
    for j in range(N-M+1):
      tmp = []
      for k in range(M):
        tmp.append(words[j+k][i])  #세로문자열이니까 열고정
      
      if tmp == tmp[::-1]:
        return tmp
```





> 글자수

```python
T = int(input())
for test_case in range(1, T + 1):
    str_1 = input()
    str_2 = input()
    # str_1 사전 생성
    s1_dict = {}
    for char in str_1:
        s1_dict[char] = 0
    res = ["",0]
    #  str_2 카운팅
    for char in str_2:
        if s1_dict.get(char) != None:
            s1_dict[char] += 1
            if s1_dict[char] > res[1]:
                res = [char,s1_dict[char]]
    print(f"#{test_case} {res[1]}")
```

