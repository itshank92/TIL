# Django로 만든 사이트 GCP에 올리기

전체 글 참고 출처: https://iamthejiheee.tistory.com/76



### 1 VM만들기

* 가장 저렴이로 만든다.(f1)
* 부팅디스크는 Centos7로 설정한다.
* 표준메모리를 사용하고 20G로 설정한다.
* 방화벽에서 HTTP, HTTPS 트래픽 허용에 체크한다.



### 2 SSH를 켜서 다음을 입력한다.

(입력하다가 오류가 발생하는 항목은 검색해서 제대로 된 경로로 설치한다)

(rpm파일의 경우 참고한 블로그에서 제시한 주소가 유효하지 않아서 다시 찾아서 설치했다)

```
$ python --version

Python 2.7.5

$ sudo yum -y install epel-release

$ sudo yum -y install https://repo.ius.io/ius-release-el7.rpm  #☆

$ sudo yum -y install python36u-devel python36u-pip

$ sudo python3.6 -m pip install -U pip

$ sudo python3.6 -m pip install virtualenv
```

* IUS repository를 사용하기 위해서 rpm파일을 설치해야 하는데 운영체제에 맞는 것을 잘 선택하자.(Centos7
  * https://ius.io/setup



### 3 SSH에서 톱니바퀴를 눌러서 Django 프로젝트 폴더를 압축한 것을 올린다











### 4 올린 후 압축을 푼다

```
$ ls

$ sudo yum -y install unzip

$ unzip <압축 파일>

```



### 5 sqlite3의 버전을 업그레이드 해준다

은인: https://stackoverflow.com/questions/55485858/using-sqlite3-with-django-2-2-and-python-3-6-7-on-centos7

* Centos7에는 기본적으로 python 라이브러리인 sqlite3 버전이 3.7인가로 설치된다.
* 그런데 django에서는 최근 업데이트로 인해 sqlite3이 3.8.3 이상만 작동하도록 되었다.
* 문제는 sqlite3가 파이썬 내부 라이브러리라서 pip로 업데이트가 안된다는 것이다.
* 이를 해결하기 위해 직접 링크를 사용해서 sqlite3파일을 받아서 설치를 하고 파이썬 라이브러리 경로에 대체해서 넣는다.
  * 정확한 과정은 이해가 더 필요하다.

```
$ sudo yum install wget

$ wget http://www6.atomicorp.com/channels/atomic/centos/7/x86_64/RPMS/atomic-sqlite-sqlite-3.8.5-3.el7.art.x86_64.rpm

$ sudo yum localinstall atomic-sqlite-sqlite-3.8.5-3.el7.art.x86_64.rpm

$ sudo mv /lib64/libsqlite3.so.0.8.6{,-3.17}

$ sudo cp /opt/atomic/atomic-sqlite/root/usr/lib64/libsqlite3.so.0.8.6 /lib64
```









### 6 가상환경을 만들고 접속한다

```
$ python3.6 -m virtualenv venv

$ ls

$ source venv/bin/activate

(venv) $ $ pip install django==2.1.*

(venv) $ pip install djangorestframework

(venv) $ python manage.py runserver 0.0.0.0:8000
```

* 가상환경을 종료하는 명령어는 deactivate
* 0.0.0.0:8000를 붙여주는 이유는 모든 IP주소로 접속할 수 있게 하려고(입력안하면 localhost가 default값)





### 7 네트워크를 설정한다(VM자체 방화벽 OFF  &  GCP 방화벽 OFF)

* 방화벽을 꺼준다.

  `sudo systemctl stop firewalld`

* VM 화면에서 nic0을 클릭해서 넘어간 페이지에서 방화벽을 클릭 → 방화벽 규칙 만들기를 클릭한다.

  * 대상은 네트워크의 모든 인스턴스
  * 소스 필터는 IP범위
  * 소스 IP범위는 0.0.0.0/0
  * 프로토콜 및 포트는 지정된 프로토콜 및 포트(TCP,UDP모두 클릭 후 각각 8000입력)

  ![img](https://blog.kakaocdn.net/dn/d9LjsT/btqBzkn2cvZ/tbmaJkKhrKeTht4yZd0HN1/img.png)



### 8 setting.py에 들어가서 접근이 허용된 호스트 목록을 전체로 바꾸자.

* 이 작업은 수정해야 하는 작업이기에 에디터를 일단 설치한다.

  `sudo yum -y install nano`

* 에디터를 사용해서 `setting.py`에 들어간다. (미리 경로 잘 찾아서 들어간다.)

  `nano settings.py`

  * `ALLOWED_HOSTS =[]` 를 찾아서  `ALLOWED_HOSTS = ['*']`로 수정한다.
  * 수정 후 ctrl + x를 누르고 y를 누르고 엔터를 누른다.

  

### 9 데몬으로 배포한다

데몬을 사용하는 이유: https://blogger.pe.kr/770

데몬배포가 필요한 이유는 우리가 터미널을 꺼놓아도 계속해서 프로그램이 실행되도록 하기 위함이다.

즉 현재 내 컴퓨터에서 데이터 센터의 컴퓨터와의 연결이 끊어져도 계속 프로그램이 실행되도록 하려면 이렇게 설정해야 한다.

* 데몬배포를 위해서는 uwsgi가 필요하고 uwsgi를 위해서는 gcc가 필요하니 차례로 설치한다.

```
(venv) $ sudo yum -y install gcc

(venv) $ pip install uwsgi
```

* 데몬 명령어로 서버를 실행해보자

```
uwsgi --http :8000 --home ~/venv --chdir ~/ --module doithank.wsgi:application
```

http : 포트번호

home : 가상환경 경로

chdir : 프로젝트 폴더

module : 프로젝트 폴더 안에 있는 wsgi 경로

:application :wsgi파일이 application이니까 이거 실행해라고 데몬에게 알려주는 역할



◇ uwsgi 문서: https://docs.djangoproject.com/en/3.1/howto/deployment/wsgi/uwsgi/



* 위에서 uwsgi를 잘못 입력한 경우, 다시 수정해서 입력해도 이미 8000번 포트가 다른 uwsgi(내가 잘못 입력한)에 할당되었다고 하면서 오류메세지가 나타난다.
* 이때 우리는 8000번 포트에 할당한 것을 초기화 해줘야 한다.
* 이를 위한 기능이 fuser인데 fuser를 사용하려면 설치해야한다.
* 설치 및 8000번 포트 초기화 코드는 아래와 같다

```
$ sudo yum install psmisc

$ sudo fuser -k 8000/tcp
```







### 10 SSH창이 꺼져도 계속 데몬이 작동하도록 nohup을 통해 데몬을 실행한다.

```
(venv) $ nohup uwsgi --http :8000 --home ~/venv --chdir ~/ --module doithank.wsgi:application &

# 출력결과
(venv) $ nohup: ignoring input and appending output to ‘nohup.out’ 
```



### 11 엔터누르고 exit로 나온다. 창을 닫아도 실행된다.

0





기타 참고

https://stackoverflow.com/questions/50305654/uwsgi-flask-no-python-application-found-check-your-startup-logs-for-errors

