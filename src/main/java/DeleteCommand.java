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

    @Override
    public boolean isExit() {
        return false;
    }
}

