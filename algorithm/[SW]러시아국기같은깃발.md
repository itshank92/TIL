# 러시아국기같은깃발





> 누적합으로 풀기

```PYTHON
# 누적합으로 풀기

T = int(input())
for tc in range(1,T+1):
    n,m = map(int,input().split())
    flag = [list(input()) for _ in range(n)]
    # 누적합 기록할 배열(초기값은 m)
    # 각 색깔별로 바꿔야 하는 횟수값을 구하고 이후에 누적합으로 바꿈
    cum_sum = [[m]*n for _ in range(3)] # 0,1,2번째 줄 각각 white,blue,red의 누적합 의미
    c_to_i = {'W':0,'B':1,'R':2}
    
    for i in range(n):
        for j in range(m):
            # 문자를 인덱스로 바꿈
            idx = c_to_i[flag[i][j]]
            cum_sum[idx][i] -= 1
        # 바꿔야하는 최소 횟수에서 누적합으로 만들기
        if i != 0:
            for k in range(3):
                cum_sum[k][i] += cum_sum[k][i-1] 

    min_cnt = 99999999
    for white_range in range(n-2):
        for blue_range in range(white_range+1,n-1): ## 이런 범위 설정 -> j를 바로 사용할 수 있다
            w_change = cum_sum[0][white_range]
            b_change = cum_sum[1][blue_range] - cum_sum[1][white_range]
            r_change = cum_sum[2][n-1] - cum_sum[2][blue_range]
            sum_change = w_change + b_change + r_change
            if min_cnt > sum_change:
                min_cnt = sum_change
    print(f"#{tc} {min_cnt}")
```

