34

조인(JOIN)



테이블과 테이블을 합치는 작업을 JOIN이라고 한다.



조인의 종류

![image-20210728155512117]([Oracle_DB] 기초(34-).assets/image-20210728155512117.png)

  

조인의 예시

![image-20210728155537681]([Oracle_DB] 기초(34-).assets/image-20210728155537681.png)

좌측 테이블의 ID 칼럼과 우측 테이블의 WRITER ID가 연결고리가 된다. 

좌측 테이블 데이터 한개가 우측 테이블 여러개의 데이터를 만드는 관계다.

이를 1:N 관계라고 하는데, 1쪽을 부모테이블, N쪽을 자식 테이블이라고 한다. 





두 테이블을 합칠 때는 데이터가 많은 쪽(N쪽)에 맞춰서 데이터가 적은쪽(1쪽) 데이터가 복사되어 합쳐진다.

![image-20210728172740257]([Oracle_DB] 기초(34-).assets/image-20210728172740257.png)



이렇게 필요할 때만 테이블을 합쳐서 결과를 얻게되는 것의 장점은 바로 데이터 공간의 절약이다.

위 그림에서 1쪽에 해당하는 테이블은 저장될 때 중복되는 것이 제거가 된 상태로 저장된다.

▶ 즉 1줄의 데이터만 저장된다.

단지 다른 테이블과 JOIN하여 출력할 때만 데이터가 복사되어 출력된다. 

이러한 방식은 저장 공간을 효율적으로 활용하면서 원하는 데이터를 얻을 수 있다는 장점이 있다. 



위 병합과정을 쿼리문으로 수행하는 방법

```SQL
SELECT * FROM TABLE1 INNER JOIN TABLE2 ON TABLE1.COLUMN_A = TABLE2.COLUMN_B
```

* `TABLE1`, `TABLE2`: 서로 합치려는 테이블
  
  * `TABLE1 INNER JOIN TABLE2` 형식으로 작성
* `ON` 뒤에는 병합 조건을 작성

  

**[ INNER JOIN은 모든 JOIN문의 default 방식이다. ]**

따라서 쿼리문에 JOIN 방식을 명시하지 않고 그냥 JOIN만 쓰는 경우 자동적으로 INNER JOIN으로 실행된다.

`SELECT * FROM TABLE1 JOIN TABLE2 ... `  == `SELECT * FROM TABLE1 INNER JOIN TABLE2 ... `



> #### INNER JOIN

**서로 관계가 있는** (=조건문에 입력한 두 테이블의 칼럼값이 일치하는) ROW만을 합치는 `JOIN`



* INNER JOIN 이해 테스트

  ![image-20210728205141608]([Oracle_DB] 기초(34-).assets/image-20210728205141608.png)

    

  [ 정답 ]  3개

  \>\> 두 테이블에서 서로 관련이 있는 데이터는 3개 밖에 없음.

  (나머지는 관계가 없는 데이터이고 이를 Outer(아우터)라고 부르기도 한다)

  

---

35 

OUTER JOIN(아우터조인)



> #### OUTER JOIN의 작성 방법

기본적으로 `OUTER JOIN`의 쿼리문 작성은 `INNER JOIN`과 동일하다.

`TABLE1`과 `TABLE2`를 `OUTER JOIN`으로 결합해주고,  ▶ ▶ ▶ `TABLE1 OUTER JOIN TABLE2`

`ON` 뒤에 결합 조건문을 작성하는 방식이다. ▶ ▶ ▶ ▶ ▶ ▶ `ON TABLE1.COL_A = TABLE2.COL_B`



※ 단 차이점은 `OUTER JOIN`의 경우 앞에 `LEFT` ,  `RIGHT` ,  `FULL` 이 붙어서 작성된다는 것과 따라서 3가지 작동 방법(결합 방법)이 존재한다는 것이다. 

```SQL
SELECT * FROM TABLE1 LEFT/RIGHT/FULL OUTER JOIN TABLE2 ON TABLE1.COL_A = TABLE2.COL_B;
```

  

> #### OUTER JOIN의 기본 작동 방법

* 기본적으로 조건문( `ON` 뒤에 작성)에 해당하는 '서로 관계가 있는' 행들은 결합 대상이 된다. 

  ▶ INNER JOIN과 동일

* 단, 이름에서 알 수 있듯이, 조건문에 해당하지 않는 Outer도 조회 대상이 된다는 점이 다르다.

  ▶ 즉, 결합 시 null 값이 존재하게 되는 Outer도 조회 결과에 포함된다.

* 어떤 테이블의 Outer를 결과 값에 포함시킬지는 `OUTER JOIN` 앞에 오는 LEFT, RIGHT, FULL이 정한다.

  ▶ `LEFT` : 왼쪽 테이블의 Outer만 포함

  ▶ `RIGHT` : 오른쪽 테이블의 Outer만 포함

  ▶ `FULL` : 양 측 테이블의 Outer 모두 포함