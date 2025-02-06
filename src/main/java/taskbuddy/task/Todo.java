package taskbuddy.task;

/**
 * Represents a to-do task with a description.
 */
public class Todo extends Task {
    /**
     * Constructs a To-do object with a description.
     *
     * @param description A description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do task formatted for saving to a file.
     *
     * @return A string representation of the to-do task in a format suitable for saving to a file.
     */
    @Override
    public String toFileString() {
        return "[T]" + super.toString();
    }
}