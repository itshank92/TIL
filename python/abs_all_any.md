### abs, all, any 함수 직접 구현해보기



> #### abs 직접 구현하기



1. **복소수의 표현**

   * `실수부 + 허수부j ` 형태로 표현되고 자료형의 명칭은 `complex` 다.

   * `복소수.real ` 은 실수부를, `복소수.imag `는 허수부를 의미한다.

   * `복소수` 를 실수로 만들려면 `(실수부**2 + 허수부**2)**(1/2)` 를 해줘야 한다. 

     * 식의 구성상 양수만 나오게 된다. 

       

2. **자료형 확인 함수를 사용하여 자료형 확인하기**

   * `type(변수)`를 통해 해당 자료형을 할 수 있다. 

     * `if type(변수) == str  #변수가 문자열인지 확인`
     * `if type(변수) == int #변수가 정수인지 확인`
     * `if type(변수) == complex # 변수가 복소수인지 확인`

   * 또다른 확인 방법 `isinstance` 함수 사용하기

     * `if isinstance(<변수>,<자료형 명칭>): `
* `<자료형 명칭>`이라는 정의로부터 해당 `<변수>`가 생성(선언)되었는지 확인하는 함수
     * 간단히 말해서 `if type(<변수>) == <자료형 명칭>:` 과 같다. 
     

3. **함수가 인자로 입력값을 받는 것과 `input()` 함수로 입력값을 받는 것을 착각하지 말자.**
   * 함수는 인자로 다양한 자료형을 있는 그대로 받을 수 있다.
     * 예를들면 `function(3+2j)` 에서 `function`은 `3+2j`라는 복소수를 있는 그대로 받는다.
   * 하지만 `input()`의 경우 무조건 `str` 형태로 받는다.
     * 예를들면 `a = input()`에서 `2+3j`를 입력했다면, `a`는 `'2+3j'`라는 `str`이 된다.



> #### all 직접 구현하기



1. `all 함수`: 모든 요소가 참이거나 요소 전체가 비어있으면 `True` 반환



2. `list`가 입력값이고 코드에 `for loop`가 있을 때, 해당 `list`가 `empty`라면 `for loop`는 수행되지 않는다. 

   → 해당 코드를 건너 뛰게 된다. 

```python
def my_all(elements):
    # elements가 empty라면 for loop는 수행되지 않을 것이다 → for loop 코드 건너 뜀
    for i in elements:  
        if not i:
            return False
    return True
```



>  #### any 직접 구현하기

1. any : 요소중 하나라고 참이면 True, 비어있으면 False

```python
def my_any(elements):
    for element in elements:
        if bool(element) == True:
            return True            
    return False
```



2. ` i==True`로 하면 안된다
   * 값비교가 아닌 해당 값이 `True`인지 `False`인지를 확인해야 한다.
   * `True`는 `1`이다.

