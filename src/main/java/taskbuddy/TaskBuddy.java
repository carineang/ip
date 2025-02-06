package taskbuddy;
import taskbuddy.command.*;

/**
 * TaskBuddy task management chatbot.
 */
public class TaskBuddy {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public TaskBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
    }

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

    public static void main(String[] args) {
        new TaskBuddy("ip/data/taskbuddy.txt").run();
    }
}