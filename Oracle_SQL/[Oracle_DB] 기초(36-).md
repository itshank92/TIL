36

`OUTER JOIN`을 이용한 게시글 목록 조회



> ####  JOIN을 사용할 때 주의해야 하는 점들

컬럼을 합칠 때 두 테이블에서 같은 이름의 컬럼이 존재하는 경우

* 이 때 그냥 합치려고 하면 에러가 발생한다.



[ 해결 방안 ]

이 때 각 칼럼을 그냥 칼럼명이 아닌 `테이블명.칼럼명` 형태로 `SELECT`에 포함시키면 된다. 

```sql
SELECT NOTICE.ID, NOTICE.NAME, MEMBER.NAME FROM MEMBER INNER JOIN NOTICE ON MEMBER.ID = NOTICE.WRITER_ID;
```



이렇게 하는 경우 칼럼명이 길어지는게 싫다면 테이블 명을 별칭을 주면 더 간단해진다. 

```SQL
SELECT N.ID, N.NAME, M.NAME FROM MEMBER M INNER JOIN NOTICE N ON M.ID = N.WRITER_ID;
```





[ 실전 예제 ]

회원별 작성한 게시글의 수를 조회하시오.(0개 작성한 회원도 조회해야함)

* 잘못된 쿼리문 (0개 작성한 회원 정보는 나오지 않음)

  ```SQL
  SELECT M.ID, M.NAME, COUNT(N.ID) FROM MEMBER M JOIN NOTICE N ON M.ID = N.WRITER GROUP BY M.ID, M.NAME;
  ```

  * `GROUP BY`에는 여러개의 칼럼이 올 수 있고, 해당 칼럼들 조합으로 그룹화가 된다. 
  * `FROM`절에 `INNER JOIN`한 결과 테이블이 오게 된다. 
  * `INNER JOIN`을 사용했기 때문에 글을 0개 작성한 회원의 경우 `NOTICE` 테이블에 일치하는 데이터가 없어서 최종 결과물에서 제외된다. 

  

* 올바른 쿼리문 (0개 작성한 회원 정보가 나온다)

  ```SQL
  SELECT M.ID, M.NAME, COUNT(N.ID) FROM MEMBER M LEFT OUTER JOIN NOTICE N ON M.ID = N.WRITER GROUP BY M.ID, M.NAME;
  ```

  * `LEFT OUTER JOIN`을 통해서 왼쪽에 있는 테이블은 모두 다 출력한다.
  * 즉 `OUTER JOIN`은 주인공이 되는 테이블을 결정해서 병합한다.
    * 위에서는 `MEMBER`가 주인공이기 때문에 `LEFT OUTER JOIN`이 사용된 것이다. 

  

---

37

SELF JOIN



JOIN의 종류는 크게 INNER JOIN과 OUTER JOIN으로 나뉜다. 

그렇다면 SELF JOIN은 무엇인가?

SELF JOIN은 테이블 하나가 자신이 자신과 합쳐지는 것이다. 



여기서 생기는 의문점 2개

> 1. #### 그렇다면 자기 자신을 합칠 이유가 있을까?



> 2. #### 자기 자신을 어떠한 방식으로 합치는 것일까?