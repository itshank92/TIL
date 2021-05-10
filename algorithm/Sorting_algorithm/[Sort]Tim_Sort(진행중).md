# 팀소트

출처: https://www.geeksforgeeks.org/timsort/      

출처: https://d2.naver.com/helloworld/0315536



기본적인 알고리즘 시간복잡도 (출처: https://d2.naver.com/helloworld/0315536)

![image-20210401121649021]([Sort]Tim_Sort(진행중).assets/image-20210401121649021.png)







팀소트는 삽입정렬과 병합정렬을 기반으로 만들어진 정렬 알고리즘이다. 

1. 일반적인 경우 n log n 시간에 정렬이 된다.

2. 파이썬과 자바의 내장 정렬 함수로 사용된다.
3. 삽입정렬을 사용하여 작은 조각들을 정렬하고 그 조각들을 병합정렬을 사용해서 순서에 맞게 병합한다.

   

가장 먼저 정렬 대상이 되는 배열을 Run이라고 부르는 작은 조각들로 나눌 것이다. 삽입 정렬을 사용해서 Run들을 하나하나 정렬한 다음, 병합정렬 방식을 사용해서 하나로 병합한다. 만약 배열의 크기가 Run보다 작다면, 배열은 단지 삽입정렬만 사용해서 정렬된다. Run은 배열의 크기에 따라 32에서 64사이의 크기로 설정된다. 

이러한 팀소트의 아이디어는 정렬 대상의 크기에 따른 병합정렬과 삽입정렬의 특성에서 출발했다. 삽입정렬의 경우 정렬 대상의 크기가 작은 경우 빠르게 수행된다. 반면에 병합정렬의 병합은 서브배열의 길이가 2의 거듭제곱일 때 잘 작동한다. 따라서 팀소트는 작은 조각에 대해서는 삽입정렬을, 큰 조각에 대해서는 병합정렬을 사용한 것이다.        

**"전체를 작은 덩어리로 잘라 각각의 덩어리를 Insertion sort로 정렬한 뒤 병합하면 좀 더 빠르지 않을까 하는 것이 팀소트의기본적인 아이디어이다."**



Tim sort에서 사용하는 Insertion sort는 Binary Insertion sort이다. Binary라는 용어에서 알 수 있듯이, 삽입해야 할 위치를 찾을 때까지 비교하는 대신, 앞의 원소들은 모두 정렬되어 있다는 전제를 기반으로 **이분 탐색**을 진행하여 위치를 찾는다. 

한 원소를 삽입할 때 O(n)*O*(*n*)번의 비교를 진행하는 대신 O(\log{}n)*O*(log*n*)번의 비교를 진행하기에 작은 n*n*에 대하여 좀 더 시간을 절약할 수 있다.



이제 팀 소트를 구현해보자.

* 우리는 Run의 최소 크기를 32로 설정할 것이다.
  * 정렬 대상의 크기를 32~64로 나눠서 나오는 가장 큰 
* 각각의 Run에 대해서 삽입정렬 방식으로 정렬한다.
* 개별 Run의 정렬이 끝나면 이들을 쌍으로 묶어서 병합한다. 이 과정을 마지막까지 수행한다.

​    

```python
# Run의 최소 사이즈 설정
MIN_MERGE = 32
 

    
# RUN의 길이를 설정하는 함수    
def calcMinRun(n):
    """ RUN의 최소길이를 32에서 33사이에서 설정한다.
    이렇게 설정한 RUN의 길이로 배열의 길이를 나눈 값(덩어리의 수)가
    2의 거듭제곱과 같거나 작도록 만든다.
 
    (예시) 1=>1, ..., 63=>63, 64=>32, 65=>33,
    ..., 127=>64, 128=>32, ...
    """
    r = 0
    # n이 RUN의 최소사이즈보다 큰 경우 계산이 시작된다.
    while n >= MIN_MERGE:
        
        # n&1는 n이 홀수인 경우만 1, 짝수인 경우는 0
        # r |= n은 n이 홀수인 경우 1, 짝수인 경우 0
        r |= n & 1
        # >>: n의 모든 값들을 한칸씩 오른쪽으로 이동시킨다.
        # = : (위에서 나온 값을) n에 할당한다.
        # '>>='은 사실상 루트 계산을 한 다음 정수화 한 결과와 같다.
        n >>= 1
    return n + r
 
 
# This function sorts array from left index to
# to right index which is of size atmost RUN
def insertionSort(arr, left, right):
    for i in range(left + 1, right + 1):
        j = i
        while j > left and arr[j] < arr[j - 1]:
            arr[j], arr[j - 1] = arr[j - 1], arr[j]
            j -= 1
 
 
# Merge function merges the sorted runs
def merge(arr, l, m, r):
     
    # original array is broken in two parts
    # left and right array
    len1, len2 = m - l + 1, r - m
    left, right = [], []
    for i in range(0, len1):
        left.append(arr[l + i])
    for i in range(0, len2):
        right.append(arr[m + 1 + i])
 
    i, j, k = 0, 0, l
     
    # after comparing, we merge those two array
    # in larger sub array
    while i < len1 and j < len2:
        if left[i] <= right[j]:
            arr[k] = left[i]
            i += 1
 
        else:
            arr[k] = right[j]
            j += 1
 
        k += 1
 
    # Copy remaining elements of left, if any
    while i < len1:
        arr[k] = left[i]
        k += 1
        i += 1
 
    # Copy remaining element of right, if any
    while j < len2:
        arr[k] = right[j]
        k += 1
        j += 1
 
 
# Iterative Timsort function to sort the
# array[0...n-1] (similar to merge sort)
def timSort(arr):
    n = len(arr)
    minRun = calcMinRun(n)
     
    # Sort individual subarrays of size RUN
    for start in range(0, n, minRun):
        end = min(start + minRun - 1, n - 1)
        insertionSort(arr, start, end)
 
    # Start merging from size RUN (or 32). It will merge
    # to form size 64, then 128, 256 and so on ....
    size = minRun
    while size < n:
         
        # Pick starting point of left sub array. We
        # are going to merge arr[left..left+size-1]
        # and arr[left+size, left+2*size-1]
        # After every merge, we increase left by 2*size
        for left in range(0, n, 2 * size):
 
            # Find ending point of left sub array
            # mid+1 is starting point of right sub array
            mid = min(n - 1, left + size - 1)
            right = min((left + 2 * size - 1), (n - 1))
 
            # Merge sub array arr[left.....mid] &
            # arr[mid+1....right]
            if mid < right:
                merge(arr, left, mid, right)
 
        size = 2 * size
 
 
# Driver program to test above function
if __name__ == "__main__":
 
    arr = [-2, 7, 15, -14, 0, 15, 0,
           7, -7, -4, -13, 5, 8, -14, 12]
 
    print("Given Array is")
    print(arr)
 
    # Function Call
    timSort(arr)
 
    print("After Sorting Array is")
    print(arr)
    # [-14, -14, -13, -7, -4, -2, 0, 0,
           5, 7, 7, 8, 12, 15, 15]
```





