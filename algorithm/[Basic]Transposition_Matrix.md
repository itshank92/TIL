## 전치행렬

![image-20210215102438324]([Basic]전치행렬.assets/image-20210215102438324.png)



```python
for i in range(3):
    for j in range(3):
        if i < j:  #이 조건이 없으면 결과값이 입력값과 같아짐
            arr[i][j] , arr[j][i] = arr[j][i] , arr[i][j]
```



