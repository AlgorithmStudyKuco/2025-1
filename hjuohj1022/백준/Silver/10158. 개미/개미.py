import sys
input = sys.stdin.readline
w, h = map(int,input().split())
p, q = map(int,input().split())
t = int(input())
dx = t+p
dy = t+q

if dx//w % 2 == 1:
    dis_x = w-dx % w
else:
    dis_x = dx % w

if dy//h % 2 == 1:
    dis_y = h-dy % h
else:
    dis_y = dy % h

print(dis_x, end=' ')
print(dis_y)
