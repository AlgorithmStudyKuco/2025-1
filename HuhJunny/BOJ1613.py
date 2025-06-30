import sys

n, k = map(int, input().split())
graph = [[-1] * (n + 1) for _ in range(n + 1)]
for i in range(1, n + 1):
    graph[i][i] = 0
for _ in range(k):
    case1, case2 = map(int, sys.stdin.readline().split())
    graph[case1][case2] = 1

for k in range(1, n + 1):
    for i in range(1, n + 1):    
        for j in range(1, n + 1):
            if graph[i][k] > 0 and graph[k][j] > 0:
                graph[i][j] = 1

s = int(input())
answerList = []
for _ in range(s):
    case1, case2 = map(int, sys.stdin.readline().split())
    if graph[case1][case2] != -1:
        answerList.append(-1)
    elif graph[case2][case1] != -1:
        answerList.append(1)
    else:
        answerList.append(0)

for answer in answerList:
    print(answer)