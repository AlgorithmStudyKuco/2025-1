string = input()
equation = string.split('-') # 뺄셈기호를 기준으로 분류
result = 0

for i,equ in enumerate(equation): # 리스트의 각 요소 인덱스 숫자를 추가
    num = sum(list(map(int, equ.split('+')))) # 덧셈 연산자로 묶인 숫자들을 연산
    if i == 0:
        if string[0] == '-': # 첫 숫자가 음수인 경우
            result = -num
        else:
            result = num     # 첫 숫자가 양수인 경우
    else:
        result -= num        # 그 뒤의 숫자를 계속 뺄셈 연산 진행

print(result)

