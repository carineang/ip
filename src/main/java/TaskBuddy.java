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
            try {
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

                // Delete a task
                } else if (command.equals("delete")) {
                    try {
                        if (inputParts.length < 2 || inputParts[1].isBlank()) {
                            throw new TaskBuddyException("Please provide a valid task number.");
                        }
                        int taskNumber = Integer.parseInt(inputParts[1]) - 1;
                        Task removedTask = tasks.remove(taskNumber);
                        System.out.println(indent + "Noted. I've removed this task:");
                        System.out.println(indent + "  " + removedTask);
                        System.out.println(indent + "Now you have " + tasks.size() + " tasks in the list.");
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(indent + "Invalid task number. Please try again.");
                    } catch (TaskBuddyException e) {
                        System.out.println(indent + e.getMessage());
                    }

                // Mark this task as completed
                } else if (command.equals("mark")) {
                    try {
                        if (inputParts.length < 2 || inputParts[1].isBlank()) {
                            throw new TaskBuddyException("Please provide a valid task number.");
                        }
                        // Convert String to Int
                        int taskNumber = Integer.parseInt(inputParts[1]) - 1;
                        tasks.get(taskNumber).markAsDone();
                        System.out.println(indent + "Nice! I've marked this task as done:");
                        System.out.println(indent + "  " + tasks.get(taskNumber));
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(indent + "Invalid task number. Please try again.");
                    } catch (TaskBuddyException e) {
                        System.out.println(indent + e.getMessage());
                    }


                // Mark this task as not completed
                } else if (command.equals("unmark")) {
                    try {
                        if (inputParts.length < 2 || inputParts[1].isBlank()) {
                            throw new TaskBuddyException("Please provide a valid task number.");
                        }
                        // Convert String to Int
                        int taskNumber = Integer.parseInt(inputParts[1]) - 1;
                        tasks.get(taskNumber).markAsNotDone();
                        System.out.println(indent + "OK, I've marked this task as not done yet:");
                        System.out.println(indent + "  " + tasks.get(taskNumber));
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println(indent + "Invalid task number. Please try again.");
                    } catch (TaskBuddyException e) {
                        System.out.println(indent + e.getMessage());
                    }

                // To-Do tasks
                } else if (command.equals("todo")) {
                    try {
                        if (inputParts.length < 2 || inputParts[1].isBlank()) {
                            throw new TaskBuddyException("Please provide a valid todo description.");
                        }
                        String description = inputParts[1];
                        Task newTask = new Todo(description);
                        tasks.add(newTask);
                        addTaskMessage(newTask, tasks.size());
                    } catch (TaskBuddyException e) {
                        System.out.println(indent + e.getMessage());
                    }

                // Deadline tasks
                } else if (command.equals("deadline")) {
                    try {
                        if (inputParts.length < 2 || inputParts[1].isBlank()) {
                            throw new TaskBuddyException("Please provide a valid deadline description.");
                        }
                        String[] deadlineParts = inputParts[1].split(" /by ", 2);
                        if (deadlineParts.length < 2) {
                            throw new TaskBuddyException("Invalid deadline format. Use: description /by date");
                        }
                        String description = deadlineParts[0];
                        String deadline = deadlineParts[1];
                        Task newTask = new Deadline(description, deadline);
                        tasks.add(newTask);
                        addTaskMessage(newTask, tasks.size());
                    } catch (TaskBuddyException e) {
                        System.out.println(indent + e.getMessage());
                    }

                // Event tasks
                } else if (command.equals("event")) {
                    try {
                        if (inputParts.length < 2 || inputParts[1].isBlank()) {
                            throw new TaskBuddyException("Please provide a valid event description.");
                        }
                        String[] eventParts = inputParts[1].split(" /from ", 2);
                        if (eventParts.length < 2) {
                            throw new TaskBuddyException("Invalid event format. Use: description /from start /to end");
                        }
                        String description = eventParts[0];
                        String[] timeParts = eventParts[1].split(" /to ", 2);
                        if (timeParts.length < 2) {
                            throw new TaskBuddyException("Invalid event format. Use: description /from start /to end");
                        }
                        String from = timeParts[0];
                        String to = timeParts[1];
                        Task newTask = new Event(description, from, to);
                        tasks.add(newTask);
                        addTaskMessage(newTask, tasks.size());
                    } catch (TaskBuddyException e) {
                        System.out.println(indent + e.getMessage());
                    }

                // Bye to exit
                } else if (command.equals("bye")) {
                    System.out.println(indent + "Bye. Hope to see you again soon!");
                    break;

                // Invalid commands
                } else {
                    throw new TaskBuddyException("Invalid command. Please try again.");
                }
            } catch (TaskBuddyException e) {
                System.out.println(indent + e.getMessage());
            }
        }
    }
    private static void addTaskMessage(Task task, int taskCount) {
        String indent = "   ";
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(indent + "  " + task);
        System.out.println(indent + "Now you have " + taskCount + " tasks in the list.");
    }
}
