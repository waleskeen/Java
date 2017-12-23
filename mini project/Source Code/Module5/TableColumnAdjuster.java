/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcharter;

//Contributed by Brenda Lee Hooi Fern

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


//Class to manage the widths of colunmns in a table.

public class TableColumnAdjuster implements PropertyChangeListener, TableModelListener
{
    private JTable table;
    private int spacing;
    private boolean isColumnHeaderIncluded;
    private boolean isColumnDataIncluded;
    private boolean isOnlyAdjustLarger;
    private boolean isDynamicAdjustment;
    private Map<TableColumn, Integer> columnSizes = new HashMap<TableColumn, Integer>();
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Specify the table and use default spacing
 * Input: table to be adjusted
 * Output: none
 */
    public TableColumnAdjuster(JTable table)
    {
        this(table, 6);
    }
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Specify the table and use custom spacing
 * Input: table to be adjusted
 * Output: none
 */
    public TableColumnAdjuster(JTable table, int spacing)
    {
        this.table = table;
        this.spacing = spacing;
        setColumnHeaderIncluded( true );
        setColumnDataIncluded( true );
        setOnlyAdjustLarger( true );
        setDynamicAdjustment( false );
        installActions();
    }
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Adjust the widths of all the columns in the table
 * Input: none
 * Output: none
 */
    public void adjustColumns()
    {
        TableColumnModel tcm = table.getColumnModel();
        
        for (int i = 0; i < tcm.getColumnCount(); i++)
        {
            adjustColumn(i);
        }
    }
    
    
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Adjust the width of the specified column in the table
 * Input: column to adjust
 * Output: none
 */
    
