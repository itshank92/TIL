# media 사용하기





1. #### css 파일 읽어와서 사용하기

   출처 : https://velog.io/@hidaehyunlee/Django-static-%EC%9C%BC%EB%A1%9C-css-%EB%A1%9C%EB%93%9C%ED%95%98%EA%B8%B0



* media 파일안에 .css 파일넣어주기

* base.html에서 링크로 불러오기

  ```html
  <head>
  {% load static %}
      <link rel="stylesheet" href="{% static "main.css" %}">
  </head>
  ```

* 사용하는 모든 html 페이지에서 load static 해주기





2. #### 미디어 사용하기

출처: http://hleecaster.com/css-media-query/

```css
@media only screen and (max-width: 480px) {
  body {
    font-size: 12px;
  }
}
```

* max-width 480px는 화면크기가 480이내일 때만 위 css 스타일이 적용된다는 것이다.
* 480px 이상일 때도 작성하고 싶으면 min-width: 480px를 써야 한다.

```css
@media only screen and (min-width: 480px) {
  body {
    font-size: 120px;
  }
}
```







3. #### css 아이디, 클래스 속성자

출처: https://www.codingfactory.net/10790

id 속성자: `#`

class속성자: `.`