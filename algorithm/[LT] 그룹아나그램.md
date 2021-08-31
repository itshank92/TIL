# 그룹 아나그램

from: https://leetcode.com/problems/group-anagrams



### 내 풀이

* hashing을 사용  →  hashing을 해서 얻을 이익이 존재하지 않기에 불필요 
* string을 list화 시켜서 정렬 사용  →  문자열 자체를 정렬할 수 있기에 불필요

```python
from collections import Counter 

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # 해싱함수
        def hashing(slist):
            d = 257
            m = 2 ** 64
            res = 0
            for i in range(len(slist)):
                res += ord(slist[i])*(d**i)
            return res%m

        # 결과값 담을 딕셔너리
        adict = dict()
        for string in strs:
            slist = list(string)
            slist.sort()
            h = hashing(slist)
            if adict.get(h) is not None:
                adict[h].append(string)
            else:
                adict[h] = [string]
        return adict.values()
```

​     

​    

(내 풀이에서 개선사항을 반영한)

### 간단한 풀이

```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        res = dict()
        for s in strs:
            # 문자열(s)를 바로 sorted()의 인자로 넣어서 정렬시켜줄 수 있다.
            key = tuple(sorted(s))
            if res.get(key) is not None:
                res[key].append(s)
            else:
                res[key] = [s]
        return list(res.values())
```

