# Join



### 사용하는 이유

한 테이블에 모든 정보를 담을 수 없다 

→  따라서 원하는 정보를 찾기 위해 여러 테이블을 함께 조회해야한다 

→ 이때 Join을 통해 여러 테이블을 하나의 테이블로 만들어서 원하는 정보를 조회할 수 있다.       



### Join의 효과

여러 테이블들의 필드들이 하나의 테이블로 합쳐지게 된다.       



### Join의 종류

> INNER JOIN

* 교집합을 만드는 쿼리문으로 Join의 기준이 되는 필드의 값이 일치하는 일치하는 레코드(행)만 가져온다.

```sqlite
SELECT *
FROM A
INNER JOIN B ON A.column1 = B.column1
```

​      

> NATURAL JOIN

* 두 테이블에서 필드명과 데이터 타입이 같은 필드에 대해서 INNER JOIN 수행

```sqlite
SELECT *
FROM 
```

