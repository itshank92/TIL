23

검색 메뉴 붙이기



1) 검색 메뉴 추가

* 5번 메뉴에 추가하고 기존의 5번 메뉴(종료)를 6번으로 옮김.

```java
// NoticeConsole.java


public int inputNoticeMenu() {
    Scanner scan = new Scanner(System.in); 

    System.out.printf("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색 /6.종료 >");
```

  

2) 실행 파일도 수정(case 조정 & 추가)

```java
// Program5.java

int menu = console.inputNoticeMenu();

switch(menu) {
    case 1: // 상세조회

        break;

    case 2:  // 이전
        console.movePrevList();
        break;

    case 3:  // 다음
        console.moveNextList();
        break;

    case 4:  //글쓰기
        break;

    case 5:  //검색
        // 검색어를 입력받아서 검색을 수행하는 메소드 호출
        console.inputSearchWord();
        break;        
        
    case 6:  //종료
        System.out.println("Bye~~~~~~~");
        // break 다음에 라벨명이 오면 해당 라벨 단계까지 모두 종료한다.
        break EXIT;

    default:
        System.out.println("메뉴는 1에서 4까지만 입력할 수 있습니다.");
        break;
```

* 검색을 위한 `case 5`에서는 검색어를 입력받는 메소드를 통해 처리하도록 작성한다. 
* 물론 해당 메소드는 현재 존재하지 않기에 **TOP-DOWN** 방식으로 `NoticeConsole.java`로 가서 해당 메소드를 작성한다.
  * 사용자로부터 검색어를 입력받아서 검색을 수행하는 함수 

  

3) `NoticeConsole.java`에서 검색 수행하는 메소드 작성

```java
public class NoticeConsole {

	private NoticeService service;
	private int page;
	private int count;

    // 검색어 맴버 변수
	private String searchWord;

    // 필드 검색어 맴버 변수
	private String searchField;
	
    // 생성자에 검색어 변수, 필드 검색어 변수 추가
	public NoticeConsole() {
		service = new NoticeService();
		page = 1;
		count = 0;
		searchField ="";   //생성자에 추가(초기값 빈문자열)
		searchWord ="";    //생성자에 추가(초기값 빈문자열)
	}


	// 생략
	
	public void inputSearchWord() {
		// 스캐너 생성
		Scanner scan = new Scanner(System.in);
		
        // 어떤 필드(제목, 작성자, 내용 등등)로 검색할 것인지 입력받는다.
		System.out.println("검색 범주(title/content/writerId) 중 하나를 입력하세요");
		System.out.print(" >");
		searchField = scan.nextLine();
		
		// 검색어를 입력받는다.
		System.out.print("검색어 >");
		searchWord = scan.nextLine();
		
	}
```

* 사용자의 입력값이 되는 `searchWord`는 데이터를 가져오고 출력하는 메소드인 `printNoticeList()`에도 사용될 것이기 때문에 로컬 변수가 아닌 맴버 변수로 만든다.  



▶ 이렇게 입력받은 필드 검색어, 검색어를 가지고 실제 검색을 수행하는 과정은 `printNoticeList()`에서부터 코드 수정을 통해 구현하는데 이는 다음시간에 배운다.  

  

  

---

24

검색 서비스 추가하기



1) `NoticeService.java`에서 데이터를 가져오는 `getList()` 메소드에 검색 기능 추가

```java
//생략

public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {


    int start = (page*10)-9 ;
    int end = page*10;

    String sql = "SELECT * FROM NOTICE_VIEW WHERE " + field + " LIKE ? AND NUM BETWEEN ? AND ?"; 
    // 생략
```

  

2) `NoticeConsole.java`에서 `getList()` 메소드에 인자 추가하기

```java
//생략

public class NoticeConsole {

	private NoticeService service;
	private int page;
	private int count;

	private String searchWord;
	private String searchField;
	
	public NoticeConsole() {
		service = new NoticeService();
		page = 1;
		count = 0;
		searchField ="TITLE";
		searchWord =""; 
	}
	
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = service.getList(page,searchField,searchWord);
        // 생략
```

