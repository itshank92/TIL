# CSS Layout

> 웹페이지에 포함되는 요소들을 어떻게 취합하고 그것들이 어느 위치에 놓일 것인지를 제어한다.

* CSS layout은 웹페이지의 요소들의 위치를 배치하는 것

* 앞서 배운 Display, Position도 CSS layout의 방법 중 하나

* 오늘은 나머지 방법들에 대해 배울 것. Float, Flexbox(1차원layout), Grid system

<br>

## float

> 한 요소(element)가 정상 흐름(normal flow)으로부터 빠져 텍스트 및 인라인(inline) 요소가 그 주위를 감싸 자기 컨테이너의 좌,우측을 따라 배치되어야 함을 지정한다.

<br>

**기본내용**

* 본래는 이미지의 좌, 우측 주변으로 텍스트를 감싸는 레이아웃을 위해 도입하였다.
  * 신문기사 형태처럼 사진 옆에 글(텍스트)이 있는 것을 만들기 위해 창조됨
* 더 나아가 이미지 뿐만 아니라 다른 요소들에도 적용해서 사이트의 전체 레이아웃을 구성하는데 사용되었다.
* HTML의 정상적인 흐름을 벗어나서 인라인 요소들을 위치시키는 방법

<br>



**Float의 속성값(3가지)**

1. `none`: 기본값
2. `left` 요소를 왼쪽으로 띄움
3. `right`: 요소를 오른쪽으로 띄움



**다른 요소들에게 float를 해제시키는 방법**

* float요소는 주변의 다른 요소들에게 영향을 미친다.

* float는 화면상 해당 공간에 붕 뜨는 기능을 수행하기에 기존에 아래에 있던 요소는 위로 올라온다. (=겹쳐짐)

  * 단, 글자의 경우 float 공간으로 들어오는 것이 아닌 주위를 감싼다.
  * 이는 float가 글자를 감싸는 목적으로 만들어졌기 때문이다

* 기존에 아래에 있던 요소가 제자리를 유지하려면, 그 요소가 float된 요소를 무시하도록 설정해야 한다.

  * 이 방식을 통해 레이아웃이 깨지는 것을 막을 수 있다. 

* float된 요소를 무시하도록 설정하려면 **float된 요소의 부모**에다가 특정 속성값을 준다.

  ▶ 바로 `clearfix`라는 클래스를 float 요소의 부모에 부여하고, `clearfix`의 `style`을 아래처럼 작성한다.

  ```html
  <style>
      .clearfix::after{
          content:"";
          display:block;
          clear:both;
      }
  </style>
  ```

  * 해석

    * 부모클래스 이후(after)에 가상요소를 만든다.
    * 해당 요소의 내용(content)은 비어있고 display는 block이다.
    * clear 속성 값이 both라는 것은 float요소가 left이든 right이든 모두 무시하겠다는 뜻이다

    ​    

* ※주의※   
  float요소의 부모 클래스 이름으로는 컨벤션으로 `clearfix`를 사용한다.

<br>

**정리**

- flexbox 및 그리드 레이아웃과 같은 기술이 나오기 이전에 float는 열 레이아웃을 만드는데 사용되었다.
- 이제는 더 새롭고 나은 레이아웃 기술이 나와기에 레거시 레이아웃 기술로 분류된다.
- 결국 원래 텍스트 블록 내에서 float 이미지를 위한 역할로 돌아간 것이다.
- 하지만 작게작게 여전히 사용하는 경우도 있다. (ex. naver nav bar)

<br>

> float는 그리드, 플랙스박스가 나오기 전에 탄생한 기능으로 이제는 사실상 정렬에 사용한다기 보다는, 텍스트를 감싸는 최초의 목적으로 돌아갔다 

<br>

<br>

---

<br>

## flexbox

> 일명 flexbox라 불리는 Flexible Box module은 flexbox 인터페이스 내의 아이템 간 공간 배분과 강력한 정렬 기능을 제공하기 위한 1차원 레이아웃 모델로 설계되었다.
>

<br>



#### **기본내용**

* **한방향으로만 정렬하는 레이아웃**

  <br>

* flexbox에서 반드시 기억해야 하는 두 가지 ▶ **요소, 축**

  * 요소

    * flex container
    * flex items

  * 축

    * 메인축(main axis): 정렬되는 축 (좌우방향 정렬인 경우 x축이 메인축이 된다)

    * 교차축(cross axis): 메인축과 수직 방향인 축

      <br>

