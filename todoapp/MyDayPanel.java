/*
* Panel that displays all tasks to be completed for the day.
*/
package todoapp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MyDayPanel extends JPanel {

	private LocalDateTime today = LocalDateTime.now();
	private String[] columnNames = {"Today's Tasks", "Priority", ""};
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel greetingLabel;
    
    public MyDayPanel() {
   	 	super();
   	 	setupTableLayout();
   	 	setupLayout();
   	 	setVisible(true);
    }
    
    private void setupLayout() {
    	setLayout(new GridBagLayout());
    	setBackground(MainFrame.BOTTOM_PANEL_COLOR);
    	GridBagConstraints gbc = new GridBagConstraints();
    	
    	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.weightx = 1;
    	gbc.weighty = 0.15;
    	gbc.insets = new Insets(15, 25, 0, 0);
    	greetingLabel = new JLabel(generateGreeting());
    	greetingLabel.setFont(new Font(MainFrame.FONT_TYPEFACE, Font.BOLD, 15));
    	add(greetingLabel, gbc);
    	
    	gbc.anchor = GridBagConstraints.CENTER;
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	gbc.weighty = 0.85;
    	gbc.insets = new Insets(0, 0, 0, 0);
    	scrollPane.setBorder(BorderFactory.createEmptyBorder());
    	scrollPane.getViewport().setBackground(MainFrame.BOTTOM_PANEL_COLOR);
    	add(scrollPane, gbc);
    }
    
    private void setupTableLayout() {
    	model = new DefaultTableModel(columnNames, 0) {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    			switch(column) {
    			case 2:
    				return true;
    			default:
    				return false;
    			}
    		}
    	};
    	table = new JTable(model);
    	scrollPane =  new JScrollPane(table);
    	CellRenderer renderer = new CellRenderer();
    	renderer.setHorizontalAlignment(SwingConstants.CENTER);
    	for (int i = 0; i < columnNames.length - 1; i++) {
    		table.getColumnModel().getColumn(i).setCellRenderer(renderer);
    	}
    	table.getColumnModel().getColumn(columnNames.length - 1).setCellRenderer(new ButtonPaneRenderer());
    	table.getColumnModel().getColumn(columnNames.length - 1).setCellEditor(new ButtonPaneEditor());
    	scrollPane.setColumnHeader(new JViewport() {
    		@Override
    		public Dimension getPreferredSize() {
    			Dimension d = super.getPreferredSize();
    			d.height = MainFrame.TABLE_HEADER_HEIGHT;
    	        return d;
    	    }
    	});
    	UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
    	table.getTableHeader().setReorderingAllowed(false);
    	table.getTableHeader().setOpaque(false);
    	table.getTableHeader().setBackground(MainFrame.BOTTOM_PANEL_COLOR);
    	table.getTableHeader().setFont(new Font(MainFrame.FONT_TYPEFACE, Font.BOLD, 14));
    	table.setFont(new Font(MainFrame.FONT_TYPEFACE, Font.PLAIN, 12));
    	table.setOpaque(false);
    	table.setShowVerticalLines(false);
    	table.setRowHeight(MainFrame.TABLE_HEADER_HEIGHT);
    	table.setFocusable(false);
    	table.setRowSelectionAllowed(false);
    }
    
    public void updateTable(ArrayList<Task> taskList) {
    	Task currTask;
    	LocalDate taskDate;
    	String[] rowData = new String[3];
    	
    	model.setRowCount(0);
    	for (int i = 0; i < taskList.size(); i++) {
    		currTask = taskList.get(i);
    		taskDate = currTask.getDueDate();
    		if (today.getYear() == taskDate.getYear()
    				&& today.getDayOfYear() == taskDate.getDayOfYear()) {
    			rowData[0] = currTask.getTitle();
    			rowData[1] = currTask.priorityAsString();
    			model.addRow(rowData);
    		}
    	}
   	 	scrollPane.setViewportView(table);
    }
    
    private class CellRenderer extends DefaultTableCellRenderer { 
    	public Component getTableCellRendererComponent(JTable table, Object value,
    			boolean isSelected, boolean hasFocus, int row, int column) { 
    		Component c = super.getTableCellRendererComponent(table, value,
    				isSelected, hasFocus, row, column);
            c.setBackground(MainFrame.BOTTOM_PANEL_COLOR);
            return c;
        }
    }

    private String generateGreeting() {
   	 	String greeting;
   	 
   	 	if (today.getHour() >= 5 && today.getHour() < 12) {
   	 		greeting = "Good morning! ";
    	} else if (today.getHour() >= 12 && today.getHour() < 17) {
   		 	greeting = "Good afternoon! ";
    	} else {
   		 	greeting = "Good evening! ";
    	}

   	 	greeting += "Today is "
    			+ initCap(today.getDayOfWeek().toString())
   			 	+ ", "
   			 	+ initCap(today.getMonth().toString())
   			 	+ " "
   			 	+ today.getDayOfMonth()
   			 	+ ". Here's what we have for you today!";
    	return greeting;
    }
    
    private String initCap(String str) {
    	if (str.isEmpty()) {
    		return "";
    	} else {
    		return Character.toUpperCase(str.charAt(0))
    				+ str.substring(1).toLowerCase();
    	}
    }
}