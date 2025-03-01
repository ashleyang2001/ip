package Prune;

import Prune.Tasks.TaskList;
import Prune.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load(Parser parser) {
        // Create file if it does not exist
        File file = new File(this.filePath);
        if (file.exists()) {
            System.out.println("Storage file found: " + file.getAbsolutePath());
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("Storage file created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create storage file.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        // Read file
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parser.processSavedData(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.printf("Storage file is not found: %s\n", this.filePath);
        }
    }

    public void writeData(TaskList tasks) {
        try {
            File file = new File(this.filePath);
            FileWriter writer = new FileWriter(file, false);
            for (Task task : tasks.tasksList) {
                writer.write(task.getInputString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.printf("Failed to write to storage file: %s\n", e.getMessage());
        }
    }
}
