import sys
T = int(input())
testCases = []
Answers = []
for i in range(T):
    peopleNum = int(input())
    lst = []
    for j in range(peopleNum):
        lst.append(list(map(int, sys.stdin.readline().split(' '))))
    testCases.append(lst)

for testCase in testCases:
    sortedCase = sorted(testCase)
    curIndex = 1
    prevIndex = 0
    maxGoukaku = len(sortedCase)
    while curIndex < len(sortedCase):
        if sortedCase[curIndex][1] > sortedCase[prevIndex][1]:
            maxGoukaku -= 1
        else:
            prevIndex = curIndex
        curIndex += 1

    Answers.append(maxGoukaku)

for answer in Answers:
    print(answer)




    
        # if person[0] > standard[0]:
        #     if person[1] > standard[1]:
        #         continue
        #     else:
        #         interview_higher[person[1] - 1] = 1

        # else:
        #     if person[1] <= standard[1]:
        #         paper_higher[person[0]:] = [0] * (len(testCase) - person[0])
        #         interview_higher[person[1]:] = [0] * (len(testCase) - person[1])
        #         standard = person
        #     else:
        #         paper_higher[person[0] - 1] = 1
#     answer = 1 + paper_higher.count(1) + interview_higher.count(1)
#     Answers.append(answer)

# for answer in Answers:
#     print(answer)

# for testCase in testCases:
#     personIndex = 0
#     while personIndex < len(testCase):
#         paperScore = testCase[personIndex][0]
#         interviewScore = testCase[personIndex][1]
#         areYouOk = True

#         for person in testCase:
#             if paperScore > person[0]:
#                 if interviewScore > person[1]:
#                     areYouOk = False
#                     break
        
#         if not areYouOk:
#             del testCase[personIndex]
#         else:
#             personIndex += 1
        
#     Answers.append(len(testCase))

# for answer in Answers:
#     print(answer)