### 베이비 진

​    



> 문제

- 각 카드는 0에서 9까지의 숫자가 쓰여져 있다.

- 6장의 카드를 받는다.

- 임의의 카드 6장 중, 3장의 카드가 연속적인 번호를 갖는 경우를 run이라 하며, 3장의 카드가 동일한 번호를 갖는 경우를 triplet 이라고 한다.

- 6장의 카드가 run과 triplet로만 구성된 경우를 baby-gin으로 부른다.

- 901은 run이 아니다.    

  

> 입력과 출력 예시 

- 556566은 두 개의 triplet이므로 baby-gin이다. (555,666)
- 162636은 한 개의 run과 한 개의 triplet이므로 역시 baby-gin이다. (123, 666)
- 101123은 한 개의 triplet가 존재하나, 023이 run이 아니므로 baby-gin이 아니다.
- (123을 run으로 사용하더라도 011이 run이나 triplet가 아님)



> 코드_ 완전탐색

```python
input_list = [1,0,1,1,2,3]
already_used = [0,0,0,0,0,0]

def check_triplet(a,b,c):
    if a == b and b == c:
        return True
    return False


def check_run(a,b,c):
    temp = [a,b,c]
    temp.sort()
    if temp[0]+1 == temp[1] and temp[1]+1 == temp[2]:
        return True
    return False

for i in range(len(input_list)-2):
    if already_used[i]:
        continue
    for j in range(i+1,len(input_list)-1):
        ## 아래 if 조건이 잘못됨(And가 아닌 or을 사용했어야 함)
        if already_used[i] and already_used[j]:
            continue
        for k in range(j+1,len(input_list)):
            ## 아래 if 조건이 잘못됨(And가 아닌 or을 사용했어야 함)
            if already_used[i] and already_used[j] and already_used[k]:
                continue
            a,b,c = input_list[i],input_list[j],input_list[k]
            # 위 if 조건문들이 or로 잘 작성되었었다면, 아래 if 조건은 필요가 없음
            if not already_used[i] and not already_used[j] and not already_used[k]:
                if check_run(a,b,c) or check_triplet(a,b,c):
                    already_used[i] = 1
                    already_used[j] = 1
                    already_used[k] = 1
                if sum(already_used) == 6:
                    print("done")
```

​    

> 코드 설명

* 완전탐색으로 풀이하였다.
* 각각의 for문에서 already_used를 사용해서 이미 사용한 것들을 체크했는데 그래도 부족해서 마지막에 `check_fun or check_triplet`을 하기전에 또 조건을 넣어주었다.
* **★★★ 부족했던 이유 ★★★**
  * and로 연결하였기에 이미 사용한 것을 `1`로 바꿔도 하나라도 사용 안한게 있으면 `True`가 아닌 `False`가 나온다.
  * if문을 통해 원했던 것은 `i,j,k` 중하나라도 사용한 적이 있다면, 해당 케이스는 건너 뛰는(`continue`) 것인데 `and`로 연결하면 [사용, 사용, 비사용]인 경우 False가 떠서 continue가 실행이 안된다.
  * 따라서 [사용,사용,비사용]인 경우 True가 떠서 continue가 동작하게하려면 `or`를 사용했어야 했다.

* 기본적으로 하나의 리스트는 하나의 경우밖에 없기에 위의 풀이가 가능했다.
  * 예를들면 triplet두개도 되고 triplet하나 run 하나도 되는 경우는 없다.

​     



> 코드_ counting sort사용

```python
## ★★★★ run을 확인하는 부분이 틀렸다. ★★★★

from _collections import defaultdict

input_list = [1,1,1,1,2,3]
count_list = [0] * 10

## 0을 default값으로 하는 defaultdict 생성
res = defaultdict(int)


# input_list의 값을 인덱스로 사용해서 count_list를 업데이트
for num in input_list:
    count_list[num] += 1
    # 업데이트 도중, triplet 발견되면 카운트 해주고 값을 빼줌
    if count_list[num] == 3:
        res["triplet"] += 1
        count_list[num] = 0

# 0부터9까지 순회하면서 count_list 값이 1이면 앞의 두 값이 1인지 비교한다.
for idx in range(10):
    ## ★★ 여기가 틀렸다 ★★ triplet이 하나도 없고 똑같은 run이 2개인 경우를 확인 못한다.
    if count_list[idx] == 1:
        if count_list[idx+1] == 1 and count_list[idx+2] == 1:
            res["run"] += 1
        # 1이 등장했을 때 앞의 두 값이 1이든 아니든 무조건 break
        break
        
print(res)
```

​     



> 코드 설명

* counting sort를 사용해서 풀면 쉽게 풀 수 있다고 떠올릴 수 있는게 중요하다.
* input_list를 돌면서 count_list를 업데이트 할 때, triplet만 확인하고 run은 확인안한 이유는, 너무 경우의 수가 많기 때문이다. 
  * `[1,1,1,1,2,3]`의 경우 마지막에 `3`이 입력됐을 때 run이 확인되어야 하는데, 이것을 확인하려면 `3-1(=2)`, `3-2(=1)`의 위치를 확인해야 한다. 
  * `3`을 중심으로 수많은 확인 경우의 수가 존재하기에 이를 다 써 줄 수 없다.
  * `%10`을 사용해서 앞으로 돌아가는 것도 여기서는 적절한 방법이 아니다.

* 따라서 run을 확인하기 위해서 다시 `for`문을 순회한다.

  * 앞의 두 개를 확인하는게 무조건 옳은 이유는 0부터 앞으로 가면서 확인하기 때문이다.

  * 8:1 , 9:2의 경우 오류가 나지 않을까라는 생각을 할 수 있지만, 어짜피 `and`로 앞부터 확인하기 때문에 10 위치(out of range Error)를 확인할 일은 없다.(에러 발생 안함)

  * triplet은 앞에서 모두 판별했기에 남은 경우의 수는 1,1,1인 run밖에 없다.

    ★ **틀림 >>>> run이 2개인 경우 커버 못함**





> 수정한 코드

```python
from _collections import defaultdict

input_list = [1,1,1,1,2,3]
count_list = [0] * 10

## 0을 default값으로 하는 defaultdict 생성
res = defaultdict(int)


# input_list의 값을 인덱스로 사용해서 count_list를 업데이트
for num in input_list:
    count_list[num] += 1
    # 업데이트 도중, triplet 발견되면 카운트 해주고 값을 빼줌
    if count_list[num] == 3:
        res["triplet"] += 1
        count_list[num] = 0

# 0부터9까지 순회하면서 count_list 값이 1이면 앞의 두 값이 1인지 비교한다.
for idx in range(8):
    ## ★★ triplet이 하나도 없고 똑같은 run이 2개인 경우를 확인할 수 있도록 확인
    while count_list[idx] >= 1:
        if count_list[idx+1] >= 1 and count_list[idx+2] >= 1:
            count_list[idx] -=1
            count_list[idx+1] -=1
            count_list[idx+2] -=1
            res["run"] += 1
        else:
            break

print(res)
```

​     

> 코드 설명

* `for idx in range()`부분에서 8까지만 순회한다(triplet은 앞에서 모두 조회했고 run은 8까지만 조회해도 나온다.)

* `while`문을 사용해서 해당 숫자의 counting 수가 1 이상인 경우 계속 run을 확인한다.

  * `else:`를 사용해서 1이상인데 run이 아닌 경우 바로 끝낸다.

  