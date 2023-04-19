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
    private JButton myDayButton = new JButton("My Day");
    private JButton tasksButton = new JButton("Tasks");
    private JButton addButton = new JButton("+ New Task");
    private JTextField taskTextField = new JTextField("Search...", 15);
    private MyDayPanel myDayPanel = new MyDayPanel();
    private TasksPanel tasksPanel = new TasksPanel();

    public ToDoListGUI() {

    	super();
    	setupLayout();
    	// Set frame properties
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TaskMate");
        setVisible(true);
    }
    
    private void setupLayout() {
    	// Create components
        JButton deleteButton = new JButton("Delete");

        GridBagConstraints gbc = new GridBagConstraints();
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        add(contentPanel);

        // Add components to content panel
        // First row
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 50, 10, 0);
        myDayButton.setOpaque(false);
        myDayButton.setContentAreaFilled(false);
        myDayButton.setBorderPainted(false);
        myDayButton.setFocusPainted(false);
        myDayButton.setForeground(new Color(99, 58, 218));
        contentPanel.add(myDayButton, gbc);

        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 10, 0);
        tasksButton.setOpaque(false);
        tasksButton.setContentAreaFilled(false);
        tasksButton.setBorderPainted(false);
        tasksButton.setFocusPainted(false);
        contentPanel.add(tasksButton, gbc);

        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = new Insets(0, 0, 10, 50);
        gbc.gridx = 2;
        gbc.gridy = 0;
        contentPanel.add(taskTextField, gbc);

        // Second Row
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 0.9;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(myDayPanel, gbc);
        contentPanel.add(tasksPanel, gbc);
        tasksPanel.setVisible(false);

        // Third Row
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(new Color(245, 247, 247));
        contentPanel.add(bottomPanel, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 10, 10, 0);
        addButton.setOpaque(false);
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(false);
        bottomPanel.add(addButton, gbc);

        // Action Listeners
        myDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	myDayButton.setForeground(new Color(99, 58, 218));
            	tasksButton.setForeground(Color.BLACK);
                myDayPanel.setVisible(true);
                tasksPanel.setVisible(false);
            }
        });

        tasksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	myDayButton.setForeground(Color.BLACK);
            	tasksButton.setForeground(new Color(99, 58, 218));
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

        contentPanel.setVisible(true);
    }

}
