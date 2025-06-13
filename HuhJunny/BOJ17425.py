import sys
import math

T = int(input())
Ns = []
for N in range(T):
    Ns.append(int(sys.stdin.readline()))

biggest = 1000000
fx = [1] * (biggest + 1)
fx[0] = 0
prefixSumList = [0] * (biggest + 1)

for i in range(2, biggest + 1, 1):
    j = 1
    while (i * j) < biggest + 1:
        fx[i * j] += i
        j += 1

for k in range(1, len(prefixSumList), 1):
    prefixSumList[k] = prefixSumList[k - 1] + fx[k]

for N in Ns:
    print(prefixSumList[N])


# biggest = max(Ns)
# limit = 0
# prefixSumList = [0] * (biggest + 1)

# if biggest % 2 == 0:
#     limit = (biggest // 2) + 1
# else:
#     limit = (biggest // 3) + 2

# for i in range(1, limit, 1):
#     for N in Ns:
#         if N >= i:
#             prefixSumList[N] += (i * (N // i))

# for N in Ns:
#     if N < limit:
#         print(prefixSumList[N])
#         continue
#     else:
#         prefixSumList[N] += (((N + limit) * (N - limit + 1)) // 2)
#         print(prefixSumList[N])





# def getPrefixSum(num):
#     sumOfDivisor = 0
#     if num % 2 == 0:
#         limit = (num // 2) + 1
#         for i in range(1, limit, 1):
#             sumOfDivisor += (i * (num // i))
        
#     else:
#         limit = (num // 3) + 2
#         for i in range(1, limit, 1):
#             sumOfDivisor += (i * (num // i))
#     sumOfDivisor += (((num + limit) * (num - limit + 1)) // 2)
#     return sumOfDivisor

# for num in Ns:
#     print(getPrefixSum(num))











# biggest = max(Ns)
# prefixSumList = [0] * (biggest + 1)
# prefixSumList[1] = 1
# divisorList = [1]

# for y in range(2, biggest + 1, 1):
#     for c in range(2, math.floor(math.sqrt(y)) + 1, 1):
#         if y % c == 0:
#             divisorList.append(c)
#             if (c != y // c):
#                 divisorList.append(y // c)
#     divisorList.append(y)
#     prefixSumList[y] = sum(divisorList) + prefixSumList[y - 1]
#     divisorList = [1]

# for N in Ns:
#     print(prefixSumList[N])