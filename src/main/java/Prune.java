import java.util.Scanner;

/**
 * The Prune class is a chatbot named Prune which interacts with the user to perform certain actions.
 */
public class Prune {
    /**
     * Command to exit the program
     **/
    public static final String EXIT_COMMAND = "bye";

    /**
     * Tasks array to store Task
     **/
    private final Task[] tasks = new Task[100];

    /**
     * Number of tasks stored in tasks.
     */
    private int tasksCount = 0;

    /**
     * Command to display all tasks stored
     **/
    public static final String DISPLAY_TASKS_COMMAND = "list";

    /**
     * Command to mark task as done
     */
    public static final String MARK_DONE_COMMAND = "mark";

    /**
     * Command to mark task as done
     */
    public static final String MARK_NOT_DONE_COMMAND = "unmark";

    /**
     * Instantiate Task and add Task to tasks array
     * Increments taskCount
     *
     * @param description Description of task
     */
    private void addTask(String description) {
        Task task = new Task(description);
        this.tasks[tasksCount] = task;
        System.out.printf("\tadded: %s\n", task.getDescription());
        tasksCount++;
    }

    /**
     * Prints all tasks in tasks array
     */
    private void printTasks() {
        System.out.println("\tHere are the tasks in your list:");
        if (tasksCount == 0) {
            System.out.println("\tHooray! There are no tasks in your list.");
        } else {
            for (int i = 0; i < tasksCount; i++) {
                System.out.printf("\t%s.%s", i + 1, tasks[i].toString());
            }
        }
    }

    /**
     * Process "mark" and "unmark" commands to reflect task completion status
     *
     * @param input String input containing command and task number separated by a space
     */
    private void markTask(String input) {
        try {
            String[] pair = input.split(" ");
            int taskIndex = Integer.parseInt(pair[1]) - 1;
            String command = pair[0];
            if (command.equals(MARK_DONE_COMMAND)) {
                // "mark" to mark task as done
                this.tasks[taskIndex].markAsDone();
            } else if (command.equals(MARK_NOT_DONE_COMMAND)) {
                // "unmark" to mark task as not done yet
                this.tasks[taskIndex].markAsNotDone();
            }
        } catch (Exception e) {
            System.out.println("\tPlease enter a valid task number.");
        }
    }

    /**
     * Main method to interact with the chatbot
     * The program first greets the user, and waits for user's input
     * It is able to add tasks, mark or unmark tasks and display all tasks depending on user's input
     *
     * @param args Command line arguments, not used in program
     */
    public static void main(String[] args) {
        // Instantiate chatbot
        Prune chatbot = new Prune();
        Scanner scanner = new Scanner(System.in);

        // Greets user
        System.out.println("\tHello! I am Prune.");
        String mascot = """
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣶⣶⣶⣶⣶⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⡀⠀⠀⠀⠀⠀
                ⠀⠀⣠⣴⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣵⣄⠀⠀⠀
                ⠀⢾⣻⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⡀⠀
                ⠸⣽⣻⠃⣿⡿⠋⣉⠛⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⡟⠉⡉⢻⣿⡌⣿⣳⡥⠀
                ⢜⣳⡟⢸⣿⣷⣄⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣤⣠⣼⣿⣇⢸⢧⢣⠀
                ⠨⢳⠇⣸⣿⣿⢿⣿⣿⣿⣿⡿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⡟⢆⠀
                ⠀⠀⠈⠀⣾⣿⣿⣼⣿⣿⣿⣿⡀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣽⣿⣿⠐⠈⠀⠀
                ⢀⣀⣼⣷⣭⣛⣯⡝⠿⢿⣛⣋⣤⣤⣀⣉⣛⣻⡿⢟⣵⣟⣯⣶⣿⣄⡀⠀
                ⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣶⣾⣶⣶⣴⣾⣿⣿⣿⣿⣿⣿⢿⣿⣿⣧
                ⣿⣿⣿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⣿⡿
                """;
        System.out.println(mascot);
        System.out.println("\tWhat can I do for you?\n");

        // Continuously ask for user's input until "bye" is inputted
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(EXIT_COMMAND)) {
                // End the program when "bye" is inputted
                System.out.println("\tBye. Hope to see you again soon!\n");
                break;
            } else if (input.equals(DISPLAY_TASKS_COMMAND)) {
                // Print all tasks when "list" is inputted
                chatbot.printTasks();
            } else {
                // Check if input is in format "mark taskNum" or "unmark taskNum"
                if (input.matches("mark -?\\d+") || input.matches("unmark -?\\d+")) {
                    // Either mark or unmark task based on input.
                    chatbot.markTask(input);
                } else {
                    // For any other commands, store user's input as task in tasks array
                    chatbot.addTask(input);
                }
            }
        }
    }
}
