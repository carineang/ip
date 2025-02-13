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
     *
     * @param taskList The list of tasks to be displayed.
     * @param ui The user interface used to display the task list.
     * @param storage The storage system (not used in this command).
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
