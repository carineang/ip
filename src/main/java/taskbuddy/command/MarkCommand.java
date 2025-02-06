package taskbuddy.command;
import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;
import taskbuddy.task.Task;

public class MarkCommand extends Command {
    private Task task;
    private int taskIndex;

    public MarkCommand(Task task, int taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(taskIndex);
        ui.printMarkTaskMessage(task);
        storage.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}