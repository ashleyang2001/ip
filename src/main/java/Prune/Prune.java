package Prune;

import Prune.Tasks.Task;

/**
 * The Prune class is a chatbot named Prune who manages all tasks.
 */
public class Prune {

    public static final int MAX_NUMBER_OF_TASKS = 100;
    public final Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];

    public int tasksCount = 0;

    /**
     * Instantiate Task and add Task to tasks array
     * Increments taskCount
     *
     * @param task Task to be added into tasks array
     */
    public void addTask(Task task) {
        this.tasks[tasksCount] = task;
        tasksCount++;
        System.out.printf("\tGot it. I've added this task:\n\t\t%s", task);
        System.out.printf("\n\tNow you have %d tasks in the list.\n", tasksCount);
    }

    /**
     * Prints all tasks in tasks array
     */
    public void printTasks() {
        System.out.println("\tHere are the tasks in your list:");
        if (tasksCount == 0) {
            System.out.println("\tHooray! There are no tasks in your list.");
        } else {
            for (int i = 0; i < tasksCount; i++) {
                System.out.printf("\t%s.%s\n", i + 1, tasks[i]);
            }
        }
    }

    /**
     * Process "mark" and "unmark" commands to reflect task completion status
     *
     * @param taskIndex Index of task to be marked
     * @param isDone    Boolean variable to decide whether to mark or unmark task
     */
    public void markTask(int taskIndex, boolean isDone) {
        if (isDone) {
            this.tasks[taskIndex].markAsDone();
        } else {
            this.tasks[taskIndex].markAsNotDone();
        }
    }

    /**
     * Main entry point of program.
     */
    public static void main(String[] args) {
        Prune prune = new Prune();
        Parser parser = new Parser(prune);
        UserInterface.greet();
        while (true) {
            try {
                String output = UserInterface.interactWithUser();
                parser.processInput(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