* **flex container**: flexbox들이 놓인 상위공간으로 **flexbox 정렬은 모두 flex container 클래스에서 수행**된다.

  * 기본적으로 left to right로 정렬된다. (메인축의 default값은 좌우방향의 x축이다)
  * **flex container를 조절함으로서 flexbox(flexitem)들을 조정한다.**  
  * flex container는 내가 정렬하려는 태그들의 부모 태그를 의미한다.
    * 부모 태그의 속성값으로 `display: flex;`를 주면 해당 부모 태그는 자동으로 flex container가 된다.   

<br>

| ※ flex container로 설정(display:flex;)한 부모 바로 아래에 있는 자식들만 flexbox가 된다. |
| ------------------------------------------------------------ |
| flexbox의 자식은 flexbox가 아니다. 따라서 flexbox의 자식들을 flexbox로 만들어서 정렬하고 싶으면, flexbox를 다시 display:flex;를 통해서 flex container로 만들어줘야 한다. |

<br>

<br>

#### **flex container**

- flexbox 레이아웃을 형성하는 가장 기본적인 모델이다.
- flexbox들이 속해있는 영역으로 부모태그에 `display:flex;` 속성을 부여함으로서 선언된다.
  - flex 컨테이너를 선언하려면 영역 내의 컨테이너 요소의 display 값을 flex 혹은 inline-flex로 지정한다.
  - 그냥 flex는 block요소로 flexbox 생성, inline-flex는 inline요소로 생성
- flex 컨테이너를 선언시 아래와 같이 기본 값(default)이 지정된다.
  - item은 행으로 나열
  - item은 주축의 시작 선에서 시작
  - item은 교차축의 크기를 채우기 위해 늘어남
  - `flex-wrap` 속성은 `nowrap`으로 지정(flexbox가 많아지는 경우 container 초과발생)
- flex container는 self가 붙은 명령어만 제외하고 모든 flexbox를 조정(정렬)한다

<br>



### flexbox의 시작

1. **부모 요소에 display: flex 혹은 inline-flex를 작성한다.**
   * 그냥 flex는 블락요소로 flexbox 생성, inline-flex는 인라인요소로 생성
   * 인라인요소로 생성하는 경우 각각의 요소들은 인라인 요소로 된다.

2. **배치 방향을 설정한다** (=메인축을 정한다 >> 자동으로 교차축도 정해진다)
   * 메인의 축을 x로 할지 y로 할지 정한다.		
   * **flex-direction** 속성을 통해 설정한다.

3. **메인축의 어떤 방향으로 정렬할지 정한다.**
   * **justify-content** 속성을 통해 설정한다.

4. **교차축의 어떤 방향으로 정렬할지 정한다.**
   * **align-items** 속성을 통해 설정한다.

<br>

**기타**

> flexbox 사용시에는 `justify-content`와 `align-items`를 가장 많이 사용하게 된다. 

<br>

<br>

#### flex-direction

* 메인축을 설정하는 속성값이다.

  * 메인축을 알면 저절도 교차축도 알수 있다.(교차축은 메인축의 수직 방향의 축)

- **row (기본값)**
  - 가로로 요소가 쌓임
  - row 는 주축의 방향을 왼쪽에서 오른쪽으로 쌓이게 한다.
- **row-reverse**
  - 가로로 요소가 쌓이는데 오른쪽에서 왼쪽으로 쌓이게 한다.
- **column**
  - 세로로 요소가 쌓임
  - 요소가 위에서 아래로 쌓이게 한다.
- **column-reverse**
  - 세로로 요소가 쌓임
  - 요소가 아래에서 위로 쌓이게 한다.

* 축방향 설정을 했으면 이제 축을 이용해 정렬하자 
  * `justify`가 붙으면 메인축 정렬방향을 설정하는 속성이다. (x축이 메인축인 경우, 좌우 or 우좌 결정)
  * `align`가 붙으면 교차축 정렬방향을 설정하는 속성이다.
  * flex-direction으로 설정한 축이 메인축으로 justify로 시작하는 명령어를 사용해 메인축 정렬을 한다.
  * 메인축과 수직인 축이 교차축으로 align으로 시작하는 명령어를 사용해서 교차축 정렬을 한다.



<br>

| flex-direction 제대로 이해하기                               |
| ------------------------------------------------------------ |
| flex-direction의 값이 row(기본값)에서 column으로 바꾼다는 것은 <br />▶ 해당 flex container를 90˚ 돌리는 것을 의미한다. <br />▶ 화면을 90˚ 돌리는 것을 생각해보면서 그 효과를 이해하자. |

