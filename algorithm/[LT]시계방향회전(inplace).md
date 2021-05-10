# Rotate Image

from: https://leetcode.com/problems/rotate-image



시계방향 회전문제. 

inplace 회전을 요구한다.

모르겠어서 discussion을 찾아봤는데

대단한 답변을 찾았다.

---

from: https://leetcode.com/problems/rotate-image/discuss/18872/A-common-method-to-rotate-the-image

```java
/*
 * clockwise rotate
 * first reverse up to down, then swap the symmetry 
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3
*/
void rotate(vector<vector<int> > &matrix) {
    reverse(matrix.begin(), matrix.end());
    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = i + 1; j < matrix[i].size(); ++j)
            swap(matrix[i][j], matrix[j][i]);
    }
}

/*
 * anticlockwise rotate
 * first reverse left to right, then swap the symmetry
 * 1 2 3     3 2 1     3 6 9
 * 4 5 6  => 6 5 4  => 2 5 8
 * 7 8 9     9 8 7     1 4 7
*/
void anti_rotate(vector<vector<int> > &matrix) {
    for (auto vi : matrix) reverse(vi.begin(), vi.end());
    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = i + 1; j < matrix[i].size(); ++j)
            swap(matrix[i][j], matrix[j][i]);
    }
}
```





# 실제 코드 작성시 주의해야 하는 사항

```python
class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        # 기능: line을 역순으로 배치
        # ★주의: len(matrix)에 해당하는 line은 없다(len(matrix)-1이 마지막)
        for lidx in range(len(matrix)//2):
            matrix[lidx],matrix[len(matrix)-lidx-1] = matrix[len(matrix)-lidx-1],matrix[lidx]

        # 기능: 절반을 swap 시킨다.
        # ★주의: 전체를 돌면서 swap을 하면 결국 두 번 swap이 이뤄져 똑같아진다.
        # 각 line별로 '해당 line의 인덱스 + 1'번 칼럼부터 순회하는 방식으로 돌아야 한다.☆☆
        # 그러면 딱 사각형을 대각선으로 자른 절반을 돌 수 있다.
        for i in range(len(matrix)):
            for j in range(i+1,len(matrix)):
                matrix[i][j],matrix[j][i] = matrix[j][i],matrix[i][j]
        return matrix
```



# 테이블을 대각선으로 자른 절반을 순회하는 방법

* 거시적(큰 for loop)으로는 전체를 순회하면서
* 미시적(작은 for loop)으로는 해당 행+1 부터 순회를 수행한다.

```python
n = 5

matrix = [['■'] * n for _ in range(n)]

for i in range(len(matrix)):
  for j in range(i+1,len(matrix)):
    matrix[i][j] = '□'

print(*matrix, sep = "\n")

"""
결과

['■', '□', '□', '□', '□']
['■', '■', '□', '□', '□']
['■', '■', '■', '□', '□']
['■', '■', '■', '■', '□']
['■', '■', '■', '■', '■']

"""
```



# 추가 _ 반대로 자르기

```python
n = 5

matrix = [['■'] * n for _ in range(n)]

for i in range(len(matrix)):
  for j in range(len(matrix)-i-1):
    matrix[i][j] = '□'

print(*matrix, sep = "\n")

"""
결과

['□', '□', '□', '□', '■']
['□', '□', '□', '■', '■']
['□', '□', '■', '■', '■']
['□', '■', '■', '■', '■']
['■', '■', '■', '■', '■']

"""
```

