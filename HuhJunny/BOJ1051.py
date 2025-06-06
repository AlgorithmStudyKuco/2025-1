import sys
(N, M)  = map(int, (input().split(' ')))
lst = []
for i in range(N):
    lst.append(sys.stdin.readline().replace('\n', ''))

shorter = N if N <= M else M
curJ = 0
curK = 0
squareLengths = []
maxLength = 0


for j in range(N):
    for k in range(M):
        if (j > N - maxLength) or (k > M - maxLength):
            continue
        curNum = lst[j][k]
        curJ = j
        curK = k
        while (curJ != N) and (curK != M):
            if curNum == lst[curJ][curK] == lst[j][curK] == lst[curJ][k]:
                squareLengths.append(curJ - j + 1)
            curJ += 1
            curK += 1
        if max(squareLengths) > maxLength:
            maxLength = max(squareLengths)
        squareLengths = []

print(maxLength ** 2)