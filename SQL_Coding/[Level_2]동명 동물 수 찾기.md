# 동명 동물 수 찾기

출처: https://programmers.co.kr/learn/courses/30/lessons/59041     





> 코드

```SQL
SELECT NAME, COUNT(NAME) FROM ANIMAL_INS GROUP BY NAME HAVING COUNT(NAME) > 1 ORDER BY NAME;
```





