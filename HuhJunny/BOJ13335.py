import sys
n, w, L = map(int, sys.stdin.readline().split(' '))
trucks = list(map(int, sys.stdin.readline().split(' ')))
weightSum = 0
truckIndex = 1
onTheBridge = 0
time = 1

class BridgeNode:
    def __init__(self, weight):
        self.weight = weight
        self.next = None

class Bridge:
    def __init__(self):
        self.front = None
        self.rear = None
    
    def enqueue(self, data):
        if self.front is None:
            self.front = self.rear = BridgeNode(data)
        else:
            node = BridgeNode(data)
            self.rear.next = node
            self.rear = node
    
    def dequeue(self):
        if self.front is None:
            return None
        node = self.front
        if self.front == self.rear:
            self.front = self.rear = None
        else:
            self.front = self.front.next
        return node.weight
    
    def is_empty(self):
        return self.front is None

B = Bridge()
for i in range(w - 1):
    B.enqueue(0)
B.enqueue(trucks[0])
weightSum += trucks[0]
onTheBridge += 1

while (truckIndex < n) or (not B.is_empty()):
    time += 1
    front = B.dequeue()
    weightSum -= front
    if front > 0:
        onTheBridge -= 1
    
    if truckIndex >= n:
        continue
    else:
        truck = trucks[truckIndex]
        if (onTheBridge < w) and (weightSum + truck <= L):
            B.enqueue(truck)
            weightSum += truck
            onTheBridge += 1
            truckIndex += 1
        else:
            B.enqueue(0)

print(time)

# for truck in trucks:
#     if (len(queue) >= w) or (weightSum + truck > L):
#         time += (w - len(queue))
#         del queue[0]
#     queue.append(truck)
#     weightSum += truck
#     # if (not queue) or ((len(queue) < w) and (weightSum + truck <= L)):
#     #     queue.append(truck)
#     #     weightSum += truck
#     # else:
#     #     time += w - len(queue)
#     #     del queue[0]
#     #     weightSum += truck