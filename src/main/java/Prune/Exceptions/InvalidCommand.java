package prune.exceptions;

/**
 * Exception thrown when an invalid command is encountered.
 * This custom Prune exception is used to indicate that the user has inputted an unknown command.
 */
public class InvalidCommand extends Exception {

    /**
     * Constructs an InvalidCommand exception with the specified message.
     *
     * @param msg The message for the exception.
     */
    public InvalidCommand(String msg) {
        super(msg);
    }
}
