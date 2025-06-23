import sys
from collections import deque
N, M = map(int, input().split())
answerList = []

graph = [[] for _ in range(N + 1)]
distanceData = [[] for _ in range(N + 1)]
for i in range(N - 1):
    a, b, distance = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)
    distanceData[a].append(distance)
    distanceData[b].append(distance)
    

for j in range(M):
    visited = [False] * (N + 1)
    start, target = map(int, sys.stdin.readline().split())
    touchaku = False
    distance = 0
    q = deque()
    q.append([start, 0])
    visited[start] = True
    while q:
        if touchaku: break
        node = q.popleft()
        num = node[0]
        imamade = node[1]
        for i in range(len(graph[num])):
            neighbor = graph[num][i]
            if distanceData[num][i] > 0:
                temp = distanceData[num][i] + imamade
                if neighbor == target:
                    distance = temp
                    touchaku = True
                    break
                if not visited[neighbor]:
                    q.append([neighbor, temp])
                    visited[neighbor] = True
    answerList.append(distance)

for answer in answerList:
    sys.stdout.write(str(answer) + '\n')