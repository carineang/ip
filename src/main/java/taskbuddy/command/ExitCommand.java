package taskbuddy.command;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;

/**
 * Represents a command that exits the TaskBuddy application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by saving the task list to storage and printing a goodbye message.
     *
     * @param taskList The list of tasks to be saved before the application exits.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage system used to save the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        new Timer().schedule(new TimerTask() {
            public void run() {
                Platform.exit();
            }
        }, 1000);
        return ui.printGoodbye();
    }

    /**
     * Returns whether this command is an "exit" command.
     *
     * @return true, as this command signifies an exit action.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
