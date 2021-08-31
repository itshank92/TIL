43

체크 제약 조건

  

> 테이블 생성할 때 체크 제약 조건을 설정하는 방법

```sql
CREATE TABLE TEST 
(
	ID VARCHAR2(5) NULL,
    PHONE VARCHART2(200) CHECK(PHONE LIKE '010-____-____') NOT NULL
);
```

  

> 테이블 생성 후 체크 제약 조건을 설정하는 방법

```SQL
ALTER TABLE TEST ADD CONSTRAINT TEST_PHONE_CHK1 CHECK(PHONE LIKE '010-____-____');
```







----

44

정규식을 이용한 체크 제약 조건



> 체크 제약 조건 없애기

```SQL
ALTER TABLE MEMBER DROP CONSTRAINT MEMBER_PHONE_CHK1
```

* `MEMBER_PHONE_CHK1`는 삭제하려는 (기존의) 체크 제약 조건의 명칭

  

 

> 정규식으로 작성한 제약 조건

```SQL
REGEXP_LIKE(PHONE,'^01[10]-\d{3,4}-\d{4}$')
```

  

  

> 정규식을 체크 제약 조건으로 만들기

```SQL
ALTER TABLE MEMBER ADD CONSTRAINT MEMBER_PHONE_CHK1 CHECK(REGEXP_LIKE(PHONE, '^01[01]-\d{3,4}-\d{4}$'))
```

* `MEMBER_PHONE_CHK1`는 만드는 체크 제약 조건의 이름으로 설정한 값
* `CHECK`이후에 제약조건의 조건문을 작성해준다.

  

**[ 체크 제약 조건 생성시 유의 사항 ]**

* 해당 체크 제약 조건에 부합하지 않는 기존의 데이터가 존재하는 경우 에러가 발생한다. 
* 따라서 체크 제약조건을 만들기 전에 해당 조건에 포함되는 데이터를 모두 올바르게 수정 or 입력한 다음 제약조건을 생성해야 한다. 

   

> (cf) 현재 존재하는 제약조건(CONSTRANINT)를 모두 확인하기

* 오라클에서는 현재 사용자와 관련된 많은 메타 데이터를 user_로 시작하는 뷰에 담고 있다.

```SQL
SELECT * FROM user_constraints;
```

