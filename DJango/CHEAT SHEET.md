# 현재까지 배운 것 CHEAT SHEET

(업데이트 날짜: 2021.03.24)





1. 환경설정
2. 프로젝트 및 어플리케이션 생성
3. 기본 폴더구조 생성
4. 기본 모델 생성

5. 기본 폼 생성







## 1. 환경 설정

1.1 가상환경 설정

```
# 가상 환경 설정
$ python -m venv venv

# 가장 환경 들어가기
$ source venv/Scripts/activate
```

​     

1.2 가상환경에서 필요한 라이브러리 설치

```
# (venv 상태)

# 장고
$ pip install django

# 템플릿에서 부트스트랩 쉽게 사용하기 → app 등록 필수
$ pip install django-bootstrap-v5

# 이미지 파일 저장하기
$ pip install Pillow

```

​    

1.3 requirements.txt 생성

```
(venv 상태)
$ pip freeze > requirements.txt
```

​      

1.4 기타 파일 생성

```
$ touch .gitignore
$ touch README.md
```

​    

## 2. 프로젝트 및 어플리케이션 생성

2.1 프로젝트 생성

```
# .을 찍어야 현재 디렉토리에 manage.py 생성된다.

django-admin startproject <project이름> .
```





2.2 앱 생성

```
python manage.py startapp <app이름>

# 회원을 관리하는 앱은 accounts라는 이름으로 만들자
python manage.py startapp accounts
```





2.3 앱등록

```python
# settings.py

INSTALLED_APPS = [
    'accounts',
    'posts',
    'bootstrap5',
    ...
```

​     

2.4 Template의 경로 설정

```python
# settings.py

TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [BASE_DIR/'pjt'/'templates'],
        ...
```

​    

2.5 커스텀 유저 모델 설정

* 커스텀 유저 모델은 프로젝트 생성과 동시에 만들어야 한다.
  * 정확히는 첫번째 migration 이전에 만들어야 한다.
  * 만들고 나서 settings.py와 admin에 등록해야 한다.
  * 지금은 settings.py에 등록부터 하고 이후 모델을 만들 것이다.

```python
# settings.py

# 'app이름.커스텀유저모델이름' 형식으로 등록한다.
AUTH_USER_MODEL  = 'accounts.CustomUser'
```

​     

​        



## 3 기본 폴더 구조 생성

3.1 프로젝트 폴더에 templates 폴더 및 앱 이름의 하위 폴더 생성

​      



3.2 프로젝트의 templates 폴더에 base.html 생성

* base.html에는 기본적으로 bootstrap의 cdn을 포함해서 작성한다.
* block태그 사용



3.3 base.html에 들어가는 기본 조각 html 생성

* 예를 들면 navbar나 footer는 따로 html파일로 생성해서 불러오는 방식으로 관리한다.(모듈화)
* 기본 조각 html은 내부적으로만 사용하는 것이기에 파일 이름 앞에 언더바(_)를 붙여서 생성한다.
  * 예시: `_nav.html`
* 생성한 조각 html 파일을 base.html에서 include 태그를 사용해서 불러오기
  * `{% include '_nav.html' %}`

​    

3.4  프로젝트 urls.py에서 각 app으로 경로 설정

* include 사용
  * `path('accounts/', include('accounts.urls')),`

​    

3.5 각 app 디렉토리 내에서 urls.py 파일 생성

* app_name 설정
* 각 url에 대해 name 설정     

​    



## 4. 기본 모델 생성

4.1 커스텀 유저 모델 생성

* accounts 앱의 models.py에 생성
* AbstractUser를 상속받아서 생성

​    

​    



4.2 기타 필요한 모델 생성

​    

​    



4.3 생성 끝났으면 makemigrations와 migrate 실행 

​    

​    

## 5. 기본 폼 생성

5.1 각 어플리케이션에서 forms.py를 만들어서 form을 생성한다.