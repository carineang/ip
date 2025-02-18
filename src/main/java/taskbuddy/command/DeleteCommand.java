package taskbuddy.command;

import taskbuddy.Storage;
import taskbuddy.TaskList;
import taskbuddy.Ui;
import taskbuddy.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private Task task;
    private int taskIndex;

    /**
     * A DeleteCommand to delete the specified task at the given index.
     *
     * @param task The task to be deleted.
     * @param taskIndex The index of the task to be deleted in the task list.
     */
    public DeleteCommand(Task task, int taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by removing the task from the task list.
     *
     * @param taskList The task list.
     * @param ui The user interface.
     * @param storage The storage system.
     * @return A confirmation message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.removeTask(taskIndex);
        storage.saveTasks(taskList);
        return ui.printDeleteTaskMessage(task);
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

