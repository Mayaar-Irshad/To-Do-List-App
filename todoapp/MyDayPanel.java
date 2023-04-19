/*
* Panel that displays all tasks to be completed for the day.
*/
package todoapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyDayPanel extends JPanel {

	public MyDayPanel() {
		super();
		setupLayout();
		setVisible(true);
	}
	
	private void setupLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setBackground(new Color(245, 247, 247));

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(13, 25, 0, 0);
        //JLabel label = new JLabel("Today's Tasks");
        //label.setFont(label.getFont().deriveFont(Font.PLAIN));
        //add(label, gbc);
        add(new JLabel("Today's Tasks"), gbc);
	}

}
