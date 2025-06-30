import copy

N, M = map(int, input().split())
office = []
cctvs = []
track = []
cctvNum = 0

one = [1, 0, 0, 0]
two = [1, 0, 1, 0]
three = [1, 1, 0, 0]
four = [1, 1, 1, 0]
five = [1, 1, 1, 1]
direction = [0, one, two, three, four, five]

for i in range(N):
    office.append(list(map(int, input().split())))
    for j in range(M):
        if 0 < office[i][j] < 6:
            cctvs.append((i, j, office[i][j], cctvNum))
            cctvNum += 1

def backtrack(graph, cN):
    if cN == cctvNum:
        return checkSafety(graph)

    cctv = cctvs[cN]
    cType = cctv[2]
    turnNum = calculateTurn(cType)
    ds = direction[cType]

    answer = 64

    for i in range(turnNum):
        newGraph = move(graph, [ds[i], ds[(i + 1) % 4], ds[(i + 2) % 4], ds[(i + 3) % 4]], cctv[0], cctv[1])
        track.append(newGraph)
        answer = min(backtrack(newGraph, cN + 1), answer)
        track.pop()

    return answer

def move(g, direction, i, j):
    nG = copy.deepcopy(g)
    dx = [direction[0], 0, -direction[2], 0]
    dy = [0, direction[1], 0, -direction[3]]
    for k in range(4):
        if dy[k] == dx[k] == 0:
            continue
        for u in range(1, 8):
            newY = i + (dy[k] * u)
            newX = j + (dx[k] * u)
            if newY < 0 or newX < 0 or newY >= N or newX >= M:
                break
            if nG[newY][newX] == 6:
                break
            if nG[newY][newX] == 0:
                nG[newY][newX] = 1
    return nG

def calculateTurn(cType):
    if cType == 1:
        return 4
    elif cType == 2:
        return 2
    elif cType == 3:
        return 4
    elif cType == 4:
        return 4
    else:
        return 1
    
def checkSafety(g):
    a = 0
    for i in range(N):
        a += g[i].count(0)
    return a


print(backtrack(office, 0))