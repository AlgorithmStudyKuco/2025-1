def bfs():
    q = [[0, 0]]
    visited = [[0]*M for _ in range(N)]
    visited[0][0] = 1
    while q:
        i, j = q.pop(0)
        for di, dj in [[0,1],[1,0],[0,-1],[-1,0]]:
            ni, nj = i+di, j+dj
            if 0 <= ni <N and 0 <= nj <M and arr[ni][nj] == 1 and visited[ni][nj] == 0:
                arr[ni][nj] = arr[i][j] + 1
                q.append([ni, nj])
                visited[ni][nj] = 1
            if [ni, nj] == [N-1, M-1]:
                return arr[ni][nj]


N, M = map(int, input().split())
arr = [list(map(int,input())) for _ in range(N)]
result = bfs()
print(result)