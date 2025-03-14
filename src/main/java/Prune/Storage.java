package prune;

import prune.exceptions.InvalidTaskFormatInFile;
import prune.tasks.TaskList;
import prune.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class is for managing data storage operations, including
 * loading and saving task data from-to a file.
 */
public class Storage {

    public String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file to load from or write to.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Processes each line of storage file using the provided parser.
     *
     * @param parser The parser used to process the data from the file.
     */
    public void load(Parser parser) {
        // Create file if it does not exist
        File file = validateStorageFile();
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
        } catch (InvalidTaskFormatInFile e) {
            System.out.printf(e.getMessage());
        }
    }

    /**
     * Loads data from the file specified in the filePath.
     * If the file does not exist, it creates the file.
     */
    private File validateStorageFile() {
        File file = new File(this.filePath);
        if (!file.exists()) {
            try {
                // Check for parent directories
                File parent = file.getParentFile();
                if (parent != null && !parent.exists()) {
                    if (parent.mkdirs()) {
                        System.out.println("Directories are created successfully.");
                    } else {
                        System.out.println("Failed to create parent directories.");
                    }
                }
                // Create new file
                if (file.createNewFile()) {
                    return file;
                } else {
                    System.out.println("Failed to create storage file.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return file;
    }

    /**
     * Writes tasks data from the provided TaskList to the file specified in the filePath.
     * Overwrites the existing content in the file with the new task data.
     *
     * @param tasks TaskList containing the tasks to write to the file.
     */
    public void writeData(TaskList tasks) {
        validateStorageFile();
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
