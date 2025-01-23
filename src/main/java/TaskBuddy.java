import java.util.ArrayList;
import java.util.Scanner;

public class TaskBuddy {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String indent = "   ";

        System.out.println("Hello! I'm TaskBuddy");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            // Split the input into 2 parts
            String[] inputParts = userInput.split(" ", 2);
            String command = inputParts[0];

            // List all tasks
            if (command.equals("list")) {
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(indent + (i + 1) + "." + tasks.get(i));
                }

            // Mark this task as completed
            } else if (command.equals("mark")) {
                try {
                    // Convert String to Int
                    int taskNumber = Integer.parseInt(inputParts[1]) - 1;
                    tasks.get(taskNumber).markAsDone();
                    System.out.println(indent + "Nice! I've marked this task as done:");
                    System.out.println(indent + "  " + tasks.get(taskNumber));
                } catch (Exception e) {
                    System.out.println(indent + "Invalid task number.");
                }

            // Mark this task as not completed
            } else if (command.equals("unmark")) {
                try {
                    // Convert String to Int
                    int taskNumber = Integer.parseInt(inputParts[1]) - 1;
                    tasks.get(taskNumber).markAsNotDone();
                    System.out.println(indent + "OK, I've marked this task as not done yet:");
                    System.out.println(indent + "  " + tasks.get(taskNumber));
                } catch (Exception e) {
                    System.out.println(indent + "Invalid task number.");
                }

            // Bye to exit
            } else if (command.equals("bye")) {
                System.out.println(indent + "Bye. Hope to see you again soon!");
                break;

            // Add new task to new task list
            } else {
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                System.out.println(indent + "added: " + userInput);
            }
        }
    }
}
