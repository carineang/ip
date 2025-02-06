package taskbuddy;
import taskbuddy.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).markAsNotDone();
    }

    public Task getTask(int index) {
        return tasks.get(index);  // Get a single task by index
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public boolean contains(Task task) {
        return tasks.contains(task);
    }
}

