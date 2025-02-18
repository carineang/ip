package taskbuddy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import taskbuddy.task.Deadline;
import taskbuddy.task.Event;
import taskbuddy.task.Task;
import taskbuddy.task.Todo;

/**
 * Handles saving and loading tasks to and from a file.
 */
public class Storage {
    private String filePath;

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
        try {
            writeTasksToFile(taskList.getTaskList());
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Writes the tasks to the file.
     *
     * @param tasks The list of tasks to be written.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeTasksToFile(List<Task> tasks) throws IOException {
        Path path = Paths.get(filePath);
        List<String> taskStrings = tasks.stream()
                .map(Task::toFileString)
                .collect(Collectors.toList());
        try {
            Files.write(path, taskStrings);
            System.out.println("Tasks have been saved to taskbuddy.txt");
        } catch (IOException e) {
            throw new IOException("Error writing tasks to file.");
        }
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an error occurs while reading from the file.
     */
    public ArrayList<Task> loadTasks() {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            System.out.println("No existing file found, returning empty task list.");
            return new ArrayList<>();
        }
        try {
            return Files.lines(path)
                    .filter(line -> !line.trim().isEmpty())
                    .map(this::parseTaskFromString)
                    .filter(task -> task != null)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            System.out.println("Error loading tasks. Starting with an empty list.");
            return new ArrayList<>();
        }
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
        boolean isDone = line.contains("[X]");
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
        boolean isDone = line.contains("[X]");
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
        boolean isDone = line.contains("[X]");
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
