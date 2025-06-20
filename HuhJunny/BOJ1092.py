import sys
N = int(input())
limits = list(map(int, sys.stdin.readline().split(' ')))
M = int(input())
boxs = list(map(int, sys.stdin.readline().split(' ')))

limits.sort()
boxs.sort()

craneNum = {}
smallest = boxs[0]
biggest = boxs[M - 1]
capability = []
toDo = []
cranePosition = []
time = 0

if limits[N - 1] < biggest:
    print(-1)
else:
    for limit in limits:
        if limit < smallest:
            continue
        else:
            if not limit in list(craneNum.keys()):
                craneNum[limit] = 1
            else:
                craneNum[limit] += 1
    for limit in list(craneNum.keys()):
        for i in range(M):
            if (i == M - 1) and (limit >= boxs[i]):
                capability.append(M - 1)
                break
            if limit < boxs[i]:
                capability.append(i - 1)
                break
    toDo.append(capability[0] + 1)
    cranes = list(craneNum.keys())
    prevCrane = 0
    cranePosition.append(craneNum[cranes[0]])
    for j in range(1, len(capability), 1):
        temp = capability[j] - capability[j - 1]
        if temp != 0:
            toDo.append(temp)
            prevCrane += 1
            cranePosition.append(craneNum[cranes[j]])
        else:
            cranePosition[prevCrane] += craneNum[cranes[j]]
            
    while True:
        time += 1
        for k in range(len(toDo) - 1, -1, -1):
            if (toDo[k] - cranePosition[k] == 0) and (toDo[k] + cranePosition[k] != 0):
                toDo[k] = 0
            elif (toDo[k] == 0) and (cranePosition[k] > 0):
                if k > 0:
                    cranePosition[k - 1] += cranePosition[k]
                cranePosition[k] = 0
            elif toDo[k] - cranePosition[k] < 0:
                if k > 0:
                    cranePosition[k - 1] += cranePosition[k] - toDo[k]
                    cranePosition[k] -= cranePosition[k] - toDo[k]
                toDo[k] = 0
            elif toDo[k] - cranePosition[k] >= 0:
                toDo[k] -= cranePosition[k]
        if toDo.count(0) == len(toDo):
            break
    print(time)









# craneHaldangRyang = [0] * N
# cranePosition = [0] * N
# time = 0
# remainingBoxs = M

# for i in range(N):
#     if limits[N - 1] < boxs[M - 1]:
#         break
#     limit = limits[i]
#     if limit == limits[i - 1]:
#         cranePosition[i] = cranePosition[i - 1]
#         continue
#     if cranePosition[i - 1] == M - 1:
#         cranePosition[i] = -2
#         continue
#     for j in range(M):
#         if (j == M - 1) and (limit >= boxs[j]):
#             if cranePosition[i - 1] <= 0:
#                 craneHaldangRyang[i] = M
#             else:
#                 craneHaldangRyang[i] = len(boxs[cranePosition[i - 1] + 1:])
#             cranePosition[i] = j
#         elif limit < boxs[j]:
#             if j != 0:
#                 if cranePosition[i - 1] <= 0:
#                     craneHaldangRyang[i] = len(boxs[:j])
#                 else:
#                     craneHaldangRyang[i] = len(boxs[cranePosition[i - 1] + 1:j])
#             cranePosition[i] = j - 1
#             break

# print(craneHaldangRyang)
# print(cranePosition)

# cranePortion = []
# craneNum = []
# for a in range(N):
#     if limits[N - 1] < boxs[M - 1]:
#         break
#     if craneHaldangRyang[a] == 0:
#         if cranePosition[a] == -1:
#             continue
#         else:
#             craneNum[len(craneNum) - 1] += 1
#     else:
#         cranePortion.append(craneHaldangRyang[a])
#         craneNum.append(1)

# while remainingBoxs > 0:
#     if limits[N - 1] < boxs[M - 1]:
#         break
#     time += 1
#     for i in range(len(cranePortion) - 1, -1, -1):
#         if cranePortion[i] - craneNum[i] <= 0:
#             if craneNum[i] > 0:
#                 if (i > 0):
#                     if cranePortion[i] - craneNum[i] == 0:
#                         craneNum[i - 1] += craneNum[i]
#                     else:
#                         craneNum[i - 1] += (craneNum[i] - cranePortion[i])
#                 craneNum[i] = 0
#                 remainingBoxs -= cranePortion[i]
#                 cranePortion[i] = 0
            
#         else:
#             cranePortion[i] -= craneNum[i]
#             remainingBoxs -= craneNum[i]

# if limits[N - 1] < boxs[M - 1]:
#     print(-1)
# else:
#     print(time)