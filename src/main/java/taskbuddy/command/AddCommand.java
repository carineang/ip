package taskbuddy.command;

import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;
import taskbuddy.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand to add the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddCommand, which involves adding the task to the task list,
     * printing a confirmation message to the user, and saving the updated task list.
     *
     * @param taskList The task list to which the task will be added.
     * @param ui The user interface that handles printing messages to the user.
     * @param storage The storage system that handles saving the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.printAddTaskMessage(task);
        storage.saveTasks(taskList);
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
     * Gets the task associated with this command.
     *
     * @return The task to be added to the task list.
     */
    public Task getTask() {
        return task;
    }
}

