import sys
N = int(input())
numbers = [int(sys.stdin.readline()) for i in range(N)]
numbers.sort()
answer = 0

ind = 0
reverseInd = N - 1

while (numbers[ind] < 0) and (len(numbers) != 1):
    if (numbers[ind + 1] <= 0):
        answer += numbers[ind] * numbers[ind + 1]
        ind += 2
    else:
        answer += numbers[ind]
        ind += 1

while ind <= reverseInd:
    if ind == reverseInd:
        answer += numbers[reverseInd]
        reverseInd -= 1
    elif (numbers[reverseInd - 1] <= 1):
        answer += numbers[reverseInd]
        reverseInd -= 1
    else:
        answer += numbers[reverseInd] * numbers[reverseInd - 1]
        reverseInd -= 2

print(answer)
