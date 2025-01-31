import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description and a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object with a description and start and end times.
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
     * @param input A string representing the date and time in the format "yyyy-MM-dd HHmm".
     * @return A LocalDateTime object representing the parsed date and time.
     */
    private LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "[E]" + super.toString() + " (from: " + from.format(outputFormatter) + " to: " + to.format(outputFormatter) + ")";
    }
}