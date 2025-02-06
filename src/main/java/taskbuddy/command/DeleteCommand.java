package taskbuddy.command;
import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;
import taskbuddy.task.Task;


public class DeleteCommand extends Command {
    private Task task;
    private int taskIndex;

    public DeleteCommand(Task task, int taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.removeTask(taskIndex);
        ui.printDeleteTaskMessage(task);
        storage.saveTasks(taskList);
    }

    public void execute(TaskList taskList) {
        taskList.removeTask(taskIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

