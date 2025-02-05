public class UnmarkCommand extends Command {
    private Task task;
    private int taskIndex;

    public UnmarkCommand(Task task, int taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmarkTask(taskIndex);
        ui.printUnmarkTaskMessage(task);
        storage.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}