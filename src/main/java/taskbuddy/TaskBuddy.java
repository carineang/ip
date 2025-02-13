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

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input, taskList);
            return c.execute(taskList, ui, storage);
        } catch (TaskBuddyException e) {
            return "Oops!!! " + e.getMessage();

        }
    }
}
