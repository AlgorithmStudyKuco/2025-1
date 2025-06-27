import sys
import math

N = int(input())
M = int(input())
graph = dict().fromkeys([i for i in range(1, N + 1)])
distance = [math.inf] * (N + 1)
for i in range(1, N + 1):
    graph[i] = dict()

visited = [False] * (N + 1)

for _ in range(M):
    start, end, d = map(int, sys.stdin.readline().split())
    if end in graph[start].keys() and graph[start][end] < d:
        continue
    else:
        graph[start][end] = d

start, target = map(int, sys.stdin.readline().split())
distance[start] = 0
for neighbor in graph[start]:
    distance[neighbor] = graph[start][neighbor]

def getSmallestDistance():
    minVal = math.inf
    index = 0
    for i in range(1, N + 1):
        if distance[i] < minVal and not visited[i]:
            minVal = distance[i]
            index = i
    return index

node = start
for i in range(N - 1):
    node = getSmallestDistance()
    if node == 0: break
    visited[node] = True
    for neighbor in graph[node]:
        if distance[neighbor] > distance[node] + graph[node][neighbor]:
            distance[neighbor] = distance[node] + graph[node][neighbor]


print(distance[target] if N != 1 else 0)