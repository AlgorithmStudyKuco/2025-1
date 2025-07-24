import sys

class DS:
    def __init__(self, n):
        self.reps = [i for i in range(n)]

    def findRep(self, n):
        if self.reps[n] != n:
            self.reps[n] = self.findRep(self.reps[n])
        return self.reps[n]
    
    def union(self, n1, n2):
        r1 = self.findRep(n1)
        r2 = self.findRep(n2)
        self.reps[r2] = r1

    def isFamily(self, n1, n2):
        return True if self.findRep(n1) == self.findRep(n2) else False

T = int(input())
for i in range(1, T + 1):
    sys.stdout.write(f"Scenario {i}:\n")
    n = int(input())
    ds = DS(n)
    k = int(input())
    for _ in range(k):
        a, b = map(int, sys.stdin.readline().split())
        ds.union(a, b)
    m = int(input())
    for _ in range(m):
        u, v = map(int, sys.stdin.readline().split())
        answer = '1' if ds.isFamily(u, v) else '0'
        sys.stdout.write(answer + '\n')
    sys.stdout.write('\n')