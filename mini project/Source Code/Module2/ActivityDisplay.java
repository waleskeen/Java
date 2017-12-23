package projectcharter;
//Contribute By Foong Wai Lap
/**
 *
 * @author Foong Wai Lap
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
 
////////////////////Display table column////////////////////////////
public class ActivityDisplay extends JPanel
{
   
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    private String[] columnNames = {"Activity No", "Activity Name"};
    private DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    JTable table = new JTable(model);

//function:the constructor to create the scrollpane
//input:table
//output:table with scrollpane
//////////////////////////////////////////////// 
public ActivityDisplay()
{
    JScrollPane scrollPane = new JScrollPane(table);
    panel.add(scrollPane);
    add(panel);
     
     
}

//function:to add the row into table
//input:activityNo and activityName
//output:display the activityNo and activityName in the table
public void addRow(String activityNo, String activityName)
{
    Object [] row = new Object [5];
    row[0] = activityNo;
    row[1] = activityName;
    
    model.addRow(row);
}

//function:to set the data from table
//input:data that need to set
//output:the original data has been changed
public void setData(Object obj, int row_index, int col_index)
{
    table.getModel().setValueAt(obj, row_index, col_index);
}    
        
 
}