/*
* this GUI class is for Interface of the app
*/
package todoapp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	// Variables used to customize the look of the GUI
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	public static final Color TOP_PANEL_COLOR = Color.decode("#4787b8");
	public static final Color BOTTOM_PANEL_COLOR = new Color(245, 247, 247);
	public static final String FONT_TYPEFACE = "STIXGeneral";
	public static final int TABLE_HEADER_HEIGHT = 45;
	public static final String DELETE_BUTTON_IMAGE = "pics/trashcan.png";
	
	// Strings used for switching "cards" for the card layout
	private final String MY_DAY_PANEL = "Card showing tasks due today";
	private final String TASKS_PANEL = "Card showing all tasks";
	
	// Static variables for other classes to be able to refer to
	public static MyDayPanel myDayPanel = new MyDayPanel();
	public static TasksPanel tasksPanel = new TasksPanel();
	public static TaskManager taskList = new TaskManager();
	public static JTextField searchTextField = new JTextField("Search...", 15);
	public static boolean focusLost = true;
	// Components used in the MainFrame
	private JButton myDayButton = new JButton("My Day");
	private JButton tasksButton = new JButton("Tasks");
	private JButton newTaskButton = new JButton("+ New Task");
	private JPanel cardPanel = new JPanel(new CardLayout());
	// Other variables
	private Font buttonFont = new Font(FONT_TYPEFACE, Font.BOLD, 14);

	// ==============================================================================
	// Constructor
	// ==============================================================================
	public MainFrame() {
		super();
		setupLayout();
		setupListeners();
		// Set frame properties
		setTitle("TaskMate");
		setSize(1000, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// ==============================================================================
	// Listeners
	// ==============================================================================
	private class myDayListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Color of buttons are changed to show which panel is active
			myDayButton.setForeground(Color.WHITE);
			tasksButton.setForeground(Color.BLACK);
			switchCards(MY_DAY_PANEL);
		}
	}

	private class tasksListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			myDayButton.setForeground(Color.BLACK);
			tasksButton.setForeground(Color.WHITE);
			switchCards(TASKS_PANEL);
		}
	}

	// Make the "Search..." string disappear when the search text field is clicked
	private class searchFocusListener implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {
			if (searchTextField.getText().equals("Search...")) {
				focusLost = false;
				searchTextField.setText("");
			}
		}

		@Override
		public void focusLost(FocusEvent event) {
			if (searchTextField.getText().equals("")) {
				focusLost = true;
				searchTextField.setText("Search...");
			}
		}
	}

	// Add functionality to the Search text field
	private class searchDocumentListener implements DocumentListener {
		String searchStr;

		@Override
		public void insertUpdate(DocumentEvent e) {
			searchStr = searchTextField.getText();
			// Case when text field is empty and focus is lost
			if (focusLost) {
				updateTables(taskList.getTasks());
			// Case when a character is entered into the text field
			} else {
				updateTables(findMatches(searchStr));
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			searchStr = searchTextField.getText();
			// Case when character is removed from text field and text field becomes empty
			if (searchStr.length() == 0) {
				updateTables(taskList.getTasks());
			// Case when character is removed from text field
			} else {
				updateTables(findMatches(searchStr));
			}
		}

		// Unused
		@Override
		public void changedUpdate(DocumentEvent e) {
		}

		// Matches task names that starts with the string entered regardless of case
		public ArrayList<Task> findMatches(String str) {
			ArrayList<Task> resultList = new ArrayList<>();
			for (Task t : taskList.getTasks()) {
				if (t.getTitle().toLowerCase().startsWith(str.toLowerCase())) {
					resultList.add(t);
				}
			}
			return resultList;
		}

		// Graphically updates both tables
		public void updateTables(ArrayList<Task> list) {
			myDayPanel.updateTable(list);
			tasksPanel.updateTable(list);
		}
	}
	
	private class newTaskListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Clicking new task button opens the add a new task window
			new AddTaskFrame();
		}
	}

	// ==============================================================================
	// Layout
	// ==============================================================================
	private void setupLayout() {
		// Outer container that will contain all components in the program
		JPanel contentPanel = new JPanel(new GridBagLayout());
		// Grid bag constraints necessary to manipulate position of components in grid bag layout
		GridBagConstraints gbc = new GridBagConstraints();
		contentPanel.setBackground(TOP_PANEL_COLOR);
		add(contentPanel);

		// Add components to panel
		// First row

		// My Day button
		// Align button south-west
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		// Place button in coordinates (0,0) where (0,0) is located north-west
		gbc.gridx = 0;
		gbc.gridy = 0;
		// Proportion of space the component will take in the y-axis
		// Convention states the sum of all weights in a direction should add to 1
		gbc.weighty = 0.15;
		gbc.insets = new Insets(0, 50, 10, 0);
		// Make button background disappear
		myDayButton.setOpaque(false);
		myDayButton.setContentAreaFilled(false);
		myDayButton.setBorderPainted(false);
		myDayButton.setFocusPainted(false);
		myDayButton.setFont(buttonFont);
		myDayButton.setForeground(Color.WHITE);
		contentPanel.add(myDayButton, gbc);

		// Tasks button
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		// Button placed in (1,0) or the first "row", second "column"
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 10, 10, 0);
		// Make button background disappear
		tasksButton.setOpaque(false);
		tasksButton.setContentAreaFilled(false);
		tasksButton.setBorderPainted(false);
		tasksButton.setFocusPainted(false);
		tasksButton.setFont(buttonFont);
		contentPanel.add(tasksButton, gbc);

		// Search text field
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.insets = new Insets(0, 0, 10, 50);
		gbc.gridx = 2;
		gbc.gridy = 0;
		// Text that appears when hovering over component
		searchTextField.setToolTipText("Enter your task");
		contentPanel.add(searchTextField, gbc);

		// Second Row

		// Card Layout panel which contains the My Day Panel and Tasks Panel
		gbc.anchor = GridBagConstraints.CENTER;
		// Stretch component horizontally and vertically
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 0.85;
		gbc.insets = new Insets(0, 0, 0, 0);
		cardPanel.add(myDayPanel, MY_DAY_PANEL);
		cardPanel.add(tasksPanel, TASKS_PANEL);
		cardPanel.setBackground(BOTTOM_PANEL_COLOR);
		contentPanel.add(cardPanel, gbc);

		// Third Row

		// Bottom panel that contains New Task button
		// Panel needed to customize background color, otherwise the bottom
		// part of the program would be the same color as the outer container (top background color)
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		JPanel bottomPanel = new JPanel(new GridBagLayout());
		bottomPanel.setBackground(BOTTOM_PANEL_COLOR);
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
		newTaskButton.setOpaque(true);
		newTaskButton.setContentAreaFilled(true);
		newTaskButton.setBackground(TOP_PANEL_COLOR);
		newTaskButton.setBorderPainted(false);
		newTaskButton.setFocusPainted(false);
		newTaskButton.setFont(buttonFont);
		bottomPanel.add(newTaskButton, gbc);

		bottomPanel.setVisible(true);
		contentPanel.setVisible(true);
	}

	private void setupListeners() {
		myDayButton.addActionListener(new myDayListener());
		tasksButton.addActionListener(new tasksListener());
		searchTextField.addFocusListener(new searchFocusListener());
		searchTextField.getDocument().addDocumentListener(new searchDocumentListener());
		newTaskButton.addActionListener(new newTaskListener());
	}
	
	private void switchCards(String card) {
		((CardLayout) (cardPanel.getLayout())).show(cardPanel, card);
	}
}