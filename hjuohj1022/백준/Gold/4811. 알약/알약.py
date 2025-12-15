while True:
    N = int(input())
    if N == 0:
        break
    com = 1
    for i in range(N+2,2*N+1):
        com *= i
    for i in range(1,N+1):
        com = com//i
    print(com)