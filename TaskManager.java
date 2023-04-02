// 
// Task Manger class 

public class TaskManager {
    // Properties
    private ArrayList<Task> tasks;

    // Methods
    public void addTask(Task task) {
        task.add(task);
    }

    public void deleteTask(Task task) {
        task.remove(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void getTasksByPriority() {
        // implementation here
    }

    public void getTasksByDate() {
        // implementation here
    }

    public void getTasksBySearch() {
        // implementation here
    }

    public void editTask(Task task) {
        // implementation here
    }

    public void getCompletedTasks() {
        // implementation here
    }
}