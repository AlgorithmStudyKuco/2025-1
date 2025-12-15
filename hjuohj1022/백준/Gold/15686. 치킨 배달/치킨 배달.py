combi = []
min_chickendistance = float('inf')

def back(num,cnt): #백트래킹을 활용한 최대 치킨집 M개를 정하는 조합 구성
    global min_chickendistance
    if num > len(chicken): # 리스트 인덱스 벗어남 방지
        return

    if cnt == M:
        distance = 0
        for x,y in house: #모든 집에서 치킨집까지의 거리를 측정하기 위해 반복
            min_dis=float('inf')
            for index in combi: # 조합을 구성하고 선택한 치킨집을 반복
                nx,ny = chicken[index]
                min_dis = min(min_dis,abs(x-nx)+abs(y-ny))
            distance += min_dis
        min_chickendistance = min(min_chickendistance,distance)
        return
    
    combi.append(num)
    back(num+1,cnt+1)
    combi.pop()
    back(num+1,cnt)
    return min_chickendistance

N,M = map(int,input().split())
grath = [list(map(int,input().split())) for _ in range(N)]
house = []
chicken = []

for i in range(N):
    for j in range(N):
        if grath[i][j] == 1:    # 집일 경우
            house.append((i,j))
        elif grath[i][j] == 2:  # 치킨집일 경우
            chicken.append((i,j))

print(back(0,0))