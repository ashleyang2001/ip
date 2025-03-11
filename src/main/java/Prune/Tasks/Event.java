package prune.tasks;

/**
 * Represents an Event task. An Event task includes a description, a start time, and an end time.
 * It extends the Task class and provides specific functionality for tasks that represent events.
 */
public class Event extends Task {

    protected String from; // The start time of the event
    protected String to;   // The end time of the event

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from        Start time of the event.
     * @param to          End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task to be used for writing task data.
     * The format is: "[mark status] event description /from start time /to end time".
     *
     * @return String representation of the event task for saving.
     */
    @Override
    public String getInputString() {
        return String.format("%s event %s /from %s /to %s", super.getMarkCommand(), this.description, this.from, this.to);
    }

    /**
     * Returns a string representation of the event task for display.
     * The format is: "[E][mark status] description (from: start time to: end time)".
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
