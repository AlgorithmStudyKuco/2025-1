from collections import deque
N, M = map(int, input().split())

graph = [[] for _ in range(N + 1)]
grade = [0] * (N + 1)
result = []
q = deque()
already_did_it = [[False] * (N + 1) for _ in range(N + 1)]

for _ in range(M):
    order = list(map(int, input().split()))
    for i in range(1, order[0]):
        pre = order[i]
        post = order[i + 1]
        if already_did_it[pre][post]:
            continue
        graph[pre].append(post)
        grade[post] += 1
        already_did_it[pre][post] = True

cycle = False

visited = [False] * (N + 1)
recursive_stack = [False] * (N + 1)

def isCycle(node):
    if not visited[node]:
        visited[node] = True
        recursive_stack[node] = True
        for neighbor in graph[node]:
            if not visited[neighbor] and isCycle(neighbor):
                return True
            elif recursive_stack[neighbor]:
                return True
    recursive_stack[node] = False
    return False

for i in range(1, N + 1):
    if grade[i] == 0:
        q.append(i)

grade[0] = 1
if 0 not in grade:
    cycle = True
else:
    for start_node in q:
        cycle = isCycle(start_node)
        if cycle: break

while q:
    if cycle: break
    node = q.popleft()
    result.append(node)
    for neighbor in graph[node]:
        grade[neighbor] -= 1
        if grade[neighbor] == 0:
            q.append(neighbor)

if cycle: print(0)
else:
    for element in result:
        print(element)