    public void adjustColumn(final int column)
    {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        
        if (! tableColumn.getResizable()) return;
        
        int columnHeaderWidth = getColumnHeaderWidth( column );
        int columnDataWidth   = getColumnDataWidth( column );
        int preferredWidth    = Math.max(columnHeaderWidth, columnDataWidth);
        
        updateTableColumn(column, preferredWidth);
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Get the width based on the column name
 * Input: column to adjust to
 * Output: none
 */
    
    private int getColumnHeaderWidth(int column)
    {
        if (! isColumnHeaderIncluded) return 0;
        
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        Object value = tableColumn.getHeaderValue();
        TableCellRenderer renderer = tableColumn.getHeaderRenderer();
        
        if (renderer == null)
        {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        
        Component c = renderer.getTableCellRendererComponent(table, value, false, false, -1, column);
        return c.getPreferredSize().width;
    }
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Calculate the width based on the widest cell renderer for the given column.
 * Input: column to adjust to
 * Output: none
 */
  
    private int getColumnDataWidth(int column)
    {
        if (! isColumnDataIncluded) return 0;
        
        int preferredWidth = 0;
        int maxWidth = table.getColumnModel().getColumn(column).getMaxWidth();
        
        for (int row = 0; row < table.getRowCount(); row++)
        {
            preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, column));
            
            // exceeded the maximum width, no need to check other rows
            
            if (preferredWidth >= maxWidth)
                break;
        }
        
        return preferredWidth;
    }
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Get the preferred width for the specified cell
 * Input: cell to adjust to
 * Output: none
 */
    private int getCellDataWidth(int row, int column)
    {
        //  Invoke the renderer for the cell to calculate the preferred width
        
        TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
        Component c = table.prepareRenderer(cellRenderer, row, column);
        int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
        
        return width;
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Update the TableColumn with the newly calculated width
 * Input: cell to adjust to
 * Output: none
 */
    private void updateTableColumn(int column, int width)
    {
        final TableColumn tableColumn = table.getColumnModel().getColumn(column);
        
        if (! tableColumn.getResizable()) return;
        
        width += spacing;
        
        if (isOnlyAdjustLarger)
        {
            width = Math.max(width, tableColumn.getPreferredWidth());
        }
        
        columnSizes.put(tableColumn, new Integer(tableColumn.getWidth()));
        table.getTableHeader().setResizingColumn(tableColumn);
        tableColumn.setWidth(width);
    }
    
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Restore the widths of the columns in the table to its previous width
 * Input: none
 * Output: none
 */
    public void restoreColumns()
    {
        TableColumnModel tcm = table.getColumnModel();
        
        for (int i = 0; i < tcm.getColumnCount(); i++)
        {
            restoreColumn(i);
        }
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Restore the width of the specified column to its previous width
 * Input: column to restore
 * Output: none
 */
    private void restoreColumn(int column)
    {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        Integer width = columnSizes.get(tableColumn);
        
        if (width != null)
        {
            table.getTableHeader().setResizingColumn(tableColumn);
            tableColumn.setWidth( width.intValue() );
        }
    }
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Indicates whether to include the header in the width calculation
 * Input: is column header included (bool)
 * Output: none
 */ 
    public void setColumnHeaderIncluded(boolean isColumnHeaderIncluded)
    {
        this.isColumnHeaderIncluded = isColumnHeaderIncluded;
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Indicates whether to include the model data in the width calculation
 * Input: is column header included (bool)
 * Output: none
 */ 
    public void setColumnDataIncluded(boolean isColumnDataIncluded)
    {
        this.isColumnDataIncluded = isColumnDataIncluded;
    }
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Indicates whether columns can only be increased in size
 * Input: is column header included (bool)
 * Output: none
 */ 
    public void setOnlyAdjustLarger(boolean isOnlyAdjustLarger)
    {
        this.isOnlyAdjustLarger = isOnlyAdjustLarger;
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Indicate whether changes to the model should cause the width to be
    dynamically recalculated.
 * Input: if dynamic adjustment is selected
 * Output: none
 */ 
    public void setDynamicAdjustment(boolean isDynamicAdjustment)
    {
        //  May need to add or remove the TableModelListener when changed
        
        if (this.isDynamicAdjustment != isDynamicAdjustment)
        {
            if (isDynamicAdjustment)
            {
                table.addPropertyChangeListener( this );
                table.getModel().addTableModelListener( this );
            }
            else
            {
                table.removePropertyChangeListener( this );
                table.getModel().removeTableModelListener( this );
            }
        }
        
        this.isDynamicAdjustment = isDynamicAdjustment;
    }
    
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: change the table's property
 * Input: the event if the table's property changes
 * Output: none
 */ 
    public void propertyChange(PropertyChangeEvent e)
    {
        if ("model".equals(e.getPropertyName()))
        {
            TableModel model = (TableModel)e.getOldValue();
            model.removeTableModelListener( this );
            
            model = (TableModel)e.getNewValue();
            model.addTableModelListener( this );
            adjustColumns();
        }
    }
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: changes the table
 * Input: the event if the table changes
 * Output: none
 */ 
    public void tableChanged(TableModelEvent e)
    {
        if (! isColumnDataIncluded) return;
        
        if (e.getType() == TableModelEvent.UPDATE)
        {
            int column = table.convertColumnIndexToView(e.getColumn());
            
            if (isOnlyAdjustLarger)
            {
                int	row = e.getFirstRow();
                TableColumn tableColumn = table.getColumnModel().getColumn(column);
                
                if (tableColumn.getResizable())
                {
                    int width =	getCellDataWidth(row, column);
                    updateTableColumn(column, width);
                }
            }
            
            else
            {
                adjustColumn( column );
            }
        }
        
        else
        {
            adjustColumns();
        }
    }
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: implements the new actions
 * Input: none
 * Output: none
 */ 
    private void installActions()
    {
        installColumnAction(true,  true,  "adjustColumn",   "control ADD");
        installColumnAction(false, true,  "adjustColumns",  "control shift ADD");
        installColumnAction(true,  false, "restoreColumn",  "control SUBTRACT");
        installColumnAction(false, false, "restoreColumns", "control shift SUBTRACT");
        
        installToggleAction(true,  false, "toggleDynamic",  "control MULTIPLY");
        installToggleAction(false, true,  "toggleLarger",   "control DIVIDE");
    }
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: implements the new actions for the column
 * Input: if the column in selected and to be adjusted with the relative keys and keystrokes
 * Output: none
 */ 
    private void installColumnAction(
            boolean isSelectedColumn, boolean isAdjust, String key, String keyStroke)
    {
        Action action = new ColumnAction(isSelectedColumn, isAdjust);
        KeyStroke ks = KeyStroke.getKeyStroke( keyStroke );
        table.getInputMap().put(ks, key);
        table.getActionMap().put(key, action);
    }
    
    private void installToggleAction(
            boolean isToggleDynamic, boolean isToggleLarger, String key, String keyStroke)
    {
        Action action = new ToggleAction(isToggleDynamic, isToggleLarger);
        KeyStroke ks = KeyStroke.getKeyStroke( keyStroke );
        table.getInputMap().put(ks, key);
        table.getActionMap().put(key, action);
    }
    
    class ColumnAction extends AbstractAction
    {
        private boolean isSelectedColumn;
        private boolean isAdjust;
        
        public ColumnAction(boolean isSelectedColumn, boolean isAdjust)
        {
            this.isSelectedColumn = isSelectedColumn;
            this.isAdjust = isAdjust;
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (isSelectedColumn)
            {
                int[] columns = table.getSelectedColumns();
                
                for (int i = 0; i < columns.length; i++)
                {
                    if (isAdjust)
                        adjustColumn(columns[i]);
                    else
                        restoreColumn(columns[i]);
                }
            }
            else
            {
                if (isAdjust)
                    adjustColumns();
                else
                    restoreColumns();
            }
        }
    }
    
    
    class ToggleAction extends AbstractAction
    {
        private boolean isToggleDynamic;
        private boolean isToggleLarger;
        
        public ToggleAction(boolean isToggleDynamic, boolean isToggleLarger)
        {
            this.isToggleDynamic = isToggleDynamic;
            this.isToggleLarger = isToggleLarger;
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (isToggleDynamic)
            {
                setDynamicAdjustment(! isDynamicAdjustment);
                return;
            }
            
            if (isToggleLarger)
            {
                setOnlyAdjustLarger(! isOnlyAdjustLarger);
                return;
            }
        }
    }
}
