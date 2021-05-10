## 5208. [파이썬 S/W 문제해결 구현] 5일차 - 전기버스2



* 파라매트릭 서치 방법

```python

def check(cnt):
    idx = 1
    while cnt >= 0 and idx < n:
        if dp[idx] == n:
            return True
        else:
            cnt -=1
            idx = dp[idx]
    if cnt < 0:
        return False
    else:
        return True




def parametric(mi,ma):
    while mi < ma:
        mid = mi + (ma-mi)//2
        if check(mid):
            ma = mid
        else:
            mi = mid + 1
    return mi




T = int(input())

for tc in range(1,T+1):
    bs = list(map(int,input().split()))
    n = bs[0]
    bs[0] = 0

    # 현재 위치에서 최대한 멀리 가기 위해 선택해야 하는 다음 위치
    # 단, 도착지에 바로 도착할 수 있는 경우, 도착지가 입력되어 있다.
    dp = [0] * n
    for idx,val in enumerate(bs):
        temp = idx
        if idx + val >= n:
            dp[idx] = n
            continue
        for i in range(1,val+1):
            if bs[idx+i] + idx+i > bs[temp] + temp:
                temp = idx+i
        dp[idx] = temp

    print(parametric(0,n-1))
```







* 더 간단한 dp방법

```python
T = int(input())

for tc in range(1,T+1):
    bs = list(map(int,input().split()))
    n = bs[0]
    bs[0] = 0
    dp = [0] * n
    for idx,val in enumerate(bs):
        temp = idx
        if idx + val >= n:
            dp[idx] = n
            continue
        for i in range(1,val+1):
            if bs[idx+i] + idx+i > bs[temp] + temp:
                temp = idx+i
        dp[idx] = temp

    cnt = 0
    idx = 1
    while dp[idx] != n:
        cnt += 1
        idx = dp[idx]

    print(f"#{tc} {cnt}")
```

