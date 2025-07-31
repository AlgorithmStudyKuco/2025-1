import sys
from collections import deque

N = int(input())

times = [0] * (N + 1)
actual_times = [0] * (N + 1)
graph = [[] for _ in range(N + 1)]
grade = [0] * (N + 1)
q = deque()

for i in range(1, N + 1):
    line = list(map(int, sys.stdin.readline().split()))
    times[i] = line[0]
    grade[i] = line[1]
    for j in range(2, 2 + line[1]):
        graph[line[j]].append(i)

for i in range(1, N + 1):
    if grade[i] == 0:
        actual_times[i] = times[i]
        q.append(i)
    
while q:
    node = q.popleft()
    for neighbor in graph[node]:
        actual_times[neighbor] = max(actual_times[neighbor], actual_times[node] + times[neighbor])
        grade[neighbor] -= 1
        if grade[neighbor] == 0:
            q.append(neighbor)

print(max(actual_times))