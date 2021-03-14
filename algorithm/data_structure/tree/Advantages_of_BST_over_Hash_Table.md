## Hash Table에 비해 BST가 좋은 이유



출처: https://www.geeksforgeeks.org/advantages-of-bst-over-hash-table/     





> 해쉬테이블의 장점과 BST

* 해쉬 테이블은 검색, 삽입, 삭제 작업에 있어 O(1)의 시간복잡도에 수행한다.
  * Search
  * Insert
  * Delete
* 균형구조의 BST의 경우, 위의 작업들에 대한 시간복잡도는 O(logN)이다. 
* 결론적으로 위 작업들만 놓고 본다면 Hash Table이 BST보다 훨씬 좋아보인다. 
* 그러면 Hash Table이 아닌 BST를 사용하는 경우, 그 이유는 무엇일까?    





> BST가 가지고 있는 장점

1. Inorder Traversal를 통해서 모든 Sorted Key를 쉽게 확인 가능하다.
   * Hash Table의 경우 Sorted Key들을 얻으려면 추가적인 노력이 필요하다.
2. 최대 or 최소값을 얻기 혹은 정렬과 같은 범위 작업(range사용해서 순회하는것) 있어 BST가 훨씬 더 간단한다.
   * Hash Table의 경우 범위 작업이 natural한 작업이 아니다.
3. BST는 쉽게 사용자가 만들어서 사용 가능하다.(Easily Implement)
   * Hash Table은 보통 스스로 만들기 어렵기에 라이브러리등을 사용해서 만든다
4. 균형구조의 BST의 경우 모든 작업은 O(logN)이 보장된다. Hash Table의 경우, O(1)이 평균 작업 시간이지만 몇몇 작업은 더 많은 시간이 소요된다.





