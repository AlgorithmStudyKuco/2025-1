def preorder(n):
    if n == '.':
        return
    print(n, end = '')
    preorder(left[node[n]])
    preorder(right[node[n]])

def inorder(n):
    if n == '.':
        return
    inorder(left[node[n]])
    print(n, end = '')
    inorder(right[node[n]])

def postorder(n):
    if n == '.':
        return
    postorder(left[node[n]])
    postorder(right[node[n]])
    print(n, end='')

N = int(input())
arr = [list(map(str,input().split())) for _ in range(N)]
left = ['.']*(N+1)
right = ['.']*(N+1)
node = {} # 알파벳의 순서를 저장하기 위해 사용
for i in range(N): # 각 노드의 왼쪽 ,오른쪽 자식 노드를 저장
    node[arr[i][0]] = i+1
    if arr[i][1] != '.':
        left[i+1] = arr[i][1]

    if arr[i][2] != '.':
        right[i+1] = arr[i][2]

preorder('A')
print()
inorder('A')
print()
postorder('A')