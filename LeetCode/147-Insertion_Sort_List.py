class ListNode:
    def init(self, val=0, next=None):
        self.val = val
        self.next = next
def insertionSortList(head):
    if not head or not head.next:
        return head
    dummy = ListNode(0)
    current = head
    while current:
        next_node = current.next
        prev = dummy
        while prev.next and prev.next.val < current.val:
            prev = prev.next
        current.next = prev.next
        prev.next = current
        current = next_node
    return dummy.next
def create_linked_list(values):
    if not values:
        return None
    head = ListNode(values[0])
    current = head
    for val in values[1:]:
        current.next = ListNode(val)
        current = current.next
    return head
def print_linked_list(head):
    result = []
    while head:
        result.append(head.val)
        head = head.next
    print(" -> ".join(map(str, result)))
values = [4, 2, 1, 3]
head = create_linked_list(values)
print("Original List:")
print_linked_list(head)
sorted_head = insertionSortList(head)
print("Sorted List:")
print_linked_list(sorted_he
