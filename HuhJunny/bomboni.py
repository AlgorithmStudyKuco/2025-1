import sys
num = int(input())
lst = []
for i in range(num):
    lst.append(sys.stdin.readline())

chars = ['C', 'P', 'Z', 'Y']
maxLen = 0
curLen = 0
okPosition = -1
candidates = []
chance = True

def areYouOk_X(lst, j, k, curChar):
    if j == 0:
        if lst[j + 1][k] == curChar:
            return True
    elif j == len(lst) - 1:
        if lst[j - 1][k] == curChar:
            return True
    else:
        if (lst[j + 1][k] == curChar) or (lst[j - 1][k] == curChar):
            return True
    return False

def areYouOk_Y(lst, j, k, curChar):
    if j == 0:
        if lst[k][j + 1] == curChar:
            return True
    elif j == len(lst) - 1:
        if lst[k][j - 1] == curChar:
            return True
    else:
        if (lst[k][j + 1] == curChar) or (lst[k][j - 1] == curChar):
            return True
    return False

for j in range(len(chars)):
    color = chars[j]
    for k in range(num):
        for h in range(num):
            if color == lst[k][h]:
                curLen += 1
            elif areYouOk_X(lst, k, h, color):
                if okPosition < 0:
                    okPosition = h
                    curLen += 1
                else:
                    candidates.append(curLen)
                    curLen = h - okPosition
                    okPosition = h
                chance = False
            elif (chance) and (h < len(lst) - 1) and (lst[k][h + 1] == color):
                chance = False
                okPosition = h
            else:
                candidates.append(curLen)
                curLen = 0
                okPosition = -1
                chance = True
        candidates.append(curLen)
        if max(candidates) > maxLen:
            maxLen = max(candidates)
        candidates = []
        curLen = 0
        okPosition = -1
        chance = True

    if maxLen == num:
        break
    else:
        YmaxLen = 0
        for k in range(num):
            for h in range(num):
                if color == lst[h][k]:
                    curLen += 1
                elif areYouOk_Y(lst, k, h, color):
                    if okPosition < 0:
                        okPosition = h
                        curLen += 1
                    else:
                        candidates.append(curLen)
                        curLen = h - okPosition
                        okPosition = h
                    chance = False
                elif (chance) and (h < len(lst) - 1) and (lst[h + 1][k] == color):
                    chance = False
                    okPosition = h
                else:
                    candidates.append(curLen)
                    curLen = 0
                    okPosition = -1
                    chance = True
            candidates.append(curLen)
            if max(candidates) > YmaxLen:
                YmaxLen = max(candidates)
            candidates = []
            curLen = 0
            okPosition = -1
            chance = True
        if YmaxLen > maxLen:
            maxLen = YmaxLen
    if maxLen == num:
        break

print(maxLen)








# maxLen = 1
# curLen = 1
# curChar = ''
# chance = True

# def areYouOk_X(lst, j, k, curChar): #k가 먼저바뀌는 친구
#     if j == 0:
#         if lst[j + 1][k] == curChar:
#             return True
#     elif j == len(lst) - 1:
#         if lst[j - 1][k] == curChar:
#             return True
#     else:
#         if (lst[j + 1][k] == curChar) or (lst[j - 1][k] == curChar):
#             return True
#     return False

# def areYouOk_Y(lst, j, k, curChar): #k가 먼저바뀌는 친구
#     if j == 0:
#         if lst[k][j + 1] == curChar:
#             return True
#     elif j == len(lst) - 1:
#         if lst[k][j - 1] == curChar:
#             return True
#     else:
#         if (lst[k][j + 1] == curChar) or (lst[k][j - 1] == curChar):
#             return True
#     return False

# for j in range(num):
#     curChar = lst[j][0]
#     for k in range(1, num):
#         if (curChar == lst[j][k]):
#             curLen += 1
#         elif (chance) and (areYouOk_X(lst, j, k, curChar)):
#             curLen += 1
#             chance = False

#         else:
#             if curLen > maxLen:
#                 maxLen = curLen
#             curLen = 1
#             chance = True
#             curChar = lst[j][k]
#     if curLen > maxLen:
#         maxLen = curLen
#     curLen = 1
#     chance = True
            
# if maxLen != num:
#     YmaxLen = 1
#     for j in range(num):
#         curChar = lst[0][j]
#         for k in range(1, num):
#             if (curChar == lst[k][j]):
#                 curLen += 1
#             elif (chance) and (areYouOk_Y(lst, j, k, curChar)):
#                 curLen += 1
#                 chance = False
#             else:
#                 if curLen > YmaxLen:
#                     YmaxLen = curLen
#                 curLen = 1
#                 chance = True
#                 curChar = lst[j][k]
#         if curLen > YmaxLen:
#             YmaxLen = curLen
#         curLen = 1
#         chance = True
#     if YmaxLen > maxLen:
#         maxLen = YmaxLen
    

# print(maxLen)