num = int(input())
lst = list(map(int, input().split(' ')))
answer = [0] * num

for i in range(num):
    height = i + 1
    position = lst[i]
    for here in range(num):
        if answer[here] == 0:
            if position > 0:
                position -= 1
            else:
                answer[here] = height
                break
        else:
            continue

for j in range(num - 1):
    print(answer[j], end = ' ')
print(answer[num - 1], end = '')