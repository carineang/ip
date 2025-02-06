package taskbuddy;
import taskbuddy.task.Task;
import java.util.Scanner;

public class Ui {
    private final String indent = "   ";
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println(indent + "Hello! I'm TaskBuddy");
        System.out.println(indent + "What can I do for you?");
    }

    public void printTaskList(TaskList tasks) {
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            System.out.println(indent + (i + 1) + "." + tasks.getTaskList().get(i));
        }
    }

    public void printAddTaskMessage(Task task) {
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(indent + "  " + task);
    }

    public void printDeleteTaskMessage(Task task) {
        System.out.println(indent + "Noted. I've removed this task:");
        System.out.println(indent + "  " + task);
    }

    public void printMarkTaskMessage(Task task) {
        System.out.println(indent + "Nice! I've marked this task as done:");
        System.out.println(indent + "  " + task);
    }

    public void printUnmarkTaskMessage(Task task) {
        System.out.println(indent + "OK, I've marked this task as not done yet:");
        System.out.println(indent + "  " + task);
    }

    public void printNoOfTasks(Task task, int taskCount) {
        System.out.println(indent + "Now you have " + taskCount + " tasks in the list.");
    }

    public void printGoodbye() {
        System.out.println(indent + "Bye. Hope to see you again soon!");
    }

}
