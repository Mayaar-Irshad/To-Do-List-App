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

    private JLabel taskNameLabel = new JLabel("Task Name:");
    private JLabel dueDateLabel = new JLabel("Due Date:");
    private JLabel priorityLabel = new JLabel("Priority:");
    private JTextField taskNameField = new JTextField(13);
    private JTextField dueDateField = new JTextField("MM/DD/YYYY", 8);
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
        setTitle("Add a New Task");
        setVisible(true);
    }

    private void setupLayout() {
    	GridBagConstraints gbc = new GridBagConstraints();
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(245, 247, 247));
        add(contentPanel);

        // Adding components
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.25;
        gbc.insets = new Insets(15,0,0,25);
        taskNameLabel.setFont(taskNameLabel.getFont().deriveFont(Font.PLAIN));
        contentPanel.add(taskNameLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(taskNameField, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,0,25);
        dueDateLabel.setFont(dueDateLabel.getFont().deriveFont(Font.PLAIN));
        contentPanel.add(dueDateLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(dueDateField, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 2;
        priorityLabel.setFont(priorityLabel.getFont().deriveFont(Font.PLAIN));
        contentPanel.add(priorityLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(priorityBox, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(20,40,0,0);
        contentPanel.add(applyButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(20,0,0,0);
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
        taskNameField.setToolTipText("Enter task name.");
        
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
        dueDateField.setToolTipText("Enter your task's due date.");
    }

}
