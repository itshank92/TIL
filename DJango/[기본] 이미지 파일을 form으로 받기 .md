# 이미지 파일을 form으로 받아오는 방법

* `form` 태그의 원래 목적은 텍스트만 입력받는 것이었다.      





(html 파일 수정)

### 사용자에게 이미지 데이터를 입력받는 부분의 form 태그 수정

* 그런데 사진과 같이 파일을 입력받는 경우 `form` 태그의 `enctype`라는 속성값을 수정해줘야 받을 수 있다.

  ```html
  `<form action="" method = 'POST' enctype = 'multipart/form-data'>`
  ```

  * `enctype`의 값을 `multipart/form-data`로 하면 텍스트 데이터가 아닌 데이터를 `form`이 전달할 수 있는 형식으로 인코딩해서 전달한다.
  * **`<input type="file">`을 통해 입력받는 값**을 form에 넣어 전송하려는 경우 반드시 설정해줘야하는 값이다. 

* 이렇게 파일을 보내는 경우 `request.POST`가 아닌 `request.FILES`에 사진데이터가 저장된다.     



(views.py 수정)

### 사용자에게 받은 데이터(form)을 저장할 때 이미지 파일도 넣어줘야 한다.

* 따라서 **모델 저장시 `request.POST`와 `request.FILES` 모두 폼 모델의 인자로 넣어줘야 한다**.

  ```python
  form = PostForm(request.POST, request.FILES)
  ```

  * POST, FILES의 순서는 지켜줘야한다.
  * 장고 문서에서 모델폼을 찾아서 보면 data에 해당하는 것이 제1인자, files에 해당하는 것이 제 2인자로 나와 있다.     





​    

## 이미지 파일 저장 위치 바꾸는 방법

* 위의 방식으로 form을 통해 이미지를 받아와서 저장하는 경우, 프로젝트 디렉토리에 이미지가 저장된다.
* 다른 특정 폴더에 이미지를 저장하여 체계적으로 관리하고 싶으면 MEDIA_ROOT 경로를 수정하면 된다.
* MEDIA_ROOT에 대해 더 자세히 알아보기 전에 MEDIA FILE에 대해 이해해보자.       





## MEDIA FILE이란



> 정의

* 사용자가 웹에서 업로드하는 정적 파일이다. 
  (즉, 유저가 업로드 한 모든 정적 파일)

  

> MEDIA FILE의 저장 공간 설정하기

* settings.py에서 `MEDIA_ROOT`와 `MEDIA_URL`을 설정해주면 MEDIA FILE의 저장공간을 설정할 수 있다.

  ```python
  # settings.py
  
  MEDIA_ROOT = BASE_DIR / 'media'
  MEDIA_URL = '/media/' 
  ```

  ​      

* `MEDIA_ROOT` : 사용자가 업로드 한 미디어 파일들을 보관할 **디렉토리 경로를 의미**한다.

  * 보통 프로젝트 디렉토리와 같은 레벨에 media라는 폴더를 만들어서 해당 폴더로 지정한다.

    ※주의※ 

    장고는 성능을 위해 업로드 파일은 데이터베이스에 저장하지 않는다.
    ▶ 데이터베이스에 저장되는 것은 단지 해당 파일의 경로다.

​       

* `MEDIA_URL` : `MEDIA_ROOT`에 업로드 된 파일의 주소를 만들기 위해 파일명 앞에 붙는 주소 값이다. 
  * 보통 media라는 폴더아래에 미디어 파일을 저장하므로 `MEDIA_URL`은 `'/media/'`가 된다.        

​       

​       

이렇게 저장된 미디어 파일을 우리가 렌더링하는 템플릿에서 사용하려면 미디어 파일에 접근할 수 있는 url을 app에 등록해줘야 한다.(프로젝트에 알려줘야 한다.)

▶ 앞서 우리는 프로젝트 디렉토리가 존재하는 레벨에 media 폴더를 만들고 해당 폴더에 미디어 파일을 저장했다. 

▶ 따라서 프로젝트는 미디어 폴더의 위치를 자동으로 감지 및 접근할 수 없다.(동일한 레벨이니까 자동탐지 불가)     

​     



## urls.py에서 미디어 파일 url 접근할 수 있게 설정해주기

