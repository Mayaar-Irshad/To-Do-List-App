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
    private JComboBox priorityBox = new JComboBox(priorities);
    private JButton applyButton = new JButton("Apply");
    private JButton closeButton = new JButton("Close");

    public AddTaskWindow() {
        super();
        setupLayout();
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Add Tasks");

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

    }

    private void setupLayout() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        contentPane.add(taskNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(taskNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;
        contentPane.add(dueDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(dueDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1;
        contentPane.add(priorityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(priorityBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(applyButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPane.add(closeButton, gbc);
    }

}
