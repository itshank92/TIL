### label과 button

* 라벨 사용해서 해당 텍스트 클릭하면 input 칸으로 바로 이동하게 만들기

* button을 만들어서 로그인하도록 만들기

```html
  <form>
    <div>
      <label for="username">USERNAME : </label>
      <input id="username" type="text" Placeholder="아이디를 입력해주세요">
    </div>
    <span>
      <label for="pwd">PWD : </label>
      <input id="pwd" type="password">
    </span>
    <button class="btn default">로그인</button>
  </form>
```

   

* `placeholder`라는 속성값을 통해서 input에 기본 안내 문구를 넣어줄 수 있다.

  

### css 작성요령

* 배경 색과 같은 것은 클래스로 만들어서 적용시킨다. 
  * `.bg-white` 혹은 `.bg-lightgray`와 같이 클래스를 만들어서 이 후 변경사항이 생기더라도 클래스 이름만 바꿔주면 바뀌도록 작성한다. 

* 마진이나 패딩 역시 하나의 클래스로 만들어서 작성한다.
  * `.m-4{margin:4px;}` → 마진이 4px인 클래스 
  * `.p-4{padding:4px;}` → 패딩이 4px인 클래스

* 텍스트 가운데 정렬 역시 하나의 클래스로 만들어준다.

  * `.text-center{text-align :center;}`

* 인라인 블록으로 만드는 것도 클래스로 만든다.

  * `.d-inline-block{display:inline-block;}`

   