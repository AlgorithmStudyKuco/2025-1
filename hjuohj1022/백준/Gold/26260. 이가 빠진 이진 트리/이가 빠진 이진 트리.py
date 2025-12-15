def postorder(arr):
    if len(arr) == 1:
        print(arr[0],end = ' ')
        return
    mid = len(arr)//2
    left = arr[:mid]
    right = arr[mid+1:]
    postorder(left)
    postorder(right)
    print(arr[mid],end = ' ')
    return


N = int(input())
arr = list(map(int,input().split()))
M = int(input())

for idx in range(N):
    if arr[idx] == -1:
        arr[idx] = M

arr.sort()
postorder(arr)