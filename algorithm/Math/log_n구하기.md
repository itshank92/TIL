## 2가 base인 log n 구하기 = 어떤 숫자의 2제곱승을 구하기



> 방법1. Math 모듈 이용

```python
import math

math.log(32,2)
#=> 5
```

​         



> 방법2 재귀 함수 사용하기

```python
def myLog(x, b):
    if x < b:
        return 0 
    # 차원이 늘어날때마다 +1해주고 재귀
    return 1 + myLog(x/b, b)

myLog(32,2)
#=> 5
```

​        



> 방법3.  수학적 접근 => 하지만 해당 로그는 자연로그의 경우다.(자연로그의 밑:2.7..)

출처1: https://stackoverflow.com/questions/13211137/get-logarithm-without-math-log-python

출처2: https://stackoverflow.com/questions/32897519/is-it-possible-to-use-logarithms-to-convert-numbers-to-binary/32901838 

```python
def ln(x):
    n = 100000
    return n * ((x ** (1/n)) - 1)
  
ln(32)
#=> 3.46..
```

   

* 자연로그는 밑이 `2.718...`의 값이다. 

* 자연로그의 값을 통해서 바이너리 로그(밑이 2인 로그) 값을 얻는 것은 기본적인 로그 연산만 알면 된다.
  * `log2(*b*)`는 `log(*b*) / log(2)` 혹은 `ln(*b*) / ln(2)` 와 같다.
  * 즉 밑이 `2`인 `log32`를 구하고 싶으면 `자연로그(32)/자연로그(2)`를 해주면 된다.

* 위 식에서 n은 커질수록 연산의 정확도가 높아진다. (미분이나 적분을 사용해서 계산하는 것 같다)