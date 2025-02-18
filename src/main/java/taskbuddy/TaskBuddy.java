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
     * A TaskBuddy object with the specified file path for storing task data.
     *
     * @param filePath The path of the file.
     */
    public TaskBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
    }

    /**
     * Processes user input by parsing the command and executing the corresponding action.
     *
     * @param input The user input as a string.
     * @return A confirmation message.
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
