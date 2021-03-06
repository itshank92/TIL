# 이진탐색

출처: *파이썬 알고리즘 인터뷰 515p ~*     



> 기본

* 이진 탐색은 **정렬된 배열**에 대해 **특정 값**을 **O(log n)** 시간내에 찾을 수 있는 탐색 방법이다.

* 이진 탐색의 작동 과정은 다음과 같다.

0. 탐색범위(`left` , `right`)가 유효할 때까지( `left <= right` ) 1번 ~ 4번과정을 반복한다.

1. 탐색 범위(`left` ,`right`)의 중간 값을 `mid`로 한다.
   * 이때 `mid`는 `(left+right)//2`로 구하는 것보다 `left + (right-left)//2`로 구하자.
   * 위 두 식의 결과값은 같지만, 앞의 식은 `left+right`라는 숫자를 메모리에 호출한 후 `//2`한다.
   * (파이썬은 아니지만) 자료형의 최대값이 정해진 언어의 경우, `left+right`가 최대값을 초과할 수 있다.
   * 따라서 `right` 내에서 연산이 수행되는 `left + (right-left)//2` 방식이 더 좋다. (*파알인 523p*)
2. `배열[mid]`가 `target`보다 큰 경우 ▶ (`target`은 `mid`의 왼쪽에 있으니) `right = mid-1`
3. `배열[mid]`가 `target`보다 작은 경우 ▶ (`target`은 `mid`의 오른쪽에 있으니) `left = mid+1`
4. `배열[mid]`가 `target`인 경우 ▶ `return mid`
5. 탐색범위가 유효하지 않은 경우(=이진탐색을 통해 `target`을 못찾은 것) `-1`을 반환한다.



> ★이진탐색에서 mid의 진정한 의미★

* 이진탐색의 `mid`의 진정한 의미는 **시작값에서 `mid`만큼 떨어진 값의 인덱스**를 의미한다.
* 예를 들면 `[1,3,5,7,9]`에서 최초 `mid`는 인덱스 `2`다.
* 이 `mid`의 의미는 시작값(`1`)로부터 `2`번째로 큰 수가 위치한 인덱스라는 뜻이다.(거리가 `2`칸 떨어진 수)
* 실제로 시작값(`1`)에서 `2`칸 떨어진 곳의 값은 `5`이고 `5`의 인덱스는 `2`다. (`mid`의 의미 확인 가능)
* 만약 `mid`가 `4`라면, 이 때 인덱스 `4`번에 해당하는 수는 `9`다.
* `9`는 시작값(`1`)로부터 `4`칸 떨어진 수로 `mid`와 일치한다.
* 다시 한번 강조하지만 `mid`는 **시작값에서 `mid`만큼 떨어진 값의 인덱스**를 의미한다.      



> 그렇다면 `mid`의 진정한 의미가 왜 중요한가?

* 일반적인 경우 시작값의 인덱스는 `0`이다. 
* 즉 일반적으로 정렬된 배열은 `[1,2,3,4,5,6]` 혹은 `[5,6,7,8,9]`와 같은 형태이다.
* 이 경우 이진탐색을 수행하면서 `mid`를 구하면 `mid`값 자체가 **시작값으로부터 mid만큼 떨어진 값의 인덱스**가 된다.
* 하지만 시작값의 인덱스가 `0`이 아닌 경우는 `mid`자체를 **시작값으로부터 `mid`만큼 떨어진 값의 인덱스**로 쓸 수 없다.
* 예를 들면 `[4,5,6,7,8,0,1,2]`와 같은 배열은 정렬되었지만 시작값(`0`)의 인덱스가 `0`이 아니다.(`5`다)
* 이 경우, `mid`가 시작값으로부터 `mid`만큼 떨어진 값의 인덱스가 되게 하려면 다음과 같은 식을 사용한다.
  * `(mid  + 시작값 인덱스)%배열의 길이`
  * 위 공식은 사실 시작값의 인덱스가 `0`인 경우에도 사용 가능하다. 
  * 다만 시작값의 인덱스가 `0`인 경우 위 공식의 결과값이 `mid`만 남기에 그냥 `mid`만 사용하는 것이다.

​    



* 이진 탐색은 **재귀**방식, **반복**방식으로 구현할 수 있다.     



> 코드 _ 재귀로 구현한 이진탐색

