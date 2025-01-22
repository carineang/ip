import java.util.Scanner;

public class TaskBuddy {
    public static void main(String[] args) {
        System.out.println("Hello! I'm TaskBuddy");
        System.out.println("What can I do for you?");

        // detect user input
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
