/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcharter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static projectcharter.JTableMain.list;
import org.jfree.ui.RefineryUtilities;

public class SaveFile extends JPanel
implements ActionListener {
    JButton button;
    int numofloops;
    //ActivityList list;
    ThreePointEstimates cost ;
    ThreePointEstimates time;
    //JFrame temp;
    Container temp;
    boolean flg;
    JTextField textfield;
    ActivityList list;
    
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: Initialize radioButton for cost
     * Input:
     * - list for cost, ThreePointEstimates newCost
     * - list for activities, ActivityList tempList
     * - list for time, ThreePointEstimates newTime
     * Output: none
     */
    public SaveFile(ThreePointEstimates newCost, ThreePointEstimates newTime,ActivityList tempList) {
        super(new BorderLayout());
        
        cost=newCost;
        time=newTime;
        list=tempList;
        
        button = new JButton("Save File");
        button.setPreferredSize(new Dimension(100, 20));
        add(button, BorderLayout.CENTER);
        // numofloops=num;
        button.addActionListener(this);
     
    }
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: saves current content in containers to file
     * Input: user clicks the "Save File Button"
     * Output: none
     */
    public void actionPerformed(ActionEvent e) {
        
        try {
            list.editFile(cost, time);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JTableMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
