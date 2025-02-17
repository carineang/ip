package taskbuddy.task;

import java.time.LocalDate;

/**
 * An abstract Task class meant for different task types and their completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with a given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task based on its completion status.
     *
     * @return A string representing the completion status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark completed task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
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

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task matches a specific target date.
     *
     * @param targetDate A LocalDate object representing the target date to compare with.
     */
    public abstract boolean matchesDate(LocalDate targetDate);
}
