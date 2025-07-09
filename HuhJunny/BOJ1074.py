N, r, c = map(int, input().split())
answer = [0]

def whereAreYou(nN, row, col):
    if nN == 1: return

    if row < (nN // 2):
        if col < (nN // 2):
            whereAreYou(nN // 2, row, col)
        else:
            answer[0] += (nN // 2) ** 2
            whereAreYou(nN // 2, row, col - (nN // 2))
    else:
        if col < (nN // 2):
            answer[0] += (nN // 2) * nN
            whereAreYou(nN // 2, row - (nN // 2), col)
        else:
            answer[0] += (nN // 2) * (nN // 2) * 3
            whereAreYou(nN // 2, row - (nN // 2), col - (nN // 2))

whereAreYou(2 ** N, r, c)
print(answer[0])