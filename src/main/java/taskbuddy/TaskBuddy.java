package taskbuddy;

import taskbuddy.command.*;

/**
 * TaskBuddy task management chatbot.
 */
public class TaskBuddy {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a TaskBuddy object with the specified file path for storing task data.
     *
     * @param filePath The path of the file where tasks will be saved and loaded from.
     */
    public TaskBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
    }

    /**
     * Starts and runs the TaskBuddy chatbot.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = Parser.readCommand();
            Command c = Parser.parseCommand(fullCommand, taskList);
            c.execute(taskList, ui, storage);
            isExit = c.isExit();
        }
    }

    /**
     * The entry point for the TaskBuddy application. It initializes the TaskBuddy object with a
     * specified file path and runs the chatbot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new TaskBuddy("ip/data/taskbuddy.txt").run();
    }
}
