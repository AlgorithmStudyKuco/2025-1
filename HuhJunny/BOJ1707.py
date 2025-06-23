import sys
from collections import deque
K = int(input())
answerList = []

for _ in range(K):
    V, E = map(int, input().split())
    red = 1
    blue = 2
    visited = [0] * (V + 1)
    visited[0] = -1
    need_visited = deque()
    areYouBi = True

    graph = [[] for _ in range(V + 1)]

    for i in range(E):
        a, b = map(int, sys.stdin.readline().split())
        graph[a].append(b)
        graph[b].append(a)

    need_visited.append([1, red])

    while need_visited or visited.count(0) != 0:
        if not areYouBi: break
        if not need_visited:
            need_visited.append([visited.index(0), red])
        node = need_visited.pop()
        num = node[0]
        color = node[1]
        visited[num] = color
        for neighbor in graph[num]:
            if visited[neighbor] == color:
                areYouBi = False
                break
            if not visited[neighbor]:
                anotherColor = red if color == blue else blue
                need_visited.append([neighbor, anotherColor])

    if areYouBi:
        answerList.append("YES")
    else:
        answerList.append("NO")

for i in range(K):
    print(answerList[i])