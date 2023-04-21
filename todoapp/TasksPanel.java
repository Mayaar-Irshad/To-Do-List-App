/*
* Panel that displays all tasks added through the app.
*/
package todoapp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TasksPanel extends JPanel {

    public TasksPanel() {
   	 	super();
   	 	setupLayout();
   	 	setVisible(true);
    }
    
    private void setupLayout() {
   	 	setLayout(new GridBagLayout());
   	 	GridBagConstraints gbc = new GridBagConstraints();
   	 	setBackground(new Color(245, 247, 247));
   	 
    	gbc.anchor = GridBagConstraints.PAGE_START;
    	gbc.weightx = 0.25;
    	gbc.weighty = 0.01;
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.insets = new Insets(15, 0, 0, 0);
    	add(new JLabel("Task Name"), gbc);

    	gbc.gridx = 1;
    	gbc.gridy = 0;
    	add(new JLabel("Priority"), gbc);

    	gbc.gridx = 2;
    	gbc.gridy = 0;
    	add(new JLabel("Due Date"), gbc);

    	gbc.gridx = 3;
    	gbc.gridy = 0;
    	add(new JLabel("Status"), gbc);
    }
}