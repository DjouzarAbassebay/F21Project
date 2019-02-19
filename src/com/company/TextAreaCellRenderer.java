package com.company;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TextAreaCellRenderer extends JTextArea
        implements TableCellRenderer {

    // TextAreaCellRenderer Constructor
    public TextAreaCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        // Set value as text in the cell
        setText((String) value);

        // If the row is selected
        // Change the color background to the same of the row selection color
        if(isSelected){
            setBackground(table.getSelectionBackground());
        } else {
            // Otherwise get back the color background (white) when nothing is selected
            setBackground(table.getBackground());
        }

        return this;
    }
}
