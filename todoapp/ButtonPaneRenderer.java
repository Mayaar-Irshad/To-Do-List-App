package todoapp;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class ButtonPaneRenderer extends JPanel implements TableCellRenderer {
	
	private ButtonPane buttonPane;
	
	public ButtonPaneRenderer() {
		buttonPane = new ButtonPane();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus,
			int row, int column) {
		return buttonPane;
	}
}
