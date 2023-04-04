/*
* this GUI class is for Interface of the app 
*/
package todoapp;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class ToDoListGUI extends JFrame {
    private ArrayList<Task> tasks = new ArrayList<>();
    private DefaultListModel<String> taskListModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(taskListModel);
    private JTextField taskTextField = new JTextField(20);

    public ToDoListGUI() {
        setTitle("To-Do List App");

        // Create components
        JLabel taskLabel = new JLabel("Task:");
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");

        // Add components to content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(taskLabel);
        topPanel.add(taskTextField);
        topPanel.add(addButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(deleteButton);

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(taskList), BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        // Add listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskTextField.getText();
                Task task = new Task(taskName);
                tasks.add(task);
                taskListModel.addElement(task.getTaskName());
                taskTextField.setText("");
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

        // Set frame properties
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Pending");
    }
}
