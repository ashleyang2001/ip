# Prune User Guide

![Prune chatbot](https://raw.githubusercontent.com/ashleyang2001/ip/refs/heads/master/prune.png)

The Prune chatbot is an app for managing tasks via a Command Line Interface (CLI).
- Features
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Adding a ToDo task: `todo`](#adding-a-todo-task-todo)
  - [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
  - [Adding an Event task: `event`](#adding-an-event-task-event)
  - [Marking task as completed: `mark`](#marking-task-as-completed-mark)
  - [Marking task as not completed: `unmark`](#marking-task-as-not-completed-unmark)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding tasks: `find`](#finding-tasks-find)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)

---

# Features

## Adding a ToDo task: `todo`
Adds a ToDo task to the task list.

**Format:** `todo DESCRIPTION`

**Example:**
- `todo Join Climbers Club`

```
	Got it. I've added this task:
		[T][ ] Join Climbers Club
	Now you have 4 tasks in the list.
```

---

## Adding a Deadline task: `deadline`
Adds a deadline task with a deadline.

**Format:** `deadline DESCRIPTION /by DATE`

**Example:**
- `deadline Submit CS2113 /by Week 9`


```
	Got it. I've added this task:
		[D][ ] submit CS2113 (by: Week 9)
	Now you have 5 tasks in the list.
```

---

## Adding an Event task: `event`
Adds an event with a start and end time.

**Format:** `event DESCRIPTION /from START /to END`

**Example:**
- `event Team meeting /from 1pm /to 2pm`

```
	Got it. I've added this task:
		[E][ ] Team meeting (from: 1pm to: 2pm)
	Now you have 6 tasks in the list.
```

---

## Listing all tasks: `list`
Displays all tasks in the task list.

**Format:** `list`

**Example:**
```
	Here are the tasks in your list:
	1.[T][ ] Join swimming club
	2.[D][X] Complete Level 8 (by: Monday)
	3.[E][ ] CS2113 Tutorial (from: 11am to: 12pm)
```

---

## Marking task as completed: `mark`
Marks a task as done in the task list.

**Format:** `mark INDEX`

- Marks the task at the specified `INDEX` as done.
- The `INDEX` must be an integer between 1 and number of existing tasks (inclusive).

**Example:**
- `mark 1` Marks the first task as completed.

```
	Nice! I've marked this task as done:
		[T][X] Join swimming club
```

---

## Marking task as not completed: `unmark`
Marks a task as not done in the task list.

**Format:** `unmark INDEX`

- Mark the task at the specified `INDEX` as not done.
- The `INDEX` must be an integer between 1 and number of existing tasks (inclusive).

**Example:**
- `unmark 2` Marks the second task as not completed.

```
	Ok, I've marked this task as not done yet:
		[D][ ] Complete Level 8 (by: Monday)
```

---

## Deleting a task: `delete`
Deletes a specified task from the task list.

**Format:** `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The `INDEX` must be an integer between 1 and number of existing tasks (inclusive).

**Example:**
- `delete 3`

```
	Got it! I've removed this task:
		[E][ ] CS2113 Tutorial (from: 11am to: 12pm)
	Now you have 5 tasks in the list.
```

---

## Finding tasks: `find`
Finds all tasks whose description contain the given keyword.

**Format:** `find KEYWORD`

- The search is case-insensitive. e.g `join` will match `Join`
- Matches substring within words. e.g `Jo` will match `Join`

**Examples:**

- `find join`  returns `Join swimming club` and `Join Climbers Club`

```
	Here are matching tasks in your list:
	1.[T][X] Join swimming club
	2.[T][ ] Join Climbers Club
```

- `find pruning` does not return anything
```
	There are no matching tasks found.
```

---

## Exiting the program: `bye`
Exits the program.

**Format:** `bye`

```
Bye. Hope to see you again soon!
```

---

## Saving the data
Tasks are automatically saved to the hard disk after any command that modifies the data. There is no need to save manually.

## Editing the data file
Tasks data are saved automatically as a txt file `[JAR file location]/saved_tasks.txt`.
Users can update task data directly by editing the data file with the right format.

**Format:** `MARKCOMMAND TASKTYPE DESCRIPTION [TASKARGUMENTS]...`

**Examples:**
- The following txt file input loads the 3 tasks.
```
mark todo Join swimming club
unmark deadline Complete Level 8 /by Monday
unmark deadline Complete Level 8 /by Monday
```

```
	Here are the tasks in your list:
	1.[T][ ] Join swimming club
	2.[D][X] Complete Level 8 (by: Monday)
	3.[E][ ] CS2113 Tutorial (from: 11am to: 12pm)
```

