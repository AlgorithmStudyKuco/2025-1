def dis(i):           #꼭지점으로부터 거리 계산
    if arr[i][0] == 1:
        return arr[i][1]
    elif arr[i][0] == 2:
        return M + arr[i][1]
    elif arr[i][0] == 3:
        return arr[i][1]
    elif arr[i][0] == 4:
        return N + arr[i][1]


N,M = map(int,input().split())
num = int(input())
arr = [list(map(int,input().split())) for _ in range(num+1)]
distance = []
for i in range(num+1):
    distance.append(dis(i))
result = 0   #최소 거리를 담기 위한 변수

for i in range(num):
    if arr[i][0] == arr[num][0] or arr[i][0] + arr[num][0] == 5: # 최저거리가 꼭지점으로부터 같은 방향일 경우
        diff = abs(distance[i] - distance[num])
        result += min(diff, 2*(M+N)-diff)

    else:
        diff = distance[i] + distance[num]
        result += min(diff, 2*(M+N)-diff)

print(result)



