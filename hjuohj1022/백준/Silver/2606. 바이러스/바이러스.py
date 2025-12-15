def DFS(s, V):
    visited =[0]*(V+1)
    stack = []
    visited[s] = 1
    v = s
    count = 0

    while True:
        for w in arr[v]:
            if visited[w] == 0:
                stack.append(v)
                v = w
                visited[w] = 1
                count += 1
                break

        else:
            if stack:
                v = stack.pop()
            else:
                break
    return count




N = int(input())               # 컴퓨터의 수
M = int(input())               # 네트워크 상에서 직접 연결되어있는 컴퓨터 쌍의 수
arr = [[] for _ in range(N+1)]
for _ in range(M):
    start, finish = map(int, input().split())
    arr[start].append(finish)
    arr[finish].append(start)

result = DFS(1,N)
print(result)


