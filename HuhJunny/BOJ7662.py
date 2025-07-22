import sys
sys.setrecursionlimit(10**6)

class PriorityQueueMax:
    def __init__(self):
        self.lst = [0] * 1000001
        self.length = 0
        self.reversePQ = PriorityQueueMin()
        self.deadNodes = dict()

    def insert(self, value):
        self.length += 1
        self.lst[self.length] = value
        self.upHeap(self.length)
        self.reversePQ.insert(value)

    def delete(self, direction, forPrint = False):
        if self.length == 0:
            return None
        if direction > 0:
            while self.length > 0:
                maxValue = self.lst[1]
                if self.deadNodes.get(maxValue, 0):
                    self.lst[1] = self.lst[self.length]
                    self.lst[self.length] = 0
                    self.length -= 1
                    self.downHeap()
                    self.deadNodes[maxValue] -= 1
                else:
                    if not forPrint:
                        self.lst[1] = self.lst[self.length]
                        self.lst[self.length] = 0
                        self.length -= 1
                        self.downHeap()
                        self.reversePQ.delete(-direction, maxValue)
                    return maxValue
        else:
            target = self.reversePQ.delete(-direction, 0)
            if target is not None:
                self.deadNodes[target] = self.deadNodes.get(target, 0) + 1

    def downHeap(self):
        parent = 1
        while parent * 2 <= self.length:
            child = parent * 2
            if child != self.length and self.lst[child] < self.lst[child + 1]:
                child += 1
            if self.lst[parent] >= self.lst[child]:
                break
            self.lst[parent], self.lst[child] = self.lst[child], self.lst[parent]
            parent = child

    def upHeap(self, startIndex):
        child = startIndex
        parent = child // 2
        while self.lst[child] > self.lst[parent] and child > 1:
            self.lst[child], self.lst[parent] = self.lst[parent], self.lst[child]
            child = parent
            parent //= 2

    def __str__(self):
        maxValue = self.delete(1, True)
        minValue = self.reversePQ.delete(1, 0)
        if maxValue is None or minValue is None:
            return "EMPTY"
        else:
            return f"{maxValue} {minValue}"
    

class PriorityQueueMin:
    def __init__(self):
        self.lst = [0] * 1000001
        self.length = 0
        self.deadNodes = dict()

    def insert(self, value):
        self.length += 1
        self.lst[self.length] = value
        self.upHeap(self.length)

    def delete(self, direction, target, forPrint = False):
        if direction > 0:
            while self.length > 0:
                minValue = self.lst[1]
                if self.deadNodes.get(minValue, 0):   
                    self.lst[1] = self.lst[self.length]
                    self.lst[self.length] = 0   
                    self.length -= 1
                    self.downHeap()
                    self.deadNodes[minValue] -= 1
                else:
                    if not forPrint:
                        self.lst[1] = self.lst[self.length]
                        self.lst[self.length] = 0   
                        self.length -= 1
                        self.downHeap()
                    return minValue
            return None
        else:
            self.deadNodes[target] = self.deadNodes.get(target, 0) + 1

    def upHeap(self, startIndex):
        child = startIndex
        parent = child // 2
        while self.lst[child] < self.lst[parent] and child > 1:
            self.lst[child], self.lst[parent] = self.lst[parent], self.lst[child]
            child = parent
            parent //= 2
    
    def downHeap(self):
        parent = 1
        while parent * 2 <= self.length:
            child = parent * 2
            if child != self.length and self.lst[child] > self.lst[child + 1]:
                child += 1
            if self.lst[parent] <= self.lst[child]:
                break
            self.lst[parent], self.lst[child] = self.lst[child], self.lst[parent]
            parent = child


T = int(input())

for _ in range(T):
    PQ = PriorityQueueMax()
    k = int(input())
    for _ in range(k):
        operator, num = sys.stdin.readline().split()
        if operator == 'I':
            PQ.insert(int(num))
        else:
            PQ.delete(int(num))
    print(PQ)