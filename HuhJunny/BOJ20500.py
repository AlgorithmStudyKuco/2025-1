import math
N = int(input())
answer = 0

for one in range(N):
    if ((one + 5 * (N - one - 1) + 5) % 3) != 0:
        continue
    else:
        temp = 1
        C = N - 1
        if one > math.floor((N - 1) / 2):
            r = N - one - 1
        else:
            r = one
        for num in range(r):
            temp *= C
            C -= 1
        for num in range(r):
            temp = temp // (num + 1)
        answer += temp
        if answer // 1000000007 >= 1:
            answer %= 1000000007
    
print(int(answer))