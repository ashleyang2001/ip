package prune;

import prune.tasks.TaskList;
import prune.exceptions.InvalidCommand;
import prune.exceptions.InvalidTaskNumber;
import prune.tasks.Deadline;
import prune.tasks.Event;
import prune.tasks.Task;
import prune.tasks.ToDo;

import java.util.Arrays;

/**
 * The Parser class processes user input and translates user's inputs into actions.
 * It interacts with the TaskList and Storage classes to manage and store them.
 */
public class Parser {

    public static final String DISPLAY_TASKS_COMMAND = "list";
    public static final String MARK_DONE_COMMAND = "mark";
    public static final String MARK_NOT_DONE_COMMAND = "unmark";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";
    public TaskList tasks;
    public Storage storage;

    /**
     * Constructs a Parser object with a given TaskList and Storage.
     *
     * @param tasks   TaskList containing the tasks.
     * @param storage Storage used to save the tasks.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Processes the user input command and executes the corresponding task action.
     * Handles user input commands.
     *
     * @param input Input string containing the command and task arguments.
     * @throws InvalidCommand    If the command is unknown.
     * @throws InvalidTaskNumber If the task number provided is invalid.
     */
    public void processInput(String input) throws InvalidCommand, InvalidTaskNumber {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0];
        Task task = null;

        switch (command) {
        case DISPLAY_TASKS_COMMAND:
            this.displayTasks();
            return;

        case MARK_DONE_COMMAND:
        case MARK_NOT_DONE_COMMAND:
            task = processMarking(inputArray);
            printMarkTaskMsg(task);
            break;

        case TODO_COMMAND:
        case DEADLINE_COMMAND:
        case EVENT_COMMAND:
            task = processAdding(inputArray);
            if (task != null) {
                printAddTaskMsg(task);
            }
            break;

        case DELETE_COMMAND:
            task = processDeleting(inputArray);
            printDeleteTaskMsg(task);
            break;
        case FIND_COMMAND:
            TaskList matchingTasks = processFinding(inputArray);
            displayMatchingTasks(matchingTasks);
            break;
        default:
            throw new InvalidCommand(String.format("\tUnknown command: %s\n\tPlease try again", command));
        }

        // Write data to file for mark, todo, deadline, event, delete commands
        if (task != null) {
            this.storage.writeData(this.tasks);
        }
    }

    /**
     * Prints all tasks in tasks list.
     */
    public void displayTasks() {
        if (this.tasks.isEmpty()) {
            System.out.println("\tHooray! There are no tasks in your list.");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            this.tasks.printTasks();
        }
    }

    /**
     * Prints all matching tasks found.
     */
    public void displayMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("\tThere are no matching tasks found.");
        } else {
            System.out.println("\tHere are matching tasks in your list:");
            matchingTasks.printTasks();
        }
    }

    /**
     * Prints a message after a task has been added.
     *
     * @param task Task that has been added.
     */
    public void printAddTaskMsg(Task task) {
        System.out.printf("\tGot it. I've added this task:\n\t\t%s", task);
        System.out.printf("\n\tNow you have %d tasks in the list.\n", this.tasks.tasksList.size());
    }

    /**
     * Prints a message after a task has been deleted.
     *
     * @param task Task that has been deleted.
     */
    public void printDeleteTaskMsg(Task task) {
        System.out.printf("\tGot it! I've removed this task:\n\t\t%s", task);
        System.out.printf("\n\tNow you have %d tasks in the list.\n", this.tasks.tasksList.size());
    }

    /**
     * Prints a message after a task has been marked as done or not done.
     *
     * @param task Task whose is done status has been changed.
     */
    public void printMarkTaskMsg(Task task) {
        if (task.getIsDone()) {
            System.out.println("\tNice! I've marked this task as done:");
        } else {
            System.out.println("\tOk, I've marked this task as not done yet:");
        }
        System.out.printf("\t\t%s\n", task.toString());
    }

    /**
     * Processes the saved data when loading from a file.
     *
     * @param input Input string containing the task data.
     */
    public void processSavedData(String input) {
        String[] inputArray = input.split(" ", 3);
        String markStatus = inputArray[0];
        boolean isDone = markStatus.equals(MARK_DONE_COMMAND);
        // Form add task input array by removing mark/unmark command
        String[] newInputArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
        processAdding(newInputArray);
        Task addedTask = this.tasks.tasksList.get(this.tasks.tasksList.size() - 1);
        this.tasks.markTask(addedTask, isDone);
    }

    /**
     * Processes the mark/unmark command and updates the task's is done status.
     *
     * @param input Array containing the mark command and the task number.
     * @return Task that was marked or unmarked.
     * @throws InvalidTaskNumber If the task number is invalid.
     */
    public Task processMarking(String[] input) throws InvalidTaskNumber, NullPointerException {
        String command = input[0];
        try {
            int taskIndex = Integer.parseInt(input[1]) - 1;
            if (taskIndex >= this.tasks.tasksList.size() || taskIndex < 0) {
                throw new InvalidTaskNumber("Please enter a valid task number");
            }
            Task task = this.tasks.tasksList.get(taskIndex);
            boolean isDone;
            isDone = command.equals(MARK_DONE_COMMAND);
            this.tasks.markTask(task, isDone);
            return task;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Task number must be an integer.");
        } catch (NullPointerException e) {
            throw new NullPointerException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Please enter a valid task number.");
        }
    }

    /**
     * Processes the delete command and removes the specified task from task list.
     *
     * @param input Array containing the delete command and task number.
     * @return The task that was deleted.
     * @throws InvalidTaskNumber If the task number is invalid.
     */
    public Task processDeleting(String[] input) throws InvalidTaskNumber {
        try {
            int taskIndex = Integer.parseInt(input[1]) - 1;
            if (taskIndex >= this.tasks.tasksList.size() || taskIndex < 0) {
                throw new InvalidTaskNumber("Please enter a valid task number");
            }
            Task task = this.tasks.tasksList.get(taskIndex);
            this.tasks.deleteTask(task);
            return task;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Task number must be an integer.");
        }
    }

    /**
     * Processes the adding command and creates a task based on the command type (todo, deadline, event).
     *
     * @param input Array containing the command and task arguments.
     * @return Added task.
     */
    public Task processAdding(String[] input) {
        String command = input[0];
        try {
            String details = input[1];
            String[] inputArray;
            String description;
            Task task;
            if (command.equals(TODO_COMMAND)) {
                // Process Todo
                task = new ToDo(details);
            } else if (command.equals(DEADLINE_COMMAND)) {
                // process Deadline
                inputArray = details.split(" /by ");
                description = inputArray[0];
                String by = inputArray[1];
                task = new Deadline(description, by);
            } else {
                // Process Event
                inputArray = details.split(" /from ");
                description = inputArray[0];
                String[] fromToArray = inputArray[1].split(" /to ");
                String from = fromToArray[0];
                String to = fromToArray[1];
                task = new Event(description, from, to);
            }
            this.tasks.addTask(task);
            return task;
        } catch (Exception e) {
            System.out.println(String.format("\tInsufficient descriptions given for command: %s\n\t" + "Please ensure the following format:\n" + "\t\ttodo description\n" + "\t\tdeadline description /by when\n" + "\t\tevent description /from start /to end", command));
        }
        return null;
    }

    public TaskList processFinding(String[] input) {
        String keyword = input[1];
        return this.tasks.find(keyword);
    }

}
