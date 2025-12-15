def dfs(node):
    global cnt
    if adj[node]:
        for i in adj[node]:
            dfs(i)
    else:
        cnt += 1

N = int(input())
arr = list(map(int,input().split()))
M = int(input()) # 지울 노드의 번호
root = -1
adj = [[]for _ in range(N)]

for i in range(N): # 지울 노드를 제외하고 인접 행렬을 만들기
    if arr[i] == -1:
        root = i
    elif i != M:
        adj[arr[i]].append(i)
cnt = 0

if root != M:
    dfs(root)
print(cnt)
