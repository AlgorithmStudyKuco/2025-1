def tower_of_hanoi(N, start, mid, end):
    if N == 1:
        print(f'{start} {end}')
    else:
        tower_of_hanoi(N-1,start,end,mid)
        print(f'{start} {end}')
        tower_of_hanoi(N-1,mid,start,end)



N = int(input())
print(2**N-1)
tower_of_hanoi(N,1,2,3)