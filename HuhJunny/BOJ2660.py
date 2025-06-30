memberNum = int(input())
friends = input()
graph = [[-1] * (memberNum + 1) for _ in range(memberNum + 1)]
for i in range(1, memberNum + 1):
    graph[i][i] = 0

while friends != '-1 -1':
    m1, m2 = map(int, friends.split())
    graph[m1][m2] = 1
    graph[m2][m1] = 1
    friends = input()

for k in range(1, memberNum + 1):
    for i in range(1, memberNum + 1):
        for j in range(1, memberNum + 1):
            if graph[i][k] > 0 and graph[k][j] > 0:
                if graph[i][j] == -1:
                    graph[i][j] = graph[i][k] + graph[k][j]
                else:
                    graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

naikouteki = [0]
minN = 51
for i in range(1, memberNum + 1):
    naikouteki.append(max(graph[i]))
    if minN > max(graph[i]):
        minN = max(graph[i])
    
print(str(minN) + ' ' + str(naikouteki.count(minN)))
for i in range(1, memberNum + 1):
    if naikouteki[i] == minN:
        print(i, end=' ')