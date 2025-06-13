import sys
N, d, k, c = map(int,input().split(' '))
belt = []
numShuryui = 0
start = 0
shuryui = [0] * (d + 1)
maxShuryui = 0

for i in range(N):
    belt.append(int(sys.stdin.readline()))

for i in range(k):
    if shuryui[belt[i]] == 0:
        numShuryui += 1
    shuryui[belt[i]] += 1
maxShuryui = numShuryui

while start < N:
    shuryui[belt[start]] -= 1
    if shuryui[belt[start]] == 0:
        numShuryui -= 1
    start += 1
    end = start + k - 1
    if end >= N:
        end -= N
    if shuryui[belt[end]] == 0:
        numShuryui += 1
    shuryui[belt[end]] += 1

    if shuryui[c] == 0:
        numShuryui += 1
        shuryui[c] -= 1
    if numShuryui > maxShuryui:
        maxShuryui = numShuryui
    if shuryui[c] == -1:
        numShuryui -= 1
        shuryui[c] += 1

print(maxShuryui)

    # if sum(shuryui) == k:
    #     start += 1
    #     shuryui[belt[start]] -= 1
    #     if shuryui[belt[start]] == 0:
    #         numShuryui -= 1
    # end += 1
    # if shuryui[belt[end]] == 0:
    #     numShuryui += 1
    # shuryui[belt[end]] += 1
    # if shuryui[c] == 0:
    #     shuryui[c] -= 1
    #     numShuryui += 1
    # if numShuryui == k + 1:
    #     maxShuryui = numShuryui
    #     break
    # if numShuryui > maxShuryui:
    #     maxShuryui = numShuryui
    # if shuryui[c] == -1:
    #     shuryui[c] += 1
    #     numShuryui -= 1