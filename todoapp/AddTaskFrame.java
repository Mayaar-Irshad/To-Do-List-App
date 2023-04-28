/*
* Pop-up window that appears when the "Add Task" button is pressed.
* Used to input information about a new task.
*/
package todoapp;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class AddTaskFrame extends JFrame {

	private JPanel contentPanel = new JPanel(new GridBagLayout());
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel taskNameLabel = new JLabel("Task Name:");
	private JLabel dueDateLabel = new JLabel("Due Date:");
	private JLabel priorityLabel = new JLabel("Priority:");
	private JLabel errorLabel = new JLabel("Invalid date. Enter date in format: MM/DD/YYYY");
	private JTextField taskNameField = new JTextField(13);
	private JTextField dueDateField = new JTextField("MM/DD/YYYY", 8);
	private String priorities[] = { "Undefined", "Low", "Medium", "High" };
	private JComboBox<String> priorityBox = new JComboBox<>(priorities);
	private JButton createButton = new JButton("Create");
	private JButton closeButton = new JButton("Close");
	private Font font = new Font(MainFrame.FONT_TYPEFACE, Font.PLAIN, 12);

	// ==============================================================================
	// Constructor
	// ==============================================================================
	public AddTaskFrame() {
		super();
    	setupLayout();
    	setupListeners();
    	setTitle("Add a New Task");
    	setSize(350, 350);
    	setResizable(false);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setVisible(true);
	}

	// ==============================================================================
	// Listeners
	// ==============================================================================
	private class dueDateListener implements FocusListener {
   	 	@Override
   	 	public void focusGained(FocusEvent e) {
   	 		if (dueDateField.getText().equals("MM/DD/YYYY")) {
            	dueDateField.setText("");
        	}
    	}

   	 	@Override
    	public void focusLost(FocusEvent e) {
        	if (dueDateField.getText().equals("")) {
            	dueDateField.setText("MM/DD/YYYY");
        	}
    	}
	}
    
	private class createListener implements ActionListener {
   	 	@Override
   	 	public void actionPerformed(ActionEvent e) {
   	 		// Case when date entered in due date text field is properly formatted
   	 		if (isValidDate(dueDateField.getText())) {
   	 			Task task = new Task();
   	 			// Grab data entered from text fields and combo box
   	 			task.setTitle(taskNameField.getText());
   	 			task.setDueDate(LocalDate.parse(dueDateField.getText(), MainFrame.DATE_FORMAT));
   	 			task.setPriority(task.stringToPriority(priorityBox.getSelectedItem().toString()));
   	 			// Store the task into the task manager static variable
   	 			MainFrame.taskList.addTask(task);

   	 			// Update the tables in the My Day panel and Tasks Panel to display the new task
   				String searchStr = MainFrame.searchTextField.getText();
   				if (searchStr.length() != 0 && !MainFrame.focusLost) {
   					ArrayList<Task> resultList = new ArrayList<>();
   					for (Task t : MainFrame.taskList.getTasks()) {
   						if (t.getTitle().toLowerCase().startsWith(searchStr.toLowerCase())) {
   							resultList.add(t);
   						}
   					}
   					updateTables(resultList);
   				} else {
   					updateTables(MainFrame.taskList.getTasks());
   				}
   	 		
   	 			// Close window after successful task creation
   	 			dispose();
   	 		} else {
   	 			// Case when date entered in due date text field is improperly formatted
   	 			// To update components, first they must be removed
   	 			contentPanel.remove(dueDateLabel);
   	 			contentPanel.remove(dueDateField);
   	 			contentPanel.remove(priorityLabel);
   	 			contentPanel.remove(priorityBox);
   	 			contentPanel.remove(createButton);
   	 			contentPanel.remove(closeButton);
   	 			
   	 			// Starting from 2nd row
   	 			
   	 			// Re-adding components that were removed
   	 			// Adding new error label above due date text field
   	 			gbc.anchor = GridBagConstraints.CENTER;
   	 			gbc.gridx = 0;
   	 			gbc.gridy = 1;
   	 			gbc.gridwidth = 2;
   	 			gbc.weighty = 0.125;
   	 			gbc.insets = new Insets(0,0,0,0);
   	 			errorLabel.setFont(new Font(MainFrame.FONT_TYPEFACE, Font.PLAIN, 10));
   	 			errorLabel.setForeground(Color.RED);
   	 			contentPanel.add(errorLabel, gbc);
   	 			
   	 			// Due date label
   	 			gbc.anchor = GridBagConstraints.FIRST_LINE_END;
   	 			gbc.gridx = 0;
   	 			gbc.gridy = 2;
   	 			gbc.gridwidth = 1;
   	 			gbc.weightx = 0.5;
   	 			gbc.weighty = 0.125;
   	 			gbc.insets = new Insets(0,0,0,25);
   	 			contentPanel.add(dueDateLabel, gbc);

   	 			// Due Date text field
   	 			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
   	 			gbc.gridx = 1;
   	 			gbc.gridy = 2;
   	 			contentPanel.add(dueDateField, gbc);
   	 			
   	 			// 3rd row
   	 			
   	 			// Priority label
   	 			gbc.anchor = GridBagConstraints.LINE_END;
   	 			gbc.gridx = 0;
   	 			gbc.gridy = 3;
   	 			gbc.weighty = 0.25;
   	 			contentPanel.add(priorityLabel, gbc);

   	 			// Priority "combo box" (drop down menu"
   	 			gbc.anchor = GridBagConstraints.LINE_START;
   	 			gbc.gridx = 1;
   	 			gbc.gridy = 3;
   	 			contentPanel.add(priorityBox, gbc);

   	 			// 4th row
   	 			
   	 			// Create button
   	 			gbc.anchor = GridBagConstraints.CENTER;
   	 			gbc.gridx = 0;
   	    		gbc.gridy = 4;
   	    		gbc.insets = new Insets(20,40,0,0);
   	    		contentPanel.add(createButton, gbc);

   	    		// Close button
   	    		gbc.gridx = 1;
   	    		gbc.gridy = 4;
   	    		gbc.insets = new Insets(20,0,0,0);
   	    		contentPanel.add(closeButton, gbc);
   	    		// Refresh content panel
   	    		contentPanel.revalidate();
   	    		contentPanel.repaint();
   	 		}
   	 	}
	}
    
	private class closeListener implements ActionListener {
   	 	@Override
    	public void actionPerformed(ActionEvent e) {
   	 		dispose();
    	}
	}
    
	// ==============================================================================
	// Layout
	// ==============================================================================
	private void setupLayout() {
    	contentPanel.setBackground(MainFrame.BOTTOM_PANEL_COLOR);
    	add(contentPanel);

    	// Adding components
    	// 1st row
    	
    	// Task name label
    	gbc.anchor = GridBagConstraints.LINE_END;
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.weightx = 0.5;
    	gbc.weighty = 0.25;
    	gbc.insets = new Insets(15,0,0,25);
    	taskNameLabel.setFont(font);
    	contentPanel.add(taskNameLabel, gbc);

    	// Task name text field
    	gbc.anchor = GridBagConstraints.LINE_START;
    	gbc.gridx = 1;
    	gbc.gridy = 0;
    	taskNameField.setToolTipText("Enter task name.");
    	contentPanel.add(taskNameField, gbc);

    	// 2nd row
    	
    	// Due date label
    	gbc.anchor = GridBagConstraints.LINE_END;
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	gbc.insets = new Insets(0,0,0,25);
    	dueDateLabel.setFont(font);
    	contentPanel.add(dueDateLabel, gbc);

    	// Due date text field
    	gbc.anchor = GridBagConstraints.LINE_START;
    	gbc.gridx = 1;
    	gbc.gridy = 1;
    	dueDateField.setToolTipText("Enter your task's due date.");
    	contentPanel.add(dueDateField, gbc);

    	// 3rd row
    	
    	// Priority label
    	gbc.anchor = GridBagConstraints.LINE_END;
    	gbc.gridx = 0;
    	gbc.gridy = 2;
    	priorityLabel.setFont(font);
    	contentPanel.add(priorityLabel, gbc);

    	// Priority "combo box" (drop down menu)
    	gbc.anchor = GridBagConstraints.LINE_START;
    	gbc.gridx = 1;
    	gbc.gridy = 2;
    	priorityBox.setFont(font);
    	contentPanel.add(priorityBox, gbc);

    	// 4th row
    	
    	// Create button
    	gbc.anchor = GridBagConstraints.CENTER;
    	gbc.gridx = 0;
    	gbc.gridy = 3;
    	gbc.insets = new Insets(20,40,0,0);
    	createButton.setFont(font);
    	contentPanel.add(createButton, gbc);

    	// Close button
    	gbc.gridx = 1;
    	gbc.gridy = 3;
    	gbc.insets = new Insets(20,0,0,0);
    	closeButton.setFont(font);
    	contentPanel.add(closeButton, gbc);
	}


	private void setupListeners() {
		dueDateField.addFocusListener(new dueDateListener());
		createButton.addActionListener(new createListener());
		closeButton.addActionListener(new closeListener());
	}
	
	// Checks if string has the format "MM/DD/YYYY"
	private boolean isValidDate(String date) {
		if (date.length() == 10 && isInteger(date.substring(0, 2))
				&& isInteger(date.substring(3, 5))
				&& isInteger(date.substring(6, 10))
				&& date.charAt(2) == '/'
				&& date.charAt(5) == '/'
				&& Character.getNumericValue(date.charAt(0)) < 2
				&& Character.getNumericValue(date.charAt(3)) < 4) {
			return true;
		} else {
			return false;
		}
	}
	
	// Checks if a string only contains integers
	private boolean isInteger(String str) {
		if (str.isEmpty()) {
			return false;
		} else {
			for (int i = 0; i < str.length(); i++) {
				if (!Character.isDigit(str.charAt(i))) {
					return false;
				}
			}
			return true;
		}
	}
	
	private void updateTables(ArrayList<Task> list) {
		MainFrame.myDayPanel.updateTable(list);
		MainFrame.tasksPanel.updateTable(list);
	}
}
