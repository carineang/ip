package taskbuddy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import taskbuddy.command.*;
import taskbuddy.task.Deadline;
import taskbuddy.task.Event;
import taskbuddy.task.Todo;
import taskbuddy.task.Task;

/**
 * Parses user input into commands that can be executed by the TaskBuddy application.
 */
public class Parser {
    private static Ui ui = new Ui();

    /**
     * Parses the user input into a Command object that can be executed.
     *
     * @param input The full user input string containing the command and its parameters.
     * @param taskList The current list of tasks.
     * @return A Command object representing the action to be executed.
     * @throws TaskBuddyException if the input is invalid or improperly formatted.
     */
    public static Command parseCommand(String input, TaskList taskList) throws TaskBuddyException {
        String[] inputParts = input.split(" ", 2);
        assert inputParts.length > 0 : "There should be input.";
        String command = inputParts[0];

        switch (command) {
            case "list":
                return new ListCommand();
            case "delete":
                return parseDeleteCommand(inputParts,taskList);
            case "mark":
                return parseMarkCommand(inputParts,taskList);
            case "unmark":
                return parseUnmarkCommand(inputParts,taskList);
            case "todo":
                return parseTodoCommand(inputParts);
            case "deadline":
                return parseDeadlineCommand(inputParts);
            case "event":
                return parseEventCommand(inputParts);
            case "find":
                return parseFindCommand(inputParts);
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand();
        }
    }

    /**
     * Parses the delete command to remove a task from the task list.
     *
     * @param inputParts The parts of the user input string.
     * @param taskList The current list of tasks.
     * @return A DeleteCommand to delete the specified task.
     * @throws TaskBuddyException if the task number is invalid or not provided.
     */
    private static Command parseDeleteCommand(String[] inputParts, TaskList taskList) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid task number.");
        }
        try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            Task task = taskList.getTask(taskNumber);
            return new DeleteCommand(task, taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new TaskBuddyException("Invalid task number. Please try again.");
        }
    }

    /**
     * Parses the mark command to mark a task as done.
     *
     * @param inputParts The parts of the user input string.
     * @param taskList The current list of tasks.
     * @return A MarkCommand to mark the specified task as done.
     * @throws TaskBuddyException if the task number is invalid or not provided.
     */
    private static Command parseMarkCommand(String[] inputParts, TaskList taskList) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid task number.");
        }
        try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            Task task = taskList.getTask(taskNumber);
            return new MarkCommand(task, taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new TaskBuddyException("Invalid task number. Please try again.");
        }
    }

    /**
     * Parses the unmark command to mark a task as not done.
     *
     * @param inputParts The parts of the user input string.
     * @param taskList The current list of tasks.
     * @return An UnmarkCommand to unmark the specified task.
     * @throws TaskBuddyException if the task number is invalid or not provided.
     */
    private static Command parseUnmarkCommand(String[] inputParts, TaskList taskList) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid task number.");
        }
        try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            Task task = taskList.getTask(taskNumber);
            return new UnmarkCommand(task, taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new TaskBuddyException("Invalid task number. Please try again.");
        }
    }

    /**
     * Parses the to-do command to add a new to-do task.
     *
     * @param inputParts The parts of the user input string.
     * @return An AddCommand to add a new to-do task to the list.
     * @throws TaskBuddyException if the to-do description is missing or empty.
     */
    private static Command parseTodoCommand(String[] inputParts) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid todo description.");
        }
        Task todo = new Todo(inputParts[1]);
        return new AddCommand(todo);
    }

    /**
     * Parses the deadline command to add a new deadline task.
     *
     * @param inputParts The parts of the user input string.
     * @return An AddCommand to add a new deadline task to the list.
     * @throws TaskBuddyException if the deadline format is invalid or missing.
     */
    private static Command parseDeadlineCommand(String[] inputParts) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid deadline description.");
        }
        String[] deadlineParts = inputParts[1].split(" /by ", 2);
        if (deadlineParts.length < 2) {
            throw new TaskBuddyException("Invalid deadline format. Use: description /by date.");
        }
        String deadlineDescription = deadlineParts[0];
        String deadline = deadlineParts[1];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime.parse(deadline, formatter);
            Task deadlineTask = new Deadline(deadlineDescription, deadline);
            return new AddCommand(deadlineTask);
        } catch (DateTimeParseException e) {
            throw new TaskBuddyException("Invalid date format for deadline. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Parses the event command to add a new event task.
     *
     * @param inputParts The parts of the user input string.
     * @return An AddCommand to add a new event task to the list.
     * @throws TaskBuddyException if the event format is invalid or missing.
     */
    private static Command parseEventCommand(String[] inputParts) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid event description.");
        }
        String[] eventParts = inputParts[1].split(" /from ", 2);
        if (eventParts.length < 2) {
            throw new TaskBuddyException("Invalid event format. Use: description /from start /to end.");
        }
        String eventDescription = eventParts[0];
        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new TaskBuddyException("Invalid event format. Use: description /from start /to end.");
        }
        String from = timeParts[0];
        String to = timeParts[1];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime.parse(from, formatter);
            LocalDateTime.parse(to, formatter);
            Task eventTask = new Event(eventDescription, from, to);
            return new AddCommand(eventTask);
        } catch (DateTimeParseException e) {
            throw new TaskBuddyException("Invalid date-time format for event. Please use yyyy-MM-dd HHmm for both /from and /to. ");
        }
    }

    /**
     * Parses the find command to search for tasks based on a keyword.
     *
     * @param inputParts The parts of the user input string.
     * @return A FindCommand to search for tasks based on the given keyword.
     * @throws TaskBuddyException if the keyword is missing or empty.
     */
    private static Command parseFindCommand(String[] inputParts) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException(ui.printFindErrorMessage());
        }
        return new FindCommand(inputParts[1]);
    }
}
