package Prune;
import Prune.Tasks.TaskList;
import Prune.Exceptions.InvalidCommand;
import Prune.Exceptions.InvalidTaskNumber;
import Prune.Tasks.Deadline;
import Prune.Tasks.Event;
import Prune.Tasks.Task;
import Prune.Tasks.ToDo;

import java.util.Arrays;

public class Parser {

    public static final String DISPLAY_TASKS_COMMAND = "list";

    public static final String MARK_DONE_COMMAND = "mark";

    public static final String MARK_NOT_DONE_COMMAND = "unmark";

    public static final String TODO_COMMAND = "todo";

    public static final String DEADLINE_COMMAND = "deadline";

    public static final String EVENT_COMMAND = "event";

    public static final String DELETE_COMMAND = "delete";

    public static final String EXIT_COMMAND = "bye";

    public TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }


    public void processInput(String input) throws InvalidCommand, InvalidTaskNumber {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0];
        Task task;
        if (command.equals(DISPLAY_TASKS_COMMAND)) {
            // Process display task command
            this.tasks.printTasks();
        } else if (command.equals(MARK_DONE_COMMAND) || command.equals(MARK_NOT_DONE_COMMAND)) {
            // Process mark/unmark commands
            task = processMarking(inputArray);
            printMarkTaskMsg(task);
        } else if (command.equals(TODO_COMMAND) || command.equals(DEADLINE_COMMAND) || command.equals(EVENT_COMMAND)) {
            // Process adding task command
            task = processAdding(inputArray);
            printAddTaskMsg(task);
            String toWrite = task.getMarkCommand() + " " + String.join(" ", input);
        } else if (command.equals(DELETE_COMMAND)) {
            // Process delete task command
            task = processDeleting(inputArray);
            printDeleteTaskMsg(task);
        } else {
            throw new InvalidCommand(String.format("\tUnknown command: %s\n\tPlease try again", command));
        }
    }

    public void printAddTaskMsg(Task task) {
        System.out.printf("\tGot it. I've added this task:\n\t\t%s", task);
        System.out.printf("\n\tNow you have %d tasks in the list.\n", this.tasks.tasksList.size());
    }

    public void printDeleteTaskMsg(Task task) {
        System.out.printf("\tGot it! I've removed this task:\n\t\t%s", task);
        System.out.printf("\n\tNow you have %d tasks in the list.\n", this.tasks.tasksList.size());
    }

    public void printMarkTaskMsg(Task task) {
        if (task.getIsDone()) {
            System.out.println("\tNice! I've marked this task as done:");
        } else {
            System.out.println("\tOk, I've marked this task as not done yet:");
        }
        System.out.printf("\t\t%s\n", task.toString());

    }

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
     * Process marking commands
     * Calls Prune chatbot's method to mark task as done or not done accordingly
     *
     * @param input Command and Index of task to be updated in an array
     */
    public Task processMarking(String[] input) throws InvalidTaskNumber {
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
        }
    }

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
     * Process adding command according to type of Task
     * Create Task accordingly and calls Prune chatbot's method to add task into array
     *
     * @param input Input include command, task description and related datetimes in an array
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
}
