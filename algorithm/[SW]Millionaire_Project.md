# 백만장자 프로젝트

출처: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LrsUaDxcDFAXc&categoryId=AV5LrsUaDxcDFAXc&categoryType=CODE&problemTitle=%ED%94%84%EB%A1%9C&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=2    



> 생각

* 처음에는 왼쪽 오른쪽 포인터를 이용해서 탐색하는 방법을 사용했다.
* 포인터가 만나는 경우 탐색을 종료하고 이후 영역에 대해 재귀를 호출했다.
* 이렇게 하면 풀리긴 풀리는데 시간초과가 발생했다.



>  코드1_ 실패

* 왼쪽, 오른쪽 포인터로 접근하는 경우 시간 초과 실패

```python
r = "5902 5728 8537 3857 739 6918 9211 9679 8506 3340 6568 1868 16 7940 6263 4593 1449 6991 310 3355 7068 1431 8580 1757 9218 4934 4328 3676 9355 6221 9080 5922 1545 8511 4067 5467 8674 4691 6504 9835 2034 4965 9980 1221 5895 2501 8152 8325 7731 9302"
test = list(map(int,r.split()))

res = [0]
def searching(p_log):
  # 종료조건 (=탐색할 영역의 크기가 1이하인 경우)
  if len(p_log) <= 1:
    return

  # 왼쪽포인터와 오른쪽포인터
  l,r = 0,len(p_log)-1

  # 왼쪽 포인터가 탐색하면서 작은 값을 담을 공간
  temp = []
  
  # 왼쪽 포인터와 오른쪽 포인터가 만나기 전까지 계속 수행
  while l != r:
    # 만약 오른쪽 포인터의 값이 왼쪽보다 크다면
    # 왼쪽 포인터의 값을 temp에 담고 왼쪽포인터 +1
    if p_log[l] < p_log[r]:
      temp.append(p_log[l])
      l += 1
    # 만약 왼쪽 포인터의 값이 오른쪽보다 크다면
    # 오른쪽 포인터를 -1
    # 이때, 위의 if문과 아래 elif문을 둘 다 if문으로 하는 경우
    # left와 right가 서로 교차해서 지나갈 수 도 있으니 주의!(elif사용해야 한다)
    elif p_log[l] >= p_log[r]:
      r -= 1

  # temp에 있는 주식들을 팔아서 결과값에 차익 반영 
  for p in temp:
    res[0] += (p_log[r] - p)
  searching(p_log[r+1:])

searching(test)
print(res)
  
```





> 뒤에서부터 수행하는 방식

* 뒤에서부터 현재 max값보다 작은 값과의 차이를 결과값에 반영하며 탐색하는 방법은 통과
* 가장 뒤의 값을 max변수에 할당한 다음 뒤에서부터 앞으로 탐색한다.
  * 탐색하다가 더 큰 값이 나오면 max를 수정하고 탐색을 계속한다.
  * 탐색하다가 더 작은 값이 나오면 max와의 차이를 결과값에 반영한다.

```python
test = [3,5,9,3,5,8]
n = 6
# 뒤에서부터 탐색할 때 사용할 포인터
idx = n-1

# 가장 큰 값
max_p = 0

# 결과값
res = 0

# 가장 처음 주식가격까지 탐색할때까지 수행
while idx >= 0:
  # 만약 현재 탐색하는 것이 최대가격보다 큰 경우
  # 최대가격 업데이트
  if test[idx] > max_p:
    max_p = test[idx]
  
  # 현재 탐색하는 것이 최대가격보다 작거나 같은 경우
  # 최대값과의 차이를 결과에 더해줌
  else:
    res += (max_p - test[idx])
  idx -= 1


print(res)
```





* 뒤에서부터 탐색을 한다는 생각을 해보는 것이 중요하다.