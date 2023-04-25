/*
* this GUI class is for Interface of the app
*/
package todoapp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JButton myDayButton = new JButton("My Day");
	private JButton tasksButton = new JButton("Tasks");
	private JButton newTaskButton = new JButton("+ New Task");
	private JTextField searchTextField = new JTextField("Search...", 15);
	private MyDayPanel myDayPanel = new MyDayPanel();
	private TasksPanel tasksPanel = new TasksPanel();

	// Constructor
	public MainFrame() {
		super();
		setupLayout();
		setupListeners();
		// Set frame properties
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TaskMate");
		setVisible(true);
		setResizable(false);
	}

	// Listeners
	private class myDayListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myDayButton.setForeground(Color.WHITE);
			tasksButton.setForeground(Color.BLACK);
			myDayPanel.setVisible(true);
			tasksPanel.setVisible(false);
		}
	}

	private class tasksListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myDayButton.setForeground(Color.BLACK);
			tasksButton.setForeground(Color.WHITE);
			myDayPanel.setVisible(false);
			tasksPanel.setVisible(true);
		}
	}

	private class searchListener implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {
			if (searchTextField.getText().equals("Search...")) {
				searchTextField.setText("");
			}
		}

		@Override
		public void focusLost(FocusEvent event) {
			if (searchTextField.getText().equals("")) {
				searchTextField.setText("Search...");
			}
		}
	}

	private class newTaskListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AddTaskFrame();
		}
	}

	private void setupLayout() {
		JPanel contentPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		contentPanel.setBackground(Color.decode("#4787b8"));
		add(contentPanel);

		// Add components to content panel
		// First row

		// My Day button
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.05;
		gbc.insets = new Insets(0, 50, 10, 0);
		myDayButton.setOpaque(false);
		myDayButton.setContentAreaFilled(false);
		myDayButton.setBorderPainted(false);
		myDayButton.setFocusPainted(false);
		myDayButton.setForeground(Color.WHITE);
		contentPanel.add(myDayButton, gbc);

		// Tasks button
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 10, 10, 0);
		tasksButton.setOpaque(false);
		tasksButton.setContentAreaFilled(false);
		tasksButton.setBorderPainted(false);
		tasksButton.setFocusPainted(false);
		contentPanel.add(tasksButton, gbc);

		// Search text field
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.insets = new Insets(0, 0, 10, 50);
		gbc.gridx = 2;
		gbc.gridy = 0;
		searchTextField.setToolTipText("Enter your task");
		contentPanel.add(searchTextField, gbc);

		// Second Row

		// My Day panel and Tasks panel
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 0.95;
		gbc.insets = new Insets(0, 0, 0, 0);
		contentPanel.add(myDayPanel, gbc);
		contentPanel.add(tasksPanel, gbc);
		tasksPanel.setVisible(false);

		// Third Row

		// Bottom panel that contains New Task button
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		JPanel bottomPanel = new JPanel(new GridBagLayout());
		bottomPanel.setBackground(new Color(245, 247, 247));
		contentPanel.add(bottomPanel, gbc);

		// New Task button
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0;
		gbc.insets = new Insets(0, 10, 10, 0);
		newTaskButton.setOpaque(false);
		newTaskButton.setContentAreaFilled(false);
		newTaskButton.setBorderPainted(false);
		bottomPanel.add(newTaskButton, gbc);

		bottomPanel.setVisible(true);
		contentPanel.setVisible(true);
	}

	private void setupListeners() {
		myDayButton.addActionListener(new myDayListener());
		tasksButton.addActionListener(new tasksListener());
		searchTextField.addFocusListener(new searchListener());
		newTaskButton.addActionListener(new newTaskListener());
	}
}