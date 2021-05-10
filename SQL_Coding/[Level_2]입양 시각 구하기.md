# 입양 시각 구하기

출처: https://programmers.co.kr/learn/courses/30/lessons/59412       



> 코드

```SQL
SELECT DISTINCT(EXTRACT(HOUR FROM DATETIME)) AS 'HOUR' ,COUNT('HOUR') AS 'COUNT' FROM ANIMAL_OUTS GROUP BY HOUR HAVING HOUR > 8 AND HOUR < 20 ORDER BY HOUR;
```



* `EXTRACT(HOUR FROM 필드)`:  Datetime유형의 필드에서 시각 데이터를 추출한다.
  * 분을 추출하고 싶은 경우 `MINUTE`, 초를 추출하고 싶은 경우 `SECOND`를 넣으면 된다.      



* 항상 조건문은 쿼리문에 반영해야한다. ▶ 지금 당장 데이터를 추출하는데 문제가 없어도 추후에 다른 데이터를 입력받는 경우 오류가 발생할 수 있다. 
  * `HOUR > 8 AND HOUR < 20`