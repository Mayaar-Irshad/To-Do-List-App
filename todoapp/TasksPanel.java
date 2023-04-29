/*
* Panel that displays all tasks added through the app.
*/
package todoapp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class TasksPanel extends JScrollPane {

	private String[] columnNames = {"Task Name", "Priority", "Due Date", "Status"};
	private DefaultTableModel model;
	private JTable table;
	
    public TasksPanel() {
   	 	super();
   	 	setupLayout();
   	 	setViewportView(table);
   	 	setVisible(true);
    }
    
    private void setupLayout() {
    	setBorder(BorderFactory.createEmptyBorder());
    	getViewport().setBackground(MainFrame.BOTTOM_PANEL_COLOR);
    	model = new DefaultTableModel(columnNames, 0) {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    			switch(column) {
    			case 3:
    				return true;
    			default:
    				return false;
    			}
    		}
    	};
    	table = new JTable(model);
    	CellRenderer renderer = new CellRenderer();
    	renderer.setHorizontalAlignment(SwingConstants.CENTER);
    	for (int i = 0; i < columnNames.length; i++) {
    		table.getColumnModel().getColumn(i).setCellRenderer(renderer);
    	}
    	table.getColumnModel().getColumn(columnNames.length - 1).setCellRenderer(new ButtonPaneRenderer());
    	table.getColumnModel().getColumn(columnNames.length - 1).setCellEditor(new ButtonPaneEditor());
    	setColumnHeader(new JViewport() {
    		@Override
    		public Dimension getPreferredSize() {
    			Dimension d = super.getPreferredSize();
    			d.height = MainFrame.TABLE_HEADER_HEIGHT;
    			return d;
    	    }
    	});
    	table.getTableHeader().setReorderingAllowed(false);
    	table.getTableHeader().setOpaque(false);
    	table.getTableHeader().setDefaultRenderer(new HeaderRenderer(table));
    	table.getTableHeader().setBackground(MainFrame.BOTTOM_PANEL_COLOR);
    	table.getTableHeader().setFont(new Font(MainFrame.FONT_TYPEFACE, Font.BOLD, 14));
    	table.setFont(new Font(MainFrame.FONT_TYPEFACE, Font.PLAIN, 12));
    	table.setOpaque(false);
    	table.setGridColor(Color.BLACK);
    	table.setShowVerticalLines(false);
    	table.setShowHorizontalLines(true);
    	table.setRowHeight(MainFrame.TABLE_HEADER_HEIGHT);
    	table.setFocusable(false);
    	table.setRowSelectionAllowed(false);
    	table.setAutoCreateRowSorter(true);
    	TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
    	table.setRowSorter(sorter);
    	sorter.setComparator(2, new Comparator<String>() {
    		@Override
    		public int compare(String o1, String o2) {
    			String[] strDate1 = o1.split("/");
    			String[] strDate2 = o2.split("/");
    			int[] date1 = new int[strDate1.length];
    			int[] date2 = new int[strDate2.length];
    			for (int i = 0; i < strDate1.length; i++) {
    				date1[i] = Integer.parseInt(strDate1[i]);
    				date2[i] = Integer.parseInt(strDate2[i]);
    			}
    			if (date1[2] != date2[2]) {
    				return date1[2] - date2[2];
    			} else if (date1[0] != date2[0]) {
    				return date1[0] - date2[0];
    			} else if (date1[1] != date2[1]) {
    				return date1[1] - date2[1];
    			}
    			return 0;
    		}
    	});
    	List<RowSorter.SortKey> sortKeys = new ArrayList<>();
    	sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
    	sorter.setSortKeys(sortKeys);
    	for (int i = 0; i < columnNames.length; i++) {
    		sorter.setSortable(i, false);
    	}
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
    
    public void updateTable(ArrayList<Task> taskList) {
    	Task currTask;
    	String[] rowData = new String[4];
    	
    	model.setRowCount(0);
    	for (int i = 0; i < taskList.size(); i++) {
    		currTask = taskList.get(i);
    		rowData[0] = currTask.getTitle();
    		rowData[1] = currTask.priorityAsString();
    		rowData[2] = currTask.getDueDate().format(MainFrame.DATE_FORMAT);
    		model.addRow(rowData);
    	}
   	 	setViewportView(table);
    }
}