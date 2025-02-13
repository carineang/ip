package taskbuddy.command;

import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;

/**
 * Abstract class that represents a command in the TaskBuddy application.
 */
public abstract class Command {

    /**
     * Executes the command, performing the desired action on the task list,
     * interacting with the UI, and managing storage as needed.
     *
     * @param tasks The task list on which the command will operate.
     * @param ui The user interface to communicate results to the user.
     * @param storage The storage system used to save or load tasks.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether this command is an "exit" command.
     *
     * @return true if the command should cause the program to exit, false otherwise.
     */
    public abstract boolean isExit();
}
