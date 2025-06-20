import sys
N, M = map(int, input().split(' '))
lst = [int(sys.stdin.readline()) for i in range(N)]
mintime = 0
maxtime = max(lst) * M
answer = maxtime
    
while mintime <= maxtime:
    time = (mintime + maxtime) // 2
    afford = [0] * N
    for i in range(N):
        afford[i] = time // lst[i]
    if sum(afford) >= M:
        answer = time
        maxtime = time - 1
    else:
        mintime = time + 1

print(answer)