import ast

def funct(arr,p):
    rotate = 0
    for index in p:
        if index == 'R':
            rotate += 1
        elif index == 'D':
            if arr:
                if rotate % 2 == 0:
                    arr.pop(0)
                else:
                    arr.pop()    
            else:
                return 'error'

    if rotate % 2 == 1 and arr != []:
        arr = arr[::-1] # 배열 뒤집기
    return arr

T = int(input())
for test_case in range(1,T+1):
    p = input()
    n = int(input())
    str_arr = input()
    arr = ast.literal_eval(str_arr)
    result = funct(arr, p)
    if result == 'error':
        print(result)
    else:
        print(str(result).replace(' ', ''))