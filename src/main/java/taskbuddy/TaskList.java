package taskbuddy;

import java.time.LocalDate;
import java.util.ArrayList;
import taskbuddy.task.Deadline;
import taskbuddy.task.Event;
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
     * Searches through all task descriptions and returns those that contain the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the specified keyword in their description.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Finds and returns all tasks that match the specified date.
     *
     * @param taskList The list of tasks to search.
     * @param targetDate The date to match tasks.
     * @return A list of tasks that match the specified date.
     */
    public ArrayList<Task> findMatchingTaskDate(TaskList taskList, LocalDate targetDate) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task instanceof Deadline) {
                LocalDate deadlineDate = ((Deadline) task).getLocalDate();
                if (deadlineDate.equals(targetDate)) {
                    matchingTasks.add(task);
                }
            } else if (task instanceof Event) {
                LocalDate eventDate = ((Event) task).getStartDate();
                if (eventDate.equals(targetDate)) {
                    matchingTasks.add(task);
                }
            }
        }
        return matchingTasks;
    }
}

