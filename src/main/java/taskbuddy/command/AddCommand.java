package taskbuddy.command;

import taskbuddy.Storage;
import taskbuddy.TaskList;
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
     * Executes the command by adding the task to the task list, displaying a confirmation
     * message to the user, and saving the updated task list to storage.
     *
     * @param taskList The task list to which the task is added.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving the updated task list.
     * @return A confirmation message indicating the task has been added.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.saveTasks(taskList);
        String response = ui.printAddTaskMessage(task);
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
     * Retrieves the task associated with this command.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        return task;
    }
}

