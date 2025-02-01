public class Parser {

    public static final String DISPLAY_TASKS_COMMAND = "list";

    public static final String MARK_DONE_COMMAND = "mark";

    public static final String MARK_NOT_DONE_COMMAND = "unmark";

    public static final String TODO_COMMAND = "todo";

    public static final String DEADLINE_COMMAND = "deadline";

    public static final String EVENT_COMMAND = "event";

    public Prune chatbot;

    public Parser(Prune chatbot) {
        this.chatbot = chatbot;
    }

    /**
     * Process user's input for making calls to Prune chatbot's methods
     *
     * @param input User's input as a string
     */
    public void processInput(String input) {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0];
        if (input.equals(DISPLAY_TASKS_COMMAND)) {
            // Process display task command
            this.chatbot.printTasks();
        } else if (command.equals(MARK_DONE_COMMAND) || command.equals(MARK_NOT_DONE_COMMAND)) {
            // Process mark/unmark commands
            try {
                int taskIndex = Integer.parseInt(inputArray[1]) - 1;
                processMarking(command, taskIndex);
            } catch (Exception e) {
                System.out.println("\tPlease enter a valid task number.");
            }
        } else {
            // Process adding task command
            String details = inputArray[1];
            processAdding(command, details);
        }
    }

    /**
     * Process marking commands
     * Calls Prune chatbot's method to mark task as done or not done accordingly
     *
     * @param command   Command "mark" to mark as done and "unmark" to mark as not done
     * @param taskIndex Index of task to be updated
     */
    public void processMarking(String command, int taskIndex) {
        if (command.equals(MARK_DONE_COMMAND)) {
            this.chatbot.markTask(taskIndex, true);
        } else if (command.equals(MARK_NOT_DONE_COMMAND)) {
            this.chatbot.markTask(taskIndex, false);
        }

    }

    /**
     * Process adding command according to type of Task
     * Create Task accordingly and calls Prune chatbot's method to add task into array
     *
     * @param command Command to denote type of task
     * @param details Details include task description and related datetimes
     */
    public void processAdding(String command, String details) {
        String[] inputArray;
        String description;
        Task task;
        if (command.equals(TODO_COMMAND)) {
            // Process Todo
            task = new ToDo(details);
        } else if (command.equals(DEADLINE_COMMAND)) {
            // process Deadline
            inputArray = details.split(" /by ");
            description = inputArray[0];
            String by = inputArray[1];
            task = new Deadline(description, by);
        } else {
            // Process Event
            inputArray = details.split(" /from ");
            description = inputArray[0];
            String[] fromToArray = inputArray[1].split(" /to ");
            String from = fromToArray[0];
            String to = fromToArray[1];
            task = new Event(description, from, to);
        }
        this.chatbot.addTask(task);
    }
}
