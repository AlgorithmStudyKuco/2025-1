N = int(input())
arr = list(map(int,input().split()))
arr.sort()
begin = 0
end = N-1
almost_zero = float('inf')

while begin < end:
    add = arr[begin] + arr[end]
    if add == 0:
        result = [arr[begin], arr[end]]
        break

    if almost_zero > abs(add):
        almost_zero = abs(add)
        result = [arr[begin], arr[end]]

    if add > 0:
        end -= 1
    else:
        begin += 1

print(*result)
