package taskbuddy.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description and a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * An Event object with a description and start and end times.
     *
     * @param description A description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Parses a string into a LocalDateTime object using a specific format.
     *
     * @param input A string representing the date and time in the format
     * @return A LocalDateTime object representing the parsed date and time.
     */
    private LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Returns the start date of the event task.
     *
     * @return The start date of the event task.
     */
    public LocalDate getStartDate() {
        return from.toLocalDate();
    }

    /**
     * Checks if the deadline task matches a specific target date.
     *
     * @param targetDate A LocalDate object representing the target date to compare with.
     * @return True if the deadline task's date matches the target date.
     */
    @Override
    public boolean matchesDate(LocalDate targetDate) {
        return this.getStartDate().equals(targetDate);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return "[E]" + super.toString() + " (from: " + from.format(displayFormatter) + " to: "
                + to.format(displayFormatter) + ")";
    }

    /**
     * Returns a string representation of the event task formatted for saving to a file.
     *
     * @return A string representation of the event task in a format suitable for saving to a file.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "[E]" + super.toString() + " (from: " + from.format(fileFormatter) + " to: "
                + to.format(fileFormatter) + ")";
    }
}
