## Migration



> 기본

* 장고가 모델에 생긴 변화(ex_ 필드 추가 및 삭제 or 모델 생성 및 삭제 등등)를 반영하는 방법이다.

  ▶ 모델에 생긴 변화를 Migration으로 만들어서 DB에 전달하여 적용시킨다.

  ▶ [모델 변화]  →  [변경사항에 대한 Migration 생성]  →  [DB에 전달하여 적용]



Migration 명령어에는 4가지가 있다.

1. `makemigrations`

   ▶ 모델의 변경사항에 기반하여 Migration을 만들다.

   ▶ 모델에 변경사항이 있으면 그 때마다 makemigrations를 해야한다. 

2. `migrate`

   ▶ 만들어진 Migration을 DB에 전달하여 적용시킨다.

   ▶ 모델에서의 변경사항이 DB의 스키마와 동기화(업데이트)가 된다.

3. `sqlmigrate`

   ▶ migration파일(ORM파일)이 **SQL로 어떻게 번역되는지 시각적으로 확인**할 수 있는 명령어

   ▶ 실제 작동하는 것은 없고 단지 migation파일이 어떤 SQL문으로 번역되는지만 보여준다.

4. `showmigration`

   ▶ 프로젝트 전체의 migration 상태를 확인하기 위해 사용된다.

   ▶ migration 파일들이 migrate 되었는지 안되었는지 여부를 확인할 수 있다.

   ​	(git의 git status와 비슷한 기능이다.)

​     

> makemigrations 명령어 실행시 결과

실제 명령어: `python manage.py makemigrations`

app 디렉토리 안에 migrations 폴더가 생성되고 그안에 순서대로 migration파일이 생성된다.

순서대로 번호가 붙어서 migration파일이 생기는 이유: 버전관리가 가능하다.

해당 파일은 ORM방식으로 작성된 것으로 이후 migrate 명령어 수행시 DB에 전달된다.

▶ 즉, makemigrations은 우리가 만든 모델(python의 클래스)을 ORM으로 번역해서 migration파일로 만드는 명령어다.      



> migrate 실행시 결과

프로젝트의 DB(`db.sqlite3`)내에`어플리케이션이름_모델이름`의 이름을 가진 테이블이 생성된다.



> sqlmigrate

실제 명령어: `python manage.py sqlmigrate articles 0001`  



> showmigration

실제 명령어: `python manage.py showmigrations`

