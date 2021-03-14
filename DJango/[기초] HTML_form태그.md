# HTML의 form태그



> html의 form 태그

* 웹에서 사용자 정보를 입력하는 여러 방식을 제공하고 사용자로부터 할당된 데이터를 서버로 전송하는 역할을 담당한다. ▶ (요약) **사용자에게 값을 입력받아서 서버에 값을 전달받는 태그**

* 핵심 속성값

  1. `action`: 입력 데이터가 전송될 URL을 지정한다.
  2. `method`: 입력 데이터의 전달 방식을 지정한다.     

  * (참고) html의 메소드 중 `GET`
    * 서버로부터 **정보를 조회하는데 사용**된다.
    * 데이터를 서버로 전송할 때 body가 아닌 Query String Parameters를 통해 전송된다. 
    * 우리가 서버에 html 문서를 받고자 요청할 때 사용되는 메소드다.



**※  `form`태그에 `action`속성이 없는 경우는 현재 요청된 페이지와 동일한 페이지로 `POST`요청이 전달됩니다.**





> html의 input태그

* **사용자로부터 데이터를 직접 입력받는 태그** (`form` 태그의 구성요소 중 하나)
* 핵심 속성값
  * `name`: 사용자가 입력한 값은 `name`이라는 key의 value로 들어간다.
    * 예를 들면 우리가 구글 검색창에서 검색하는 단어는 `query`라는 key값의 value로 입력된다. 
* 이러한 `input` 입력값은 `form`의 `action`에 해당하는 `URL`주소로 전달된다. 
  * 해당 `url` 주소를 관장하는 `view` 파일의 함수에서는 `input`값을 다음과 같은 형태로 읽을 수 있다.
    * `requests.Method종류.get('input의 name값')`으로 해당 값에 접근할 수 있다.
  * 이러한 값들을 읽어와서 dictionary형태로 만들어서 `render`의 입력값으로 전달할 수 있다. 
    * (예시) `request.POST.get('content')` ▶ POST 형식으로 전송된 `form` 데이터 항목 중 `name`이 'content'인 값을 의미

