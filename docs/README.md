# LeChatBot User Guide

![LeChatBot Interface](/docs/Ui.png)

## Overview
LeChatBot is a command-line based chatbot that helps users manage tasks efficiently. It supports different types of tasks, including todos, deadlines, and events, and provides various commands for interacting with your task list.

---

## Features

### 1. Adding a Todo Task
Adds a new todo task to the list.

**Format:**
```plaintext
todo <task description>
```

**Example:**
```plaintext
todo Watch Bronny in the G-League
```

**Expected Output:**
```plaintext
Got it. I've added this task:
  [T][ ] Watch Bronny in the G-League
Now you have 1 task in the list.
```

---

### 2. Adding a Deadline Task
Adds a task with a deadline.

**Format:**
```plaintext
deadline <task description> /by <due date>
```

**Example:**
```plaintext
deadline trade Anthony Davis /by 06/02/2025
```

**Expected Output:**
```plaintext
Got it. I've added this task:
  [D][ ] trade Anthony Davis (by: Feb 6 2025)
Now you have 2 tasks in the list.
```

---

### 3. Adding an Event Task
Adds an event with a start and end time.

**Format:**
```plaintext
event <event description> /from <start date/time> /to <end date/time>
```

**Example:**
```plaintext
event team meeting /from 03/02/2025 1400 /to 03/02/2025 1600
```

**Expected Output:**
```plaintext
Got it. I've added this task:
  [E][ ] team meeting (from: Feb 3 2025 14:00 to: Feb 3 2025 16:00)
Now you have 3 tasks in the list.
```

---

### 4. Listing Tasks
Displays all the tasks currently in the task list.

**Format:**
```plaintext
list
```

**Example:**
```plaintext
list
```

**Expected Output:**
```plaintext
Here are the tasks in your list:
1. [ ] Watch Bronny in the G-League
2. [D][ ] trade Anthony Davis (by: Feb 6 2025)
3. [E][ ] team meeting (from: Feb 3 2025 14:00 to: Feb 3 2025 16:00)
```

---

### 5. Marking a Task as Done
Marks a task as completed.

**Format:**
```plaintext
mark <task number>
```

**Example:**
```plaintext
mark 2
```

**Expected Output:**
```plaintext
Nice! I've marked this task as done:
  [D][X] trade Anthony Davis (by: Feb 6 2025)
```

---

### 6. Unmarking a Task
Marks a previously completed task as not done.

**Format:**
```plaintext
unmark <task number>
```

**Example:**
```plaintext
unmark 2
```

**Expected Output:**
```plaintext
OK, I've marked this task as not done yet:
  [D][ ] trade Anthony Davis (by: Feb 6 2025)
```

---

### 7. Deleting a Task
Deletes a task from the list.

**Format:**
```plaintext
delete <task number>
```

**Example:**
```plaintext
delete 1
```

**Expected Output:**
```plaintext
Noted. I've removed this task:
  [ ] Watch Bronny in the G-League
```

---

### 8. Finding a Task
Finds tasks containing a specific keyword.

**Format:**
```plaintext
find <keyword>
```

**Example:**
```plaintext
find meeting
```

**Expected Output:**
```plaintext
Here are the matching tasks in your list:
3. [E][ ] team meeting (from: Feb 3 2025 14:00 to: Feb 3 2025 16:00)
```

---

### 9. Showing Help
Displays the list of available commands.

**Format:**
```plaintext
help
```

**Example:**
```plaintext
help
```

**Expected Output:**
```plaintext
Here are the available commands in LeChatBot:
1. todo <task> - Adds a new todo.
2. deadline <task> /by <due date> - Adds a deadline task.
3. event <event> /from <start> /to <end> - Adds an event task.
4. list - Displays all tasks.
5. mark <task number> - Marks a task as done.
6. unmark <task number> - Marks a task as not done.
7. delete <task number> - Deletes a task.
8. find <keyword> - Searches for tasks containing the keyword.
9. help - Displays this help message.
10. exit - Exits the application.
```

---

### 10. Exiting the Application
Closes the chatbot.

**Format:**
```plaintext
exit
```

**Example:**
```plaintext
exit
```

**Expected Output:**
```plaintext
Goodbye! Hope to see you again soon!
```

---

## Notes
- Task numbers in commands refer to the index in the task list (1-based index).
- Dates are in `DD/MM/YYYY` format, and times are in `HHMM` format (24-hour clock).

Enjoy using **LeChatBot**! 🏀