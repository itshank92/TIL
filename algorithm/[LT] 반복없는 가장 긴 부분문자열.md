# 반복없는 가장 긴 부분문자열

from: https://leetcode.com/problems/longest-substring-without-repeating-characters/

​    



### 내 풀이 (100ms)

* 현재 길이를 저장하는 변수(`curl`) 사용  →  시작위치 변수(`start`)만 사용해서 구할 수 있음 
* 문자열 순회시 인덱스로 순회하고 인덱스를 사용해서 개별 문자를 변수(`l`)로 따로 받음 →  `enumerate` 사용

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        loc = dict()
        maxl = 0
        start = 0
        curl = 0
        for idx in range(len(s)):
            l = s[idx]
            # 길이 늘리는 경우
            # 해당 문자가 등장한 적 없거나, 현재 조합 범위 내에 없을 때
            if loc.get(l) is None or loc[l] < start:
                curl += 1
                maxl = max(maxl,curl)
                loc[l] = idx
            
            # 반복되어서 시작점 다시 잡아야 하는 경우
            else:
                start = loc[l] + 1  # 시작점 다시 세팅
                loc[l] = idx # 현재 문자 등장 위치 다시 세팅
                curl = idx - start + 1  # 현재 문자열 길이 다시 세팅
        return maxl
```

​     

​        

### 개선된 풀이 (40ms)

* 변수 사용 갯수 절반

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        used = dict()
        maxl = start = 0
        for idx, char in enumerate(s):
            # start 다시 세팅해야 하는 경우
            # 등장한 적 있고 + 이전 등장 위치가 start 이후인 경우
            if used.get(char) is not None and used[char] >= start:
                start = used[char] + 1  # ★★ start 위치는 현재 문자의 이전 등장 위치 + 1
            else:
                maxl = max(maxl,idx-start+1)
            # 매번 타겟 문자의 위치 정보는 무조건 업데이트 필요(if, else 조건과 관계없음)
            used[char] = idx
        return maxl
```

