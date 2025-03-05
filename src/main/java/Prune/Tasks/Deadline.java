package Prune.Tasks;

/**
 * Represents a Deadline task. Deadline task includes a description and a deadline date.
 * It extends the Task class and provides specific functionality for tasks that have a deadline.
 */
public class Deadline extends Task {

    protected String by; // The deadline date for the task

    /**
     * Constructs a Deadline task with a description and deadline.
     *
     * @param description Description of the task.
     * @param by          Deadline date for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the task to be used for writing task data.
     * The format is: "[mark status] deadline description /by deadline date".
     *
     * @return String representation of the task for saving.
     */
    @Override
    public String getInputString() {
        return String.format("%s deadline %s /by %s", super.getMarkCommand(), this.description, this.by);
    }

    /**
     * Returns a string representation of the task for display.
     * The format is: "[D][mark status] description (by: deadline date)".
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
