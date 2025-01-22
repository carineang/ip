import java.util.Scanner;

public class TaskBuddy {
    public static void main(String[] args) {
        // Initialize storage for maximum 100 tasks
        String[] tasks = new String[100];
        // Keep track of the number of tasks
        int taskCount = 0;

        System.out.println("Hello! I'm TaskBuddy");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            // List all tasks
            if (userInput.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }
            // bye to exit
            } else if (userInput.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            // add new task to task list
            } else {
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println("    added: " + userInput);
            }
        }
    }
}
