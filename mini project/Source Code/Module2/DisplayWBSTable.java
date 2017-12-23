package projectcharter;
//Contribute By Foong Wai Lap
/**
 *
 * @author Foong Wai Lap
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

//To display the activity list
public class DisplayWBSTable extends JFrame
{
    DefaultTableModel DTM=new DefaultTableModel();
    JTable T=new JTable(DTM);
    Container C;
    JScrollPane P=new JScrollPane(T);
    
    //function:the constructor is to create the table that to display the activity list
    //input:list of activity
    //output:display the activity list
    public DisplayWBSTable(ArrayList<String> WBSList)
    {
        C=this.getContentPane();
        C.add(P);
        String[] ArrayWBS=new String[WBSList.size()];
        for(int a=0;a<WBSList.size();a++)
        {
            ArrayWBS[a]=WBSList.get(a).replace("*", "");
        }
        DTM.addColumn("Activity", ArrayWBS);
        this.setVisible(true);
        
        pack();
    }
}
