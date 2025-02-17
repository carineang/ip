package taskbuddy.command;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import taskbuddy.Storage;
import taskbuddy.TaskList;
import taskbuddy.Ui;

/**
 * Represents a command that exits the TaskBuddy application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by saving the current task list to storage,
     * displaying a goodbye message to the user, and scheduling the application
     * to close after a short delay.
     *
     * @param taskList The list of tasks to be saved before exiting.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system responsible for saving the task list.
     * @return A goodbye message indicating that the application is closing.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        new Timer().schedule(new TimerTask() {
            public void run() {
                Platform.exit();
                System.exit(0);
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
