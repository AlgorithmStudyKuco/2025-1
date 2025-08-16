from itertools import combinations
import math

A, B, C, D, E, F = 0, 1, 2, 3, 4, 5
dice = []

def main():
    N = get_input()
    print(get_ans(N))

def get_input():
    N = int(input())
    for num in map(int, input().split()):
        dice.append(num)
    return N

def get_ans(N):
    if N == 1:
        return sum(dice) - max(dice)
    
    sum_of_two_faces = get_faces(2)
    sum_of_three_faces = get_faces(3)

    ans = 4 * sum_of_three_faces + (8 * (N - 2) + 4) * sum_of_two_faces + (5 * (N - 2) * (N - 2) + 4 * (N - 2)) * min(dice)
    
    return ans
    
def get_faces(num):
    return_sum = math.inf
    for comb in combinations([A, B, C, D, E, F], num):
        if A in comb and F in comb:
            continue
        elif B in comb and E in comb:
            continue
        elif C in comb and D in comb:
            continue

        total = 0
        for face in comb:
            total += dice[face]
        return_sum = min(total, return_sum)
    return return_sum
    

main()