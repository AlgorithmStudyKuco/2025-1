import math
n, k = map(int, input().split())
dp = [math.inf] * (k + 1)
dp[0] = 0

for _ in range(n):
    coin = int(input())
    for i in range(coin, k + 1):
        if dp[i - coin] == math.inf:
            continue
        dp[i] = min(dp[i], dp[i - coin] + 1)

print(-1 if dp[k] == math.inf else dp[k])