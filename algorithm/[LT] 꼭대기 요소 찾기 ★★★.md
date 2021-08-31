# 꼭대기 요소 찾기

(from: https://leetcode.com/problems/find-peak-element/)     

​     



> ## 핵심: 문제이해



이 문제는 문제 내용이 약간 독특해서 문제를 이해하는 것이 핵심이다.

문제에서 눈여겨서 읽어봐야 할 내용은 다음과 같다.



**1) A peak element is an element that is strictly greater than its neighbors.**

 ▶ 꼭대기 요소는 이웃들보다 큰 숫자를 의미한다. 여기에는 이웃의 수에 대해서는 어떠한 조건도 없다.

따라서 이웃이 아무도 없는 숫자, 예를 들면 `[1]`의 경우 바로 꼭대기 요소가 된다.

또한 하나의 이웃만 있는 숫자 역시, 자신의 이웃보다 크다면 꼭대기 요소가 된다. (ex_ `[3,2,1]`에서 `[3]`) 



**2) If the array contains multiple peaks, return the index to any of the peaks.**

▶ 배열은 여러개의 꼭대기 요소를 가질 수 있고, 우리는 그 중 아무거나 하나만 찾아서 그 인덱스를 반환하면 된다.

따라서 꼭대기가 있을 수도 있는 모든 영역을 탐색하는 것이 아니라, 100% 있는 영역만 탐색하면 된다.

다시 말하면 어떤 숫자를 기점으로 왼쪽 영역과 오른쪽 영역이 나뉠 때, 왼쪽 영역에는 꼭대기 요소가 있을 수 있고 오른쪽 영역에는 100% 꼭대기 요소가 있을 때, 왼쪽 영역은 버리고 오른쪽 영역만 탐색하는 것이다.



**3) You must write an algorithm that runs in O(log n) time.**

▶ log n 시간으로 탐색을 수행해야 한다는 것을 보고 **이진탐색**을 떠올려야 한다. 

이 문제는 이진탐색의 응용문제다. 어떤 기준으로 하나의 영역(왼쪽 or 오른쪽)을 날릴 수 있는지 파악하는 것이 문제의 핵심이다.







> ## 핵심: 문제 풀이

(from: https://leetcode.com/problems/find-peak-element/discuss/50259/My-clean-and-readable-python-solution)

이진탐색을 수행한다고 가정했을 때, 꼭대기 요소에 대한 정의를 바탕으로 우리는 다음과 같이 생각해 볼 수 있다. 



총 3가지 케이스로 나뉜다.



**케이스 1. pivot의 값이 양 쪽 이웃보다 클 때** 

▶ 축하한다. 바로 꼭대기 요소를 찾았다.



**케이스 2. pivot의 오른쪽 값이 pivot보다 클 때**

▶ 탐색 영역을 오른쪽을 바꾸고 다시 탐색을 수행한다.

꼭대기 요소는 반드시 오른쪽에 존재한다. 

이유는 오른쪽 영역이 어떤 양식으로 펼쳐지든 꼭대기 요소가 존재하기 때문이다.

오른쪽 영역이 보여줄 수 있는 양상은 다음과 같다.

* 양상1. 바로 줄어든다. → 현재 pivot의 오른쪽 요소가 꼭대기 요소가 된다.

* 양상2. 계속 증가한다 → 오른쪽 영역의 가장 마지막 요소가 꼭대기 요소가 된다. 

* 양상3. 증가했다가 줄어든다 → 증가 영역의 마지막 요소가 꼭대기 요소가 된다.



**케이스 3. pivot의 왼쪽 값이 pivot보다 클 때**

▶ 탐색 영역을 왼쪽으로 바꾸고 다시 탐색을 수행한다.

무조건 왼쪽 영역에 꼭대기 요소가 존재하기 때문에 왼쪽 영역 탐색을 수행한다.

왼쪽 영역이 보여줄 수 있는 양상 역시 케이스 2에서 설명한 양상과 같다.







> ## 문제 풀이 코드

```python
def findPeakElement(self, nums):

    ## (예외처리) 입력값의 길이가 1일 때
    if len(nums) == 1:
        return 0

	left = 0
    right = len(nums)-1
    
    # 입력값의 길이가 2이상일 때,
	# "<" 가 아니라 "<="           >>>>  케이스: [1,2]
    while left <= right:  
        mid = left + (right-left)//2
        if (mid + 1 == len(nums) or nums[mid] > nums[mid+1]) and (mid-1 < 0 or nums[mid] > nums[mid-1]):
            return mid
        
        if nums[mid] < nums[mid+1]:
            left = mid + 1
        else:
            right = mid - 1                                   
```



* `mid` 구할 때, `(left + right)//2`가 아닌 `left + (right-left)//2`로 푸는 이유 (오버플로우 방지)

  https://stackoverflow.com/questions/27167943/why-leftright-left-2-will-not-overflow

