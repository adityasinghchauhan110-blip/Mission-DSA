class ListNode:
    def init(self, val=0, next=None):
        self.val = val
        self.next = next
def detectCycle(head):
    if not head or not head.next:
        return None
    slow, fast = head, head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow == fast: 
            break
    else:
        return None  
    slow = head
    while slow != fast:
        slow = slow.next
        fast = fast.next
    return slow
