# Permutation2



#### 순열 조합은 출력해서 붙여놓자

더 이상 고통받지말자



#### 1부터 n까지의 요소에서 k개의 순열을 사전순으로 출력하는 함수 

> 입력과 출력값

```
[input]
3 1

[output]
1
2
3
```

```
[input]
4 2

[output]
1 2
1 3
1 4
2 1
2 3
2 4
3 1
3 2
3 4
4 1
4 2
4 3
```



> 코드

```python
def dfs(res,k):
    #1 길이 만족하면 출력
    if len(res) == k:
        print(*res)
        return
    
    #2 전체를 순회하면서 넣지 않은 경우 넣어줌
    for i in range(n):
      if i+1 not in res:
        dfs(res + [i+1],k)

        
dfs([],k)
```



* 주의: 넣지 않는 경우를 `dfs(res,k)`로 작성하면 무한 재귀에 빠진다.(넣지 않는 경우는 끝나지 않는다)
  * 넣는 경우만 작성해도 모든 순서의 순열이 만들어진다.
  * 단, 매번 모든 인자를 순회하기에(`for i in range(n)`) 전에 넣었던 것을 확인하는 절차가 필수적이다.





#### 중복 허용 순열 구하기 

> 입력과 출력

```
input
4 2

output
1 1
1 2
1 3
1 4
2 1
2 2
2 3
2 4
3 1
3 2
3 3
3 4
4 1
4 2
4 3
4 4
```







> 코드

```python
def dfs(res,k):
    if len(res) == k:
        print(*res)
        return
   	# 중복 허용이기에 모든 n에 대해서 순회
    for i in range(n):
        dfs(res + [i+1],k)

        
dfs([],k)      
```







#### 중복허용 조합

> 입력과 출력

```
input
4 2

output
1 1
1 2
1 3
1 4
2 2
2 3
2 4
3 3
3 4
4 4
```



```
input
3 3

output
1 1 1
1 1 2
1 1 3
1 2 2
1 2 3
1 3 3
2 2 2
2 2 3
2 3 3
3 3 3
```

​    



> 코드

```python
def dfs(last,res,k):
    if len(res) == k:
        print(*res)
        return
    # 앞으로 들어올 수 있는 것은 이전 값과 같거나 큰것
    for i in range(n):
        if i >= last:
            dfs(i,res + [i+1],k) #★주의: i+1로 넘어가는게 아니다, 아직 기준은 i

        
dfs(0,[],k)

```







> Counter 형식으로 주어진 배열을 가지고 순열구하기



* 상황: 요소들의 갯수 정보가 주어질 때 이를 사용해 만들 수 있는 모든 순열을 구하라
  * 이때 각 요소들은 각각의 인덱스로 구분된다.
  * 예시: `[1,3,5,0]` ▶ 0번 요소: 1개,  1번 요소: 3개,  2번 요소: 5개,  3번 요소: 0개



* 생각
  * 원래는 각 요소들을 하나의 배열로 만들어서 순열을 구하려고 생각했다.
    * `[0,1,1,1,2,2,2,2,2]` 이러한 형태
  * 하지만 굳이 새로운 배열을 만들지 않고 푸는 방법을 찾았다.     



* 코드

  ```python
  def make_pm(arr,res):
      # 종료조건
      if len(res) == 전체요소갯수:
          return 
      
      # 각 인덱스별로 순회
      for idx in range(len(arr)):
          # 요소의 원래 횟수 기록 용도
          c = arr[idx]
          # 요소가 다 떨어질 때까지 순회
          while arr[idx]:
              arr[idx] -= 1
              make_pm(arr, res + [idx])
          # 순회끝났으면 복원
          arr[idx] = c
  ```

  

* 코드 설명
  * 위 코드의 핵심은 사용한 요소 상태를 arr에 반영하여 계속해서 재귀(`make_pm`)에 넘겨주는 방식이다.
  * 사용한 요소에 대한 복원 역시 중요하다.

