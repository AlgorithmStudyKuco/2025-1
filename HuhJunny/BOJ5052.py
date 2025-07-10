import sys

def checkConsistency(nums):
    for i in range(2, 11):
        for pN in nums[i]:
            for j in range(1, i, 1):
                if pN[:j] in nums[j]:
                    return False
    return True


T = int(input())
answerList = []
for _ in range(T):
    n = int(input())
    nums = [[] for _ in range(11)]
    for i in range(n):
        num = sys.stdin.readline().rstrip()
        nums[len(num)].append(num)
    
    isConsistent = checkConsistency(nums)
    answer = "YES" if isConsistent else "NO"
    answerList.append(answer)

for answer in answerList:
    print(answer)