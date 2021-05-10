# 이진 힙

출처: 5177. [파이썬 S/W 문제해결 기본] 8일차 - 이진 힙



> 코드 생각

* 일단은 익숙하지 않은 자료구조를 구현하는 느낌(ADT)의 문제는 무조건 조건을 잘 읽고 그대로 구현하는 방식으로 푼다.
  * 이 문제 역시 처음에 값을 `insert`할 때, 가장 뒤에 넣어준다는 조건을 잘 읽고 그대로 하는게 중요했다.
* 트리를 배열로 바꿔주는 `Serialization`수행함에 있어서는 **인덱스를 활용하는 능력이 핵심**이다.



> 코드

```python
T = int(input())
for tc in range(1,T+1):
    n = int(input())
    nums = list(map(int,input().split()))
    res = [0]
    
    # 마지막에 넣고 부모보다 작다면 계속 교환 
    for num in nums:
        res.append(num)
        idx = len(res)-1
        while idx//2 > 0:
            if res[idx] < res[idx//2]:
                res[idx],res[idx // 2] = res[idx // 2],res[idx]
                idx = idx//2
            else:
                break
    
    # 마지막 인덱스에서 부모로 올라가면서 정답 업데이트
    ridx = (len(res)-1)
    total = 0
    while ridx//2 > 0:
        total += res[ridx//2]
        ridx = ridx//2
    print(f"#{tc} {total}")
```

