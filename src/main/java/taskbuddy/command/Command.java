package taskbuddy.command;
import taskbuddy.TaskList;
import taskbuddy.Storage;
import taskbuddy.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
