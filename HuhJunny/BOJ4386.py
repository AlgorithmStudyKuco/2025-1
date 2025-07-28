import heapq
n = int(input())
lst = []
edges = []

def calculate_distance(v1, v2):
    return ((v1[0] - v2[0]) ** 2 + (v1[1] - v2[1]) ** 2) ** 0.5

class DS:
    def __init__(self, n):
        self.reps = [i for i in range(n)]
    
    def find_representative(self, n):
        if self.reps[n] != n:
            self.reps[n] = self.find_representative(self.reps[n])
        return self.reps[n]
    
    def union(self, a, b):
        r1 = self.find_representative(a)
        r2 = self.find_representative(b)
        self.reps[r2] = r1

    def isFamily(self, a, b):
        return self.find_representative(a) == self.find_representative(b)
    

for _ in range(n):
    x, y = map(float, input().split())
    lst.append((x, y))

for i in range(n):
    for j in range(n):
        if i >= j: continue
        v1 = lst[i]
        v2 = lst[j]
        heapq.heappush(edges, [calculate_distance(v1, v2), [i, j]])

ds = DS(n)
answer = 0
selected_edges = 0

for _ in range(len(edges)):
    distance_of_edge, vertexs = heapq.heappop(edges)
    if ds.isFamily(vertexs[0], vertexs[1]):
        continue
    ds.union(vertexs[0], vertexs[1])
    answer += distance_of_edge
    selected_edges += 1
    if selected_edges == n - 1:
        break

print(format(answer, ".2f"))