### 객체지향프로그래밍

* 과거는 함수와 인자가 따로 존재했다면, 객체지향프로그래밍에서는 이 둘을 묶어서 하나의 객체로 만들어준다.



### 객체란?

* 파이썬에서 모든 것은 **객체**다.
  * `a = 10` 일때, `print isinstance(a,int)` 는 `True` 라고 나온다.
  * `a` 는 `int`라는 class의 인스턴스다.
  * 따라서 `a`는 `int` 라는 class의 여러 메소드를 사용할 수 있다.
* 모든 객체는 타입, 속성, 조작법(메서드)을 가진다.
* 객체의 활용법을 알고 싶으면 해당 객체를 `dir(<인스턴스>)`를 출력해보면 된다
* 모든 클래스의 데이터 유형은 `type`이다.
  * `print(type(int))    # type`
  * `print(type(<사용자가 만든 클래스>))  #type`
* 사용자가 만든 클래스로 만든 인스턴스의 데이터 유형은 `__main__.<클래스이름>`이다.

   

> 클래스 생성시 상속받는 부모클래스가 없더라도 클래스 이름 뒤에 ()를 붙여서 만드는 게 좋다.

   



> 클래스 이름 생성시 여러 단어를 사용해 명명한다면 각 단어의 첫글자를 대문자로 하는 것이 컨벤션이다.
>
> (이 방식을 Camel case라고 부른다)
>
> ▶ `class PersonInThePark():`

​      



> 변수나 함수 이름은 snake_case로 표현하는 것이 컨벤션이다.



### 메서드

* 클래스 메서드는 첫번째 인자로 항상 자기자신을 입력하도록 되어있다.

  * 메서드 사용시에는 생각할 필요 없다

    (어짜피 `<인스턴스>.메서드()` 형태로 사용하기에 자동으로 인자로 입력된다.)

  * 클래스 메서드 작성시 `self` 라는 용어로 파라미터 첫 번째 위치에 넣어줘야 한다.

* 생성자 메서드(`def __init__(self)`)
  * 인스턴스가 생성될 때, 자동으로 호출되는 함수다.
  * 생성자 메서드의 경우 보통 `return` 값이 없다

* 소멸자 메서드(destructor method)
  * 인스턴스 객체를 소멸시키고 싶을 때 사용하는 메서드
  * `def __del__(self):`
  * 소멸자 메서드는 __를 사용하여 명명되었기에 `del <인스턴스 이름>` 형태로 사용된다.
  * 소멸자 메서드는 보통 `return` 값이 없다
  * 같은 인스턴스 명칭으로 또 인스턴스를 생성하는 경우, 기존의 인스턴스는 자동적으로 소멸자 메서드가 작동되어 사라지고, 해당 인스턴스 이름에 새로운 인스턴스가 생성된다.





​     

> 메소드를 연속적으로 작성하는 방식을 **'메소드 체인이닝'** 이라고 부른다.
>
> ▶ `some_list.pop().lower() `



### 클래스 변수

* attribute(속성)은 클래스 변수와 인스턴스 변수 모두를 아우르는 말이다.

* 클래스 내에 변수를 만들 수 있다.

  ```python
  class class_name:
      class_variable = 10
      def __init__(self):
          pass
  
      
  print(class_name.class_variable)  #=> 10
  
  instance_name = class_name() #인스턴스 생성
  
  print(instance_name.class_variable)  #=> 10
  ```

* 클래스 변수는 `클래스이름. 클래스변수명`으로 접근 가능하다.

* 인스턴스 역시 클래스의 모든것을 상속받기 때문에 `인스턴스이름.클래스변수명` 으로 접근할 수 있다. 

  * 단, 인스턴스가 해당 변수명에 따로 값을 재할당한 경우, 재할당된 값으로 접근한다.
  * 인스턴스 메서드 내에서 클래스 변수로 접근하려면 self.변수명으로 접근해야한다.
    * 인스턴스 먼저 찾고나서 클래스를 찾는 방식으로 변수 탐색이 시행된다. 

​    

​      



### 인스턴스 메서드와 클래스 메서드 그리고 스태틱 메서드

* 클래스 내에서 만드는 메서드는 기본적으로 **인스턴스 메서드**

  * 인스턴스 메서드는 인스턴스를 조작하는 함수 

    ▶ 따라서 인스턴스 메서드안에는 인스턴스를 넘겨받아서 사용해야한다.

    ▶ Python에서는 자동으로 넘겨준다.

    ​    

