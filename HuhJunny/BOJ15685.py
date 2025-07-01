N = int(input())
lst = []
zone = [[0] * 101 for _ in range(101)]

for _ in range(N):
    x, y, d, g = map(int, input().split())
    lst.append((x, y, d, g))

for element in lst:
    dragon = []
    zone[element[0]][element[1]] = 1
    dragon.append((element[0], element[1], element[2]))
    curX = element[0] + (1 - element[2]) * ((element[2] + 1) % 2)
    curY = element[1] + (element[2] - 2) * ((element[2]) % 2)
    zone[curX][curY] = 1
    for i in range(element[3]):
        for j in range(len(dragon) - 1, -1, -1):
            newDirection = (dragon[j][2] + 1) % 4
            dragon.append((curX, curY, newDirection))
            curX = curX + (1 - newDirection) * ((newDirection + 1) % 2)
            curY = curY + (newDirection - 2) * ((newDirection) % 2)
            zone[curX][curY] = 1

count = 0

for i in range(100):
    for j in range(100):
        if zone[i][j] == 1 and zone[i + 1][j] == 1 and zone[i][j + 1] == 1 and zone[i + 1][j + 1] == 1:
            count += 1
print(count)