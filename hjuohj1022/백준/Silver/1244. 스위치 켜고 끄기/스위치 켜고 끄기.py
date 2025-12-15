N = int(input())
arr = list(map(int, input().split()))
M = int(input())
index = [list(map(int,input().split())) for _ in range(M)]
for student in index:
    if student[0] == 1:
        for i in range(1,N+1):
            if student[1]*i < N+1:
                arr[(student[1]*i-1)] += 1

        for i in range(N):
            if arr[i] == 2:
                arr[i] = 0

    else:
        arr[student[1]-1] += 1
        for i in range(1, N+1):
            if 0 <= student[1]-1-i < N and 0 <= student[1]-1+i < N and arr[student[1]-1-i] == arr[student[1]-1+i]:
                arr[student[1] - 1 - i] += 1
                arr[student[1] - 1 + i] += 1
            else:
                break
        for i in range(N):
            if arr[i] == 2:
                arr[i] = 0
for i in range(N):
    if (i+1) % 20 == 0 and i != 0:
        print(arr[i])

    elif i == N-1:
        print(arr[i])

    else:
        print(arr[i], end = ' ')
