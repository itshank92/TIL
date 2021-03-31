# include html

> include 기본

* template내에서 다른 template을 포함하는 방법
* 여러 template에 적용되는 공통되는 요소의 경우 따로  html 파일로 만들어서 include로 불러온다.
* 나중에 수정, 유지, 보수 작업시 딱 하나의 template만 수정하면 되기에 매우 편하다.



`include`: template 내에서 다른 template을 불러와서 넣는 DTL 태그

※ 주의: 지금 배우는 include는 DTL 태그이고 전에 path 설정에서 배웠던 include는 모듈의 메소드다.(전혀 상관없는 것)     





> 사용방법

template내에서 다른 template을 불러와서 해당 내용을 넣어주고 싶을 때 다음과 같이 사용한다.

`{% include "불러오려는template파일" %}`

(예시: `{% include "navbar.html" %}`)      





> 사용하는 이유

템플릿의 특정 영역을 중복, 반복해서 사용할 경우에 유용하다. 

▶ 반복되는 경우 따로 분리된 html파일로 만들어서 독립적인 template으로 관리하자.





