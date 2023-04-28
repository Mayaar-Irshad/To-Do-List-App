package todoapp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonPane extends JPanel {
	
	private GridBagConstraints gbc = new GridBagConstraints();
	private JButton deleteButton;
	private Task task;
	
	public ButtonPane() {
		setLayout(new GridBagLayout());
		setBackground(MainFrame.BOTTOM_PANEL_COLOR);
		try {
			Image img = ImageIO.read(getClass().getResource(MainFrame.DELETE_BUTTON_IMAGE));
    		deleteButton = new JButton(new ImageIcon(img.getScaledInstance(20, -1, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		deleteButton.setOpaque(false);
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBorderPainted(false);
		deleteButton.setFocusPainted(false);
		add(deleteButton, gbc);
		deleteButton.addActionListener(new deleteListener());
	}
	
	private class deleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.taskList.deleteTask(task);
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
		}
	}
	
	private void updateTables(ArrayList<Task> list) {
		MainFrame.myDayPanel.updateTable(list);
		MainFrame.tasksPanel.updateTable(list);
	}
	
	public void addActionListener(ActionListener listener) {
		deleteButton.addActionListener(listener);
	}
	
	public void setTask(Task t) {
		task = t;
	}
}
