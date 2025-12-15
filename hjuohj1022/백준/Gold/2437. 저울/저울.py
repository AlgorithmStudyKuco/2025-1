input(); sum = 0
for w in sorted(map(int, input().split())):
    if w > sum + 1: break;
    sum += w
print(sum + 1)