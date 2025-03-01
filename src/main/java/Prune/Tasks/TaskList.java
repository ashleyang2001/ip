package Prune.Tasks;

import java.util.ArrayList;

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
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

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
