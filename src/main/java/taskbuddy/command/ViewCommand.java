package taskbuddy.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import taskbuddy.Storage;
import taskbuddy.TaskList;
import taskbuddy.Ui;
import taskbuddy.task.Task;

/**
 * Represents a command to view tasks that match a specific date.
 */
public class ViewCommand extends Command {
    private String date;

    /**
     * A ViewCommand object with a specified date.
     *
     * @param date A string representing the date to find tasks.
     */
    public ViewCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the view command to find tasks that match the specified date.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage system.
     * @return A confirmation message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate targetDate = LocalDate.parse(date, formatter);
        ArrayList<Task> matchingTasks = taskList.findMatchingTaskDate(targetDate);
        return ui.printMatchingTasksDate(matchingTasks);
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

