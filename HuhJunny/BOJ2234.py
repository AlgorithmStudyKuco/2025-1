dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

class Node:
    def __init__(self, bit, n, m):
        self.bit = bit
        self.n = n
        self.m = m
        self.roomNumber = None

    def searchNeighbors(self):
        return (self.bit & 1, self.bit & 2, self.bit & 4, self.bit & 8)
        

class Castle:
    def __init__(self, N, M):
        self.lst = [[] for _ in range(N)]
        self.N = N
        self.M = M
        self.D = dict()

    def putNode(self, bit, n, m):
        node = Node(bit, n, m)
        self.lst[n].append(node)
    
    def analyze(self):
        maxSize = 0
        size = 0
        stack = []
        rN = 1
        visited = [False] * (self.N * self.M)
        stack.append(self.lst[0][0])

        while len(stack) > 0 or visited.count(False) > 0:
            if len(stack) == 0:
                temp = visited.index(False)
                stack.append(self.lst[temp // self.M][temp % self.M])
                if maxSize < size:
                    maxSize = size
                self.D[rN] = size
                size = 0
                rN += 1

            node = stack.pop()
            if visited[node.n * self.M + node.m]: continue
            visited[node.n * self.M + node.m] = True
            node.roomNumber = rN
            size += 1

            ds = node.searchNeighbors()
            for i in range(4):
                if ds[i] > 0: continue
                stack.append(self.lst[node.n + dy[i]][node.m + dx[i]])
        self.D[rN] = size

        return max(maxSize, size), rN
    
    def eliminate_and_search(self):
        maxS = 0
        tempLst = [[0] * self.M for _ in range(self.N)]
        for i in range(self.N):
            for j in range(self.M):
                tempLst[i][j] = self.lst[i][j].roomNumber
        for i in range(self.N):
            for j in range(self.M):
                for k in range(4):
                    if 0 <= i + dy[k] < self.N and 0 <= j + dx[k] < self.M:
                        if tempLst[i][j] == tempLst[i + dy[k]][j + dx[k]]: continue
                        maxS = max(self.D[tempLst[i][j]] + self.D[tempLst[i + dy[k]][j + dx[k]]], maxS)
        return maxS
                    

M, N = map(int, input().split())

C = Castle(N, M)
for i in range(N):
    t = list(map(int, input().split()))
    for j in range(M):
        C.putNode(t[j], i, j)

mS, rN = C.analyze()

print(rN)
print(mS)
print(C.eliminate_and_search())