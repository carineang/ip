package taskbuddy;

import taskbuddy.command.Command;

/**
 * TaskBuddy is a task management chatbot that allows users to interact with
 * and manage their tasks.
 */
public class TaskBuddy {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a TaskBuddy object with the specified file path for storing task data.
     * Initializes the User Interface, Storage, and loads existing tasks from the file
     * into the TaskList.
     *
     * @param filePath The path of the file where tasks will be saved and loaded from.
     */
    public TaskBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
    }

    /**
     * Processes user input by parsing the command and executing the corresponding action.
     *
     * @param input The user input as a string, which represents the command the user wishes to execute.
     * @return A response message as a string, based on the execution of the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input, taskList);
            return c.execute(taskList, ui, storage);
        } catch (TaskBuddyException e) {
            return "Beep!!! " + e.getMessage();
        }
    }
}
