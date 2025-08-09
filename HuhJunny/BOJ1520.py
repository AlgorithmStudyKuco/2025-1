import heapq

d_x = [0, 1, 0, -1]
d_y = [1, 0, -1, 0]

def main():
    M, N = map(int, input().split())
    graph = [0] * M
    sorted_list = []
    get_input(graph, sorted_list, M , N)

    memo = [[0] * N for _ in range(M)]
    memo[0][0] = 1
    DP(graph, sorted_list, M, N, memo)

    print(memo[M - 1][N - 1])


def get_input(graph, sorted_list, M, N):
    for i in range(M):
        graph[i] = list(map(int, input().split()))
        for j in range(N):
            height = graph[i][j]
            heapq.heappush(sorted_list, [-height, [i, j]])


def DP(graph, sorted_list, M, N, memo):
    for _ in range(M * N):
        height, location = heapq.heappop(sorted_list)
        height *= -1
        x = location[1]
        y = location[0]
        if x == 0 and y == 0:
            continue
        
        case_num = 0
        for j in range(4):
            n_y = y + d_y[j]
            n_x = x + d_x[j]
            if n_y < 0 or n_x < 0 or n_y == M or n_x == N:
                continue
            if graph[n_y][n_x] > height:
                case_num += memo[n_y][n_x]
        memo[y][x] = case_num


main()