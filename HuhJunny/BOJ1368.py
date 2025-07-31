import heapq
import sys

N = int(input())
answer = 0

class DS:
    def __init__(self, N):
        self.reps = [i for i in range(N)]

    def find_rep(self, n):
        if self.reps[n] != n:
            self.reps[n] = self.find_rep(self.reps[n])
        return self.reps[n]
    
    def union(self, a, b, lst, w):
        r1 = self.find_rep(a)
        r2 = self.find_rep(b)
        if lst[r1] < w and lst[r2] < w:
            return 0
        elif lst[r1] > lst[r2]:
            self.reps[r1] = r2
            return w
        else:
            self.reps[r2] = r1
            return w

    def is_family(self, a, b):
        return self.find_rep(a) == self.find_rep(b)
    
    def calculate_well_costs(self, lst):
        for i in range(len(self.reps)):
            self.find_rep(i)
        well_cost = 0
        welled_non = set(self.reps)
        for non in welled_non:
            well_cost += lst[non]
        return well_cost


well_costs = [0] * N
edges = []

for i in range(N):
    w = int(sys.stdin.readline())
    well_costs[i] = w

for i in range(N):
    p = list(map(int, sys.stdin.readline().split()))
    for j in range(N):
        if i >= j: continue
        heapq.heappush(edges, [p[j], [i, j]])

ds = DS(N)
for _ in range(len(edges)):
    weight, vertex = heapq.heappop(edges)
    if ds.is_family(vertex[0], vertex[1]):
        continue
    answer += ds.union(vertex[0],vertex[1], well_costs, weight)

answer += ds.calculate_well_costs(well_costs)

print(answer)