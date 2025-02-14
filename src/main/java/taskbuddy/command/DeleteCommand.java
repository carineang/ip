package taskbuddy.command;

import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;
import taskbuddy.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private Task task;
    private int taskIndex;

    /**
     * Constructs a DeleteCommand to delete the specified task at the given index.
     *
     * @param task The task to be deleted.
     * @param taskIndex The index of the task to be deleted in the task list.
     */
    public DeleteCommand(Task task, int taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by removing the task from the task list, displaying
     * a confirmation message to the user, and saving the updated task list to storage.
     *
     * @param taskList The task list from which the task is removed.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving the updated task list.
     * @return A confirmation message indicating the task has been deleted.
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

