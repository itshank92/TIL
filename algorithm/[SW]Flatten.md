### 평탄화 문제

​    

> 문제

각각의 높이를 가지고 있는 상자 더미들이 있는데, 가장 높은곳에서 가장 낮은 곳으로 상자를 1개씩 이동하는 작업을 한다.

입력값으로는 이 작업을 수행할 수 있는 횟수가 주어지고 다음줄에는 상자의 높이 정보들이 주어진다.

평탄화의 결과로 얻은 최대높이와 최소높이의 차이값을 반환하라  





> 입력과 출력 예시

```python
834
42 68 35 1 70 25 79 59 63 65 6 46 82 28 62 92 96 43 28 37 92 5 3 54 93 83 22 17 19 96 48 27 72 39 70 13 68 100 36 95 4 12 23 34 74 65 42 12 54 69 48 45 63 58 38 60 24 42 30 79 17 36 91 43 89 7 41 43 65 49 47 6 91 30 71 51 7 2 94 49 30 24 85 55 57 41 67 77 32 9 45 40 27 24 38 39 19 83 30 42 
```

​    

> 코드_최소,최대힙 사용

```python
import heapq
 
for test_case in range(1, 11):
    dump_count = int(input())
    map_ = list(map(int,input().split()))
    # 최대힙 
    max_h = []
    # 최소힙
    min_h = []
    for idx,height in enumerate(map_):
        heapq.heappush(max_h,(-height,idx))
        heapq.heappush(min_h,(height,idx))
     
    # dump_count가 0이 될때까지 평탄화 수행
    while dump_count != 0:
        max_height, max_idx = heapq.heappop(max_h)
        min_height, min_idx = heapq.heappop(min_h)
        # 최대높이와 최소높이 차이가 1이하인 경우 종료
        if abs(max_height)-min_height <= 1:
            break
        # 최대높이지점과 최소높이지점이 같은 경우 종료
        if max_idx == min_idx:
            break
        # 최대힙과 최소힙에 변경된 높이값 push
        heapq.heappush(max_h,(max_height+1,max_idx))
        heapq.heappush(min_h,(min_height+1,min_idx))
        # 평탄화 횟수 차감
        dump_count -= 1
    # 최대 높이, 최소 높이 가져옴
    max_height, max_idx = heapq.heappop(max_h)
    min_height, min_idx = heapq.heappop(min_h)
    print(f"#{test_case} {abs(max_height)-min_height}")

```

​       

> 코드 설명

* 최대힙과 최소힙을 동시에 사용하는 방식을 시도해봤다.

* ~~두 힙은 동기화가 되지않기 때문에 idx를 통해 같은 경우를 제외하였다.~~

  * 그런데 이게 사실 필요한지는 모르겠다. 최소높이를 계속 올려주고 최대 높이를 계속 낮춰주다보면 결국 두 높이 차가 1이하가 되는 경우가 발생하기 때문이다. 

  *         # 최대높이지점과 최소높이지점이 같은 경우 종료
            if max_idx == min_idx:
                break
    * 위 조건을 제외하고 수행한 결과 ▶ 수행된다.





> 코드_counting 정렬 사용 ★★생각못해본 방법 

```python
for t in range(1, 11):
    # dumps, boxes = 평탄화 가능 횟수, 박스높이 리스트 
    dumps = int(input())
    boxes = map(int, input().rstrip().split())
 
    # 박스 높이는 0부터 100까지 → 높이별로 박스의 수를 counting할 리스트 생성 
    num_boxes = [0] * 101
    
    # 최소, 최대 높이를 가리킬 포인터 
    mn = 1
    mx = 100
 

    # 박스높이 리스트를 순회하면서 counting 리스트 업데이트
    for box in boxes:
        num_boxes[box] += 1
 
	# 평탄화 작업 수행
    while dumps:
        ##### 최소높이 박스 업데이트 #####
        # 최소 높이 포인터가 유효한 지점(박스가 1개 이상)을 가리키는 작업 수행 
        while not num_boxes[mn]:
           # mn 포인터에 박스가 없으면 한칸 앞으로 
           mn += 1
        
        # 최소높이 박스갯수에서 하나 빼서 다음높이 박스갯수에 하나를 더해줌
        num_boxes[mn] -= 1
        num_boxes[mn+1] += 1
        
        # 해당 높이의 박스 갯수가 0개이면 mn 포인터 한칸앞으로
        if num_boxes[mn] == 0:
            mn += 1
 
        ##### 최대높이 박스 업데이트 #####
		# 최대 높이 포인터가 유효한 지점(박스가 1개 이상)을 가리키는 작업 수행 
        while not num_boxes[mx]:
            mx -= 1
            
        # 최대높이 박스갯수에서 하나 빼서 이전 높이 박스갯수에 하나를 더해줌    
        num_boxes[mx] -= 1
        num_boxes[mx-1] += 1
        
        # 해당 높이의 박스 갯수가 0개이면 mx 포인터 한칸 뒤로 
        if num_boxes[mx] == 0:
            mx -= 1
 		
        # 최대높이와 최소높이의 차이가 1이하인 경우 평탄화 작업 종료
        if mx - mn <= 1:
            break
 
		# 평탄화 가능 횟수 -= 1
        dumps -= 1
 
    print(f'#{t} {mx-mn}')
```

​     

> 코드 설명

  **[counting list]**

* `박스 높이는 0부터 100까지`라는 정보를 읽고 박스를 높이별로 갯수를 기록하는 `counting list`를 만들 생각을 하지 못했다.

  [**투 포인터**]

* **두 개의 포인터**(`mn`, `mx`) 를 사용해서 최소 높이 박스와 최소 높이 박스 갯수를 업데이트 하는 방식으로 작업

  