package taskbuddy;

import java.util.Scanner;
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
    private static String INDENT = "   ";
    private static String[] inputParts;
    private static String command;

    /**
     * Reads a command entered by the user.
     *
     * @return The command keyword.
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        // Split the input into 2 different parts
        inputParts = userInput.split(" ", 2);
        command = inputParts[0];
        return command;
    }

    /**
     * Parses the user input into a Command object that can be executed.
     *
     * @param input The command keyword entered by the user.
     * @param taskList The current list of tasks in the system.
     * @return A Command object that represents the desired action to be executed.
     * @throws TaskBuddyException if the input is invalid or improperly formatted.
     */
    public static Command parseCommand(String input, TaskList taskList) {
        try {
            switch (input) {
            // List all tasks
            case "list":
                return new ListCommand();

            // Delete a task
            case "delete":
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

            // Mark this task as completed
            case "mark":
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

            // Mark this task as not completed
            case "unmark":
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

            // To-Do tasks
            case "todo":
                if (inputParts.length < 2 || inputParts[1].isBlank()) {
                    throw new TaskBuddyException("Please provide a valid todo description.");
                }
                Task todo = new Todo(inputParts[1]);
                return new AddCommand(todo);

            // Deadline tasks
            case "deadline":
                if (inputParts.length < 2 || inputParts[1].isBlank()) {
                    throw new TaskBuddyException("Please provide a valid deadline description.");
                }
                String[] deadlineParts = inputParts[1].split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    throw new TaskBuddyException("Invalid deadline format. Use: description /by date");
                }
                String deadlineDescription = deadlineParts[0];
                String deadline = deadlineParts[1];
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime.parse(deadline, formatter);
                    Task actual = new Deadline(deadlineDescription, deadline);
                    return new AddCommand(actual);
                } catch (DateTimeParseException e) {
                    throw new TaskBuddyException("Invalid date format for deadline. Please use the format: yyyy-MM-dd HHmm");
                }

            // Event tasks
            case "event":
                if (inputParts.length < 2 || inputParts[1].isBlank()) {
                    throw new TaskBuddyException("Please provide a valid event description.");
                }
                String[] eventParts = inputParts[1].split(" /from ", 2);
                if (eventParts.length < 2) {
                    throw new TaskBuddyException("Invalid event format. Use: description /from start /to end");
                }
                String eventDescription = eventParts[0];
                String[] timeParts = eventParts[1].split(" /to ", 2);
                if (timeParts.length < 2) {
                    throw new TaskBuddyException("Invalid event format. Use: description /from start /to end");
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
                    throw new TaskBuddyException("Invalid date-time format for event. Please use yyyy-MM-dd HHmm for both /from and /to times.");
                }

            // find keyword command
            case "find":
                if (inputParts.length < 2 || inputParts[1].isBlank()) {
                    throw new TaskBuddyException("Please provide a valid keyword.");
                }
                return new FindCommand(inputParts[1]);

            // Bye command
            case "bye":
                return new ExitCommand();

            // Invalid commands
            default:
                throw new TaskBuddyException("Invalid command. Please try again.");
            }
        } catch (TaskBuddyException e) {
            System.out.println(INDENT + e.getMessage());
            // No Operation command
            return new NoOperationCommand();
        }
    }
}
