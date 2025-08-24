import sys

N, M, H = map(int, input().split())
ladder = [[0] * (N + 1) for _ in range(H + 1)]

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    ladder[a][b] = b + 1
    ladder[a][b + 1] = b

def check(line):
    completed = 0
    for location in range(1, N + 1):
        id = location
        for i in range(1, H + 1):
            id = ladder[i][id] if ladder[i][id] != 0 else id
        if location == id:
            completed += 1
    if completed == N:
        return 1
    elif completed > N - 2 * (3 - line) - 1:
        return 0
    else:
        return -1

def backtrack(line, i, j, answer):
    hows_going = check(line)
    if hows_going < 0:
        return -1
    elif hows_going > 0:
        return line
    
    while i < H + 1:
        if j != N and ladder[i][j] == 0 and ladder[i][j + 1] == 0:
            ladder[i][j] = j + 1
            ladder[i][j + 1] = j
            ans = backtrack(line + 1, i, j, answer)
            if ans == 1:
                return 1
            answer = answer if ans == -1 else min(answer, ans)
            ladder[i][j] = 0
            ladder[i][j + 1] = 0
        j += 1
        if j == N + 1:
            j -= N
            i += 1
    
    return answer


a = backtrack(0, 0, 0, 4)
print(-1 if a == 4 else a)