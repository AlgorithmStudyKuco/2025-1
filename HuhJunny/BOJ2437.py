N = int(input())
num_list = list(map(int, input().split()))

num_list.sort()

continuous_length = 0

for num in num_list:
    if continuous_length + 1 < num:
        print(continuous_length + 1)
        break
    continuous_length += num

if continuous_length == sum(num_list):
    print(continuous_length + 1)