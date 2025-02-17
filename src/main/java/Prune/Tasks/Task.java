package Prune.Tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getMarkCommand() {
        return (isDone ? "mark" : "unmark");
    }

    /**
     * Mark task as done by setting isDone to true
     */
    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String getInputString();

    /**
     * Returns a string representation of the task
     *
     * @return Formatted string using its completion status and description
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
