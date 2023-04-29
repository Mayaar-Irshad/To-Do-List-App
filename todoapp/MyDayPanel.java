/*
* Panel that displays all tasks to be completed for the day.
*/
package todoapp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
    	// Set column headers for table model
    	model = new DefaultTableModel(columnNames, 0) {
    		// Make cells for all columns except the last column un-editable
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
    	// Associate table model with jtable
    	table = new JTable(model);
    	// Place jtable in jscrollpane
    	scrollPane =  new JScrollPane(table);
    	// Renderer that changes the background color of table rows
    	CellRenderer renderer = new CellRenderer();
    	// Make render change the alignment of text in table rows
    	renderer.setHorizontalAlignment(SwingConstants.CENTER);
    	// Apply the renderer to every column except the last column
    	for (int i = 0; i < columnNames.length - 1; i++) {
    		table.getColumnModel().getColumn(i).setCellRenderer(renderer);
    	}
    	// Set-up the custom renderer for the last column to become a column of clickable jbuttons
    	table.getColumnModel().getColumn(columnNames.length - 1).setCellRenderer(new ButtonPaneRenderer());
    	table.getColumnModel().getColumn(columnNames.length - 1).setCellEditor(new ButtonPaneEditor());
    	// Change the height of the table header
    	scrollPane.setColumnHeader(new JViewport() {
    		@Override
    		public Dimension getPreferredSize() {
    			Dimension d = super.getPreferredSize();
    			d.height = MainFrame.TABLE_HEADER_HEIGHT;
    	        return d;
    	    }
    	});
    	// Make only the bottom border of the header visible
    	UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
    	// Make it so the columns cannot be dragged and re-positioned by the user
    	table.getTableHeader().setReorderingAllowed(false);
    	// Make the column header background see-through
    	table.getTableHeader().setOpaque(false);
    	// Make the column header text center-aligned
    	table.getTableHeader().setDefaultRenderer(new HeaderRenderer(table));
    	// Set the background color of the header
    	table.getTableHeader().setBackground(MainFrame.BOTTOM_PANEL_COLOR);
    	// Set the font of the header text
    	table.getTableHeader().setFont(new Font(MainFrame.FONT_TYPEFACE, Font.BOLD, 14));
    	// Set the font of the row text
    	table.setFont(new Font(MainFrame.FONT_TYPEFACE, Font.PLAIN, 12));
    	// Make table row background see-through
    	table.setOpaque(false);
    	// Hide vertical gridlines of rows
    	table.setShowVerticalLines(false);
    	// Show horizontal gridlines of rows
    	table.setShowHorizontalLines(true);
    	// Change the color of the gridlines
    	table.setGridColor(Color.BLACK);
    	// Change the height of the table rows
    	table.setRowHeight(MainFrame.TABLE_HEADER_HEIGHT);
    	// Make row cells unable to be selected
    	table.setFocusable(false);
    	table.setRowSelectionAllowed(false);
    	// Create a automatic sorter for the table
    	table.setAutoCreateRowSorter(true);
    	TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
    	table.setRowSorter(sorter);
    	// Customized comparator for the row to be sorted (the priority row)
    	sorter.setComparator(1, new Comparator<String>() {
    		@Override
    		public int compare(String o1, String o2) {
    			Task t = new Task();
    			int priority1 = t.stringToPriority(o1);
    			int priority2 = t.stringToPriority(o2);
    			return priority1 - priority2;
    		}
    	});
    	List<RowSorter.SortKey> sortKeys = new ArrayList<>();
    	// Sort the priority row in descneding order (highest priority shown first)
    	sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
    	sorter.setSortKeys(sortKeys);
    	for (int i = 0; i < columnNames.length; i++) {
    		sorter.setSortable(i, false);
    	}
    	// Hide the triangle icon that signifies sorting order next to column headers
    	UIManager.put("Table.ascendingSortIcon", new EmptyIcon());
    	UIManager.put("Table.descendingSortIcon", new EmptyIcon());
    }
    
    private class EmptyIcon implements Icon {
    	int width = 0;
    	int height = 0;
		public void paintIcon(Component c, Graphics g, int x, int y) {
		}
		public int getIconWidth() {
			return width;
		}
		public int getIconHeight() {
			return height;
		}
    }
    
    public void updateTable(ArrayList<Task> taskList) {
    	Task currTask;
    	LocalDate taskDate;
    	String[] rowData = new String[3];
    	
    	model.setRowCount(0);
    	for (int i = 0; i < taskList.size(); i++) {
    		currTask = taskList.get(i);
    		taskDate = currTask.getDueDate();
    		// Additional condition that checks if the task is due today
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