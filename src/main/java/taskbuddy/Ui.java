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
    public void printWelcomeMessage() {
        System.out.println(INDENT + "Hello! I'm TaskBuddy");
        System.out.println(INDENT + "What can I do for you?");
    }

    /**
     * Prints the list of tasks currently in the task list.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks.getTaskList().get(i));
        }
    }

    /**
     * Prints a confirmation message after a task has been added.
     *
     * @param task The task that was added to the list.
     */
    public void printAddTaskMessage(Task task) {
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + "  " + task);
    }

    /**
     * Prints a confirmation message after a task has been deleted.
     *
     * @param task The task that was removed from the list.
     */
    public void printDeleteTaskMessage(Task task) {
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + task);
    }

    /**
     * Prints a confirmation message after a task has been marked as done.
     *
     * @param task The task that was marked as completed.
     */
    public void printMarkTaskMessage(Task task) {
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  " + task);
    }

    /**
     * Prints a confirmation message after a task has been marked as not done.
     *
     * @param task The task that was unmarked, not done.
     */
    public void printUnmarkTaskMessage(Task task) {
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  " + task);
    }

    /**
     * Prints a goodbye message when the user exits the program.
     */
    public void printGoodbye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks that match a given keyword.
     *
     * @param matchingTaskList The list of tasks that match the user's search keyword.
     */
    public void printMatchingTasks(ArrayList<Task> matchingTaskList) {
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTaskList.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + matchingTaskList.get(i));
        }
    }
}
