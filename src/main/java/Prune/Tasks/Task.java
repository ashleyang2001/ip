package prune.tasks;

/**
 * Represents an abstract task with a description and completion status.
 * Subclasses of Task should implement the abstract method to provide specific task functionality.
 */
public abstract class Task {
    protected String description; // The description of the task
    protected boolean isDone; // The completion status of the task

    /**
     * Constructs a Task with a description and initialise its completion status to false (not done).
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     * Returns "X" if the task is marked as done, and " " (empty space) if not done.
     *
     * @return String representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Getter method to returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter method to return the completion status of the task.
     *
     * @return True if the task is done, false if not.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the mark command based on the completion status of the task.
     * Returns "mark" if the task is done, and "unmark" if not.
     *
     * @return Mark command for the task.
     */
    public String getMarkCommand() {
        return (isDone ? "mark" : "unmark");
    }

    /**
     * Marks the task as done or not done based on the provided isDone value.
     *
     * @param isDone Completion status to set for the task.
     */
    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task for writing task data.
     *
     * @return A string representation of the task for saving.
     */
    public abstract String getInputString();

    /**
     * Returns a string representation of the task for display.
     * The format is: "[status icon] description".
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