* **클래스 메서드**인 경우 해당 메서드 작성 바로 윗줄에 `@classmethod`라고 표현해준다.

  * `@classmethod`는 그냥 읽기 좋게 하기 위한 용도가 아니다. 

    * `@classmethod`를 써주지 않으면 해당 메소드의 첫번째 파라미터를 클래스로 인식하지 못한다.

  * **클래스 메서드**는 첫번째 인자로 `self`가 아닌 `cls`를 넘겨준다.

    * 클래스 메서드에서 직접 클래스 이름을 사용해서 클래스 변수에 접근할 수 도 있다. 
    * 그럼에도 `cls` 라는 변수를 사용하는 이유는 클래스명이 바뀌어도 메서드를 수정안해도 되기 때문.
    * ★ `cls`는 해당 클래스메소드를 사용하는 객체의 클래스를 의미하게 된다.
      * 특정 클래스의 변수를 가져오게 하려면 `cls`가 아닌 실제 클래스명을 사용하면 된다.

  * 클래스 메서드는 클래스를 조작하는 함수

    ▶ 따라서 클래스 메서드 안에는 클래스를 넘겨받아서 사용해야한다.

    ▶ Python에서는 자동으로 넘겨준다.    

    

* **스태틱 메서드**는 클래스가 사용하는 메서드인데, `cls` 인자가 필요 없는 메서드를 의미한다.

  * **클래스 변수의 값을 사용하지 않는 메서드**인데 **클래스와 관련있는 메서드**는 **스태틱메서드**로 한다.

  * `@staticmethod`를 윗줄에 표현해준다

  * **클래스 메서드**와 다르게 `cls` 를 매개변수로 넣지 않는다(`self`는 물론 넣지 않음)    

    

* 함수 작성부(`def`) 위에 아무것도 없으면 **인스턴스 메서드,** `@classmethod`가 있으면 **클래스 메서드**, `@staticmethod`가 있으면 **스태틱 메서드**

​      

* 어떤 메서드를 작성할 때, 인스턴스 메서드로 작성할지, 클래스 메서드로 작성할지, 스태틱 메서드로 작성할지 헷갈린다면 다음과 같이 생각하자. (순서를 따라가야 한다)

  1. 인스턴스 데이터를 사용하는 경우 → **인스턴스 메서드**

  2. 클래스 데이터를 사용하는 경우 → **클래스 메서드**
  3. 아무런 데이터를 사용하지 않는 경우 → **스태틱 메서드**

​      

​      



### 인스턴스와 메서드

* 인스턴스는 3가지 메서드 모두에 접근할 수 있다.
  * 클래스는 인스턴스 메서드에 접근할 수 없다 (인스턴스 메서드는 인스턴스(self)가 인자로 필요하기에)
* 하지만 인스턴스에서 클래스 메서드와 스태틱 메서드는 호출하지 않아야 한다
* 인스턴스가 할 행동은 모두 인스턴스 메서드로 한정 지어서 설계한다.



  

  



### 매직메서드

* 더블 언더바(`__`)로 이루어진 메서드를 매직메서드라고 부른다.

* 클래스 생성시 Python에서 기본적으로 생성해주는 메서드들을 의미한다.

* 여러가지가 있지만 대표적인 것만 배운다.

  ```python
  class Person():
      def __init__(self):
          self.name = '거북이'
      def __str__(self):
          return self.name
      
  turtle = Person()
  print(turtle)  #=> 원래하면 인스턴스 객체가 출력되어야 하지만, __str__로 인해 self.name 출력
  ```

* `__str__`의 경우, 인스턴스명을 `print`할 때 작동하는 매직메서드이다.

  `print(<인스턴스명>)   #=> __str__ 함수가 작동함`



---

​      



### 클래스와 상속

##### 사람 클래스를 만들고, 해당 클래스를 상속받는 학생, 교수 클래스를 각각 만들어 보자

