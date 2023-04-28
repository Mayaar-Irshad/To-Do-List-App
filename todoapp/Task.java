/*
* this Task class is for creating tasks and its attributes
*/
package todoapp;

import java.time.LocalDate;

public class Task {
	// properties
	private String title;
	private String description;
	private LocalDate dueDate;
	private int priority = -1; // by default, priority level is set as undefined
	private boolean completed = false; // by default, a task is not completed

	// constructor
	public Task() {
		this.title = "";
		this.description = "";
		this.dueDate = null;
	}
	
	public Task(String title, String description, LocalDate dueDate) {
    	this.title = title;
    	this.description = description;
    	this.dueDate = dueDate;
	}
    
	// overloaded constructor to create an optional parameter for the variable priority
	public Task(String title, String description, LocalDate dueDate, int priority) {
    	this.title = title;
    	this.description = description;
    	this.dueDate = dueDate;
    	this.priority = priority;
	}

	// getters and setters
	public String getTitle() {
    	return title;
	}

	public void setTitle(String title) {
    	this.title = title;
	}

	public String getDescription() {
    	return description;
	}

	public void setDescription(String description) {
    	this.description = description;
	}

	public LocalDate getDueDate() {
    	return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
    	this.dueDate = dueDate;
	}

	public int getPriority() {
    	return priority;
	}

	public void setPriority(int priority) {
    	// topic for discussion in meeting
    	// potentially add enum type to signify which integer represents which level of priority
    	this.priority = priority;
	}

	public boolean getCompleted() {
    	return completed;
	}
    
	public void setCompleted(boolean completed) {
    	this.completed = completed;
	}
    
	public void markAsComplete() {
    	this.completed = true;
	}
	
	@Override
	public boolean equals(Object o) {
		// self check
		if (this == o) {
			return true;
		}
		// null check
		if (o == null) {
			return false;
		}
		// type check and cast
		if (getClass() != o.getClass()) {
			return false;
		}
		Task task = (Task) o;
		return getTitle().equals(task.getTitle())
				&& getDescription().equals(task.getDescription())
				&& getDueDate().equals(task.getDueDate())
				&& getPriority() == task.getPriority()
				&& getCompleted() == task.getCompleted();
	}
	
	public String priorityAsString() {
		switch(getPriority()) {
		case 0:
			return "Low";
		case 1:
			return "Medium";
		case 2:
			return "High";
		default:
			return "Undefined";
		}
	}
	
	public int stringToPriority(String str) {
		switch(str) {
		case "Low":
			return 0;
		case "Medium":
			return 1;
		case "High":
			return 2;
		default:
			return -1;
		}
	}
}
