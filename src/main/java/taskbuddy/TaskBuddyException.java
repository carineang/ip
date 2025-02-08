package taskbuddy;

/**
 * TaskBuddy exception for handling errors specific to the TaskBuddy application.
 */
public class TaskBuddyException extends Exception {
    /**
     * Constructs a TaskBuddyException with specified detail message containing information about the error.
     *
     * @param message The detail message that explains the error.
     */
    public TaskBuddyException(String message) {
        super(message);
    }
}
