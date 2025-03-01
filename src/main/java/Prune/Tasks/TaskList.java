package Prune.Tasks;

import java.util.ArrayList;

public class TaskList {
    public final ArrayList<Task> tasksList = new ArrayList<>();

    /**
     * Instantiate Task and add Task to tasks array
     * Increments taskCount
     *
     * @param task Task to be added into tasks array
     */
    public void addTask(Task task) {
        this.tasksList.add(task);
    }

    public void deleteTask(Task task) {
        this.tasksList.remove(task);
    }

    /**
     * Process "mark" and "unmark" commands to reflect task completion status
     *
     * @param task   Task to be marked
     * @param isDone Boolean variable to decide whether to mark or unmark task
     */
    public void markTask(Task task, boolean isDone) {
        task.markAsDone(isDone);
    }

    /**
     * Prints all tasks in tasks array
     */
    public void printTasks() {
        System.out.println("\tHere are the tasks in your list:");
        if (this.tasksList.isEmpty()) {
            System.out.println("\tHooray! There are no tasks in your list.");
        } else {
            for (int i = 0; i < this.tasksList.size(); i++) {
                System.out.printf("\t%s.%s\n", i + 1, tasksList.get(i));
            }
        }
    }
}
