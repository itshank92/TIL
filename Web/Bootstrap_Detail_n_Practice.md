## Bootstrap Details 

> 부트스트랩의 세부 항목들과 좀 더 자세한 내용을 배우고 실습을 진행하며 사용법에 익숙해진다.



<br>

### Bootstrap의 모든 요소들은 컨테이너에 넣는다.

* container에 넣으면 내부 항목들의 양옆에 약간의 공백이 생기고 자동으로 중앙정렬이 된다.

* 예외 두 가지
  * nav: 화면 위에 공백없이 붙어있는게 디자인적으로 괜찮은 경우가 대부분이다.
  * footer: 화면 아래에 공백없이 붙어있는게 디자인적으로 괜찮은 경우가 대부분이다.

<br>



| bootstrap에서 js파일을 body의 하단에 위치시키는 이유         |
| ------------------------------------------------------------ |
| ● javascript는 용량이 가장 크다 (html,css와 비교해서)<br /><br />● 따라서 사용자에게 html, css부터 보여주고 용량이 큰 부가적인 동적 이미지는 나중에 보여주는 것이다.<br /> |

<br>



### 부트스트랩의 주요 요소들

#### alert

* 잠깐 보여주는 안내화면을 만들 때 사용한다.
* 예를들면 '*성공적으로 로그인했습니다.*', '*암호가 틀렸습니다.*' 와 같이 화면 상단에 뜨는 것을 말한다.
* role이라는 속성값을 통해서 의미를 부여할 수 있다. 
  * role은 의미없는 div 태그에 의미를 명시적으로 표현만 해주는 것으로 실제 작동하지는 않는다.
* 자바스크립트와 같이 사용하면 해당 안내창을 동적으로 구현할 수 있다.

<br>



#### buttons

```html
<button type="button" class="btn btn-secondary">Secondary</button>
```

* btn-outline-primary
  
  * 안에는 색이 없고 테두리만 파란색(`primary`)인 버튼
* 버튼 객체는 크게 3가지 방법으로 만들 수 있다.
  1. html의 button 태그

  2. html의 a 태그
     * a 태그에 `"btn"`이라는 클래스를 주면 언더바가 사라진다.
       * 혹은 `"text-decoration-none"` 클래스를 사용해도 좋다(텍스트의 언더바를 없앤다.)
     * 색을 바꾸고 싶으면 `"btn-primary"`와 같은 클래스를 추가해주면 된다.

  3. html의 input 태그
     * input 역시 `"btn"`과 `"btn-color명"` 클래스를 통해서 버튼처럼 쓸 수 있다.
     * input의 경우 안에 버튼명을 넣기 위해 `value="버튼명"`으로 입력한다.
     * 하지만 input태그는 기본적으로 입력값을 받는 것이기에 사용자가 안에 내용을 없앨 수 있다.
     * 이를 실질적인 버튼으로 만들기 위해서는 `type="submit"`이라는 속성을 넣어준다.
     * (참고) input의 경우 누르면 입력데이터들을 모아 전달하는 기능이 있다. 

<br>

#### 실제 버튼을 만드는데는 어떤 태그가 많이 사용될까?

* 버튼을 만드는데 많이 사용하는 태그는 `button`이 아니다. 

* `a`태그는 누르면 `href`의 값으로 이동하는 기능이 있다. 
*  `input`태그는 데이터를 모아서 전달하는 기능이 있다.
* `button`의 경우 기능이 없기에 기능을 넣어주려면 javascript로 코딩을 해줘야한다. 
* **따라서 버튼을 만들때는 기능이 있는 `a`나 `input`을 사용한다.**

<br>

#### 버튼을 늘려서 가운데 정렬하는 방법

```html
      <div class="row justify-content-center">
          <!--input 태그를 사용해서 button을 만들었다-->
          <input type="submit" class="btn btn-success col-10"> 
      </div>
```

* 상위에 div를 생성해서 row로 설정한다. 
* button을 row의 자식으로 넣고 `col-숫자`를 이용해서 원하는 크기로 만든다.
* row에서 `justify-content-center`를 사용해서 가운데 정렬을 한다. 

<br>

#### cards

* 카드 형태의 클래스로 웹페이지 구성시 자주 사용하는 요소 중 하나다.

