package taskbuddy.command;

import java.util.ArrayList;
import taskbuddy.Storage;
import taskbuddy.TaskList;
import taskbuddy.Ui;
import taskbuddy.task.Task;

/**
 * Represents a command that searches for tasks containing a specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search within the tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword
     * in their description. The results are then displayed to the user.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The user interface used to display the search results to the user.
     * @param storage The storage system, though not directly used in this command.
     * @return A message containing the list of tasks that match the search keyword.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
        String response = ui.printMatchingTasks(matchingTasks);
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
}
