arr = [list(map(int,input().split()))for _ in range(4)]
fun = [[0]*101 for _ in range(101)]
for rec in arr:
    for i in range(rec[0],rec[2]):
        for j in range(rec[1],rec[3]):
            fun[i][j] = 1
area = 0
for i in range(101):
    for j in range(101):
        if fun[i][j] == 1:
            area += 1
print(area) 