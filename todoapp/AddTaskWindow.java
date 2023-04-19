/*
* Pop-up window that appears when the "Add Task" button is pressed.
* Used to input information about a new task.
*/
package todoapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AddTaskWindow extends JFrame {

    private JLabel taskNameLabel = new JLabel("Task Name");
    private JLabel dueDateLabel = new JLabel("Due Date");
    private JLabel priorityLabel = new JLabel("Priority");
    private JTextField taskNameField = new JTextField(15);
    private JTextField dueDateField = new JTextField("MM/DD/YYYY", 15);
    private String priorities[] = { "Undefined", "Low", "Medium", "High" };
    private JComboBox<String> priorityBox = new JComboBox<String>(priorities);
    private JButton applyButton = new JButton("Add Task");
    private JButton closeButton = new JButton("Close");

    public AddTaskWindow() {
        super();
        setupLayout();
        setSize(300, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setTitle("Add Task");
    }

    private void setupLayout() {
        JPanel contentPanel = new JPanel(new GridBagLayout());
        add(contentPanel);
        GridBagConstraints gbc = new GridBagConstraints();

        // Adding components
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        contentPanel.add(taskNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(taskNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;
        contentPanel.add(dueDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(dueDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1;
        contentPanel.add(priorityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(priorityBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(applyButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPanel.add(closeButton, gbc);
        
        // Action listeners
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == closeButton) {
                    dispose();
                }
            }
        });

        taskNameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (taskNameField.getText().equals("Enter Task Name")) {
                    taskNameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (taskNameField.getText().equals("")) {
                    taskNameField.setText("Enter Task Name");
                }
            }
        });
        taskNameField.setToolTipText("Enter Task Name");
        
        dueDateField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                if (dueDateField.getText().equals("MM/DD/YYYY")) {
                    dueDateField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (dueDateField.getText().equals("")) {
                    dueDateField.setText("MM/DD/YYYY");
                }
            }
        });
        dueDateField.setToolTipText("Enter Your Task's Due Date");
    }

}
