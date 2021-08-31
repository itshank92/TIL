27

형식 변환



> #### 문자열 변환 ▶  TO_CHAR(숫자, 형식(optional))

* 숫자를 문자열로 변환

```SQL
SELECT TO_CHAR(12345678, '99999,999') || 'HELLO' FROM DUAL;

/*  12345,678HELLO  */
```

  

* 날짜를 문자열로 변환

```SQL
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') FROM DUAL;

/*2021-07-28 08:03:35 */
```

​    

> #### 날짜 변환 ▶ TO_DATE(문자열, 형식(optional))

* 문자를 날짜로 변환

```SQL
SELECT TO_DATE('2021-07-28', 'YYYY-MM-DD' ) FROM DUAL;

/* 21/07/28 */
```

  

> #### 숫자 변환 ▶ TO_NUMBER(문자열)

* 문자를 숫자로 변환

```SQL
SELECT TO_NUMBER('1994') FROM DUAL;

/* 1994 */
```

  

  

---

28

NULL 관련 함수



> #### NULL 대체값 제공 함수 ▶ NVL

* `NVL(대상 칼럼, 대체값)` : 칼럼의 값 중 NULL이 있으면 대체값으로 제공한다. 

  ```SQL
  SELECT NVL(AGE,0) FROM MEMBER;
  
  /* 0 */
  ```

  * MEMBER.AGE 칼럼의 값을 꺼내는 데, 꺼내는 값 중 NULL 값은 0으로 나오게 한다.
    (위에서 결과값이 한 줄만 나온 이유는 MEMBER에 데이터가 하나밖에 없기 때문)



> #### NULL & NOTNULL 대체값 제공 함수  ▶ NVL2

* `NVL2(대상 칼럼, NOTNULL 대체값, NULL 대체값)`

  ```SQL
  SELECT NVL2(AGE, 100, 999) FROM MEMBER;
  
  /* 999 */
  ```

  

> #### 두 값이 같은 경우만 NULL, 아니면 첫 번째 값 반환 함수 ▶ NULLIF

* `NULLIF(값1, 값2)`

  ```SQL
  SELECT NULLIF(AGE,19) FROM MEMBER;
  
  /* NULL */ 
  ```

  * 위의 데이터에서 MEMBER.AGE의 값은 NULL로 되어 있음

  

> #### 값에 따라 특정 값을 맵핑해서 보여주는 함수 ▶ DECODE

* `DECODE(기준 칼럼, 비교값, True일때 출력값, False일때 출력값)`

  ```SQL
  SELECT DECODE(GENDER, '남성', '남성입니다', '여성입니다') FROM MEMBER;
  
  /* 남성입니다. */
  ```

  * MEMBER.GENDER 칼럼의 값이 '남성'인 경우 '남성입니다'를, 아닌 경우 '여성입니다'를 출력하는 함수

  

* DECODE에는 비교값과 True일때 출력값을 여러개 둘 수 도 있다. 

  * 마지막 값이 모든 비교가 해당되지 않을 때의 출력값이 된다.

  ``` SQL
  SELECT DECODE(SUBSTR(PHONE,1,3), '011', 'SKT', '016', 'KT', '019', 'LG', '기타') FROM MEMBER;
  ```

  * PHONE의 인덱스 1번부터 3번 데이터가 
    * '011'인 경우 'SKT'를 출력
    * '016'인 경우 'KT'를 출력
    * '019'인 경우 'LG'를 출려
    * 위에 해당하지 않는 경우 '기타'를 출력 