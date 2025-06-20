import sys
N = int(input())
lst = list(map(int, sys.stdin.readline().split(' ')))
lst.sort()
didYouFind = False
jotta = 0

def binary_search(target, lst):
    left = 0
    right = len(lst) - 1
    while left <= right:
        mid = (left + right) // 2
        if lst[mid] == target:
            return mid
        elif lst[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1

for i in range(N):
    didYouFind = False
    Iam = lst[i]
    for j in range(N):
        if i == j: continue
        target = Iam - lst[j]
        targetIndex = binary_search(target, lst)
        if targetIndex == -1:
            continue
        elif (targetIndex == i) or (targetIndex == j):
            belowTarget = overTarget = targetIndex
            while (belowTarget == i) or (belowTarget == j):
                if belowTarget == 0:
                    break
                else:
                    belowTarget -= 1
            while (overTarget == i) or (overTarget == j):
                if overTarget == N - 1:
                    break
                else:
                    overTarget += 1
            if (belowTarget in [i, j]) and (overTarget in [i, j]):
                continue
            elif (belowTarget in [i, j]):
                if lst[overTarget] == target:
                    didYouFind = True
                    jotta += 1
            elif (overTarget in [i, j]):
                if lst[belowTarget] == target:
                    didYouFind = True
                    jotta += 1
            else:
                if (lst[overTarget] == target) or (lst[belowTarget] == target):
                    didYouFind = True
                    jotta += 1
        else:
            didYouFind = True
            jotta += 1
        if didYouFind:
            break
            
print(jotta)