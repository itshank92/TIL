39

Union

레코드(행)을 합치는 작업으로 JOIN이 테이블을 옆으로 이어서 합치는 작업이었다면, 

UNION은 테이블을 아래로 이어서 합치는 작업이다. 

```SQL
SELECT ID, NAME FROM MEMBER
    UNION
SELECT WRITER_ID, TITLE FROM NOTICE;
```



UNION의 종류

`UNION`

▶ 같은 데이터의 경우, 하나만 (최종 출력물에) 남긴다.

`MINUS`

▶ 같은 데이터의 경우, 모두 없앤다. (유니크한 것만 최종 결과물로 출력)

`INTERSECT`

▶ 같은 데이터만 (최종 출력물에) 남긴다.(교집합)

`UNION ALL`

▶ 같은 데이터도 중복해서 모두 (최종 출력물에) 남긴다.  

  



---

40

View

실제 업무를 할 때는 여러 테이블이 합쳐지고 가공된 형태의 테이블이 주로 사용된다. 

▷ 예를 들면 학생정보 테이블과 도서 대출 테이블이 합쳐진 테이블이 도서관에서 사용되는 것과 같다.  

이렇게 실무에서 사용되는 최종 형태의 테이블이 존재하는데, 매번 작업을 할 때마다 재료가 되는 원본 테이블들을 조합하고 가공해서 최종 형태의 테이블을 만들어내는것은 불필요한 작업으로 느껴진다. 

이를 극복하고자 최종 형태의 테이블을 미리 만들어서 저장해 놓고 이를 VIEW라고 부르며 편하게 불러와서 사용하는 것이 일반적이다. 



> #### View 만들기

* `CREATE VIEW <만들려는 view 이름 > AS` 이하에 만들려는 테이블을 작성한다.

```SQL
CREATE VIEW NOTICEVIEW 
AS
SELECT N.ID, N.TITLE, N.WRITER_ID, M.NAME WRITER_NAME, COUNT(C.ID) COMMNET_CNT
FROM MEMBER M
RIGHT OUTER JOIN NOTICE N ON M.ID = N.WRITER_ID
LEFT OUTER JOIN "COMMENT" C ON N.ID = C.NOTICE_ID
GROUP BY N.ID, N.TITLE, N.WRITER_ID, M.NAME;
```

  

> #### view 사용하기

* 그냥 불러와서 쓰면 된다. 

```sql
SELECT * FROM NOTICEVIEW;
```

* 뷰를 사용함으로서 테이블 저장은 각각 독립적으로 하고(중복 방지) 사용하는 것은 통합해서 쓸 수 있게 된다.



---

42

제약조건

▶데이터를 입력할 때 제약조건을 넣는 것에 대해 배운다.

(예시: 어떤 칼럼A에는 null값을 넣을 수 없도록 제약조건을 걸어두는 방법)



제약조건을 걸 수 있는 3가지 층위

1) 도메인 (칼럼) 

* 칼럼 별로 올 수 있는 데이터의 유효한 범위를 '도메인'이라고 한다. 

* (예시) '학번'이라는 칼럼에는 0보다 큰 정수가 올 수 있을 때, '0보다 큰 정수'는 도메인이 된다.
  

  [ 도메일 제약조건(3) ]

  * 도메인이 아닌 데이터(값)가 올 수 없도록 하기 위해 사용되는 제약조건

    (1) `NOT NULL`

    * 값을 넣지 않으면(=null인 경우0) 에러가 발생하는 제약조건
    * 반드시 값을 입력해야 하는 칼럼에 사용한다.
      

    (2) `DEFAULT`

    * `NOT NULL` 처럼 반드시 값을 입력해야 하는 칼럼인데, 값을 **사용자가 입력하지 않으면** 지정된 기본값이 사용되는 제약조건
    * 대표적인 예로 조회수이 있다.
      → 반드시 작성되어야 하는 데이터지만 사용자가 직접 입력하는 것이 아닌 지정된 기본값을 가져와서 입력된다.

    (3) `CHECK`

    * 입력값에 대해서 값의 유효범위(도메인 범위)를 체크하는 제약조건



2) 엔티티 (테이블)

3) 릴레이션 (테이블 간의 관계) 

  

  

> #### NOT NULL 제약조건

1) 테이블을 생성할 때 적용하는 방법

```SQL
CREATE TABLE TEST 
(
	ID VARCHAR2(50) NOT NULL,
    EMAIL VARCHAR2(200) NULL,
    PHONE CHAR(13) NOT NULL
);
```

* 위처럼 테이블을 생성하는 경우, `ID`와 `PHONE` 칼럼에는 반드시 값이 입력되어야 한다.
* 값이 입력되지 않는 경우 에러 발생

  

2) 이미 만들어진 테이블에 대해 NOT NULL 조건 주는 경우

```SQL
ALTER TABLE TEST MODOFY EMAIL VARCHAR2(200) NOT NULL;
```

   

> #### DEFAULT 제약조건

1) 테이블 생성시

```SQL
CREATE TABLE TEST 
(
	ID VARCHAR2(50) NOT NULL,
    PWD VARCHAR2(200) DEFAULT '111'
);
```

  

2) 테이블 생성 후에 

```SQL
ALTER TABLE TEST MODIFY EMAIL VARCHAR2(200) DEFAULT '111';
```





