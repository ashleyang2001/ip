package Prune;

import Prune.Tasks.TaskList;

/**
 * The Prune class is a chatbot named Prune who manages all tasks.
 */
public class Prune {
    public TaskList tasks;
    public Storage storage;
    public Ui ui;
    public Parser parser;


    public Prune(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.parser = new Parser(tasks);
        this.storage = new Storage(filePath, parser);
    }


    public void run() {
        // Load file
        this.storage.load();
        this.ui.greet();
        this.tasks.printTasks();

        while (true) {
            try {
                String output = this.ui.interactWithUser();
                if (output == null) {
                    break;
                }
                parser.processInput(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        // Rewrite file based on current list
        this.storage.writeData(tasks);
    }

    /**
     * Main entry point of program.
     */
    public static void main(String[] args) {
        Prune chatbot = new Prune("./data/saved_tasks.txt");
        chatbot.run();
    }
}
