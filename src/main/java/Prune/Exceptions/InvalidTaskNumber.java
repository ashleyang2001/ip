package prune.exceptions;

/**
 * Exception thrown when an invalid task number is provided.
 * This custom Prune exception is used to indicate that the user has inputted an invalid or out-of-range task number.
 */
public class InvalidTaskNumber extends Exception {

    /**
     * Constructs an InvalidTaskNumber exception with the specified message.
     *
     * @param msg The detail message for the exception.
     */
    public InvalidTaskNumber(String msg) {
        super(msg);
    }
}
