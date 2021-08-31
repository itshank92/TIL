29

SELECT 구절과 정렬(ORDER BY)





> #### SELECT 문의 구성 (★순서 중요)

※ 전체 SELECT 문의 구성이 한 화면에 나와있지 않아서 처음 3개 요소 이후에 나오는 요소들은 필기로 정리하였다. 

![image-20210728091542859]([Oracle_DB] 기초(29-).assets/image-20210728091542859.png)

1. `FROM` : 어떤 테이블에서 데이터를 가져올지 결정  



2. `WHERE` : 어떤 조건으로 데이터를 가져올지 결정  



3. `GROUP BY`  : 최종 데이터를 어떻게 집계(집합화)해서 보여줄지 결정
   * 집계 방식에는 여러가지(`COUNT`, `AVG`,..)가 존재한다.  



4. `HAVING`: 집계된 데이터를 대상으로 조건문을 넣을 때 사용
   * `GROUP BY`의 집계 방식(`COUNT`, `AVG`,..)은 `WHERE` 절 안에서 사용이 불가하다.
   * 따라서 `GROUP BY` 처리 이후 데이터에 조건을 주기 위해서는 `HAVING`이라는 명령어를 사용해서 표현한다.   



5. `ORDER BY` : `HAVING` 처리까지 끝난 데이터를 어떤 방식으로 정렬해서 보여줄 것인지 결정한다.
   * 데이터를 정렬할 때 사용한다고 기억하면 된다. 
   * 정렬 방식
     * `ASC` : 오름차순 정렬
     * `DESC`: 내림차순 정렬





> #### ORDER BY

* 이름을 기준으로 역순 정렬 방식으로 조회하시오.

  ```SQL
  SELECT * FROM MEMBER ORDER BY NAME DESC;
  ```

  

* 회원 중에서 '박'씨 정을 가진 회원을 조회하시오. (단 나이를 오름차 순으로 정렬)

  ```SQL
  SELECT * FROM MEMBER WHERE NAME LIKE '박%' ORDER BY AGE ASC;
  ```

  * `FROM`,  `WHERE`,  `ORDER BY`  순서 주의	



* 정렬 기준을 여러개 설정 가능하다.

  ```SQL
  SELECT * FROM MEMBER WHERE NAME LIKE '박%' ORDER BY AGE ASC REGDATE DESC;
  ```

  * AGE를 기준으로 우선 정렬 수행(오름차순)
  * 이후 같은 값의 경우 REGDATE를 기준으로 정렬 수행(내림차순)

  

---

집계함수

![image-20210728132944014]([Oracle_DB] 기초(29-).assets/image-20210728132944014.png)

  

> #### COUNT

데이터의 갯수를 세는 함수

* TITLE에 있는 데이터 갯수를 구하시오 

  ```SQL
  SELECT COUNT(TITLE) FROM MEMBER;
  ```

  

* COUNT 함수는 NULL의 갯수는 세지 않는다. 
  * 따라서 COUNT를 사용해서 전체 데이터 갯수를 세려면 ID와 같이 NULL이 들어있지 않는 칼럼으로 COUNT를 수행한다. 



> #### SUM

데이터의 총합을 구하는 함수

* HIT에 들어있는 값의 총 합을 구하시오.

  ```SQL
  SELECT SUM(HIT) FROM NOTICE;
  ```

  

> #### AVG

데이터의 평균을 구하는 함수



작성자 별로 몇 개의 게시글을 썼는지 확인하시오.

```SQL
SELECT WRITER_ID, COUNT(ID) FROM NOTICE GROUP BY WRITER_ID;
```

   

  



> #### SELECT 문 실행순서

* 실행순서가 중요한 이유
  * 먼저 실행된 영역의 별칭을 이후 실행된 영역에서 사용(호출)할 수 있다. 
  * 하지만 반대는 불가능하다.(주의)

  

1) FROM

2) CONNECT BY

3) WHERE

4) GROUP BY

5) HAVING

6) SELECT

7) ORDER BY



SELECT 문 실행 순서는 ***" 북어 왜구했어요?"***  로 외울 수 있다.

* 북  FROM의 프
* 어 CONNECT의 커
* 왜 WHERE의 왜
* 구 GROUP BY의 그
* 했 HAVING의 해
* 어 SELECT의 세
* 요 ORDER BY의 오

---

