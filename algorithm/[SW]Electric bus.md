### Electric bus

> 문제

한 번 충전으로 버스가 갈 수 있는 길이, 버스 정류장 수, 충전소 위치 정보가 주어진다.

도착지까지 최소한의 충전횟수로 갈 때의 횟수 반환

못가는 경우 0을 반환





> 코드

```python
# 버스가 한번에 최대한 갈 수 있는 거리, 최종목적지, 충전소 수
battery, dest, charging = map(int,input().split())

# 충전소 위치
charging_list = list(map(int,input().split()))

# 지도 초기화
map_ = [0] * (dest+1)
# 출발지는 충전소가 항상 있다
map_[0] = 1
# 충전소 위치 순회하면서 지도 업데이트 
for idx in charging_list:
    map_[idx] = 1

# 충전횟수(-1인 이유는 출발 위치에서도 충전횟수를 count하는 코드 때문)
cnt = -1

# 최종 목적지에 pointer를 위치시킴
pointer = dest

# pointer가 출발지를 가리키기까지 while문 수행
while pointer != 0:
    # 새로운 포인터 생성(초기값 -1)
    new_pointer = -1
    
    # 포인터 기준으로 battery만큼 순회
    for i in range(1,battery+1):
        # 순회 위치값을 loc으로 받음
        loc = pointer-i
        # 만얀 loc에 충전소가 있다면
        if loc >= 0 and map_[loc] == 1:
            # 새로운 포인터 값에 loc 저장
            new_pointer = loc

    # 새로운 충전소가 없었다면(=새로운 포인터 값이 그대로라면) 실패
    if new_pointer == -1:
        cnt = 0
        break
    # 충전소가 있었다면 pointer를 new_pointer로 바꾸고 충전횟수 +1
    else:
        pointer = new_pointer
        cnt += 1
        
print(f"#{test_case} {cnt}")
```

   



> 코드설명

* 뒤에서부터(도착지부터) 최선의 선택(batter 범위내에서 가장 멀리 있는 충전소에서 충전)을 하는 방식으로 풀이했다.





