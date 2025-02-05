public class NoOpCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Does nothing, just to prevent errors and continue execution
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
