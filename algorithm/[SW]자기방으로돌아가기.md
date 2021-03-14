# 자기 방으로 돌아가기

* 출처: https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWNcJ2sapZMDFAV8&categoryId=AWNcJ2sapZMDFAV8&categoryType=CODE&problemTitle=%EB%8F%8C%EC%95%84&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1 
  (로그인을 하고 클릭해야 할 수도 있다.)

​     

* 문제의 기본 내용은 위 링크를 참고한다.    





> 생각

* **카운팅 배열**을 통해서 겹치는 구간을 카운트한다.
  * 카운팅 배열을 떠올릴 수 있어야 한다.
* 모든 방에 대해 카운팅 배열을 만드는 것이 아닌 **복도를 기준으로 카운팅 배열**을 만든다.
  * 방이 겹치는 것이 아닌 **복도**가 겹치는 것이다.
  * 복도를 기준으로 하면 카운팅 배열의 크기를 (반으로) 줄일 수 있다.





> 풀이 방법

1. **카운팅 배열**을 이용
2. **방의 범위를 반으로 줄일 수 있다**.
   * 겹치는 공간은 결국 방과 방 사이 **복도**다.
   * 방을 1차원 배열로 놓지 말고 **복도를 1차원 배열로** 만들자
     * 1,2번 방은 1번 복도로
     * 3,4번 방을 2번 복도로
     * 이러한 방식으로 카운팅 배열을 만들면 방의 갯수의 절반 크기로 구성할 수 있다. 



> 코드

```python
# 이동경로에 따라 겹치는 범위 조정(범위의 min,max로 바꿈)
def range_arrange(l):
    for idx in range(n):
        cr,dr = l[idx]
        # ★출발방보다 도착방의 번호가 작은 경우 → 스왑
        if cr > dr:
            cr,dr = dr,cr
        # ★ 출발방과 도착방을 짝수로 만들어준다.
        if cr%2 == 1:
            cr = cr + 1
        if dr%2 == 1:
            dr = dr + 1
        # ★ 짝수로 만든 방 번호에 //2를 해주면 복도 번호가 나온다.
        cr //=2
        dr //=2
        # 이렇게 바뀐 것을 다시 넣어준다.
        l[idx] = (cr,dr)
 
T = int(input())
for test_case in range(1, T + 1):
    n = int(input())
    l = [ list(map(int,input().split())) for _ in range(n) ]
    # 출발방과 도착방을 복도 번호로 바꿔준다.
    range_arrange(l)
    
    # 카운팅 배열(각 인덱스는 복도 번호를 나타냄)
    rooms= [0] * 201
    
    # 각 학생의 이동 경로를 순회하면서 경로에 포함된 복도에 +1을 해준다.
    for cr,dr in l:
        for j in range(cr,dr+1):
            rooms[j] += 1
    
    # 최대로 겹치는 복도의 값
    max_ = 0
    
    # 카운팅배열을 순회하며(각 복도를 순회하며) 가장 많은 경로가 지나가는 복도의 값을 얻는다.
    for room in rooms:
        if room > max_:
            max_ = room
    print(f"#{test_case} {max_}")
```





> ★코드 수정 _ 방의 번호를 복도 번호로 바꾸는 부분★

* 위에서 방의 번호를 복도 번호로 변환해주는 작업을 좀 더 단순화시킬 수 있는 코드가 있다.
* 현재 방의 번호에 따른 복도 번호는 다음과 같다.
  * 1번방, 2번방 → 1번복도
  * 3번방, 4번방 → 2번복도
  * ...

* 방은 순차적으로 2개씩 묶이는데 이들이 가진 공통점을 찾는 식을 바로 생각하기는 쉽지않다.
  * 공통점을 찾는 식을 통해 방의 번호를 바로 복도 번호로 바꾸려고 한다.
  * 예를들어 1과 2는 //2로 나눈 몫이 다르다.(3,4도 마찬가지)
  * 보통 (0,1), (2,3), (3,4)의 경우가 묶기 쉬운 경우다. ▶ 2로 나누면 몫이 공통점이 된다
* 원래 코드(위의 코드)는 방의 번호가 홀수인 경우 +1을 해줘서 복도 번호를 제대로 가리키게 했다.
  * 1번방의 경우 +1을 해서 2로 만든다.
  * 이를 복도번호를 구하는 식인 //2에 넣으면 제대로 된 복도번호(2번방과 같은 번호)가 나온다.
* 생각을 바꿔서 방의 번호를 쉽게 바꿔주자.
  * 1,2번 방을 0,1번 방으로 바꾸면 //2를 통해 둘 다 0번 복도를 가리키게 된다.
  * 3,4번 방을 2,3번 방으로 바꾸면 //2를 통해 둘 다 1번 복도를 가리키게 된다.
  * 따라서 방번호의 홀짝여부와 상관없이 -1을 해주면 된다.
* 위 방식을 통해 방번호를 변환하고 복도번호를 확보하는 경우, 복도 배열의 크기는 200이면 된다.
  * 0번 ~ 199번



> 위 방식으로 작성한 코드 

```python
def range_arrange(l):
    for idx in range(n):
        cr,dr = l[idx]
        # 출발방보다 도착방의 번호가 작은 경우
        if cr > dr:
            cr,dr = dr,cr
        # 홀짝 여부와 상관없이 방번호에서 -1을 뺀 다음, //2를 통해 복도번호를 알아낸다.
        cr = (cr-1)//2
        dr = (dr-1)//2
        l[idx] = (cr,dr)

T = int(input())
for test_case in range(1, T + 1):
    n = int(input())
    l = [ list(map(int,input().split())) for _ in range(n) ]
    range_arrange(l)
    # range_arrange를 통해 복도 번호를 0부터 199까지했기에 복도배열 크기는 200이면 된다.
    rooms= [0] * 200
    # max_를 미리 설정해서 복도에 경로 횟수를 입력할 때, 최대값을 확인하도록 했다.
    # => 따로 for문을 수행할 필요가 없어진다.
    max_ = 0
    for cr,dr in l:
        for j in range(cr,dr+1):
            rooms[j] += 1
            if rooms[j] > max_:
                max_ = rooms[j]
    print(f"#{test_case} {max_}")

```



