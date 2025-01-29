public class Task {
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

    /**
     * Mark task as done by setting isDone to true
     */
    public void markAsDone() {
        if (this.isDone) {
            System.out.println("\tThis task was already marked as done:");
        } else {
            this.isDone = true;
            System.out.println("\tNice! I've marked this task as done:");
        }
        System.out.printf("\t\t%s", toString());
    }

    /**
     * Mark task as not done by setting isDone to false
     */
    public void markAsNotDone() {
        if (!this.isDone) {
            System.out.println("\tThis task was already marked as not done yet:");
        } else {
            this.isDone = false;
            System.out.println("\tOk, I've marked this task as not done yet:");
        }
        System.out.printf("\t\t%s", toString());
    }

    /**
     * Returns a string representation of the task
     *
     * @return Formatted string using its completion status and description
     */
    public String toString() {
        return String.format("[%s] %s\n", this.getStatusIcon(), this.description);
    }
}
