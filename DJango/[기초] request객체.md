# request 객체

출처: https://docs.djangoproject.com/en/3.1/ref/request-response/



> **HttpRequest** == **request**

클라이언트가 서버에 요청(`request`)을 보내면 장고에서는 해당 요청(`request`)의 metadata를 담고 있는 `HttpRequest` 객체로 만든다. 이렇게 만든 `HttpRequest`객체를 `views.py`에 있는 함수에 인자로 보내준다. 



`views.py`에서는 컨벤션으로 모든 함수에서 `request`라는 `parameter`를 사용해서 `HttpRequest`를 입력받는다. 



​      

> HttpRequest의 API 

* `HttpRequest`의 API는 `django.http` 모듈에 포함되어 있다. 

* `HttpRequest`의 속성(attributes)

  * **path**: domain 주소를 제외한 주소 경로를 반환한다.

    (예시: `www.naver.com/movie/new/top10` 이라는 주소에서 `/movie/new/top10`만을 반환한다.)

  * **method**: 현재 request에 사용된 HTTP 메소드를 문자열로 반환한다.

    (예시 `if request.method == 'GET':`)

  * **GET**:  

    * `request.GET`은 `request`의 `url`뒤에 붙은 params의 정보를 `key:value` 형태로 읽어오는 것이다. 
    * 따라서 `request`의 method가 `POST`일지라도 `url`에 붙은 정보를 읽어오려면 `GET`을 사용해야 한다. 
    * ※ `GET`을 사용할 수 있다고 해서 해당 `request`의 method가 `GET`이라는 것은 절대 아니다(상관 X)

  * **POST**:  `post`메소드의 `request`가 가진 모든 `parameters`를 `dictionary` 형태로 담고 있는 객체를 반환해준다. 
    * `.post`의 결과값으로는 `request`의 `form`이 유효한지(모든 입력값이 다 입력되었는지) 확인할 수 없다.
    * 따라서 장고 공식 문서에서는 `.post`를 사용해서 `form`의 유효성 검사를 하지말 것을 명시적으로 설명하고 있다. 

  





> [cf] 딕셔너리의 get 메소드에 대해서 



**갑자기 웬 get 메소드?**

* `request.GET` 혹은 `request.POST` 방식으로 받은 딕셔너리에서 원하는 `parameter`의 값을 불러오는데 `get` 메소드가 사용되기 때문이다.



**get의 문법**

* `get(key, value(옵션))`      





**get()의 parameter**

* **key** : `dictionary`에서 찾을 `key`값

* **value(optional)** : `key`를 찾지 못한 경우 반환해주는 값 (defalut는 `None`으로 설정되어 있다.)    



**예시**

```python
d = { 'apple':0, 'orange : 1' }

print(d.get('mango'))  #=> None
print(d.get('mango', 'mango없어요'))  #=> 'mango없어요'
```

