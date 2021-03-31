# 장고의 뷰 데코레이터 (Django View Decorators)



> 기본 

* 어떤 함수에 기능을 추가하고 싶을 때 해당 함수를 수정하지 않고 기능을 연장해주는 함수

* django는 다양한 기능을 지원하기 위해 view 함수에 적용할 수 있는 여러 데코레이터를 제공한다.

* 예를들면 데코레이터를 사용하여 reqeust의 방식이 POST인 방식만 해당 함수에 접근 가능하게 만들 수 있다.

  (함수의 수문장의 역할을 하는 데코레이터)



> 사용 방법

* 기본적으로 import 해서 사용한다
  * `from django.views.decorators.http import require_http_methods`
* 함수 위에서 @ 형태로 명시해서 사용한다.
* `django.views.decorators.http`에서 import 해 올 수 있는 데코레이터 종류
  * `require_GET`  ▶ 현재는 require_safe()로 대체됨
  * `require_POST` ▶ 해당 함수는 POST만 허용
  * `require_safe()`   ▶  해당 함수는 GET만 허용
  * `require_http_methods`  ▶ GET, POST 모두 허용하고 싶으면 뒤에 (['GET', 'POST'])를 인자로 사용한다.





> 기타

데코레이터를 사용하여 POST의 요청만 받도록 한 함수에 GET방식으로 접근하려하면 405번 에러가 나온다.

405에러: 허용되지 않은 HTTP 메소드로 접근 시도시 나타나는 에러