<br>



<br>

#### flex-wrap

>  item들이 강제로 한 줄에 배치 되게 할 것인지 여부 설정

- nowrap (기본 값)
  - 모든 아이템들 한 줄에 나타내려고 함 (그래서 자리가 없어도 튀어나옴)
- wrap : 넘치면 그 다음 줄로
- wrap-reverse : 넘치면 그 윗줄로 (역순)

<br>

<br>

#### flex-flow

>  flex-direction 과 flex-wrap를 합쳐서 만든 속성값으로 한번에 메인축과 wrap 여부를 결정할 수 있다.

* flex-direction과 flex-wrap을 합친 것
* `flex-flow: <디렉션값> <wrap값>`
  * (예시) `flex-flow: colunm, wrap;`

```css
flex-flow: row nowrap;
```

<br>

#### 명령어 이해하기

* contents: 여러줄을 컨트롤(정렬) 하겠다
* items: 한줄을 컨트롤(정렬) 하겠다
* self: flex item 개별 요소를 컨트롤(정렬) 하겠다.
* justify-content: 메인축 기준으로 여러줄을 컨트롤(정렬)하겠다.
* aligh-items: 교차축을 기준으로 한줄을 컨트롤(정렬)하겠다.
* align-self: 교차축을 기준으로 선택한 요소 하나를 컨트롤(정렬)하겠다.

<br>

#### 축을 기준으로 정렬을 하는 세부적인 방법 설정

* flex-start, flex-end, center 등등 여러 방법이 있다.

* flex-start: 해당 축의 시작부분을 기점으로 정렬

* flex-end: 해당 축의 끝 부분을 기점으로 정렬

* center: 해당축의 중앙에 정렬

* space-between: 해당 축에 요소들이 같은 간격을 가지도록 정렬 → **양쪽의 외부 여백이 없음**

* space-around: 해당 축의 요소들의 내부 여백이 양 끝의 외부 여백의 두배가 되도록 정렬

* space-evenly: 해당 축의 요소들의 내부여백과 외부여백이 같도록 정렬

<br>



#### **justify-content**

> 메인축의 정렬방향을 설정하는 속성값이다.

(아래 값들의 세부 설명의 경우`flex-direction: row`를 기준으로 작성되었다)

- flex-start (기본 값)
  - 시작 지점에서 쌓임(왼쪽 → 오른쪽)
- flex-end
  - 쌓이는 방향이 반대 (`flex-direction: row-reverse` 와는 다르다. 아이템의 순서는 그대로 정렬만 우측에 되는 것.)
- center
- space-between
  - 좌우 정렬 (item 들 간격 동일)
- space-around
  - 균등 좌우 정렬 (내부 요소 여백은 외곽 여백의 2배)
- space-evenly
  - 균등 정렬 (내부 요소 여백과 외각 여백 모두 동일)

<br>

#### align-items

> 교차축의 정렬방향을 정하는 속성이다.

(아래 값들의 세부 설명의 경우`flex-direction: row`를 기준으로 작성되었다)

- stretch (기본 값)
  - 컨테이너를 가득 채움
- flex-start
  - 위
- flex-end
  - 아래
- center
- baseline
  - item 내부의 text에 기준선을 맞춤

<br>

#### align-self

> 교차축의 정렬방향을 설정하는 것인데 개별 item에 사용하는 속성값이다.

- auto (기본 값)
- flex-start
- flex-end
- center
- baseline
- stretch
  - 부모 컨테이너에 자동으로 맞춰서 늘어난다. (Stretch 'auto'-sized items to fit the container)

<br>

#### order

- 개별 요소(flexbox)에 부여되는 속성으로 값이 작을 수록 정렬의 우선순위가 높아진다. 
- order는 개별 요소(flexbox)에 적용이 된다. 
- order의 기본값은 0이다. 
  * 따라서 기본적으로 모든 flexbox의 order 값은 0이다.
  * order값이 같은 경우 먼저 작성된 것이 앞으로 온다.
- order는 작은 숫자일 수 록 우선순위가 높아서 앞으로 온다
- order는 item에 적용을 시킬 수 있다. 

<br>

#### flex-grow

