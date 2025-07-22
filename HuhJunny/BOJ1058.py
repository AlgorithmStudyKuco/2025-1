N = int(input())
d = dict()

for i in range(N):
    line = input()
    d[i] = list()
    for j in range(N):
        if line[j] == 'Y': d[i].append(j)
             
ans = 0
for i in range(N):
    count = 0
    for j in range(N):
        if i == j: continue
        if j in d[i] or list(set(d[i]).intersection(d[j])): count += 1
    ans = max(ans, count)
print(ans)