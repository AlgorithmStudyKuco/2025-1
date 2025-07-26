import sys

class DS:
    def __init__(self, n):
        self.reps = [i for i in range(n + 1)]

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

n, m = map(int, input().split())
ds = DS(n)
for _ in range(m):
    op, n1, n2 = map(int, sys.stdin.readline().split())
    if op == 0:
        ds.union(n1, n2)
    else:
        answer = "YES" if ds.isFamily(n1, n2) else "NO"
        sys.stdout.write(answer + '\n')