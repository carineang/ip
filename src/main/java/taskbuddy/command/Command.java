package taskbuddy.command;

import taskbuddy.Storage;
import taskbuddy.TaskList;
import taskbuddy.Ui;

/**
 * An abstract class representing a command in the TaskBuddy application.
 */
public abstract class Command {

    /**
     * Executes the command, performing the necessary operations on the task list,
     * updating the user interface, and managing storage as needed.
     *
     * @param tasks The task list on which the command operates.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage system used for saving or retrieving tasks.
     * @return A message reflecting the result of the command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether this command is an "exit" command.
     *
     * @return true if the command should cause the program to exit, false otherwise.
     */
    public abstract boolean isExit();
}
