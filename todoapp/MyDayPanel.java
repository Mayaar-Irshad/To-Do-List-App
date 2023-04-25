/*
* Panel that displays all tasks to be completed for the day.
*/
package todoapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyDayPanel extends JPanel {

    private JLabel greetingLabel;
    
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
   	 	gbc.weightx = 1;
    	gbc.weighty = 0.01;
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.insets = new Insets(15, 25, 0, 0);
    	greetingLabel = new JLabel(generateGreeting());
    	greetingLabel.setFont(greetingLabel.getFont().deriveFont(Font.PLAIN));
    	add(greetingLabel, gbc);
   	 
    	gbc.weighty = 0.99;
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	add(new JLabel("Today's Tasks"), gbc);
    }

    private String generateGreeting() {
   	 	LocalDateTime today = LocalDateTime.now();
   	 	String greeting;
   	 
   	 	if (today.getHour() >= 5 && today.getHour() < 12) {
   	 		greeting = "Good morning! ";
    	} else if (today.getHour() <= 17) {
   		 	greeting = "Good afternoon! ";
    	} else {
   		 	greeting = "Good evening! ";
    	}
   	 	greeting += "Today is "
    			+ Character.toUpperCase(today.getDayOfWeek().toString().charAt(0))
   			 	+ today.getDayOfWeek().toString().substring(1).toLowerCase()
   			 	+ ", "
   			 	+ Character.toUpperCase(today.getMonth().toString().charAt(0))
   			 	+ today.getMonth().toString().substring(1).toLowerCase()
   			 	+ " " + today.getDayOfMonth() + ". Here's what we have for you today!";
    	return greeting;
    }
}