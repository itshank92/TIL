## REAMME 파일과 .gitignore 파일



### repository의 필수 요소 두 가지

* repository를 생성할 때는 `REAMME.md` 파일과 `.gitignore.txt` 파일을 반드시 만들어야 한다

* `README` 파일은 대문자로 작성하는 것이 컨벤션이다
* 항상 새로운 repo를 만들 때마다 `README.md`와 `.gitignore.txt`를 만드는 것을 잊지말자



### README.md

* `README.md`는 해당 프로젝트에 대한 설명서다
* repo를 만들 때는 항상 `README.md`를 작성하는 것을 습관화 해야한다.
* 사용자가 사용할 수 있는 서비스를 저장한 repo의 경우 사용법 설명서를 `README.md`에 작성해야 한다.
* 우리는 수행하는 프로젝트를 repo로 만들 것이다. 
  * 프로젝트 주제, 기술스택, 구현한 기능, 이슈들, 느낀점 등을  `README.md`에 적는다.
  * 느낀점(배운점, 부족한부분)은 꼭 적는게 좋다. ★ 



### .gitignore.txt

* git으로 관리하는 경우에는 모든 파일을 한번에 올리는게 일반적이다. 
* 그런데 이러한 경우 쓸데없는 파일도 같이 올라갈 수 있다.
* 따라서 git은 repo에 올릴 필요 없는 파일 리스트를 따로 만들어서 `commit`할 때 이들을 제외한다. 
* .gitignore라는 txt파일을 생성해 여기에 push할 필요없는 파일 목록을 적어 둔다. 
* 어떤 파일이 여기에 해당하는지 쉽게 알기 위해 gitignore.io 사이트를 사용한다.
  * 검색창에 우리가 사용하는 운영체제를 입력하고
  * 현재 사용하는 통합개발환경을 입력해준다.
  * 현재 사용하는 언어를 입력한다.
  * 입력후 생성 누르면 내가 무시해도 되는 파일들을 알려준다.
    * `*`은 모든 것을 의미한다.
    * `.log`의 경우 log라는 확장자인 모든 파일을 의미한다
    * `/`는 폴더를 의미한다.
    * pycash/의 경우 pycash라는 폴더를 의미한다.
  * gitignore.io에 나온 결과를 전체선택해서 복사해서 `.gitignore.txt`에 붙여넣는다.
        

* git bash로 돌아와 `touch .gitignore` 명령어를 입력해서 `gitignore`라는 파일을 숨김 형태로 생성한다.
* `.gitignore`라는 파일을 오른쪽 버튼 눌러서 [Code로 열기]를 누른다. 
  * VScode로 열기 위한 작업이다.
  * 메모장으로 열어도 상관없지만 학습상 통일한다

* 우리가 복사한 내용을 해당 코드에 붙여 넣고 저장한다.

* gitignore.io를 통해서 우리는 해당 프로젝트를 `commit`할 때 무시해도 되는 파일, 폴더들을 알 수 있다.

