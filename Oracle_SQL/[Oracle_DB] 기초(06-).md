06 

멤버 테이블 생성하기



테이블 

* 구조화된 데이터로 다른 프로그래밍 언어에서는 Class 혹은 Entity로 불린다.

* 테이블에 속한 각 데이터는 생성 시 그 자료형을 명시해줘야 한다.
  (Java에서 클래스의 속성 값 생성시 자료형 명시해주는 것과 동일)

* 데이터 생성 시 명시하는 자료형은 Oracle에 존재하는 자료형으로 명시해줘야 한다.

  ▶ 이러한 이유로 07, 08강에서는 **Oracle 데이터 형식**을 배운다. 



테이블 생성 명령어 

```sql
CREATE TABLE MEMBER 
(
	ID       VARCHAR2(50),
    PWD      VARCHAR2(50),
    NAME     VARCHAR2(50),
    GENDER   VARCHAR2(50),
    AGE      NUMBER,
    BIRTHDAY VARCHAR2(50),
    PHONE    VARCHAR2(50),
    REGDATE  DATE
);
```





SQL문 실행시키는 단축키

1) SQL문끝에서 세미콜론(;)을 붙여줌

2) 해당 위치에서 `Ctrl`  +  `ENTER`키를 눌러줌

  



---

07

Oracle 데이터 형식_1(문자열 형식)



데이터 형식은 크게 4 카테고리로 나뉜다.

1) 문자 → 홑 따옴표(')를 사용해서 표현한다.

2) 숫자

3) 날짜 → Date와 TimeStamp가 존재한다.

4) 큰 데이터 





문자 형식 자료형(4가지)

* CHAR(size)

  * size의 단위는 기본적으로 1byte를 뜻한다.
    * CHAR를 단위로 명시하는 경우 글자 수를 뜻한다.
  * 입력된 값의 길이와 상관없이 size만큼의 공간을 사용한다.
  * 따라서 가변 길이 데이터의 경우 CHAR를 사용하면 공간 낭비가 발생한다.
  * 하지만 검색의 경우 매우 빠르게 수행된다는 장점이 있다.  

  

* VARCHAR2(size)

  * 가변 길이의 데이터를 저장하기 적합한 자료형이다.
  * 입력된 값의 길이만큼만 저장공간을 사용한다.
  * 따라서 가변 길이 데이터의 경우 VARCHAR2를 사용하면 공간 낭비를 피할 수 있다. 
  * 하지만 검색의 경우 속도가 느리다는 단점이 있다.  

  

* NCHAR(size)

  * N이 붙은 자료형은 UTF-8과 같이 영어가 아닌 문자도 표현할 수 있는 자료형이다. 
    * N for National Language
  * size의 단위는 기본적으로 CHAR(문자 1개)가 된다.   

  

* NVARCHAR2(size)



> 최대 사이즈

기본(STANDARD)적으로는 4000bytes가 최대

EXTENDED를 적용하면 32767bytes가 최대가 된다. 



한글과 영어의 BYTE 수

> 단순 길이를 보여주는 LENGTH

SELECT LENGTH('ab') FROM DUAL; 

▶ 2

SELECT LENGTH('한글') FROM DUAL;

▶ 2

> BYTE크기를 보여주는 LENGTHB

SELECT LENGTHB('ab') FROM DUAL;

▶ 2

SELECT LENGTHB('한글') FROM DUAL;

▶ 6



[결론]

한글은 한글자가 3byte를 차지한다.(영어는 1byte)





칼럼에 값넣기

```SQL
INSERT INTO MEMBER(GENDER) VALUES ('남성');
```





