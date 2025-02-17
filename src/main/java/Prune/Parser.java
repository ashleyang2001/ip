package Prune;

import Prune.Exceptions.InvalidCommand;
import Prune.Exceptions.InvalidTaskNumber;
import Prune.Tasks.Deadline;
import Prune.Tasks.Event;
import Prune.Tasks.Task;
import Prune.Tasks.ToDo;

import java.util.ArrayList;

public class Parser {

    public static final String DISPLAY_TASKS_COMMAND = "list";

    public static final String MARK_DONE_COMMAND = "mark";

    public static final String MARK_NOT_DONE_COMMAND = "unmark";

    public static final String TODO_COMMAND = "todo";

    public static final String DEADLINE_COMMAND = "deadline";

    public static final String EVENT_COMMAND = "event";

    public static final String DELETE_COMMAND = "delete";

    public Prune chatbot;

    public Parser(Prune chatbot) {
        this.chatbot = chatbot;
    }

    /**
     * Process user's input for making calls to Prune chatbot's methods
     *
     * @param input User's input as a string
     */
    public void processInput(String input) throws InvalidCommand, InvalidTaskNumber {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0];
        if (command.equals(DISPLAY_TASKS_COMMAND)) {
            // Process display task command
            this.chatbot.printTasks();
        } else if (command.equals(MARK_DONE_COMMAND) || command.equals(MARK_NOT_DONE_COMMAND)) {
            // Process mark/unmark commands
            processMarking(inputArray);
        } else if (command.equals(TODO_COMMAND) || command.equals(DEADLINE_COMMAND) || command.equals(EVENT_COMMAND)) {
            // Process adding task command
            processAdding(inputArray);
        } else if (command.equals(DELETE_COMMAND)) {
            // Process delete task command
            processDeleting(inputArray);
        } else {
            throw new InvalidCommand(String.format("\tUnknown command: %s\n\tPlease try again", command));
        }
    }

    /**
     * Process marking commands
     * Calls Prune chatbot's method to mark task as done or not done accordingly
     *
     * @param input Command and Index of task to be updated in an array
     */
    public void processMarking(String[] input) throws InvalidTaskNumber {
        String command = input[0];
        try {
            int taskIndex = Integer.parseInt(input[1]) - 1;
            if (taskIndex >= this.chatbot.tasks.size() || taskIndex < 0) {
                throw new InvalidTaskNumber("Please enter a valid task number");
            }
            Task task = this.chatbot.tasks.get(taskIndex);
            boolean isDone;
            isDone = command.equals(MARK_DONE_COMMAND);
            this.chatbot.markTask(task, isDone);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Task number must be an integer.");
        }
    }

    public void processDeleting(String[] input) throws InvalidTaskNumber {
        try {
            int taskIndex = Integer.parseInt(input[1]) - 1;
            if (taskIndex >= this.chatbot.tasks.size() || taskIndex < 0) {
                throw new InvalidTaskNumber("Please enter a valid task number");
            }
            Task task = this.chatbot.tasks.get(taskIndex);
            this.chatbot.deleteTask(task);
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
    public void processAdding(String[] input) {
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
            this.chatbot.addTask(task);
        } catch (Exception e) {
            System.out.println(String.format("\tInsufficient descriptions given for command: %s\n\t" +
                    "Please ensure the following format:\n" +
                    "\t\ttodo description\n" +
                    "\t\tdeadline description /by when\n" +
                    "\t\tevent description /from start /to end", command));
        }
    }
}
