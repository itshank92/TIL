### 중복 문자가 없는 가장 긴  부분 문자열

  

출처: https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/     



> #### 문제 

중복 문자가 없는 가장 긴 부분 문자열(substring)의 길이를 반환하라.   



> #### 입력과 출력 예시

* 입력1: `'abcabcbb'`

* 출력1: `3`  → 정답은 'abc'로 길이가 3   

  

* 입력2: `'bbbbb'`

* 출력2: `1` → 정답은 'b'로 길이가 1   

  

* 입력3: `'pwwkew'`

* 출력3: `3` → 정답은 'wke'로 길이가 3



> #### 풀이 코드

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        # 이전에 등장한 character를 key로 index를 value로 담는 딕셔너리 생성
        used = {}
        
        # max_length, start 생성
        # start는 길이를 카운트하는 첫번째 위치를 가리킬 포인터 역할
        max_length = start = 0
        
        # enumerate를 사용해서 index, character 단위로 순회
        for index, char in enumerate(s):
            # ★만약 해당 문자가 이미 사용되었고, 현재 카운팅하는 범위내에서 사용된 적이 있다면
            if char in used and start <= used[char]:
                # 카운팅의 시작지점을 해당 문자가 이전에 나온 지점 +1로 바꿈
                start = used[char] + 1
            # 현재 문자가 이전에 나온 적이 없다면
            else:
                # 최대 길이 업데이트
                max_length = max(max_length,index-start+1)
                
            # 순회 끝난 문자는 이미 사용되었기 때문에 used에 넣어줌
            used[char] = index

        return max_length
```

​     

​    

> #### 코드 설명

* 해당 코드 이해의 핵심은 `start`라는 포인터가 수정되는 부분이다.

* 문자열을 순회하다가 현재 카운팅중인 영역내에서 문자 중복이 발견되는 경우, `중복지점 +1`의 위치로 
  `start`를 바꿔준다.

* 생각해보면 `기존의 start 지점`부터 `중복지점+1` 이전을 기점으로 카운팅하는 경우, 어짜피 현재 문자에서 
  또 중복이 발생하기 때문에 절대로 max_length가 될 수 없다.

* 따라서 `start`는 이전에 등장한 `중복문자 +1`의 위치부터 다시 카운팅을 시작하는 것이다.   

  



> #### 배운점

* 앞서 코드에서 작성했듯이 중복 문자가 발생할 때, `start` 포인터를 위치시키는 방식이 새로웠다.  

  

* 문자열 데이터 역시 `enumerate`를 사용할 수 있다는 것을 처음 알았다.   

  

* 나는 보통 문자를 변수로 표현할 때 `letter`라는 변수명을 사용했는데, `character`를 사용하는게 
  일반적이라는 것을 배웠다.

