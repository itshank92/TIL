# 필요한 라이브러리 설치

### django-seed

기능: 더미 데이터 쉽게 만들어 주는 라이브러리

설치: `pip install django-seed`

등록:  settings.py에 `'dajngo_seed'` 등록 (언더바 주의)



### djangorestframework 

기능: 모델의 데이터를 json 형식으로 바꿔서 생성, 출력, 수정, 삭제를 도와주는 serializer를 제공하는 라이브러리

설치: `pip install djangorestframework`

등록:  settings.py에 ` 'rest_framework'`등록 (언더바주의)

​       



# API 역할을 수행할 application 생성 및 url 등록



#### 어플리케이션 생성 및 등록

이전에 했던 것과 동일 



#### url 등록

프로젝트 디렉토리 urls.py에서 `path('api/v1/' , include('application이름.urls '))` 로 작성

* `'api'`는 해당 url이 json을 반환하는 기능임을 알려줌 (RESTful한 url이다 = url만 보면 기능 알 수 있음)
* `'v1'`은 해당 api의 버전을 알려준다. (첫 생성시에는 vesion 1의 의미로 v1을 사용하였다)

​     





# 어플리케이션에서 모델 생성



#### 게시글 모델인 Article을 생성

``` python
from django.db import models

class Article(models.Model):
    title = models.CharField(max_length=50)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
```

​      

​      

#### 댓글 모델인 Comment를 생성

```python
class Comment(models.Model):
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
```

* [복습] 1:n의 구조에서는 n에 해당하는 쪽에서 ForeignKey를 등록해준다.

​      



# (app 폴더) serializers.py에 Serializer를 생성

모델