def swapPairs(head):
    if not head or not head.next:
        return head
    first = head
    second = head.next
    first.next = swapPairs(second.next)
    second.next = first
    return second
