import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * TaskBuddy task management chatbot.
 */
public class TaskBuddy {
    /**
     * Main entry point for TaskBuddy chatbot.
     * User can interact with the app by entering commands through the console.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String indent = "   ";

        // Load tasks from text file
        ArrayList<Task> tasks = loadTasks(indent);

        System.out.println("Hello! I'm TaskBuddy");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String userInput = sc.nextLine();
                // Split the input into 2 parts
                String[] inputParts = userInput.split(" ", 2);
                String command = inputParts[0];

                switch (command) {
                    // List all tasks
                    case "list":
                        listTasks(tasks, indent);
                        break;
                    // Delete a task
                    case "delete":
                        deleteTask(tasks, inputParts, indent);
                        break;
                    // Mark this task as completed
                    case "mark":
                        markTask(tasks, inputParts, indent, true);
                        break;
                    // Mark this task as not completed
                    case "unmark":
                        markTask(tasks, inputParts, indent, false);
                        break;
                    // To-Do tasks
                    case "todo":
                        addTodoTask(tasks, inputParts, indent);
                        break;
                    // Deadline tasks
                    case "deadline":
                        addDeadlineTask(tasks, inputParts, indent);
                        break;
                    // Event tasks
                    case "event":
                        addEventTask(tasks, inputParts, indent);
                        break;
                    // Bye to exit
                    case "bye":
                        System.out.println(indent + "Bye. Hope to see you again soon!");
                        return;
                    // Invalid commands
                    default:
                        throw new TaskBuddyException("Invalid command. Please try again.");
                }
            } catch (TaskBuddyException e) {
                System.out.println(indent + e.getMessage());
            }
        }
    }

    /**
     * Lists all tasks currently saved in the task list.
     *
     * @param tasks List of tasks to be displayed.
     * @param indent Indentation used for formatting the output.
     */
    private static void listTasks(ArrayList<Task> tasks, String indent) {
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(indent + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Deletes a task from the task list based on the task number provided.
     * If the task number is invalid, a TaskBuddyException is thrown.
     *
     * @param tasks List of tasks.
     * @param inputParts The user input parts, where the second part is the task number.
     * @param indent Indentation used for formatting the output.
     * @throws TaskBuddyException If the task number is invalid.
     */
    private static void deleteTask(ArrayList<Task> tasks, String[] inputParts, String indent) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid task number.");
        }
        try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            Task removedTask = tasks.remove(taskNumber);
            System.out.println(indent + "Noted. I've removed this task:");
            System.out.println(indent + "  " + removedTask);
            System.out.println(indent + "Now you have " + tasks.size() + " tasks in the list.");
            saveTasks(tasks, indent);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(indent + "Invalid task number. Please try again.");
        }
    }

    /**
     * Marks a task as done or not done based on user input.
     * The status of the task is updated, and changes are saved to the text file.
     *
     * @param tasks List of tasks.
     * @param inputParts The user input parts, where the second part is the task number.
     * @param indent Indentation used for formatting the output.
     * @param isDone Boolean indicating whether to mark the task as done or not.
     * @throws TaskBuddyException If the task number is invalid.
     */
    private static void markTask(ArrayList<Task> tasks, String[] inputParts, String indent, boolean isDone) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid task number.");
        }
        try {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (isDone) {
                tasks.get(taskNumber).markAsDone();
                System.out.println(indent + "Nice! I've marked this task as done:");
            } else {
                tasks.get(taskNumber).markAsNotDone();
                System.out.println(indent + "OK, I've marked this task as not done yet:");
            }
            System.out.println(indent + "  " + tasks.get(taskNumber));
            saveTasks(tasks, indent);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(indent + "Invalid task number. Please try again.");
        }
    }

    /**
     * Adds a to-do task to the task list.
     * A task is added with a description provided by the user.
     *
     * @param tasks List of tasks.
     * @param inputParts The user input parts, where the second part is the task description.
     * @param indent Indentation used for formatting the output.
     * @throws TaskBuddyException If the task description is invalid.
     */
    private static void addTodoTask(ArrayList<Task> tasks, String[] inputParts, String indent) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid todo description.");
        }
        Task newTask = new Todo(inputParts[1]);
        tasks.add(newTask);
        addTaskMessage(newTask, tasks.size(), indent);
        saveTasks(tasks, indent);
    }

    /**
     * Adds a deadline task to the task list.
     * A task is added with a description and deadline date provided by the user.
     *
     * @param tasks List of tasks.
     * @param inputParts The user input parts, where the second part is the task description and deadline.
     * @param indent Indentation used for formatting the output.
     * @throws TaskBuddyException If the task description or deadline is invalid.
     */
    private static void addDeadlineTask(ArrayList<Task> tasks, String[] inputParts, String indent) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid deadline description.");
        }
        String[] deadlineParts = inputParts[1].split(" /by ", 2);
        if (deadlineParts.length < 2) {
            throw new TaskBuddyException("Invalid deadline format. Use: description /by date");
        }
        String description = deadlineParts[0];
        String deadline = deadlineParts[1];
        Task newTask = new Deadline(description, deadline);
        tasks.add(newTask);
        addTaskMessage(newTask, tasks.size(), indent);
        saveTasks(tasks, indent);
    }

    /**
     * Adds an event task to the task list.
     * A task is added with a description and start and end times provided by the user.
     *
     * @param tasks List of tasks.
     * @param inputParts The user input parts, where the second part is the task description, start time, and end time.
     * @param indent Indentation used for formatting the output.
     * @throws TaskBuddyException If the task description or time details are invalid.
     */
    private static void addEventTask(ArrayList<Task> tasks, String[] inputParts, String indent) throws TaskBuddyException {
        if (inputParts.length < 2 || inputParts[1].isBlank()) {
            throw new TaskBuddyException("Please provide a valid event description.");
        }
        String[] eventParts = inputParts[1].split(" /from ", 2);
        if (eventParts.length < 2) {
            throw new TaskBuddyException("Invalid event format. Use: description /from start /to end");
        }
        String description = eventParts[0];
        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new TaskBuddyException("Invalid event format. Use: description /from start /to end");
        }
        String from = timeParts[0];
        String to = timeParts[1];
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        addTaskMessage(newTask, tasks.size(), indent);
        saveTasks(tasks, indent);
    }

    /**
     * Prints a message indicating the task was successfully added.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks after adding the new one.
     * @param indent Indentation used for formatting the output.
     */
    private static void addTaskMessage(Task task, int taskCount, String indent) {
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(indent + "  " + task);
        System.out.println(indent + "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Saves the current task list to a file named "taskbuddy.txt" and written in a specific format for loading later.
     *
     * @param tasks List of tasks to be saved.
     * @param indent Indentation used for formatting the output.
     */
    private static void saveTasks(ArrayList<Task> tasks, String indent) {
        //File file = new File("taskbuddy.txt");
        File file = new File("ip/data/taskbuddy.txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            System.out.println(indent + "Tasks have been saved to taskbuddy.txt.");
        } catch (IOException e) {
            System.out.println(indent + "Error saving tasks to file. Please try again.");
        }
    }

    /**Level_7
     * Loads the tasks from the "taskbuddy.txt" file into the task list.
     * The tasks are parsed from the file and added to the list.
     *
     * @param indent Indentation used for formatting the output.
     * @return A list of tasks loaded from the file.
     */
    private static ArrayList<Task> loadTasks(String indent) {
        ArrayList<Task> tasks = new ArrayList<>();
        //File file = new File("taskbuddy.txt");
        File file = new File("ip/data/taskbuddy.txt");
        if (!file.exists()) {
            return tasks;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            System.out.println(indent + "Loaded " + tasks.size() + " tasks from taskbuddy.txt.");
        } catch (IOException e) {
            System.out.println(indent + "Error loading tasks. Starting with an empty list.");
        }
        return tasks;
    }

    /**
     * Parses a task from a string representation read from the file.
     *
     * @param line The string representing the task.
     * @return The parsed Task object, or null if the line cannot be parsed.
     */
    private static Task parseTaskFromString(String line) {
        boolean isDone = line.contains("[1]"); // Completed task status
        if (line.startsWith("[T]")) {           // To-do
            String description = line.substring(7);
            Todo task = new Todo(description);
            if (isDone) {
                task.markAsDone();
            }
            return task;
        } else if (line.startsWith("[D]")) {    // Deadline
            int byIndex = line.indexOf("(by:");
            if (byIndex == -1) {
                return null;
            }
            String description = line.substring(7, byIndex).trim();
            String by = line.substring(byIndex + 5, line.length() - 1).trim();
            Deadline task = new Deadline(description, by);
            if (isDone) {
                task.markAsDone();
            }
            return task;
        } else if (line.startsWith("[E]")) {    // Event
            int fromIndex = line.indexOf("(from:");
            int toIndex = line.indexOf("to:");
            if (fromIndex == -1 || toIndex == -1) {
                return null;
            }
            String description = line.substring(7, fromIndex).trim();
            String from = line.substring(fromIndex + 6, toIndex).trim();
            String to = line.substring(toIndex + 3, line.length() - 1).trim();
            Event task = new Event(description, from, to);
            if (isDone) {
                task.markAsDone();
            }
            return task;
        }
        return null;
    }
}
