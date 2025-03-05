package Prune.Tasks;

/**
 * Represents a ToDo task. A ToDo task includes a description and is used for tasks that have no specific time or deadline.
 * It extends the Task class and provides specific functionality for tasks that represent a general ToDo item.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a description.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task to be used for writing task data.
     * The format is: "[mark status] todo description".
     *
     * @return String representation of the ToDo task for saving.
     */
    @Override
    public String getInputString() {
        return String.format("%s todo %s", super.getMarkCommand(), this.description);
    }

    /**
     * Returns a string representation of the ToDo task for display.
     * The format is: "[T][mark status] description".
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
