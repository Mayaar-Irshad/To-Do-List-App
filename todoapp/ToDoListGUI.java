/*
* this GUI class is for Interface of the app 
*/
package todoapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ToDoListGUI extends JFrame {
    private ArrayList<Task> tasks = new ArrayList<>();
    private DefaultListModel<String> taskListModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(taskListModel);
    private JTextField taskTextField = new JTextField("Search...", 15);
    private JButton addButton = new JButton("+ New Task");
    private MyDayPanel myDayPanel = new MyDayPanel();
    private TasksPanel tasksPanel = new TasksPanel();

    public ToDoListGUI() {

    	super();
    	setupLayout();
    }
    
    private void setupLayout() {
    	// Set frame properties
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("TaskMate");
    	
        // Create components
        JButton deleteButton = new JButton("Delete");

        // Add components to content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        add(contentPanel);
        GridBagConstraints gbc = new GridBagConstraints();

        // First row
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton myDayButton = new JButton("My Day");
        contentPanel.add(myDayButton, gbc);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton tasksButton = new JButton("Tasks");
        contentPanel.add(tasksButton, gbc);

        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridwidth = 10;
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridy = 0;
        contentPanel.add(taskTextField, gbc);

        // Second Row
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 100;
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(myDayPanel, gbc);
        contentPanel.add(tasksPanel, gbc);

        // Final Row
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(addButton, gbc);

        // Action Listeners
        myDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myDayPanel.setVisible(true);
                tasksPanel.setVisible(false);
            }
        });

        tasksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myDayPanel.setVisible(false);
                tasksPanel.setVisible(true);
            }
        });

        // Add task button listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == addButton) {
                    new AddTaskWindow();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    tasks.remove(selectedIndex);
                    taskListModel.remove(selectedIndex);
                }
            }
        });

        // Search JTextField
        taskTextField.setToolTipText("Enter your task");
        taskTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (taskTextField.getText().equals("Search...")) {
                    taskTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (taskTextField.getText().equals("")) {
                    taskTextField.setText("Search...");
                }
            }
        });

    }

}
