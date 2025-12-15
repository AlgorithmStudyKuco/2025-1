def dfs(v):
    visited = [0]*(N+1)
    stack = []
    visited[v] = 1
    w = v
    print(w, end=' ')
    while True:
        for i in arr[w]:
            if visited[i] == 0:
                stack.append(w)
                visited[i] = 1
                w = i
                print(w, end=' ')
                break
        else:
            if stack:
                w = stack.pop()
            else:
                return

def bfs(v):
    visited = [0]*(N+1)
    q = [v]
    visited[v] = 1
    while q:
        w = q.pop(0)
        print(w, end=' ')
        for i in arr[w]:
            if visited[i] == 0:
                q.append(i)
                visited[i] = 1



N,M,V = map(int,input().split())
arr = [[] for _ in range(N+1)]

for _ in range(M):
    s,f = map(int,input().split())
    arr[s].append(f)
    arr[f].append(s)

for j in range(1,N+1):
    arr[j].sort()

dfs(V)
print()
bfs(V)
print()
