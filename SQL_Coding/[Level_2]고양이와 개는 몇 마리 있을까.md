# 고양이와 개는 몇 마리 있을까?

출처: https://programmers.co.kr/learn/courses/30/lessons/59040     



> 코드

```SQL
SELECT ANIMAL_TYPE, COUNT(ANIMAL_TYPE) AS 'count' FROM ANIMAL_INS GROUP BY ANIMAL_TYPE ORDER BY ANIMAL_TYPE;
```



* `AS '필드명'` : 사용자가 지정하려는 필드명을 입력할 때는 `AS`를 사용하고 따옴표로 표현한다.
* `ORDER BY`: Cat이 Dog보다 먼저 오라고 했기에 오름차순으로 정렬하면 된다('a'가 가장 낮고 'z'가 가장 높다.)