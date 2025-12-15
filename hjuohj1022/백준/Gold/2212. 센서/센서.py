n = int(input())
sensor = int(input())

my_list = list(map(int, input().split()))
my_list.sort()

range_minus = []

for i in range(n-1):
	range_minus.append(my_list[i+1] - my_list[i])
	
range_minus.sort(reverse=True)

if sensor >= n:
  print(0)
else:
  print(sum(range_minus[sensor-1:]))