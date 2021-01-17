## git이란

### git

* 분산 버전 관리 시스템 (DVCS)

* 코드의 history를 관리하는 도구

* 변경사항을 비교하고 분석 및 병합도 가능

  

### DVCS(Distributed Version Control System)

* 여러 컴퓨터에 같은 코드가 복사되어 있다.
   (여러 팀원들이 각자 컴퓨터에 코드 파일이 있음)
* git을 통해서 각각의 차이(변동사항)을 볼 수 있다.



### git의 공간 종류

* git은 3구역으로 나뉜다.

  **(1) working directory**
  ▷ 로컬 공간으로 개별 사용자의 PC에서 작업하는 경로(폴더)를 의미한다.

  **(2) index(staging area)**
  ▷ 로컬 공간과 서버(remote) 단계 사이에 위치하는 폴더다. 
  ▷ 위치는 로컬에 있지만 언제든지 `git` 명령어를 통해 해당 폴더에 있는 것들을 서버에 올릴 수 있다. 

  **(3) remote**
  ▷ `git`을 사용해 최종적으로 파일을 보내는 서버를 의미하는 공간이다.
  ▷  `github`, `gitlab`, `bitbucket`등 다양한 서비스 제공자들이 해당 서버 서비스를 제공한다.

  

* **working directory(1)**에서 **index(2)**로 파일을 올리기 위해서는 2단계의 명령어가 사용된다.
  * `add` : **(1)**에서 **(2)**로 올릴 대상들을 추가해주는 명령어다.   *(대상들을 종이에 막 적어놓는다)*
  * `commit`  : `add`된 대상들을 하나로 잘 정렬시켜 **(2)**로 올려준다.  *(대상들을 잘 정리해 제출한다)*


### git의 작업순서

1. 내가 파일을 작성하거나 수정한다.
   - 해당 파일을 ***working directorty***라고 한다

2. 해당 파일을 `commit` 목록을 담을 폴더에 넣음
   - 이 폴더를 ***staging area(=index)***라고 하고 이 행위를 ***staging***이라고 한다.

3. 해당 폴더에서 `commit`을 수행
   - 폴더에 쌓인 working directory들을 촬영

4. `push`를 통해 github에 올림





## github이란

### github

- 개발자들의 SNS: 코드를 올리는 공간
- 자신의 프로젝트에 다른 사람이 조언, 기여할 수 있음
- 오픈소스 생태계에서는 모두가 참여해서 발전 가능 → `github`은 오픈소스의 필수적 도구이자 환경
- 취업을 위해 관리해야 하는 대상 
  - `github`을 관리하는 것 + 해당 내용을 말할 수 있어야 됨 (실제 인터뷰에서 질문을 한다)
  - 말할 수 있도록 `github`에 코드 뿐만아니라 해당 코드에 대한 블로그도 만들어서 관리하자

### git과 github

- `git`과 `github`은 서로 다르다.
- 내 사진첩의 사진들을 백업하는 방식이 다 다르다.
  (naver 클라우드, google 포토, dropbox 등)
- `git`을 사용하기 위한 여러 서비스가 있다.
  (`github`, `gitlab`, `bitbucket`)
- `git`은 코드를 분산해서 버전 관리하는 방식을 의미하고, 이러한 git의 작동 방식을 서비스로 제공하는 것이 `github`을 비롯한 다양한 서비스들이다. 



## git으로 로컬 폴더를 관리하기

### 목표

* `git`을 통해서 working directory를 `git`으로 관리할 것임을 선언한다. 

* `git 명령어`를 통해 working directory의 내용을 staging area(index)로 전달한다.

  

1. **일단 `git`에서 (관리하려는) 해당 폴더로 이동해야 한다.**

   * `cd` 명령어를 사용해서 해당 폴더로 이동한다.

     


2. **`touch test.md` 명령어로 마크다운 파일을 만든다**

   

3. **해당 폴더를 `git` 방식으로 관리할 것임을 선언한다.**

   * `git init`

   * 현재 경로에 비어있는 깃 저장소를 숨겨진 폴더 형태로 만든다( `.git` )

     * 깃 저장소를 통해서 해당 폴더를 `git`의 방식으로 관리할 수 있다.

     * 깃 저장소에 해당 폴더의 스냅샷(`commit`)이 저장되고 관리됨

     * 해당 폴더의 하위 폴더도 `git`으로 관린된다.

     * 단, `git`의 모든 명령어는 `.git` 폴더가 속한 가장 상위의 경로에서 작성해야 한다.

       

