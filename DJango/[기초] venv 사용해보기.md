# venv 사용해보기



0. 가상환경은 해당 프로젝트의 파이썬과 모듈을 따로 관리하는 것(폴더 형태)
   * 파이썬에서는 `venv`라는 내장 기능을 통해 가상환경 설정이 가능하다.



1. 가상환경 설정할 프로젝트 폴더레벨에서 `cmd` 실행



2. `python -m venv venv`
   * `python -m` : 파이썬에 있는 module 실행시켜줘
   * `venv`: 실행시킬 모듈은 바로 `venv`라는 것이야
   * `vevn`: 이 실행환경을 `venv`라는 이름으로 부를거야(명명)



3. 프로젝트 폴더에 venv라는 가상환경 폴더가 생성된다.



4. `source venv/Scripts/activate`
   * 가상환경 폴더안에 `activate`라는 `script파일`이 작동해야 가상환경이 실행된다.
   * `source`는 `script파일`을 실행시키는 리눅스 명령어다.



5. 가상환경에 들어왔다.



6. 가상환경을 끄는 방법은 `deactivate` 이다.





# venv는 git에 올리지 않는다.

venv는 프로젝트 관리시 git으로 관리하는 것이 아니다.

따라서 .gitignore에서 venv를 추가해야 한다.





# 패키지 종속성 관리

git 을 통해 협업을 하게 되면 다음과 같은 문제에 봉착하게 됩니다.

파일들은 버젼관리가 잘되더라도, install 한 패키지는 내 환경에만 남아있기 때문이죠.

이럴 경우에는 내 환경에 어떤 패키지들을 사용하고 있나 함께 넘겨주도록 합니다.

현재 환경에서 설치한 패키지를 알려주는 명령어는 다음과 같습니다.

 

```shell
pip freeze
```

​       



## python -m pip freeze 

`pip freeze`를 하면 현재 환경에서 사용하고 있는 라이브러리와 버전을 출력해준다.

이 출력된 결과를 이용해서 파일로 저장할 것이다.

파일의 이름은 `requirements.txt`라고 만들것이다.



(가상환경 구동 상태) 

`pip freeze > requirements.txt`

* `>`: 리눅스 명령어로 왼쪽의 실행결과를 오른쪽에 넣어주라는 말이다.

* 오른쪽에 해당하는 파일이 없는 경우 생성해서 넣어준다.





>  requirements.txt를 다운받아서 프로젝트 폴더에 넣고 가상환경에 모두 설치해달라고 하는 명령어

`pip install -r requirements.txt`

* `-r`: 이 요구사항을 만족하는 것을 모두 설치해줘('r' for 'require')

* pip가 requirements.txt에 존재하는 모든 모듈을 다운받아서 설치해준다.





## README에 적어야하는 가상환경 사항

사용하는 python 버전정도 표기한다.

(나머지 라이브러리 정보는README가 아닌  `requirements.txt`에 적는다.)