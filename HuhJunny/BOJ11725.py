import sys
sys.setrecursionlimit(10**6)

N = int(input())

class Node:
    def __init__(self, data):
        self.data = data
        self.parent = None
        self.neighbors = []

    def put(self, n):
        self.neighbors.append(n)
        if n.data == 1: self.setParent(1)

    def setParent(self, n):
        self.parent = n

    def checkParent(self):
        if not self.parent: return
        for neighbor in self.neighbors:
            if neighbor.data == self.parent: continue
            neighbor.setParent(self.data)
            neighbor.checkParent()
    
    def getParent(self):
        return self.parent


class Tree:
    def __init__(self, N):
        self.lst = [None] * (N + 1)
        self.root = None
        self.length = N
    
    def relate(self, a, b):
        if self.lst[a]: n1 = self.lst[a]
        else:
            n1 = Node(a)
            self.lst[a] = n1
            if n1.data == 1: self.root = n1
        if self.lst[b]: n2 = self.lst[b]
        else:
            n2 = Node(b)
            self.lst[b] = n2
            if n2.data == 1: self.root = n2
        n1.put(n2)
        n2.put(n1)
        n1.checkParent()
        n2.checkParent()

    def __str__(self):
        answer = ''
        for i in range(2, self.length + 1): answer += (str(self.lst[i].getParent()) + '\n')
        return answer


T = Tree(N)
for _ in range(N - 1):
    a, b = map(int, sys.stdin.readline().split())
    T.relate(a, b)

print(T)