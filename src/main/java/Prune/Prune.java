package Prune;

import Prune.Tasks.TaskList;

/**
 * Represents a chatbot named Prune that manages task list, storage, ui and parser objects
 * to store tasks inputted by user.
 */
public class Prune {
    public TaskList tasks;
    public Storage storage;
    public Ui ui;
    public Parser parser;

    /**
     * Constructs a Prune chatbot with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Prune(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
        this.parser = new Parser(this.tasks, this.storage);
    }

    /**
     * Runs the Prune chatbot, calls Parser to process input.
     */
    public void run() {
        // Load file
        this.storage.load(this.parser);
        this.ui.greet();
        this.tasks.printTasks();

        while (true) {
            try {
                String output = this.ui.interactWithUser();
                if (output == null) {
                    break;
                }
                this.parser.processInput(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main entry point of the program. Initializes and runs the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Prune chatbot = new Prune("./data/saved_tasks.txt");
        chatbot.run();
    }
}