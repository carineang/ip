package taskbuddy;

import java.util.ArrayList;
import java.util.Scanner;

import taskbuddy.task.Task;

/**
 * Represents the user interface for the TaskBuddy application.
 */
public class Ui {
    private final String indent = "   ";
    private Scanner sc;

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when the program starts.
     */
    public String printWelcomeMessage() {
        String response1 = indent + "Hello! I'm TaskBuddy";
        String response2 = indent + "What can I do for you?";
        return response1 + "\n" + response2;
    }

    /**
     * Prints the list of tasks currently in the task list.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public String printTaskList(TaskList tasks) {
        if (tasks.getTaskListSize() == 0) {
            return indent + "Your task list is empty.";
        }
        StringBuilder list = new StringBuilder();
        list.append(indent).append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            list.append(indent)
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.getTaskList().get(i))
                    .append("\n");
        }
        return list.toString().trim();
    }

    /**
     * Prints a confirmation message after a task has been added.
     *
     * @param task The task that was added to the list.
     */
    public String printAddTaskMessage(Task task) {
        String response1 = indent + "Got it. I've added this task:";
        String response2 = indent + "  " + task;
        return response1 + "\n" + response2;
    }

    /**
     * Prints a confirmation message after a task has been deleted.
     *
     * @param task The task that was removed from the list.
     */
    public String printDeleteTaskMessage(Task task) {
        String response1 = indent + "Noted. I've removed this task:";
        String response2 = indent + "  " + task;
        return response1 + "\n" + response2;
    }

    /**
     * Prints a confirmation message after a task has been marked as done.
     *
     * @param task The task that was marked as completed.
     */
    public String printMarkTaskMessage(Task task) {
        String response1 = indent + "Nice! I've marked this task as done:";
        String response2 = indent + "  " + task;
        return response1 + "\n" + response2;
    }

    /**
     * Prints a confirmation message after a task has been marked as not done.
     *
     * @param task The task that was unmarked, not done.
     */
    public String printUnmarkTaskMessage(Task task) {
        String response1 = indent + "OK, I've marked this task as not done yet:";
        String response2 = indent + "  " + task;
        return response1 + "\n" + response2;
    }

    /**
     * Prints a goodbye message when the user exits the program.
     */
    public String printGoodbye() {
        String response = indent + "Bye. Hope to see you again soon!";
        return response;
    }

    /**
     * Prints the list of tasks that match a given keyword.
     *
     * @param matchingTaskList The list of tasks that match the user's search keyword.
     */
    public String printMatchingTasks(ArrayList<Task> matchingTaskList) {
        if (matchingTaskList.isEmpty()) {
            return indent + "There are no matching tasks in your list.";
        }
        StringBuilder result = new StringBuilder(indent + "Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTaskList.size(); i++) {
            result.append(indent)
                    .append(i + 1)
                    .append(". ")
                    .append(matchingTaskList.get(i))
                    .append("\n");
        }
        return result.toString().trim();
    }

    /**
     * Prints the list of tasks that match a given date.
     *
     * @param matchingTaskList The list of tasks that match the user's specified date.
     * @return A string representation of the tasks for the given date.
     */
    public String printMatchingTasksDate(ArrayList<Task> matchingTaskList) {
        if (matchingTaskList.isEmpty()) {
            return "There are no task for this date.";
        }
        StringBuilder result = new StringBuilder(indent + "Here are the tasks for this date:\n");
        for (int i = 0; i < matchingTaskList.size(); i++) {
            result.append(indent)
                    .append(i + 1)
                    .append(". ")
                    .append(matchingTaskList.get(i))
                    .append("\n");
        }
        return result.toString().trim();
    }

    /**
     * Prints a message indicating that the command entered was invalid.
     *
     * @return The error message to be printed.
     */
    public String printInvalidCommand() {
        return "Invalid command. Please try again.";
    }

    /**
     * Prints a message indicating that the user provided an invalid keyword.
     *
     * @return The error message to be printed.
     */
    public String printFindErrorMessage() {
        return "Please provide a valid keyword.";
    }
}
