package taskbuddy.command;

import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;

/**
 * Represents a command that performs no operation. just a placeholder.
 */
public class InvalidCommand extends Command {

    /**
     * Executes no operation command.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage system.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printInvalidCommand();
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