- flex-grow는 flex container에 남는 공간에 대해서 분배하는 것을 의미한다.
  - = 주축에서 남는 공간을 개별 flexbox에게 분배하는 방법이다.
  - 개별 요소(flexbox)에 적용되는 속성이다.
- 각 flexbox의 상대적 비율을 정하는 것이 아니다.
- 음수는 불가능하다(order와 다른점)
- flex-grow 값은 기본적으로 0으로 설정되어 있다.
- 모든 flexbox에 대해 flex-grow값들을 모두 더한 수치로 container의 남은 공간을 나눈다.
  * 이렇게 나눈 덩어리를 각각의 flexbox의 flex-grow값의 수만큼 준다. 



<br>



> flexbox 연습 게임 사이트 : https://flexboxfroggy.com/
> 또 다른 연습 게임 사이트 : http://www.flexboxdefense.com/



<br>

## flexbox의 과정 총 정리

(순서가 의미가 있으니 순서 유의하면서 보자)

<br>

1. **정렬을 하려는 태그들의 부모에게 display: flex 속성값을 준다. (클래스 형태로 부여)**

   * 이 경우 해당 부모 태그는 flex container가 되고 부모의 (직계)자식들은 모두 flexbox처럼 취급된다.
   * 주의해야 할 것은 만약 flexbox 태그의 자식 태그를 flexbox방식으로 정렬해주고 싶다면, 해당 flexbox에  flex container 속성을 부여하는 작업(display:flex;)을 해줘야 한다. 
     * 이렇게 해줘야 flexbox의 자식 태그 역시 flexbox가 된다. 

   <br>

2. **flex-wrap 설정을 통해 flex containter를 초과하는 flexbox를어떠한 방식으로 처리할 지 결정한다.** 

   * `flex-wrap:no;` → flexbox가 flex container를 초과해도 감싸지 않고 flexbox가 테두리 밖에 표현된다.
   * `flex-wrap: wrap;` → 이렇게 하면 초과하는 flexbox들을 위해 박스가 커진다.
   * `flex-wrap: wrap-reverse'` → 방향을 바꿔서 wrap한다.

   <br>

3. **flex-direction 설정을 통해 메인축을 설정한다.**

   * row(default), row-reverse, column, column-reverse
   * flex-direction에서 설정한 메인축은 이후 justify로 시작하는 명령어로 통제한다.
   * flex-direction에서 설정한 메인축과 수직하는 교차축은 이후 align으로 시작하는 명령어로 통제한다.

   <br>

4. **메인축으로 정렬하려면 justify로 시작하는 명령어를 사용한다.**

   * justify-content: flex-start; (축의 시작방향으로 정렬\_ 메인축이 좌우라면 '좌')

   * justify-content: flex-end; (축의 끝방향으로 정렬\_ 메인축이 좌우라면 '우')

   * justify-content: center;(축의 가운데 방향부터 정렬\_ 메인축이 좌우라면 '중간')

   * justify-content: space-between;(전체 공간에서 아이템들간의 간격을 동일하게 한다.)

   * justify-content: space-around;(아이템들간의 내부 요소 여백이 각각 외각 아이템 요소 여백의 두배)

   * justify-content: space-evenly;(아이템들간의 내부 요소 여백이 각각 외각 아이템 요소 여백과 동일)

     <br>

5. **교차축으로 정렬하려면 align으로 시작하는 명령어를 사용한다.** 

   * 현재 메인축이 좌우x축이라면 교차축은 상하y축
   * 교차축이 y축인 경우 정렬하려는 줄이 한줄이기에 align-items가 된다. 
     * align-items: stretch; (기본값)
     * align-items: flex-start; (교차축의 시작점에 요소들이 붙는다)
     * align-items: flex-end; (교차축의 끝점에 요소들이 붙는다)
     * align-items: center; (교차축의 중간에 요소들이 붙는다)
     * align-items: baseline; (폰트의 크기에 따라 정렬 선이 정해진다.)
   * align-self: 개별 요소 단위로 움직이는 것
     * align-self: flex-start;
     * align-self: flex-end;
     * align-self: flex-center;
   * align-self만 각각 개별 요소에 작성이 되어 정렬을 수행하는 경우고, 나머지 모든 경우는 항상 부모 태그에서 (flex container에서) 정렬한다.

   <br>

   <br>



> 기본적으로 flexbox는 flex container(부모태그)를 기준으로 정렬, 컨트롤 하는 것이다.
>
> 단, align-self의 경우에만 개별 요소를 대상으로 flexbox를 정렬한다.