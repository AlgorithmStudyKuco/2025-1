from collections import deque

def detecttarget():  # 목표지점 찾기
    for i in range(n):  # n 행을 기준으로 반복
        for j in range(m):  # m 열을 기준으로 반복
            if arr[i][j] == 2:
                target = [i, j, 0]  # i, j = 목표지점 인덱스, 0 = 거리
                return target
    return None  # 목표 지점이 없을 경우 None 반환

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def bfs(arr, startpoint, dis):
    visited = [[0] * m for _ in range(n)]
    q = deque()
    q.append(startpoint)
    
    while q:
        posit = q.popleft()
        x, y, distance = posit
        
        if visited[x][y] == 0:
            dis[x][y] = distance
            visited[x][y] = 1
            
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < n and 0 <= ny < m and visited[nx][ny] == 0 and arr[nx][ny] == 1:
                    q.append([nx, ny, distance + 1])
    
    # 도달할 수 없는 경우 -1로 설정
    for i in range(n):  # n 행을 기준으로 반복
        for j in range(m):  # m 열을 기준으로 반복
            if dis[i][j] == 0 and arr[i][j] == 1:  # 도달할 수 없고 벽이 아닐 경우
                dis[i][j] = -1

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
dis = [[0] * m for _ in range(n)]
start = detecttarget()

if start:
    bfs(arr, start, dis)
else:
    print("목표 지점이 존재하지 않습니다.")  # 목표 지점이 없는 경우 처리

for i in range(n):  # n 행을 기준으로 출력
    for j in range(m):  # m 열을 기준으로 출력
        print(dis[i][j], end=' ')
    print()
