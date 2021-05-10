



>  코드

```SQL
select o.animal_id, o.name
from animal_outs o
    left join animal_ins i using(animal_id)
where i.animal_id is null
order by o.animal_id;
```



* 같은 필드명을 가진 두 테이블을 읽어야 하는 경우 필드 앞에 `테이블명`과 `.`을 써서 구분해준다. 

* 테이블 명 뒤에 한칸 띄고 쓰는 문자는 해당 테이블의 닉네임이 된다.
* 기준테이블 LEFT JOIN 대상테이블 USING(사용필드) WHERE 조건
  * 사용필드를 기준으로 LEFT JOIN을 한다. 
  * 이때 WHERE이후 조건에 해당하는 것들만 JOIN한다.