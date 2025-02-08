package taskbuddy;

import taskbuddy.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface for the TaskBuddy application.
 */
public class Ui {
    private final String indent = "   ";
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
        System.out.println(indent + "Hello! I'm TaskBuddy");
        System.out.println(indent + "What can I do for you?");
    }

    /**
     * Prints the list of tasks currently in the task list.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            System.out.println(indent + (i + 1) + "." + tasks.getTaskList().get(i));
        }
    }

    /**
     * Prints a confirmation message after a task has been added.
     *
     * @param task The task that was added to the list.
     */
    public void printAddTaskMessage(Task task) {
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(indent + "  " + task);
    }

    /**
     * Prints a confirmation message after a task has been deleted.
     *
     * @param task The task that was removed from the list.
     */
    public void printDeleteTaskMessage(Task task) {
        System.out.println(indent + "Noted. I've removed this task:");
        System.out.println(indent + "  " + task);
    }

    /**
     * Prints a confirmation message after a task has been marked as done.
     *
     * @param task The task that was marked as completed.
     */
    public void printMarkTaskMessage(Task task) {
        System.out.println(indent + "Nice! I've marked this task as done:");
        System.out.println(indent + "  " + task);
    }

    /**
     * Prints a confirmation message after a task has been marked as not done.
     *
     * @param task The task that was unmarked, not done.
     */
    public void printUnmarkTaskMessage(Task task) {
        System.out.println(indent + "OK, I've marked this task as not done yet:");
        System.out.println(indent + "  " + task);
    }

    /**
     * Prints a goodbye message when the user exits the program.
     */
    public void printGoodbye() {
        System.out.println(indent + "Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks that match a given keyword.
     *
     * @param matchingTaskList The list of tasks that match the user's search keyword.
     */
    public void printMatchingTasks(ArrayList<Task> matchingTaskList) {
        System.out.println(indent + "Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTaskList.size(); i++) {
            System.out.println(indent + (i + 1) + "." + matchingTaskList.get(i));
        }
    }
}
