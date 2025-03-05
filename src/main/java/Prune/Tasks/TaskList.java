package Prune.Tasks;

import java.util.ArrayList;

/**
 * The TaskList class manages a collection of tasks.
 * It maintains a list of tasks and provides functionalities for task manipulation.
 */
public class TaskList {
    public final ArrayList<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasksList = tasks;
    }

    public TaskList find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasksList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Adds a task to the tasks list.
     *
     * @param task Task to be added into the tasks list.
     */
    public void addTask(Task task) {
        this.tasksList.add(task);
    }

    /**
     * Deletes a specified task from the tasks list.
     *
     * @param task Task to be removed from the tasks list.
     */
    public void deleteTask(Task task) {
        this.tasksList.remove(task);
    }

    /**
     * Marks a task completion status based on the provided boolean value.
     *
     * @param task   Task to be marked.
     * @param isDone Boolean value specifying whether to mark or unmark the task.
     */
    public void markTask(Task task, boolean isDone) {
        task.markAsDone(isDone);
    }

    /**
     * Prints all tasks in the tasks list.
     * If the list is empty, a message will be displayed indicating that there are no tasks.
     */
    public void printTasks() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.printf("\t%s.%s\n", i + 1, tasksList.get(i));
        }
    }

    public boolean isEmpty() {
        return this.tasksList.isEmpty();
    }

    public int size() {
        return this.tasksList.size();
    }
}
