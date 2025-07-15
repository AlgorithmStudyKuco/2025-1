import sys

n = int(input())
lst = []
for _ in range(n):
    address = tuple(map(int, sys.stdin.readline().split('.')))
    lst.append(address)
network_address = []
network_mask = [0, 0, 0, 0]

base = lst[0]
for i in range(32):
    p = 7 - (i % 8)
    temp_m = 1 << p
    for address in lst:
        base_bit = base[i // 8] & (1 << p)
        address_bit = address[i // 8] & (1 << p)
        temp_m &= ~(base_bit ^ address_bit)
    if temp_m == 0:
        break
    else:
        network_mask[i // 8] |= temp_m

for i in range(4):
    network_address.append(network_mask[i] & base[i])

print('.'.join(map(str, network_address)))
print('.'.join(map(str, network_mask)))