```python
# binary search using recursion
def bs_recur(arr,left,right):
    # 재귀는 1)종료조건을 명시하던지 2)수행할수 있는 조건을 명시해야 한다.
    # 여기서는 수행할 수 있는 조건을 명시했다.
    if left <= right:
        mid = left + (right-left)//2
        if arr[mid] > target:
            return bs_recur(arr,left,mid-1)  # 재귀니까 return 처리해야 하는 것(주의)
        elif arr[mid] < target:
        	return bs_recur(arr,mid+1,right) # 재귀니까 return 처리해야 하는 것(주의)
        else:
            return mid
    # 재귀수행이 불가능한 경우
    return -1


# target이 arr에 존재하는 경우 (arr에서 9의 인덱스 찾기(정답:4))
arr = [-1,0,3,5,9,12]
target = 9
print(bs_recur(arr,0,len(arr)-1))   #=>  4

# target이 arr에 존재하지 않는 경우 (arr에서 99의 인덱스 찾기(정답:-1))
arr = [-1,0,3,5,9,12]
target = 99
print(bs_recur(arr,0,len(arr)-1))   #=> -1
```

   





> 코드 _ 반복으로 구현한 이진탐색

```python
# binary search using loop
def bs_loop(arr,left,right):
    # 반복조건 
    while left <= right:
        mid = left + (right-left)//2
        if arr[mid] > target:
            right = mid-1
        elif arr[mid] < target:
            left = mid + 1
        else:
            return mid
    # 탐색 실패시(target 못찾은 경우)
    return -1

# target이 arr에 존재하는 경우 (arr에서 9의 인덱스 찾기(정답:4))
arr = [-1,0,3,5,9,12]
target = 9
print(bs_loop(arr,0,len(arr)-1))   #=>  4

# target이 arr에 존재하지 않는 경우 (arr에서 99의 인덱스 찾기(정답:-1))
arr = [-1,0,3,5,9,12]
target = 99
print(bs_loop(arr,0,len(arr)-1))   #=> -1
```

​    



> ★(정렬된 배열에서) 인덱스를 사용하여 특정 값이 시작값으로부터 얼마나 떨어져있는지 알 수 있는 방법★

* 일단 **특정값이 시작값으로부터 얼마나 떨어져 있는지**에 대한 정의는 아래 예시를 통해 설명한다.
  * 정렬된 배열:  `[1,3,4,5,6,7,9]`
  * 시작값: `1` (정렬된 배열에서 가장 작은 값) 
  * `5`는 시작값(`1`)에서 3칸 떨어져 있다. (`1 → 3 → 4 → 5`)    



* 정렬된 배열에서 특정 값이 시작값으로부터 얼만큼 떨어져 있는지를 알기 위한 식은 아래와 같다.
  * **`(특정값 인덱스 + 시작값 인덱스) % 배열 길이`**    



* (정렬된 배열에서) **시작값(가장 작은 값)의 인덱스가 `0`인 경우 (일반적인 경우)** 
  * 정렬된 배열:  `[1,3,4,5,6,7,9]`   (길이:7)
  * 시작값: `1` & 시작값의 인덱스: `0`
  * 숫자 `4`의 인덱스는 `2`  
  * 위 공식을 사용해서 계산하면 `(2+0)%7` → `2`  ▶ 따라서 `4`는 시작값(`1`)으로부터 **2칸** 떨어져 있음    



* (정렬된 배열에서) 시작값(가장 작은 값)의 인덱스가 `0`이 아닌 경우

  * 정렬된 배열: `[4,5,6,7,8,0,1,3]`   (길이:8)

  * 시작값: `0`     &   시작값의 인덱스: `5`

  * 숫자 `4`의 인덱스는 `0`

  * 위 공식을 사용하여 계산하면 `(0+5)%8` → `5` ▶ 따라서 `4`는 시작값(`0`)으로부터 **5칸** 떨어져 있음

    

* 즉, 정렬된 배열이라면, **시작값의 위치를 통해 특정 값이 시작값으로부터 얼만큼 떨어져 있는지** 알 수 있다. 

* 그렇다면 **시작값으로부터 얼만큼 떨어져 있는지**가 왜 중요할까?

* 이진탐색에서  `mid`로 접근하는 값은 결국 **시작값으로부터 `mid`칸 만큼 떨어져 있는 값**이다.

  * 접근결과에 따라 탐색범위를 조정하여 시작값으로부터 안쪽 절반으로 갈지, 바깥쪽 절반으로 갈지 선택

* 일반적인 경우 시작값 인덱스가 `0`이니까, 시작값을 고려하지 않고 단순히 `mid`만으로 이진탐색이 가능했다.

* 하지만 시작값의 인덱스가 `0`이 아닌 경우, 시작값의 인덱스를 사용해서 탐색범위의 중간 값을 찾아야 한다.

* 따라서 이때는 `(탐색범위의 mid + 시작값 인덱스)%배열의 길이`를 통해 시작값으로부터 `mid`만큼 떨어진 값을 탐색한다. 



**위 내용이 이해가 어렵다면 파알인 525p의 66번 문제를 풀고 필기를 보자**