# DFS for a Graph(그래프를 깊이 우선 탐색하기)

출처: https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/      



> 기본

Graph DFS는 Tree DFS와 대부분 유사하지만 한가지 차이점이 존재한다.

그 차이점은 바로 Tree와 Graph의 차이에서 기인한 것으로 Cycle이 존재하는 Graph의 경우 DFS 수행시 한번 방문했던 노드를 다시 방문하는 경우가 생길 수 있다. 

이를 방지하기 위해서 Graph DFS는 방문 여부를 기록해두는 배열(visited array)을 만들어 이를 활용한다.     





