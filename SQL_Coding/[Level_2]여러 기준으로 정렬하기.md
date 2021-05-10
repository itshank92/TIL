## 여러 기준으로 정렬하기

출처: https://programmers.co.kr/learn/courses/30/lessons/59404     





- 

> 코드

```SQL
SELECT ANIMAL_ID, NAME, DATETIME FROM ANIMAL_INS ORDER BY NAME ASC, DATETIME DESC;
```



* `ORDER BY` 뒤에 오는 조건들은 `,`를 기준으로 작성한다.