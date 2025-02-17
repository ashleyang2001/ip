package Prune;

import Prune.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Prune class is a chatbot named Prune who manages all tasks.
 */
public class Prune {

    public final ArrayList<Task> tasks = new ArrayList<>();
    // DATA_PATH relative to root folder
    public static final String DATA_PATH = "src/main/java/Prune/Data/saved_tasks.txt";

    /**
     * Instantiate Task and add Task to tasks array
     * Increments taskCount
     *
     * @param task Task to be added into tasks array
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Process "mark" and "unmark" commands to reflect task completion status
     *
     * @param task   Task to be marked
     * @param isDone Boolean variable to decide whether to mark or unmark task
     */
    public void markTask(Task task, boolean isDone) {
        task.markAsDone(isDone);
    }

    /**
     * Prints all tasks in tasks array
     */
    public void printTasks() {
        System.out.println("\tHere are the tasks in your list:");
        if (this.tasks.isEmpty()) {
            System.out.println("\tHooray! There are no tasks in your list.");
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.printf("\t%s.%s\n", i + 1, tasks.get(i));
            }
        }
    }

    public static void writeData(ArrayList<Task> tasks) {
        try {
            File file = new File(DATA_PATH);
            FileWriter writer = new FileWriter(file, false);
            for (Task task : tasks) {
                writer.write(task.getInputString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.printf("Failed to write to file: %s\n", e.getMessage());
        }
    }

    /**
     * Main entry point of program.
     */
    public static void main(String[] args) {
        Prune prune = new Prune();
        Parser parser = new Parser(prune);
        // Load saved tasks
        try {
            File file = new File(DATA_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parser.processSavedData(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found.", DATA_PATH);

        }
        UserInterface.greet();
        prune.printTasks();

        while (true) {
            try {
                String output = UserInterface.interactWithUser();
                if (output.contains("Bye")) {
                    break;
                }
                parser.processInput(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        // Rewrite file based on current list
        writeData(prune.tasks);
    }
}
