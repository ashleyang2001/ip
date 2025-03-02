package Prune.Tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getInputString() {
        return String.format("%s todo %s", super.getMarkCommand(), this.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
