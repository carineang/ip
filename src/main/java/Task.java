/**
 * An abstract Task class meant for different task types and their completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with a given description. Initially, the task is marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task based on its completion status.
     * If the task is completed, it returns "1", otherwise "0".
     *
     * @return A string representing the completion status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0"); // mark done task with 1
    }

    /**
     * Marks the task as completed.
     * After calling this method, the task will be considered completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     * After calling this method, the task will be considered not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task, in the format "[status] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     *
     * @return A string representation of the task in a format suitable for saving to a file.
     */
    public abstract String toFileString();
}