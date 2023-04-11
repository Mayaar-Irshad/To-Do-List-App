/*
* this Task class is for creating tasks and its attributes
*/
package todoapp;

import java.util.Date;

public class Task {
    // properties
    private String title;
    private String description;
    private Date dueDate;
    private int priority = -1; // by default, priority level is set as undefined
    private boolean completed = false; // by default, a task is not completed

    // constructor
    public Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
    
    // overloaded constructor to create an optional parameter for the variable priority
    public Task(String title, String description, Date dueDate, int priority) {
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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
}
