package taskbuddy.command;

import taskbuddy.Storage;
import taskbuddy.TaskList;
import taskbuddy.Ui;
import taskbuddy.task.Task;

/**
 * Represents a command that marks a task as completed.
 */
public class MarkCommand extends Command {
    private Task task;
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task and its index in the task list.
     *
     * @param task The task to be marked as completed.
     * @param taskIndex The index of the task in the task list.
     */
    public MarkCommand(Task task, int taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by marking the specified task as completed,
     * printing a confirmation message to the user, and saving the updated task list to storage.
     *
     * @param taskList The list of tasks that contains the task to be marked as completed.
     * @param ui The user interface used to display the confirmation message to the user.
     * @param storage The storage system used to save the updated task list after the task is marked.
     * @return A string containing the confirmation message indicating the task has been marked as completed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(taskIndex);
        storage.saveTasks(taskList);
        String response = ui.printMarkTaskMessage(task);
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
