import sys
num = int(input())
lst = list(map(int, sys.stdin.readline().split(' ')))
stackTower = []

for i in range(len(lst)):
    me = lst[i]
    for j in range(len(stackTower) - 1, -2, -1):
        if not stackTower:
            print(0, end=' ')
            break
        elif me < stackTower[j][0]:
            print(stackTower[j][1] + 1, end=' ')
            break
        else:
            stackTower.pop()
            continue
    stackTower.append([lst[i], i])