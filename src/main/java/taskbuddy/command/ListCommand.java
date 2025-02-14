package taskbuddy.command;

import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;

/**
 * Represents a command that displays the list of all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by printing all tasks in the current task list.
     * This method fetches the current task list and delegates the task of displaying it to the user interface.
     *
     * @param taskList The list of tasks to be displayed to the user.
     * @param ui The user interface that handles displaying the task list.
     * @param storage The storage system, which is not used in this command but included for compatibility with the Command class.
     * @return A string containing the formatted task list to be displayed to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printTaskList(taskList);
    }


    /**
     * Returns whether this command is an "exit" command.
     *
     * @return false, as this command does not trigger program exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
