package Prune.Tasks;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getInputString() {
        return String.format("%s event %s /from %s /to %s", super.getMarkCommand(), this.description, this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
