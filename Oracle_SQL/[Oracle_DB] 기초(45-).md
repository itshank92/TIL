45

Entity 제약조건 (테이블 전체의 데이터 유효성 검증을 위한 제약 조건)



> #### Entity 제약 조건이 필요한 이유

이전에는 도메인 층위에서 검증을 수행하는 제약조건들에 대해 학습하였다.

( `NOT NULL` , `DEFAULT` , `CHECK` )

하지만 이렇게 도메인 단위에서 데이터 유효성이 검증된다고 해서 테이블 전체의 데이터 유효성이 보장되는 것은 아니다.

▶ 같은 데이터가 중복되어 입력되는 경우, 도메인 단위의 검증에서는 이를 식별, 방지하지 못한다.

* 같은 데이터가 입력된 것이 문제는 아니다. 예를 들면 김씨와 이씨의 혈액형이 A형으로 동일할 때, 혈액형 테이블에 A형이 두 번 입력되는 것은 문제라고 할 수 없다.
  * 데이터가 가리키는 실체(김씨, 이씨)가 다르기 때문.
* 하지만 김씨의 혈액형 데이터가 두 번 중복되어 입력되는 경우는 문제가 된다.

▶ 따라서 도메인 단위의 검증 상위에 (테이블 전체 검증을 위한) Entity 단위의 검증이 추가적으로 필요하다.

  

  



> #### Entity 제약 조건1 ▶ PRIMARY KEY (기본키)

* 테이블의 각 Row의 고유성을 표현하기 위해 식별 칼럼을 따로 설정하여 그 곳에 고유한 키 값을 넣어 관리한다. 
  
* 식별 칼럼의 값은 `NULL`도 허용하지 않고 중복도 허용하지 않는다.

   

  

> #### Entity 제약 조건2 ▶ UNIQUE 

* `NULL`은 허용하지만 중복은 허용하지 않는 칼럼에 대해 사용하는 제약 조건



  

> #### 테이블 생성 시 PRIMARY KEY, UNIQUE 설정

```SQL
CREATE TABLE NOTICE 
(
	ID NUMBER PRIMARY KEY,
    TITLE VARCHAR2(300) NOT NULL,
    WRITER_ID VARCHAR2(50) NOT NULL UNIQUE,
    CONTENT VARCHAR2(4000),
    REGDATE DATE DEFAULT SYSDATE,
    HIT NUMBER DEFAULT 0
);
```

* 만약 `PRIMARY KEY` 제약 조건에 명시적으로 (제약 조건) 이름을 부여하고 싶은 경우, 아래와 같이 작성하면 된다. 

  ```SQL
  CREATE TABLE NOTICE 
  (
  	ID NUMBER CONSTRAINT NOTICE_ID_PK PRIMARY KEY
      /* 생 략 */
  )
  ```

  * `CONSTRAINT` + `제약 조건 이름`  형식으로 작성한다.



* 위와 같이 작성하면 쿼리문의 직관성이 떨어지므로 따로 아랫부분에 Entity 제약조건을 몰아 넣어서 작성하기도 한다.

  ```SQL
  CREATE TABLE NOTICE
  (
  	ID NUMBER,
      TITLE VARCHAR2(300) NOT NULL,
      WRITER_ID VARCHAR2(50) NOT NULL,
      CONTENT VARCHAR2(4000),
      REGDATE DATE DEFAULT SYSDATE,
      HIT NUMBER DEFAULT 0,
      
      CONSTRAINT NOTICE_ID_PK PRIMARY KEY(ID),
      CONSTRAINT NOTICE_WRITER_ID_UK UNIQUE(WRITER_ID)
  )
  ```

  

  

> #### 테이블 생성 후 ENTITY 제약 조건 추가

```SQL
ALTER TABLE NOTICE
ADD CONSTRAINT NOTICE_ID_PK PRIMARY KEY(ID);
```

  

```SQL
ALTER TABLE NOTICE
ADD CONSTRAINT NOTICE_WRITER_ID_UK UNIQUE(WRITER_ID);
```

  

  

---

46

시퀀스 (일련 번호)



ID에 사용할 일련번호를 시퀀스라고 부른다.

사용자가 직접 규칙을 설정하여 시퀀스를 만들고 관리할 수 있다. 



> 이름 생성하기(컨벤션)

* `TABLE명_칼럼명_SEQ` 형태로 작성한다.
* (예시) `NOTICE_ID_SEQ`

  

  

> 캐시

* 캐시는 사용할 일련번호를 뽑아 놓을 때 미리 캐시에 일정 크기의 번호를 뽑는 것을 의미한다.
* 이러한 방식을 통해 일련번호를 사용할 때마다 새로 뽑는 것이 아닌 캐시에 있는 것을 사용해 속도를 높일 수 있다.