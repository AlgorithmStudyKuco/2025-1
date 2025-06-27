import sys
import math

N, M = map(int, sys.stdin.readline().split())
edges = []
for i in range(M):
    A, B, C = map(int, sys.stdin.readline().split())
    edges.append((A, B, C))


def bellman_ford(start):
    distance = [math.inf] * (N + 1)
    distance[start] = 0

    for _ in range(N - 1):
        for a, b, c in edges:
            if distance[a] != math.inf and distance[b] > distance[a] + c:
                distance[b] = distance[a] + c
    
    for a, b, c in edges:
        if distance[a] != math.inf and distance[b] > distance[a] + c:
            return -1
            
    return distance

distance = bellman_ford(1)

if distance == -1:
    print(-1)
else:
    for i in range(2, N + 1):
        print(-1 if distance[i] == math.inf else distance[i])