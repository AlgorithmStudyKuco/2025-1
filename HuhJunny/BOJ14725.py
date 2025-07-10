import sys

class Node:
    def __init__(self, key, floor=-1):
        self.key = key
        self.floor = floor
        self.children = dict()
    def print_Nodes(self):
        if self.floor >= 0:
            print('--' * self.floor, end='')
            print(self.key)
        
        for child in sorted(self.children):
            self.children[child].print_Nodes()

class Trie:
    def __init__(self):
        self.head = Node(None)

    def insert(self, lst):
        curr_node = self.head

        for i in range(len(lst)):
            string = lst[i]
            if string not in curr_node.children:
                curr_node.children[string] = Node(string)
            curr_node = curr_node.children[string]
            curr_node.floor = i
        
    def search_and_print(self):
        self.head.print_Nodes()


N = int(input())
T = Trie()

for _ in range(N):
    lst = list(sys.stdin.readline().split())
    T.insert(lst[1:])

T.search_and_print()