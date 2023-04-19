/*
* Panel that displays all tasks to be completed for the day.
*/
package todoapp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JPanel;

public class MyDayPanel extends JPanel {

	public MyDayPanel() {
		super();
		setupLayout();
	}
	
	private void setupLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		setBackground(Color.WHITE);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new Label("Today's Tasks"), gbc);
	}

}
