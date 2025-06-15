import sys
N = int(input())
schedule = [0]
for i in range(N):
    schedule.append(list(map(int, sys.stdin.readline().split(' '))))
DP = [0] * (N + 2)
maxPay = 0

for j in range(1, N + 1, 1):
    days = schedule[j][0]
    pay = schedule[j][1]
    if DP[j] > maxPay:
        maxPay = DP[j]
    if j + days > N + 1:
        continue
    sumOfPays = maxPay + pay
    DP[j + days] = max([sumOfPays, DP[j + days]])

print(max(DP))