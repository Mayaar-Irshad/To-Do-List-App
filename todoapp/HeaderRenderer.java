package todoapp;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class HeaderRenderer implements TableCellRenderer {
	
	private DefaultTableCellRenderer renderer;
	
	public HeaderRenderer(JTable table) {
		renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return renderer.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
	}
}
