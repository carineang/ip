package taskbuddy.command;

import taskbuddy.Storage;
import taskbuddy.TaskList;
import taskbuddy.Ui;

/**
 * Represents a command that performs no operation. just a placeholder.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command.
     *
     * @param taskList The task list.
     * @param ui The user interface.
     * @param storage The storage system.
     * @return A confirmation message.
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
