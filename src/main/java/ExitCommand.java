public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasks(taskList);
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
