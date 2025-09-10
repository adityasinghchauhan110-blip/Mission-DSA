from collections import deque
def countStudents(students, sandwiches):
    students_queue = deque(students)
    sandwiches_stack = deque(sandwiches)
    attempts = 0
    while students_queue and attempts < len(students_queue):
        if students_queue[0] == sandwiches_stack[0]:
            students_queue.popleft()
            sandwiches_stack.popleft()
            attempts = 0
        else:
            students_queue.append(students_queue.popleft())
            attempts += 1  
    return len(students_queue)
students = [1, 1, 0, 0]
sandwiches = [0, 1, 0, 1]
print(countStudents(students, sandwiches))