* 크게 **카드에 이미지를 넣는 부분**, **내용을 넣는 부분**, **버튼** 으로 구성되어 있다.
* 카드가 시작하는 `div` 위에 주석을 달아서 카드가 시작함을 알리고 끝나는 부분도 주석을 달아야 한다.
  * `<!-- card start-->`와 같은 주석을 달아줘야 html 문서의 구조파악과 디버깅이 용이해진다. 
  * 이렇게 구분해주지 않으면 코드량이 많을 때는 카드를 구분하기 힘들다.
* 여러 카드를 정렬하는 경우, row안에 col으로 넣어서 사용자가 원하는데로 정렬할 수 있다.
  * col-숫자를 통해서 크기도 조정할 수 있다.
* 혹은 부모 태그로 card-groups라는 클래스를 이용해서 정렬할 수 있다.
  * bootstrap에서 제공하는 기능으로 사용하려면 더 알아보자.
* 카드와 카드 사이에 마진을 주고 싶으면 mx-3과 같은 클래스를 사용한다.
  * 해당 카드 객체에 클래스로 입력해준다.

<br>

>  궁금: row-cols-2는 무엇인가

* 공식 문서에 따르면 해당 클래스의 명칭은 row columns다.
* 해당 기능은 한 줄에 오는 column 갯수를 지정해주는 것으로 이 갯수에 따라 column들의 크기가 자동적으로 결정된다.

* 하지만 여기서 정한 갯수가 불가능한 경우 최대한 가깝게 적용된다. 



<br>

#### carousel

* 이미지 옆에 화살표가 있어서 이미지를 넘겨 볼 수 있는 것

* 자바스크립트를 알아야 조작할 수 있다.
* 작동원리는 각 이미지 **태그의 클래스가 동적으로 변경**되면서 화면에 보이는 이미지가 변하는 것이다.
  * 이는 이후 Javascript를 사용해 조작하는 것.
  * 좌우 버튼은 `a`태그로 만들어져있는데 `href`가 url 주소가 아닌 #로시작하는 `특정 id`를 가리키고 있다. 

<br><br>

| html 문서에서 명명할 때는 '*케밥케이스*'를 사용한다.         |
| ------------------------------------------------------------ |
| ● 케밥케이스: '소문자'를 '-'로 이어져있다.<br />(예시: my-class)<br /><br />● 스네이크 케이스는 절때 쓰면 안된다.<br /><br />● 이유는 언더바가 잘 안보이기 때문이다.<br /><br />● 대부분 링크는 밑줄이 그려져 있는데, 이게 언더바랑 구분이 잘 안된다.<br /><br /> |



<br>

<br>





#### modal

* 버튼을 클릭하면 화면 전체를 어둡게하고 팝업을 띄우는 것이다. 

* 작동원리는 버튼을 누르면 팝업창(modal)의 display 속성을 none에서 block으로 바꾸는 것이다.

* 모달의 구성요소

  1. 버튼
     * 모달을 보여주기 위한 버튼

  2. 모달
     * 모달 본체

* 버튼은 `data-bs-target`이라는 javascript 특성을 사용한다.
  * data-bs-target의 속성값으로 모달 본체의 id가 지정된다.
  * 해당 id가 호출되면 `fade`라는 클래스가 `show` 클래스로 변한다.

* 모달의 불편한 점은 모달을 띄우기 위해서 버튼을 직접 눌러야 한다는 것이다.

* 공식문서를 보면 모달에 다양한 속성을 줄 수 있다.



<br>





#### nav

* 보통 `container` 바깥에 만든다.
  * cotainer 안에 만들면 강제 여백이 생기고 가운데 정렬이 된다. 
* `justify-content-위치` 클래스를 통해 정렬할 수 있다.
  * 이미 위 코드에는 d-flex라는 코드가 생략 적용되어 있는 것이다. (그래서 justify를 사용할 수 있다.)
* `nav`를 만드는 두 가지 방법
  * `ul` 태그로 만들기
  * `a` 태그로 만들기
