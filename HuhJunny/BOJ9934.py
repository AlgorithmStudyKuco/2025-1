import sys
K = int(input())
order = list(map(int, input().split()))

tree = [0] * (2 ** K)

position = 1
curB = 0

def havingChild(p, K):
    return True if p * 2 + 1 < 2 ** K else False

while position < 2 ** K and curB < 2 ** K - 1:
    if havingChild(position, K) and tree[position * 2] and tree[position * 2 + 1]: position //= 2
    elif havingChild(position, K) and not tree[position * 2]: position *= 2
    elif not havingChild(position, K) or tree[position * 2]:
        tree[position] = order[curB]
        curB += 1
        if not havingChild(position, K): position //= 2
        else: position = position * 2 + 1

num = 1
for i in range(K):
    a = 2 ** i
    for j in range(a):
        sys.stdout.write(str(tree[num]) + ' ')
        num += 1
    sys.stdout.write('\n')