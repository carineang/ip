package taskbuddy.command;

import taskbuddy.Storage;
import taskbuddy.TaskList;
import taskbuddy.Ui;
import taskbuddy.task.Task;

/**
 * Represents a command that unmarks a task as completed.
 */
public class UnmarkCommand extends Command {
    private Task task;
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task and its index in the task list.
     *
     * @param task The task to be unmarked as completed.
     * @param taskIndex The index of the task in the task list.
     */
    public UnmarkCommand(Task task, int taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by unmarking the specified task as incomplete,
     * printing a confirmation message to the user, and saving the updated task list to storage.
     *
     * @param taskList The list of tasks that contains the task to be unmarked as incomplete.
     * @param ui The user interface used to display the confirmation message to the user.
     * @param storage The storage system used to save the updated task list after the task is unmarked.
     * @return A string containing the confirmation message indicating the task has been unmarked as incomplete.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmarkTask(taskIndex);
        storage.saveTasks(taskList);
        String response = ui.printUnmarkTaskMessage(task);
        return response;
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
