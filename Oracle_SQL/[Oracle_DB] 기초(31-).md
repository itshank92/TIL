31

HAVING 절



회원별 게시글 수를 조회하시오. 단 게시글이 2개 이하인 목록만 출력하시오.

```SQL
SELECT WRITER_ID, COUNT(ID) FROM NOTICE GROUP BY WRITER_ID HAVING COUNT(ID) <= 2
```

* WHERE 절에서는 집계 함수를 쓸 수 없다( GROUP BY 이후에 오는 HAVING 절에서 사용한다.)

  * 집계함수란?

    ![image-20210728132944014]([Oracle_DB] 기초(31-).assets/image-20210728132944014.png)

  

32 

순위함수

  

> #### ROW_NUMBER()

  

`HIT` 칼럼을 기준으로 정렬된 순서대로 각 ROW의 일련번호를 붙이고 싶을 때

* 잘못된 쿼리문 (`ROWNUM` 사용)

  ```SQL
  SELECT ROWNUM, ID, TITLE, HIT FROM NOTICE ORDER BY HIT; 
  ```

  * `SELECT`는 `ORDER BY`보다 먼저 실행되기 때문에 `ROWNUM`은 `ORDER BY` 기준을 적용받지 않는다. 



`ORDER BY`를 한 다음에 각 ROW의 일련번호 붙이기 ▶ `ROW_NUMBER()` 함수 사용

* `ROW_NUMBER()` 함수는 조건문을 줄 수 있는데, 해당 조건문 실행 이후에 ROW별로 일련번호를 주는 작업을 수행할 수 있다.
  * `ROW_NUMBER() OVER (조건문)` 형태로 작성한다.
* 현재 우리는 `ORDER_BY HIT` 이후에 각 ROW에 일련번호를 부여해야 하기에, 조건문은 `ORDER BY HIT`이 된다. 

```SQL
SELECT ROW_NUMBER() OVER (ORDER BY HIT), ID, TITLE, HIT FROM NOTICE;
```

  

  

> #### RANK

  

정렬(`ORDER BY`) 이후에 순위를 붙이고 싶을 때 사용하는 함수

* `ROW_NUMBER()` 과 마찬가지로 `OVER (조건문)` 형태로 작성한다.
  * `RANK OVER (조건문)`

```sql
SELECT RANK() OVER (ORDER BY HIT), ID, TITLE, HIT FROM NOTICE;
```

  

* `RANK()` 함수의 경우 공동 순위를 다음과 같이 처리한다.
  * 1위 1명, 2위 1명, 3위 2명이 있는 경우 4위가 없고 5위로 넘어간다.
* 만약, 공동 3위 이후에 4위가 오도록 하고 싶다면 `DENSE_RANK()` 함수를 사용하자.

  

  

> #### DENSE_RANK

  

* 공동 순위 다음에 해당 순위 +1로 정렬하는 RANK 함수

```SQL
SELECT DENSE_RANK() OVER (ORDER BY HIT), ID, TITLE, WRITER_ID, REGDATE, HIT FROM NOTICE;
```

  

  

> #### 조건문에서 정렬 수행시 특정 기준을 주고 싶을 때 ▶ PARTITION BY

* 위 예제에서 조건문에서 사용했던 `ORDER BY HIT`는 `HIT`이라는 칼럼값을 기준으로 (오름차순) 정렬을 한 것이다. 
* 이 때, 모든 ROW의 `HIT`을 대상으로 정렬하는 것이 아닌 <u>같은 `WRITER_ID`</u>의 `HIT`들  그룹 별로 정렬을 하고 싶은 경우 `PARTITION BY`을 사용하면 된다. 

* RANK를 매기는데, 같은 사용자가 작성한 글들의 HIT에 대한 RANK를 매기는 조건식은 다음과 같다.

  ```SQL
  SELECT DENSE_RANK() OVER (PARTITION BY WRITER_ID ORDER BY HIT) ID, TITLE, HIT FROM NOTICE;
  ```

  

  

---

33

부조회(서브쿼리)



서브쿼리는 왜 사용하는가?

쿼리문에는 작동 순서가 있는데, 그 순서를 바꿔야 할 때 서브쿼리를 사용한다.

  



> #### 쿼리문 구절의 순서를 바꿔야 하는 경우



예제를 통해 서브쿼리의 용도를 이해해보자.



**[ 예제 상황 ]**

최신 가입순으로 정렬한 결과에서 상위 10명을 구하고 싶을 때



**[ 작업 구분 ]**

1) 최신 등록순으로 정렬

​	▶ `ORDER BY REGDATE DESC;`

2) 그 결과에서 상위 10명 추출

​	▶ `WHERE ROWNUM BETWEEN 1 AND 10`



**[ 문제점 ]**  

하지만 위의 방식으로 쿼리문 작성 시 쿼리문 수행 순서에 따라 `WHERE` → `ORDER BY` 로 수행된다.

따라서 원하는 결과를 얻을 수 없다.



**[ 해결 ]**

서브쿼리를 사용

위와 같은 상황에서 서브쿼리를 사용하면 쿼리문 수행 순서를 바꿀 수 있다.

먼저 수행하고 싶은 쿼리문을 소괄호()로 묶어서 `FROM` 절에 넣는방식으로 사용한다.

```SQL
SELECT * FROM (SELECT * FROM MEMBER ORDER BY REGDATE DESC) WHERE ROWNUM BETWEEN 1 AND 10;
```

* 먼저 수행하고 싶은 정렬 쿼리문을 작성해서 소괄호로 묶는다.

  ```SQL
  (SELECT * FROM MEMBER ORDER BY REGDATE DESC)
  ```

* 정렬 쿼리문을 FROM에 담고 WHERE 쿼리문을 같이 작성한다.

  ```SQL
  SELECT * FROM (SELECT * FROM MEMBER ORDER BY REGDATE DESC) WHERE ROWNUM BETWEEN 1 AND 10;
  ```

  

  

서브쿼리 두번째 예시

**[ 상황 ]**

나이가 평균 나이 이상인 회원 목록을 조회하시오.



**[ 작업 나누기 ]**

1) 평균 나이를 구한다.

```SQL
SELECT AVG(AGE) AVG_AGE FROM MEMBER;
```



2) 회원들 나이와 평균 나이를 비교해서 이상인 회원만 조회한다. (서브쿼리문 사용)

``` SQL
SELECT * FROM MEMBER WHERE AGE >= (SELECT AVG(AGE) AVG_AGE FROM MEMBER);
```