* **프로젝트 디렉토리**에 있는 urls.py를 수정한다.

  ```python
  ## 프로젝트 디렉토리/urls.py
  
  
  # settings는 settings.py를 가리킨다.(단지 라이브러리 형태로 불러와 사용하는 것)
  from django.conf import settings  
  # 미디어 파일 역시 static 파일에 속하기에 static을 불러와 설정한다.
  from django.conf.urls.static import static 
  
  
  # ★ urlpattern 리스트 뒤에 +로 static()을 연결해준다.
  urlpatterns = [
      path('admin/', admin.site.urls),
      path('posts/', include('posts.urls')),
  ] + static(settings.MEDIA_URL, document_root = settings.MEDIA_ROOT)
  
  # [위의 static 인자 설명]
  # settings.py의 MEDIA_URL에 저장된 파일이름으로 요청이 들어오면 document_root에 설정된 주소로 가서 해당 이름의 파일을 찾아서 돌려보내줌
  
  # 정확하게는 static 파일 경로에 MEDIA_URL을 추가하고 해당 URL의 파일이 실제 존재하는 ROOT 경로까지 추가해주는 작업을 의미한다.
  ## ▶어떤 URL을 정적으로 추가할래? → MEDIA_URL을 static 파일 경로로 추가
  ## ▶ 실제 파일은 어디에 있는데? → MEDIA_ROOT 경로내의 파일을 static 파일로 설정
  
  
  ```

  



### 실제 이미지 파일을 템플릿에서 어떤식으로 표현하는가

* 어떤 데이터 테이블의 row를 template에서 불러왔을 때, 해당 row의 각종 필드값은 `.field이름` 형태로 불러온다.

  (예시)`{{ 데이터변수.title }}`      



* 이미지 역시 이미지 필드 명을 사용해서 동일한 방식으로 읽어올 수 있다. 다만 이미지 필드 명으로 읽어오는 경우 해당 파일 이름만 읽어오는 것이다. 
  * `{{ 데이터 변수.image field이름}}` 인 경우 해당 이미지 파일의 이름만 불러온다. 
  * 실제 이미지 파일을  불러오기 위해서는 이름이 아닌 **경로**가 필요하다.      



* 이미지 파일의 경로를 가져오려면 위의 태그에 `.url`을 추가로 붙여야한다.
  * `{{ 데이터 변수.image field이름.url }}`
  * 물론 이 값 역시 경로를 나타낸 단순 문자열으로, 이를 이미지로 보려면 `html`의 `img` 태그의 `src`에 값으로 넣어줘야 한다. 



■ 요약

▶▶`{{post.image}}` : 파일 이름

▶▶`{{post.image.url}}`: 파일 경로      





### 그렇다면 모델 클래스에서 이미지 파일을 어떤 필드로 작성하는지 알아보자

* 이미지파일의 경우 `models`의 `ImageField`를 사용하는데 `blank` 인자를 `True`로 설정하여 비어있는 경우도 제출이 가능하도록 설정하자.
  * `black=True`는 해당 필드의 Null값('')을 허용하는 것이다.
  * 유효성 검사시 `blank=True`인 필드는 비어있어도 통과한다.

```python
# models.py

class Article(models.Model):
    title = models.CharField(max_length=20)
    content = models.TextField()
    image = models.ImageField(blank=True) # ←← "Null을 허락한다"
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
```



* 그런데 이렇게하면 `ImageField`를 사용할 수 없다는 오류가 발생한다. 왜냐하면 ImageField는 장고에서 기본적으로 제공하는 것이 아니기 때문이다.



* `ImageField`를 사용하기 위해서는 `Pillow`라는 라이브러리를 사용해야한다.

  `pip install Pillow`를 사용해서 설치하자.     





### 템플릿에서 이미지 파일 불러올 때 이미지 파일 없을때와 있을 때 구분해서 오류 해결하기

* 위에서 템플릿에서 이미지 파일을 불러오는 방법을 설명했다.
  (`.이미지필드명.url` 을 통해 경로 불러옴)
* 하지만 이렇게 작성했는데 실제 경로에 파일이 없는 경우 오류가 발생한다.
* 따라서 템플릿에서 이미지 파일을 불러올 때는 해당 이미지 파일의 존재 여부를 미리 파악(`if`태그 사용)하는 방식으로 코드를 작성해야 한다.
* 이미지 파일의 존재 여부는 해당 데이터 row에 이미지 필드의 값이 존재하는지(Null값이 아닌지)를 판단하는 방식으로 가능하다.

```django
<!--이미지 파일이 있으면 보여주고 없으면 설정한 이미지로 보여준다.-->

{% if post.image %}
	<img src="{{post.image.url}}" class="card-img-top" alt="{{post.image}}">

{% else %}
	<p> 이미지가 없습니다. </p>

{% endif %}
```

​      





> 이미지 파일의 트리비아

* 사용자가 같은 이름의 미디어 파일을 업로드 하는 경우
  * 같은 이름의 파일이 존재하는 경우 장고에서 자동으로 파일 이름 뒤에 임의의 문자를 붙여서 저장한다.