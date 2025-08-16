import copy

N = int(input())
nums = [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
pairs = dict()
answer = 0

for _ in range(N):
    num = input()
    L = len(num)
    for i in range(L - 1, -1, -1):
        pairs[num[i]] = pairs.get(num[i], 0) + 1 * 10 ** (L - i - 1)

table = list(pairs.values())

for num in nums:
    temp_table = copy.deepcopy(table)
    max_val = 0
    ind = 0

    for i in range(len(temp_table)):
        temp_table[i] *= num
        if max_val < temp_table[i]:
            ind = i
            max_val = temp_table[i]
        
    answer += max_val
    table[ind] = 0

print(answer)