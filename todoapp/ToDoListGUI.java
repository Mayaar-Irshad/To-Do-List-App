/*
* this GUI class is for Interface of the app 
*/
package todoapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ToDoListGUI extends JFrame implements ActionListener {
    private ArrayList<Task> tasks = new ArrayList<>();
    private DefaultListModel<String> taskListModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(taskListModel);
    private JTextField taskTextField = new JTextField("Search...", 15);

    public ToDoListGUI() {

        // Create components
        JLabel taskLabel = new JLabel("Task:");
        JButton addButton = new JButton("+ Add Task");
        JButton deleteButton = new JButton("Delete");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem MenuOne = new JMenuItem("Today's Tasks");
        JMenuItem MenuTwo = new JMenuItem("TaskManger");
        JMenuItem MenuExit = new JMenuItem("Exit");

        // Add components to content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // First row
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton myDayButton = new JButton("My Day");
        contentPane.add(myDayButton, gbc);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton tasksButton = new JButton("Tasks");
        contentPane.add(tasksButton, gbc);

        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridwidth = 10;
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridy = 0;
        contentPane.add(taskTextField, gbc);

        // Row 2
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 100;
        gbc.gridx = 0;
        gbc.gridy = 1;
        final JPanel myDayPanel = new JPanel(new GridBagLayout());
        final JPanel tasksPanel = new JPanel(new GridBagLayout());
        myDayPanel.setBackground(Color.WHITE);
        tasksPanel.setBackground(Color.WHITE);
        contentPane.add(myDayPanel, gbc);
        contentPane.add(tasksPanel, gbc);

        // Final Row
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 100;
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(addButton, gbc);

        // Everything about the "My Day" panel
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        myDayPanel.add(new Label("Today's Tasks"), gbc);

        // Everything about the "Tasks" panel
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        tasksPanel.add(new Label("Task Name"), gbc);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        tasksPanel.add(new Label("Priority"), gbc);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 2;
        gbc.gridy = 0;
        tasksPanel.add(new Label("Due Date"), gbc);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 3;
        gbc.gridy = 0;
        tasksPanel.add(new Label("Status"), gbc);

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

        // Add listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == addButton) {
                    AddTaskWindow addtask = new AddTaskWindow();
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

        // Set frame properties
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("TaskMate");

        // Menu
        setJMenuBar(menuBar);
        fileMenu.add(MenuOne);
        fileMenu.add(MenuTwo);
        fileMenu.add(MenuExit);
        menuBar.add(fileMenu);
        ImageIcon icon = new ImageIcon("Menu.png"); // idk how to put the image as the icon
        fileMenu.setIcon(icon);

        // Add Button opens new JFrame

    }

}
