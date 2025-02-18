package taskbuddy.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a specific deadline time.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * A Deadline object with a description and a deadline time.
     *
     * @param description A description of the deadline task.
     * @param deadline The deadline time.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = parseDateTime(deadline);
    }

    /**
     * Parses a string into a LocalDateTime object using a specific format.
     *
     * @param input A string representing the date and time of the deadline.
     * @return A LocalDateTime object representing the parsed deadline.
     */
    private LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Returns the date of the deadline task.
     *
     * @return The date of the deadline task.
     */
    public LocalDate getLocalDate() {
        return deadline.toLocalDate();
    }

    /**
     * Checks if the deadline task matches a specific target date.
     *
     * @param targetDate A LocalDate object representing the target date to compare with.
     * @return True if the deadline task's date matches the target date.
     */
    @Override
    public boolean matchesDate(LocalDate targetDate) {
        return this.getLocalDate().equals(targetDate);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return "[D]" + super.toString() + " (by: " + deadline.format(displayFormatter) + ")";
    }

    /**
     * Returns a string representation of the deadline task formatted for saving to a file.
     *
     * @return A string representation of the deadline task in a format suitable for saving to a file.
     */
    public String toFileString() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "[D]" + super.toString() + " (by: " + deadline.format(fileFormatter) + ")";
    }
}

