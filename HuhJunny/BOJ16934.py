import sys

class Node:
    def __init__(self, key):
        self.key = key
        self.children = dict()
        self.num_of_this_name = 0
        

class Trie:
    def __init__(self):
        self.head = Node(None)

    def make_nickname(self, name):
        curr_node = self.head
        nickname = ''
        is_printed = False

        for letter in name:
            nickname += letter
            if letter not in curr_node.children:
                if not is_printed:
                    sys.stdout.write(nickname + '\n')
                    is_printed = True
                curr_node.children[letter] = Node(letter)
            curr_node = curr_node.children[letter]
        curr_node.num_of_this_name += 1

        if not is_printed:
            if curr_node.num_of_this_name > 1:
                sys.stdout.write(f'{nickname}' + f'{curr_node.num_of_this_name}\n')
            else:
                sys.stdout.write(nickname + '\n')

T = Trie()
N = int(input())
for _ in range(N):
    T.make_nickname(sys.stdin.readline().rstrip())