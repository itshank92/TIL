## 가장 빠른 문자열 타이핑

​      

> 문제

문자열 A를 타이핑할 때 여러 문자를 한번에 입력할 수 있는 키 B가 주어진다. 

이때 B를 활용하여 A를 타이핑할 때, 타이핑의 최소 횟수를 반환하라.

단, 한번 입력한 문자열을 지울 수는 없다.     



> 입력과 출력

```
INPUT: "banana bana"

OUTPUT: 3

Explanation
▶ 'banana' includes 'bana' once. It means you can type 'bana' just by one typing. Rest of part is 'na' and you need to type one by one. So number of total typeing is three.
```

```
INPUT: "asakusa sa"

OUTPUT: 5
```

​     



> 나의 코드 _ KMP 사용

```PYTHON
# 실패배열 만들기(각문자 기준 이전에 접두사와 접미사가 일치하는 최장영역)
def making_flist(ps):
  ps_len = len(ps)
  flist = [0] * ps_len
  begin = 1
  pointer = 0
  while begin+pointer < ps_len:
    if ps[begin+pointer] == ps[pointer]:
      pointer += 1
      flist[begin+pointer-1] = pointer
    else:
      if pointer == 0:
        begin += 1
      else:
        begin += pointer - flist[pointer-1]
        pointer = flist[pointer-1]
  return flist

# 패턴매칭 수행 
def pattern_matching(ts,ps):
  ts_len = len(ts)
  ps_len = len(ps)
  flist = making_flist(ps)
  start = 0
  pointer = 0
  # ts에 존재하는 ps갯수 담는 변수
  cnt = 0
  while start+pointer < ts_len:
    if ts[start+pointer] == ps[pointer]:
      pointer += 1
      # ps가 완전히 탐색된 경우 cnt +1
      if pointer == ps_len:
        cnt += 1
        # start위치를 현재 start위치 + 1로 옮긴다
        start += pointer
        # pointer를 0으로 초기화한다.
        pointer = 0
    else:
      if pointer == 0:
        start += 1
      else:
        start += pointer - flist[pointer-1]
        pointer = flist[pointer-1]
  
  # ts의 길이(원래 타이핑수) - (ps갯수*ps길이) + ps갯수
  return ts_len - (cnt*ps_len) + cnt
```

* KMP관련 자세한 내용은 `KMP_algorithm.md` 문서 참고 
* 일반 KMP와 다른 부분을 위에서 주석으로 설명했다.

​    



> ㄱㅇ님코드 _ 직관적

```python
# 입력값 받음(A는 ts, B는 ps)
A, B = input().split()
  
# 길이
N = len(A)
M = len(B)

cnt = 0 # while문의 반복 횟수를 센다.
i = 0   # ts탐색의 시작위치를 가리키는 인덱스

# ts탐색의 시작위치가 N-M이내(탐색영역에 ps존재가능)까지 수행
while i <= N - M:
    cnt += 1 
    # ps를 시작부터 끝까지 순회(j를 ps포인터로 사용)
    for j in range(M):
        # ts와 ps를 하나하나씩 비교 시작
        if A[i+j] != B[j]:
            # 다른 경우 시작지점 +1해주고 break
            i += 1
            break

    else: #break없는 경우(=ts탐색영역에서 ps발견한 경우)
        i += M # 현재 ts탐색 시작위치에 B의 길이만큼 더해서 탐색 시작위치 변경


# ★ts길이(원래입력해야하는 횟수) - ts탐색시작인덱스 + while문 반복횟수
cnt += N - i

print(f'#{test_case} {cnt}')
```

* ★ **ts길이(원래입력해야하는 횟수) - ts탐색시작인덱스 + while문 반복횟수**
  * 이 부분이 이해가 안된다.
  * 결과값을 `ts전체 타이핑 횟수 - 발견된 ps 갯수 * ps길이 + 발견된 ps 갯수`로 작성하면 이해가 쉬울 것 같다.
    * ts 전체 타이핑 횟수  = ts 길이 (`N`)
    * 발견된 ps 갯수 ▶ 기존의 `cnt` 
  * 
  * 좀 더 직관적으로 바꿔보자 



> ㅇㅎ님코드 > 브루트 포스 과정이 직관적으로 이해됨

```PYTHON
N = len(A)
M = len(B)

# i는 A 문자열 중 체크하려는 인덱스 , answer는 타이핑 횟수를 의미.
i, answer = 0, 0

while i < N: 
    # i 부터 M개의 글자가 B와 일치한다면,
    # ... 한 번만 더 누르면 되고, i 인덱스는 M만큼 shift
    if A[i: i+M] == B:
        answer += 1
        i += M
    else:
        i += 1
        answer += 1
 
print(f'#{tc} {answer}')
```







> 생각

