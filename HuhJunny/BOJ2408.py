def calculate(op, n1, n2):
    if op == '+':
        return add_nums(n1, n2)
    elif op == '-':
        return sub_nums(n1, n2)
    elif op == '*':
        return mul_nums(n1, n2)
    else:
        return div_nums(n1, n2)
    

def preprocess(n):
    results = []
    operations = []

    num = input()
    for _ in range(n - 1):
        op = input()
        if op == '+' or op == '-':
            results.append(num)
            operations.append(op)
            num = input()
        else:
            num = calculate(op, num, input())
    results.append(num)

    num = results[0]
    for i in range(0, len(operations)):
        num = calculate(operations[i], num, results[i + 1])
    
    return num


def add_nums(n1, n2):
    if n1[0] == '-' and n2[0] == '-':
        return '-' + add_nums(n1[1:], n2[1:])
    elif n1[0] == '-':
        return sub_nums(n2, n1[1:])
    elif n2[0] == '-':
        return sub_nums(n1, n2[1:])
    
    length = max(len(n1), len(n2))
    carry = [0] * length
    result = [''] * length

    if len(n1) < len(n2):
        n1 = '0' * (length - len(n1)) + n1
    elif len(n2) < len(n1):
        n2 = '0' * (length - len(n2)) + n2

    for i in range(-1, -length - 1, -1):
        digit_num = int(n1[i]) + int(n2[i]) + carry[i]
        result[i] = str(digit_num % 10)
        if digit_num >= 10 and i == -length:
            return '1' + "".join(result)
        elif digit_num >= 10:
            carry[i - 1] = 1
    
    return "".join(result)
        
def sub_nums(n1, n2):
    if n1[0] == '-' and n2[0] == '-':
        return sub_nums(n2[1:], n1[1:])
    elif n1[0] == '-':
        return '-' + add_nums(n1[1:], n2)
    elif n2[0] == '-':
        return add_nums(n1, n2[1:])

    length = max(len(n1), len(n2))
    carry = [0] * length
    result = [''] * length

    if len(n1) < len(n2):
        n1 = '0' * (length - len(n1)) + n1
    elif len(n2) < len(n1):
        n2 = '0' * (length - len(n2)) + n2

    for i in range(-1, -length - 1, -1):
        digit_num = int(n1[i]) - int(n2[i]) - carry[i]

        if i == -length and digit_num == 0:
            result[i] = str(0)
            ind = 0
            while ind < length and result[ind] == '0':
                ind += 1
            return "".join(result[ind:]) if ind != length else '0'
        elif i == -length and digit_num < 0:
            result[i] = str(digit_num + 10)
            return '-' + sub_nums('1' + '0' * length, "".join(result))
        elif i == -length and digit_num > 0:
            result[i] = str(abs(digit_num) % 10)
            return "".join(result)
        elif digit_num == 0:
            result[i] = str(0)
        elif digit_num > 0:
            result[i] = str(digit_num)
        else:
            result[i] = str(digit_num + 10)
            carry[i - 1] = 1

def mul_nums(n1, n2):
    if n1[0] == '-' and n2[0] != '-':
        return '-' + mul_nums(n1[1:], n2)
    elif n1[0] != '-' and n2[0] == '-':
        return '-' + mul_nums(n1, n2[1:])
    elif n1[0] == '-' and n2[0] == '-':
        return mul_nums(n1[1:], n2[1:])
    
    if n1 == '0' or n2 == '0':
        return '0'
    
    length = len(n1) + len(n2)
    result = [0] * length

    if len(n1) < len(n2):
        n1, n2 = n2, n1

    for i in range(-1, -len(n2) - 1, -1):
        for j in range(-1, -len(n1) - 1, -1):
            digit = i + j + 1
            mul = int(n1[j]) * int(n2[i])

            result[digit] += mul % 10
            if result[digit] >= 10:
                result[digit] -= 10
                result[digit - 1] += 1

            result[digit - 1] += mul // 10
            if result[digit - 1] >= 10:
                result[digit - 1] -= 10
                result[digit - 2] += 1
            
            ind = digit - 2
            while ind >= -length and result[ind] == 10:
                result[ind] -= 10
                result[ind - 1] += 1
                ind -= 1
    
    id = 0
    while id < length and result[id] == 0:
        id += 1

    return "".join(map(str, result[id:]))
            
def div_nums(n1, n2):
    minus = False
    if n1[0] == '-' and n2[0] != '-':
        minus = True
        n1 = n1[1:]
    elif n1[0] != '-' and n2[0] == '-':
        minus = True
        n2 = n2[1:]
    elif n1[0] == '-' and n2[0] == '-':
        n1 = n1[1:]
        n2 = n2[1:]

    if len(n1) < len(n2):
        return '-1' if minus else '0'
    
    l1, l2 = len(n1), len(n2)
    length = l1 - l2 + 1
    result = [0] * length
    n1_copy = list(map(int, n1))
    ind = -l1
    dividend = n1_copy[ind : ind + l2] if ind + l2 != 0 else n1_copy[ind:]

    while ind <= -l2:
        mini_result = [0] * l2
        carry = [0] * l2
        for i in range(-1, -l2 - 1, -1):
            digit_num = int(dividend[i]) - int(n2[i]) - carry[i]

            if i == -l2 and digit_num < 0 and (ind == -l1 or n1_copy[ind - 1] == 0):
                ind += 1
                if ind == -l2:
                    dividend = n1_copy[ind:]
                elif ind < -l2:
                    dividend = n1_copy[ind : ind + l2]
            elif i == -l2:
                if digit_num < 0:
                    n1_copy[ind - 1] -= 1
                    digit_num += 10
                mini_result[i] = digit_num
                for j in range(l2):
                    n1_copy[ind + j] = mini_result[j]
                result[ind + l2 - 1] += 1
                dividend = mini_result
            elif digit_num == 0:
                continue
            elif digit_num > 0:
                mini_result[i] = digit_num
            else:
                mini_result[i] = digit_num + 10
                carry[i - 1] = 1

    remain = False
    for i in range(-l2, 0, 1):
        if n1_copy[i] != 0:
            remain = True
    
    sign = '-' if minus else ''

    if minus and remain:
        result[-1] += 1
        id = -1
        while id >= -length and result[id] == 10:
            result[id] -= 10
            result[id - 1] += 1
            id -= 1

    i = 0
    while i < length and result[i] == 0:
        i += 1
    return sign + "".join(map(str, result[i:])) if i != length else '0'



N = int(input())
print(preprocess(N))