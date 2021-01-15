# git command

> git 기초 명령어 정리



## 설정 관련 명령어

### init

* `git init`
* 처음 폴더를 git으로 관리하기 위해 `.git` 폴더를 생성하는 명령어
* 최초에 한번만 실행하면 된다.
  

### status

* `git status`
* 현재 git의 상태를 출력
* 수시로 체크하면서 작업함



### log

* `git log`
* 현재 쌓여있는 commit 기록을 출력해줌
* 수시로 체크하면서 작업함
  

### diff

* `git diff`
* 마지막 commit과 현재 working directory의 상태를 비교



### remote add

* `git remote add <별명> <주소>`
* 원격저장소 주소를 등록하는 명령어



## 조작 관련 명령어

### add

* `git add <파일이름>`
  * `<파일이름>`에 `.`을 입력하면 전체 파일을 add할 수 있다.
* 현재 working directory에 있는 파일을 staging area(=index)에 올리는 작업을 수행한다.



### commit

* `git commit -m "커밋메세지"`
* staging area에 올라간 파일들을 스냅샷으로 저장



### push

* `git push <원격저장소 이름> < 올릴 브랜치이름>`
  * `git push origin master`
* commit history을 원격 저장소에 업로드하는 기능을 수행















