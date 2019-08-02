/**
 * Formato de talba
 */
package Herramientas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FromatoTablaLaboratorios extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        if (String.valueOf(table.getValueAt(row, 8)).equals("NO") && String.valueOf(table.getValueAt(row, 9)).equals("NO") && String.valueOf(table.getValueAt(row, 10)).equals("NO")) {
            if (String.valueOf(table.getValueAt(row, 6)).equals("null")) {
                setBackground(new Color(128, 128, 255));
            } else {
                Color rojo = new Color(244, 70, 74);
                setBackground(rojo);
            }
        } else {
            if (String.valueOf(table.getValueAt(row, 9)).equals("NO")) {
                setBackground(Color.YELLOW);
            } else {
                Color verde = new Color(0, 191, 0);
                //setBackground(Color.green);
                setBackground(verde);
            }
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
