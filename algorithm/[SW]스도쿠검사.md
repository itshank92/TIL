스도쿠 검사



> 코드1

* 각 덩어리 영역의 중앙점을 기준으로 9방향순회(자기자신 포함)

```python
def Sudoku(arr):
    dx = [-1, -1, -1, 0, 0, 0, 1, 1, 1]
    dy = [-1, 0, 1, -1, 0, 1, -1, 0, 1]
    dia = [1, 4, 7]
    for i in range(9):
        # 가로 세로줄 검사
        # 1 ~ 9까지 모두 있는지 확인
        check_x = [0] * 9
        check_y = [0] * 9
        for j in range(9):
            check_x[arr[i][j]-1] += 1
            check_y[arr[j][i]-1] += 1
 
        for j in range(9):
            if check_x[j] == 0 or check_y[j] == 0:
                return 0
    # 정사각형 검사
    for i in dia:
        for j in dia:
            check_dia = [0] * 9
            for k in range(9):
                check_dia[arr[i + dx[k]][j + dy[k]] - 1] += 1
            for p in range(9):
                if check_dia[p] == 0:
                    return 0
    return 1
 
T = int(input())
for test in range(1, T+1):
    arr = [list(map(int, input().split())) for _ in range(9)]
    print(f'#{test}', Sudoku(arr))
```



> 코드2

* 행열확인, 덩어리도 인덱스로 확인

```python
def check(arr):
    # 행, 열 확인
    for i in range(9):
        count1 = [0] * 10
        count2 = [0] * 10
        for j in range(9):
            count1[arr[i][j]] += 1
            count2[arr[j][i]] += 1
 
        for k in range(1, 10):
            if count1[k] != 1 or count2[k] != 1:
                return 0
 
    # 3 x 3 확인
    for r in range(0, 9, 3):
        for c in range(0, 9, 3):
            count = [0] * 10
            for i in range(r, r+3):
                for j in range(c, c+3):
                    count[arr[i][j]] += 1
 
            for k in range(1, 10):
                if count[k] != 1:
                    return 0
 
    return 1
 
T = int(input())
for test_case in range(1, T + 1):
    arr = [list(map(int, input().split())) for _ in range(9)]
 
    print(f'#{test_case} {check(arr)}')

```



> 내 코드

```python
def check_s(s):
    #행과 열 확인
    for i in range(9):
        c_r , c_c = [0]*10 , [0]*10
        for j in range(9):
            if c_r[s[i][j]] == 1 or c_c[s[j][i]] == 1:
                return 0
            c_r[s[i][j]] , c_c[s[j][i]] = 1,1
            
    # 덩어리 확인 ★
    for h in range(3): # 행을 3 덩어리로 나눠 순회 
        for k in range(3): # 각 행 덩어리에 대해 열을 3등분으로 나눠 순회
            # 위의 h는 행을 3등분, k는 열을 3등분 ▶▶이제 아래는 하나의 덩어리(9개요소)가 된다.
            c_m = [0]*10
            for i in range((h*3),(h+1)*3): # 행 덩어리의 행들 순회
                for j in range((k*3),(k+1)*3): # 열 덩어리의 열을 순회
                    if c_m[s[i][j]] == 1:
                        return 0
                    c_m[s[i][j]] = 1
    return 1
```

