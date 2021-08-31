# 정렬된 리스트 병합하기

from: https://leetcode.com/problems/merge-sorted-array/







```python
class Solution:
    """
    이미 정렬된 두 배열을 활용하기 위해서 
    뒤에서부터 가장 큰 것을 정렬한다.
    그리고 남은 것이 있다면 넣는다.
    
    이를 위해 배열1의 포인터, 배열2의 포인터, 배열1의 전체를 순회할 포인터를 생성한다.
    전자의 배열1의 포인터는 배열1 中 정렬된 부분만을 순회하는 포인터다.
    """
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        one_p = m - 1   # 배열1의 포인터
        two_p = n - 1   # 배열2의 포인터
        tot_p = m + n - 1   # 배열1의 전체 순회 포인터
        
        # 두 포인터가 모두 유효한 대상을 가리킬 수 있다면 계속 수행
        while one_p >= 0 and two_p >= 0:
            # 큰 것을 배치
            if nums1[one_p] > nums2[two_p]:
                nums1[tot_p] = nums1[one_p]
                one_p -= 1
            else:
                nums1[tot_p] = nums2[two_p]
                two_p -= 1
            tot_p -= 1
        
        # 이미 정렬이 끝난 nums1이 남았다면 그냥 두면 된다.
        # nums2가 남았다면 남아있는 공간(tot_p가 가리키고 있다)에 
        # 차곡차곡 쌓아주면 된다.
        while two_p >= 0:
            nums1[tot_p] = nums2[two_p]
            two_p -= 1
            tot_p -= 1
```