```python
# 사람 클래스 만들기
class Person:
    population = 0
    
    def __init__(self, name = '사람'):
        self.name = name
        Person.population += 1
     
    def talk(self):
        print(f'반갑습니다. {self.name}입니다.')
        

kim = Person('김교수')
kim.talk()   #=> 반갑습니다. 김교수입니다.

# 학생 클래스 만들기
class Student(Person):
    def __init__(self,name,student_id):
        self.name = name
        self.student_id = student_id

s1 = Student('박학생', '20210127')
print(s1.student_id)   #=> 20210127

# 부모 클래스에 정의된 메서드를 호출 할 수 있다.
s1.talk  #=> 반갑습니다. 박학생입니다.


# population을 출력하면 1로 나옴 >>> Student 클래스에는 따로 population 변경해주는 코드 없기 때문
print(Person.population)  #=> 1

# 상속관계 확인(issubclass함수 사용)
issubclass(Student, Person)  #=> True

# 인스턴스의 클래스 확인
isinstance(s1,Student) #=> True 
isinstance(s1,Person)  #=> True 

# 인스턴스의 데이터 타입 확인
type(s1) is Person   #=> False  
```



### 타입 검사 방법들 (동작방식차이 有)

* `isinstance(True,int) #=> True` 상속관계에 있어도 True

* `type(True) is int  #=> Fasle`해당 클래스인 경우만 True
  * `issubclass(bool, int) #=> True`  boolean 자료형은 int 자료형을 상속받아서 만들어졌다. 

* 클래스의 상속구조를 정확히 보고 싶다면 `<클래스>.mro()`  메소드를 사용한다.
  * `bool.mro()  #=> [bool, int, object]`  boolean은 int를, int는 object를 상속받아서 만들어졌다.
  * `mro()`는 해당 클래스가 속성값을 검색할 때, 우선적으로 찾는 클래스부터 보여준다. 



### super() : 부모클래스의 메소드를 호출해서 사용하기

* 자식클래스에서 부모 클래스의 메소드 코드를 중복해서 작성하지 않고 바로 받아와서 사용하기 위한 도구
  * 부모 클래스의 내용을 사용하고자 할 때, `.super()`를 사용한다.
* 코드작성 중복시키지 않고 유지보수가 편해진다.
* 예를들면, 위의 Student 클래스의 생성시 Person의 population을 자동적으로 변경하고 싶다면, Student 클래스의 `__init__` 메소드에서 `super().__init__`을  불러와서 사용하는게 필요하다.
* `super()`는 바로 위의 부모 노드를 의미한다.

```python
class Person:
    population = 0   
    def __init__(self, name = '사람'):
        self.name = name
        Person.population += 1
     
    def talk(self):
        print(f'반갑습니다. {self.name}입니다.')


class Student(Person):
    def __init__(self,name,student_id):
        # Super()를 사용해보자
		super().__init__(name)   # 부모클레스의 init 메소드를 실행한다.
		# Student 클래스만의 속성값 지정 
        self.student_id = student_id

s1 = Student('박학생', '20210127')

print(Person.population)  #=> 1

```



​    



* `super().<부모의 메소드>(부모메소드의 인자)`

  



### Python에서 인스턴스가 속성을 찾는 순서

* 인스턴스 → 자식클래스 → 부모클래스 → 전역





### 다중상속

* 두개 이상의 클래스를 상속받는 경우, 다중상속이 된다.



```python
class Person():
    def __init__(self, name):
        self.name = name
        
    def talk(self):
        print("사람입니다.")
        
        
class Mom(Person):
    gene = 'XX'
    
    def swim(self):
        print('첨벙첨벙')
      
    
class Dad(Person):
    gene = 'XY'
    
    def walk(self):
        print('뚜벅뚜벅')
        
        
mommy = Mom('박엄마')
daddy = Dad('김아빠')

"""
■ Mom과 Dad는 Person을 상속받아서 만들어졌다.
"""

class FirstChild(Mom,Dad): #상속받을 클래스들을 넣어준다.
    def cry(self):
        print('응애')
	
    # baby에서 walk 메소드 오버라이딩!
    def walk(self):
        print('아장아장')
        
        
baby = FirstChild('김아가')
baby.swim  #=> '첨벙첨벙'
baby.walk  #=> '아장아장'
print(baby.gene)  #=> 'XX'  #Mom의 gene 속성값을 받아왔다. → 순서상 Mom을 먼저 상속받았기 때문

"""
■ 그렇다면 상속 순서를 바꿔봅시다.
"""

class Boy(Dad,Mom):
    
    def cry(self):
        print('으아아앙')
                
  
boy = Boy()
boy.cry()  #=> 으아아앙
boy.walk() #=> 뚜벅뚜벅
boy.swim() #=> 첨벙첨벙
print(boy.gene)  #=> 'XY'   #Dad의 gene 속성값을 받아왔다. → 순서상 Dad를 먼저 상속받았기 때문
```

