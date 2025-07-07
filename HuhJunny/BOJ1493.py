import math
import sys
sys.setrecursionlimit(10**6)

length, width, height = map(int, input().split())
n = int(input())
cubes = [0] * 20
for a in range(n):
    cube, num = map(int, input().split())
    cubes[cube] = num
answer = [0, True]

def makeSmallerBox(l, w, h):
    if l * w * h == 0: return 0
    box = [l, w, h]
    box.sort()
    l = box[0]
    w = box[1]
    h = box[2]
    for i in range(math.floor(math.log2(l)), -1, -1):
        if cubes[i] <= 0:
            if i == 0:
                answer[1] = False
            continue

        a = 2 ** i
        n = 0
        D = 1
        
        if cubes[i] < l // a:
            n = cubes[i]
        elif cubes[i] < (l // a) * (w // a):
            n = l // a
        elif cubes[i] < (l // a) * (w // a) * (h // a):
            n = (l // a) * (w // a)
            D = 2
        else:
            n = (l // a) * (w // a) * (h // a)
            D = 3

        cubes[i] -= n
        answer[0] += n
        if D == 1:
            makeSmallerBox(l, w - a, h)
            makeSmallerBox(l - a * n, a, h)
            makeSmallerBox(n * a, a, h - a)
        elif D == 2:
            makeSmallerBox(l, w % a, h)
            makeSmallerBox(l % a, a * (w // a), h)
            makeSmallerBox(a * (l // a), a * (w // a), h - a)
        else:
            makeSmallerBox(l, w % a, h)
            makeSmallerBox(l % a, a * (w // a), h)
            makeSmallerBox(a * (l // a), a * (w // a), h % a)

        break

makeSmallerBox(length, width, height)
print(answer[0] if answer[1] else -1)