import sys

L, C = map(int, input().split())
literal = list(input().split())

moeum = ['a', 'e', 'i', 'o', 'u']
literal.sort()

def backtrack(code, ind, moeum_num, length):
    if length == L:
        if moeum_num != 0 and moeum_num <= L - 2:
            sys.stdout.write(code + '\n')
        return

    if ind - length > C - L:
        return

    for i in range(ind, C):
        zaeum = True if literal[i] not in moeum else False
        if not zaeum:
            moeum_num += 1
        backtrack(code + literal[i], i + 1, moeum_num, length + 1)
        if not zaeum:
            moeum_num -= 1

backtrack('', 0, 0, 0)