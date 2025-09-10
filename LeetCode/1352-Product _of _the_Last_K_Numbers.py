from collections import deque
class ProductOfNumbers:
    def init(self):
        self.queue = deque()
        self.product = 1
    def add(self, num: int) -> None:
        if num == 0:
            self.queue.clear()
            self.product = 1
        else:
            self.queue.append(num)
            self.product *= num
    def getProduct(self, k: int) -> int:
        if k > len(self.queue):
            return 0 
        temp_product = self.product
        for _ in range(len(self.queue) - k):
            temp_product //= self.queue.popleft()
        return temp_product
