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
     * A MarkCommand with the specified task and its index in the task list.
     *
     * @param task The task to be marked as completed.
     * @param taskIndex The index of the task in the task list.
     */
    public MarkCommand(Task task, int taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by marking the specified task.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage system.
     * @return A confirmation message.
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

    /**
     * Returns the task for this MarkCommand.
     *
     * @return Task.
     */
    public Task getTask() {
        return task;
    }
}
