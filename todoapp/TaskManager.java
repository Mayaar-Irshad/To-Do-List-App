//
// Task Manger class
package todoapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskManager {
	private ArrayList<Task> tasks; // list of tasks

	// constructor for task manager
	public TaskManager() {
    	tasks = new ArrayList<>(); // initialize the list
	}

	// adds a task to the list
	public void addTask(Task task) {
    	tasks.add(task);
	}

	// updates the details of a task
	public void editTask(Task task, String title, String description, LocalDate dueDate) {
    	task.setTitle(title);
    	task.setDescription(description);
    	task.setDueDate(dueDate);
	}

	// removes a task from the list
	public void deleteTask(Task task) {
    	tasks.remove(task);
	}

	// marks a task as completed
	public void markTaskAsCompleted(Task task) {
    	task.setCompleted(true);
	}

	// returns the list of tasks
	public ArrayList<Task> getTasks() {
    	return tasks;
	}

	// returns a list of tasks with a due date equal to the parameter
	public ArrayList<Task> getTasksByDate(LocalDate dueDate) {
    	ArrayList<Task> matchingTasks = new ArrayList<>();
    	for (Task task : tasks) {
        	if (task.getDueDate().equals(dueDate)) { // check if due date matches
            	matchingTasks.add(task); // add to list of matching tasks
        	}
    	}
    	return matchingTasks;
	}

	// returns a list of tasks with a title or description containing the search
	// term
	public List<Task> getTasksBySearch(String searchTerm) {
    	List<Task> matchingTasks = new ArrayList<>();
    	for (Task task : tasks) {
        	if (task.getTitle().contains(searchTerm) || task.getDescription().contains(searchTerm)) {
            	matchingTasks.add(task);
        	}
    	}
    	return matchingTasks;
	}

	// getTaskByPriority
	// returns a list of tasks with a matching priority
	public List<Task> getTasksByPriority(int priority) {

    	List<Task> matchingTasks = new ArrayList<>();
    	for (Task task : tasks) {
        	if (task.getPriority() == priority) {
            	matchingTasks.add(task);
        	}
    	}
    	return matchingTasks;

	}

	// Sort tasks by priority
	// sorting tasks by priority DESCENDING
	public List<Task> sortTasksByPriority() {
    	List<Task> sortedTasks = new ArrayList<>(tasks);
    	Collections.sort(sortedTasks, new Comparator<Task>() {
    		@Override
        	public int compare(Task task1, Task task2) {
            	return Integer.compare(task2.getPriority(), task1.getPriority());
        	}
    	});
    	return sortedTasks;
	}

	// note: sort tasks by priority ascending method for 2nd spring.
}
