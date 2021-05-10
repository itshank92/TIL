# 중복 제거하기

출처: https://programmers.co.kr/learn/courses/30/lessons/59408    





> 코드

```SQL
SELECT COUNT(DISTINCT NAME) FROM ANIMAL_INS WHERE NAME IS NOT NULL;
```



* `DISTINCT NAME`: NAME 필드의 값 중 고유한 값들만 열로 추출한다.
* `COUNT`: 인자로 오는 열의 행 갯수를 반환한다.
* `WHERE NAME IS NOT NULL`: NAME 필드의 값이 NULL이 아닌 레코드만을 대상으로 한다.



