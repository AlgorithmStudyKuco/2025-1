import math

C, N = map(int, input().split())
dp = [math.inf] * (C + 101)
dp[0] = 0

for _ in range(N):
    cost, customer = map(int, input().split())
    for i in range(customer, C + 101):
        dp[i] = min(dp[i], dp[i - customer] + cost)
        
print(min(dp[C:]))