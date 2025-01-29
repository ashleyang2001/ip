import java.util.Scanner;

/**
 * The Prune class is a chatbot named Prune which interacts with the user to perform certain actions.
 */
public class Prune {
    /**
     * Command to exit the program
     **/
    private static final String EXIT_COMMAND = "bye";

    /**
     * String array to store user's input
     **/
    private final String[] tasks = new String[100];

    /**
     * Number of tasks stored in tasks.
     */
    private int tasksCount = 0;

    /**
     * Command to display all tasks stored
     **/
    private static final String DISPLAY_TASKS_COMMAND = "list";

    /**
     * Adds task to tasks array, increment tasks' count
     * @param task Task to be added in tasks array
     */
    private void addTask(String task) {
        this.tasks[tasksCount] = task;
        System.out.printf("\tadded: %s\n", task);
        tasksCount++;
    }

    /**
     * Prints all tasks in tasks array
     */
    private void printTasks() {
        for (int i = 0; i < tasksCount; i++) {
            System.out.printf("\t%s. %s\n", i + 1, tasks[i]);
        }
    }

    /**
     * Main method to interact with the chatbot.
     * The program first greets the user, and wait for user input.
     * It stores text entered by user and display them back to the user when requested using command "list".
     * Exits when the user types the command "bye".
     *
     * @param args Command line arguments, not used in program.
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
                // Store user's input as task in tasks array
                chatbot.addTask(input);
            }
        }
    }
}
