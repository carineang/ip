package taskbuddy;

import java.util.ArrayList;
import java.util.Scanner;
import taskbuddy.task.Task;

/**
 * Represents the user interface for the TaskBuddy application.
 */
public class Ui {
    private final String INDENT = "   ";
    Scanner sc;

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
        String response1 = INDENT + "Hello! I'm TaskBuddy";
        String response2 = INDENT + "What can I do for you?";
        return response1 +"\n" + response2;
    }

    /**
     * Prints the list of tasks currently in the task list.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public String printTaskList(TaskList tasks) {
        if (tasks.getTaskListSize() == 0) {
            return INDENT + "Your task list is empty.";
        }

        StringBuilder list = new StringBuilder();
        list.append(INDENT).append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            list.append(INDENT)
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
        String response1 = INDENT + "Got it. I've added this task:";
        String response2 = INDENT + "  " + task;
        return response1 +"\n" + response2;
    }

    /**
     * Prints a confirmation message after a task has been deleted.
     *
     * @param task The task that was removed from the list.
     */
    public String printDeleteTaskMessage(Task task) {
        String response1 = INDENT + "Noted. I've removed this task:";
        String response2 = INDENT + "  " + task;
        return response1 +"\n" + response2;
    }

    /**
     * Prints a confirmation message after a task has been marked as done.
     *
     * @param task The task that was marked as completed.
     */
    public String printMarkTaskMessage(Task task) {
        String response1 = INDENT + "Nice! I've marked this task as done:";
        String response2 = INDENT + "  " + task;
        return response1 +"\n" + response2;
    }

    /**
     * Prints a confirmation message after a task has been marked as not done.
     *
     * @param task The task that was unmarked, not done.
     */
    public String printUnmarkTaskMessage(Task task) {
        String response1 = INDENT + "OK, I've marked this task as not done yet:";
        String response2 = INDENT + "  " + task;
        return response1 +"\n" + response2;
    }

    /**
     * Prints a goodbye message when the user exits the program.
     */
    public String printGoodbye() {
        String response = INDENT + "Bye. Hope to see you again soon!";
        return response;
    }

    /**
     * Prints the list of tasks that match a given keyword.
     *
     * @param matchingTaskList The list of tasks that match the user's search keyword.
     */
    public String printMatchingTasks(ArrayList<Task> matchingTaskList) {
        if (matchingTaskList.isEmpty()) {
            return INDENT + "There are no matching tasks in your list.";
        }

        StringBuilder result = new StringBuilder(INDENT + "Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTaskList.size(); i++) {
            result.append(INDENT)
                    .append(i + 1)
                    .append(". ")
                    .append(matchingTaskList.get(i))
                    .append("\n");
        }

        return result.toString().trim(); // Remove trailing newline
    }

    public String printInvalidCommand() {
        return "Invalid command. Please try again.";
    }

    public String printFindErrorMessage() {
        return "Please provide a valid keyword.";
    }
}
