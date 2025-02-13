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
     * Executes the DeleteCommand, which involves removing the task from the task list,
     * printing a confirmation message to the user, and saving the updated task list.
     *
     * @param taskList The task list from which the task will be removed.
     * @param ui The user interface that handles printing messages to the user.
     * @param storage The storage system that handles saving the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.removeTask(taskIndex);
        storage.saveTasks(taskList);
        return ui.printDeleteTaskMessage(task);

    }

    /**
     * Executes the DeleteCommand, which involves removing the task from the task list.
     *
     * @param taskList The task list from which the task will be removed.
     */
    public void execute(TaskList taskList) {
        taskList.removeTask(taskIndex);
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

