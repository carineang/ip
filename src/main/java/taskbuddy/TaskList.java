package taskbuddy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import taskbuddy.task.Task;

/**
 * Represents a list of tasks in the TaskBuddy application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Your task list should not be empty.";
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list by its index.
     *
     * @param taskIndex The index of the task to be removed.
     */
    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Marks a task as completed by its index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Unmarks a task, setting it as not completed by its index.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).markAsNotDone();
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the entire task list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Checks if the task list contains the given task.
     *
     * @param task The task to check for existence in the list.
     * @return true if the task is found in the list.
     */
    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    /**
     * Finds and returns all tasks whose descriptions contain the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the specified keyword in their description.
     */
    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Finds and returns all tasks that match the specified date.
     *
     * @param targetDate The date to match tasks.
     * @return A list of tasks that match the specified date.
     */
    public ArrayList<Task> findMatchingTaskDate(LocalDate targetDate) {
        return tasks.stream()
                .filter(task -> task.matchesDate(targetDate))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