4. **`master`라는 이름이 CLI의 경로 옆에 표기 되는 것을 확인할 수 있다.**

   * `master`는 해당 경로가 `git` 방식으로 관리되는 directory라는 의미다.
     
     * 우리는 `master`를 보고 *'현재 이 폴더가 `git`으로 관리되는 폴더구나'* 라고 생각할 수 있다.
     
       

5. **`test.md`를 `git` 저장소에 `add`한다.   **

   * `git add test.md`

     * 아무 명령어가 안 나온다면 잘 된 것이다.

       

6. **`git`의 상태를 확인해본다.**
   
   * `git status`
   
     * `test.md`라는 파일이 생성되었음을 알 수 있다.
   
       

7. **`add`를 모두 다 했으면 `commit`을 통해 전체 사진을 찍는다.**
   
   * `git commit -m "first commit"`  

     * `-m`을 통해 `commit message` 입력이 가능하다
   
       
   
8. **로그인 정보를 넣어준다.**
   
* `git config --global user.email "<사용자의 email주소>"`
   
     
   
9. **유저 이름을 넣어준다.**
   
* `git config --global user.name "<사용자의 이름>"`
   
     
   
10. **설정이 잘 됐는지 확인한다.**
    
    * `git config --global -l`
    
      * `-l`은 리스트를 의미한다. → *'내용을 리스트로 보여줘.'*라는 뜻이다.
    
        

11. **이게 설정이 다 되었으니 `commit`을 다시 해보자**
    
    * `git commit `
    
      


12. **`git log`를 통해 내가 `commit`한 기록을 볼 수 있다.**

    


13. **새로운 파일을 만들고 `git status`를 하면 `master`에 있는 파일 중 `add`가 안된것이 있다고 알려준다.**
    
    * `add`와 `commit`은 하나의 패키지로 생각하자.
    
    * `add`는 파일 추가할 때 뿐만 아니라, 파일 내용이 수정되었을 때도 계속 해줘야 하는 작업이다.
    
      

14. **`test.md` 파일 내용을 typora를 사용해 수정한다.**
   
   
   
15. **수정 이후 `git add test.md`를 해주고 `git status` 명령을 통해 변동사항을 확인 한다.**

    * 명령어의 결과, 파일이 modified 되었다고 알려준다.
    * modified 되었으니 새로 `commit`해야 한다는 것이다.



16. **여러개 파일 생성, test.md도 내용 수정후 저장해주기**
    
    * `touch a.txt b.txt c.txt`   →  a,b,c이름의 txt파일 생성
    
    * typora로 `test.md`  내용 수정
    
      


17. **현재 폴더의 변경사항 확인하기**
    
    * `git diff`
    
      


18. **여러개의 파일을 한번에 `add`해주기**
    
    * `git add . `
    
      

19. **다시 `git status`로 상태 확인**

    

20. **다시 `commit`을 해준다.**

    

21. **`git log`를 통해 `commit` 기록을 볼 수 있다.**

    * 가장 위가 가장 최신



## 로컬공간의 파일을 github의 repository로 올리기

### 목표

* 위에서 로컬에 쌓아둔 commit들을 `github`에 업로드 한다. 
  

1. **`github`에 로그인**

    

2. **+ 버튼 → new repository → repository 생성** 

    * description에는 폴더에 대한 설명을 적는다 
    * repo(repository)는 폴더와 같은 개념이다.



3. **repo를 만들면 첫 화면에 해당 repository와 로컬 폴더를 연결할 수 있는 명령어가 나온다.**
   
   * `git remote add origin https://github.com/<user이름>/<repo이름>.git`
   
   * `git remote add <repo를 부를 닉네임> <repo의 링크>`
   
     * 로컬의 특정 경로(폴더)를  `github`의 repository와 연결하는 명령어가 `git remote add` 다
   
       
   
   * 

4. **해당 명령어를 git bash의 `master` 경로에서 입력해주자**

    

5. **입력후 `git remote -v` 명령어를 통해 해당 폴더가 어떤 깃헙 repository와 연결되었는지 보여준다.**

    * `git remote -v`는 현재 경로 파일이 연결된 remote의 version 정보를 보여준다. 

      

6. **(연결된)로컬의 파일들을 `github`으로 업로드 하려는 명령어**

   * `git push origin master`

     * `origin`: 위에서 우리가 연결한 repo를 가리키는 닉네임 (re를 쉽게 부르기 위해 사용)

     * `master`: repo에 연결해서 업로드하려는 로컬 폴더

       

7. **처음인 경우 웹페이지가 뜨고 `github` 로그인을 요구한다.**

    

8. **`push`가 완료되었다면, `github`의 해당 repository에 로컬 폴더에 있는 파일들이 업로드되었을 것이다.** 

