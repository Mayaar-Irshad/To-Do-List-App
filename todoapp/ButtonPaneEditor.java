package todoapp;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("serial")
public class ButtonPaneEditor extends AbstractCellEditor implements TableCellEditor {

    private ButtonPane buttonPane;

    public ButtonPaneEditor() {
    	buttonPane = new ButtonPane();
    	buttonPane.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			SwingUtilities.invokeLater(new Runnable() {
    				@Override
    				public void run() {
    				}
    			});
    			stopCellEditing();
    		}
    	});
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
    		boolean isSelected, int row, int column) {
    	Task task = new Task();
    	int currRow = row;
    	String taskName = (String) table.getModel().getValueAt(currRow, 0);
    	int priority = task.stringToPriority((String) table.getModel().getValueAt(currRow, 1));
    	LocalDate date;
    	if (table.getColumnCount() < 4) {
    		date = LocalDate.now();
    	} else {
    		date = LocalDate.parse((String) table.getModel().getValueAt(currRow, 2), MainFrame.DATE_FORMAT);
    	}
    	task.setTitle(taskName);
    	task.setPriority(priority);
    	task.setDueDate(date);
    	buttonPane.setTask(task);
        return buttonPane;
    }
}