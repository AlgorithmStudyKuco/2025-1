R, C, K = map(int, input().split())
Ts = []
dX = [0, 1, 0, -1]
dY = [1, 0, -1, 0]

for i in range(R):
    row = input()
    for j in range(C):
        if row[j] == 'T':
            Ts.append([i, j])

def backtrack(position, d):
    if d == K:
        if position == [0, C - 1]:
            return 1
        else:
            return 0

    solutions = 0

    for i in range(4):
        nY = position[0] + dY[i]
        nX = position[1] + dX[i]
        if nY < 0 or nX < 0 or nY >= R or nX >= C:
            continue
        if [nY, nX] in Ts:
            continue

        Ts.append([nY, nX])
        solutions += backtrack([nY, nX], d + 1)
        Ts.pop()

    return solutions

Ts.append([R - 1, 0])
print(backtrack([R - 1, 0], 1))