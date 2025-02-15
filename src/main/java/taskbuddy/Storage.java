package taskbuddy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import taskbuddy.task.Deadline;
import taskbuddy.task.Event;
import taskbuddy.task.Task;
import taskbuddy.task.Todo;

/**
 * Handles saving and loading tasks to and from a file.
 */
public class Storage {
    private String filePath;
    private final String indent = "   ";

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be saved or loaded.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the file specified by the file path.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();
        writeTasksToFile(tasks);
    }

    /**
     * Writes the tasks to the file.
     *
     * @param tasks The list of tasks to be written.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeTasksToFile(ArrayList<Task> tasks) {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                assert task != null : "Task should not be null.";
                writer.write(task.toFileString() + "\n");
            }
            System.out.println(indent + "Tasks have been saved to taskbuddy.txt.");
        } catch (IOException e) {
            System.out.println(indent + "Error saving tasks to file. Please try again.");
        }
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an error occurs while reading from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                assert line != null && !line.trim().isEmpty() : "Invalid line read from file.";
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
     * Parses a task from a string representation.
     *
     * @param line The string representation of a task.
     * @return The corresponding Task object.
     */
    private Task parseTaskFromString(String line) {
        if (line.startsWith("[T]")) {
            return parseTodo(line);
        } else if (line.startsWith("[D]")) {
            return parseDeadline(line);
        } else if (line.startsWith("[E]")) {
            return parseEvent(line);
        }
        return null;
    }

    /**
     * Parses a To-do task from a string.
     *
     * @param line The string representation of a To-do task.
     * @return A To-do object.
     */
    private Todo parseTodo(String line) {
        boolean isDone = line.contains("[1]");
        String description = line.substring(7);
        Todo task = new Todo(description);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parses a Deadline task from a string.
     *
     * @param line The string representation of a Deadline task.
     * @return A Deadline object.
     */
    private Deadline parseDeadline(String line) {
        boolean isDone = line.contains("[1]");
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
    }

    /**
     * Parses an Event task from a string.
     *
     * @param line The string representation of an Event task.
     * @return An Event object.
     */
    private Event parseEvent(String line) {
        boolean isDone = line.contains("[1]");
        int fromIndex = line.indexOf("(from:");
        int toIndex = line.indexOf("to:");
        if (fromIndex == -1 || toIndex == -1) {
            return null;
        }
        String description = line.substring(7, fromIndex).trim();
        String from = line.substring(fromIndex + 6, toIndex).trim();
        String to = line.substring(toIndex + 4, line.length() - 1).trim();
        Event task = new Event(description, from, to);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
