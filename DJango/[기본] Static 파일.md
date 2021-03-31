# Static files



### 기본 정의 

* 웹 사이트의 구성 요소 중에서 image, css, js 파일과 같이 해당 내용이 고정되어 응답을 할 때 별도의 처리 없이 파일 내용을 그대로 보여주면 되는 파일     



* 즉, 사용자의 요청에 따라 내용이 바뀌는 것이 아니라 요청하는 것을 그대로 응답하면 되는 파일     

  

* 따라서 정적 파일을 사용하는 경우는 사용자의 입력과 상관없이 항상 같은 형태로 보여주는 홈페이지 대문과 같은 것을 구성할 때는 정적 파일로 만들어서 구성한다 (장고 권장사항)

   

    

### static 파일 저장해두는 위치

* template와 마찬가지로 각 app에 static 폴더를 생성하고 해당 폴더에 static 파일을 넣어주면 장고에서 자동으로 인식한다.    



* 하지만 template과 마찬가지로 프로젝트 디렉토리 아래에 static 폴더를 만들어주고 해당 폴더 아래에 app 이름의 폴더를 따로 만들어 static 파일들을 관리할 수 도 있다.   



* 위와 같이 하려는 경우, template과 동일하게 settings.py에서 static 경로를 등록해줘야 한다.
  * static도 기본적으로는 장고가 app 디렉토리 아래에 있는 static 파일만 인식하게 되어 있다.     

​      



### settings.py에 등록하는 static 관련 3가지

1. `STATICFILES_DIRS`

   * app내의 static 디렉토리 경로를 사용하는 것(기본 경로) 외에 추가적으로 static 파일의 경로를 정의하는 것이다. (templates에서 경로 지정해주는 것과 동일)

   * 프로젝트 디렉토리 아래 static 폴더를 등록해주고 싶다면 아래와 같이 입력한다.

     ```python
     # settings.py
     
     STATICFILES_DIRS = [BASE_DIR/'프로젝트폴더 이름'/ 'static']
     ```

     

2. `STATIC_ROOT`

   * 장고에서 실제 웹사이트를 배포하려고 할 때는 모든 static 경로에 있는 static 파일을 한 곳에 모아서 저장하는데, 이 때의 경로를 설정하는 것이 바로 STATIC_ROOT다.

   * 개발 단계에서는 작동하지 않기에 개발을 하는 과정에서는 설정할 필요가 없다.

   * 즉, 실제 서비스를 배포할 때 필요한 경로다.

     ```python
     # settings.py
     
     STATIC_ROOT = BASE_DIR / 'staticfiles'
     ```

     

3. `STATIC_URL`

   * STATIC_ROOT에 모든 static 파일을 한 곳에 모아서 저장하는데 , 이들에 대한 URL을 만들기 위해 앞에 붙여주는 주소 값이다.

   * 보통 static 디렉토리에 저장하므로 '/static/' 으로 지정한다.

     ```python
     # settings.py
     
     STATIC_URL = '/static/'
     ```

   * 이렇게 저장하기 때문에 우리가 static 파일을 불러올 때 static 폴더를 생략하고 그 아래에 있는 `"app폴더 / static파일"`형태로 불러올 수 있는 것이다. 

​       

### static 사용하기

* static은 DTL에서 바로 사용할 수 있는 것이 아니기에 코드 첫줄에서 load를 해야 한다.

  ```html
  {% load static %}
  
  <img src = "{% static %}">
  ```

  

  