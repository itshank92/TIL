# Tree traversal

출처: https://en.wikipedia.org/wiki/Tree_traversal    



In [computer science](https://en.wikipedia.org/wiki/Computer_science), **tree traversal** (also known as **tree search** and **walking the tree**) is a form of [graph traversal](https://en.wikipedia.org/wiki/Graph_traversal) and refers to the process of visiting (checking and/or updating) each node in a [tree data structure](https://en.wikipedia.org/wiki/Tree_(data_structure)), exactly once. Such traversals are classified by the order in which the nodes are visited. The following algorithms are described for a [binary tree](https://en.wikipedia.org/wiki/Binary_tree), but they may be generalized to other trees as well.



컴퓨터 공학 영역에서 트리 검색은 그래프를 순회하는 방법으로 트리의 각 노드를 한 번씩만 방문하는 방식을 의미한다. 트리 검색 방식은 어떤 노드가 먼저 방문되는지에 따라 나뉜다. 



트리는 재귀로 정의된 자기 참조적인 자료 구조이다. 즉 트리는 자식도 트리고 또 그 자식도 트리다. 이러한 대귀적인 특성 때문에 트리 검색은 보통 재귀적으로 수행된다. 



![img](https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Sorted_binary_tree_ALL_RGB.svg/293px-Sorted_binary_tree_ALL_RGB.svg.png)

Depth-first traversal (dotted path) of a binary tree:

- Pre-order (node access at position red ●)

  : F, B, A, D, C, E, G, I, H;

- In-order (node access at position green ●)

  : A, B, C, D, E, F, G, H, I;

- Post-order (node access at position blue ●)

  : A, C, E, D, B, H, I, G, F.