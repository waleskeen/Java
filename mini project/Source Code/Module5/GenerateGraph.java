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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.ui.RefineryUtilities;


public class GenerateGraph extends JPanel
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
    
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: Initialize GenerateGraph
     * Input:
     *- textfield that have the number of loops, JTextField text
     * - list for cost, ThreePointEstimates newCost
     * - list for time, ThreePointEstimtates newTime
     * Output: none
     */
    public GenerateGraph(JTextField text, ThreePointEstimates newCost, ThreePointEstimates newTime) {
        super(new BorderLayout());
        textfield=text;
        cost=newCost;
        time=newTime;
        // temp=tmp;
        //flg=flag;
        
        button = new JButton("Generate");
        button.setPreferredSize(new Dimension(100, 20));
        add(button, BorderLayout.CENTER);
        // numofloops=num;
        button.addActionListener(this);
       
    }
    
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process:
     * - validate number of loops textfield
     * - create cost estimation and project duration graphs
     * Input: action event (click button)
     * Output: none
     */
    public void actionPerformed(ActionEvent e) {
        
        // numofloops=Integer.parseInt(textfield.getText());
        try {
            numofloops = Integer.parseInt(textfield.getText());
            textfield.requestFocusInWindow();
            
            
            
        } catch (Exception z) {
            JOptionPane.showMessageDialog(this, "Please enter integer values for number of loops",
                    "Error Message", JOptionPane.ERROR_MESSAGE);
            textfield.setText("");
            textfield.requestFocusInWindow();
            return;
        }
        
        if (numofloops<500)
        {
            JOptionPane.showMessageDialog(this, "Please enter a value more than 500",
                    "Error Message", JOptionPane.ERROR_MESSAGE);
            textfield.setText("");
            textfield.requestFocusInWindow();
            return;
        }
        
        cost.displayContents(numofloops);
        time.displayContents(numofloops);
        
        SimpleBar demo = new SimpleBar(cost.getCount(), "Cost Estimation Probability Distribution","Estimated Cost");
        demo.pack();
        demo.setVisible(true);
        SimpleBar demo2 = new SimpleBar(time.getCount(),"Project Duration Probability Distribution","Estimated Time");
        demo2.pack();
        RefineryUtilities.centerFrameOnScreen(demo2);
        demo2.setVisible(true);
    }
}
