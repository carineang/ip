package taskbuddy.command;
import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}