* 하나의 `nav`라는 클래스 안에 각각의 버튼은 `nav-link`라는 클래스로 존재한다.
* 클릭이 안되게 하려는 `nav`의 항목의 경우, 해당 태그에 `"disabled"`라는 클래스를 주면 된다.
* 현재 클릭이 되어 있는 `nav` 항목의 색을 진하게 하고 싶으면 `"activate"`라는 클래스를 사용한다.

<br>

| nav 만드는 것과 관련                                         |
| ------------------------------------------------------------ |
| 1. nav에는 container로 감싸지 않는다.<br />▶ 이유는 위를 다 차지하는게 더 보기 좋은 경우가 많으니<br /><br />2. nav안의 항목들을 정렬하기 위해서는 nav 자체의 클래스에 justify-content-를 사용한다<br /><br />3. nav 안의 항목들을 정렬할 때는 정렬 덩어리끼리 div로 묶는다.<br />▶ 단, ul 덩어리의 경우 div로 묶지 말고 ul 자체가 덩어리니까 그것을 그냥 둔다. |

<br>

<br>



#### nav와 nav bar의 차이점

* nav bar는 전체적인 nav를 보여주는 것
  * nav bar는 [정치, 경제, 사회]가 한 줄로 나오는 형태
* nav는 리스트를 보여주는 것
  * nav는 [사회-노동, 사회-일반, 사회-이슈]가 세로로 나오는 형태 



<br>



#### pagination

* 페이지를 변경하는 버튼들
* 보통 아래에 있는 [prev 1 2 3 4 5 next] 이것을 pagination이라고 부른다.
* 웹페이지의 각 항목들을 어떤 용어로 부르는지 알고 있어야 우리가 만들 때 검색해 볼 수 있다. 



<br>

#### form

* 사용자의 입력값을 받는 것으로 매우 중요하기 때문에 bootstrap의 doc을 보면 독립적인 항목으로 존재한다.

<br><br>



## 부트스트랩 실습하면서 배운점

<br>

### Breakpoints를 활용한 동적 웹페이지 구축

* `col-sm-2` : sm 이상일 때 2

* `col-1  col-md-2`:  md 이상일 때 2, md 미만일 때 1
  * 기준이 md가 된다. 

* `col-4   col-sm-3   col-md-6`: sm 미만 4, sm 이상 md미만 3 md이상 6

* `col-12   col-md-4   col-xl-2`: md 미만 12, md 이상 4, xl이상 2

<br>

#### offset 사용

* `offset` 역시 크기가 변할때마다 크기를 조정할 수 있다. 
  * `offset-md-4 `

* `offset-4`를 설정하고 `md`이상일때는 `offset`을 없애려면 `offset-md-0`으로 **초기화** 해줘야 한다.
* `offset`을 오타나는 경우가 있다. `offsef`로 치면 정말 구분하기 어렵다.





<br>

### 요소들을 정렬하기

* d-flex라는 클래스를 통해서 요소들을 좌우로 정렬 시켜줄 수 있다.
  * 메인축바꾸기 ▶ `"flex-column"` 클래스 (`"flex-direction:column"`과 같음)
* 리스트 요소들 간격 조절하는 두 가지 접근 방법
  1. `ul` 자체의 `width`를 넓히고 `d-flex`와 `justify-content-방식`을 사용한다.
  2. 각각의 `li`의 `margin`을 준다.  

<br>

### 글자 조정 클래스

* text-white: 글자 색을 흰색으로
* text-decoration-none: 밑줄 없애줌
* fw-bold:글자 진하게 하는 클래스
* text-center: 텍스트를 가운데 정렬하는 클래스
* display-숫자
  * 일반적으로 글자를 키우려면 h태그를 사용했었다. 
  * display-1~6까지의 클래스를 이용해서 글자 크기를 조정할수 있다.(h1부터 h6와 같음)
  * h태그와 다르게 의미와 상관없이 글자 크기를 조절하는 것이다. 

<br>     

#### list의 마크(=bullet)를 없애는 방법

* ul태그의 클래스에 list-inline을 넣어준다.



<br>

#### `col-숫자`는 입력해주는게 좋다

* 구체적으로 모든 칸의 크기를 입력해주는게 유지보수에 더 좋다. 
* `col-숫자`를 생략해도 되는 경우가 있는데 그래도 숫자를 명시적으로 써주는게 디버깅에 더 좋은 코드다.










