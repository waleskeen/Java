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
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Contributed by Brenda Lee Hooi Fern

public class StorePercentage extends JPanel
implements ActionListener {
    JButton button;
    double cost;
    double time;
    
    ThreePointEstimates costList ;
    ThreePointEstimates timeList;
    
    
    Container temp;
    boolean flg;
    JTextField textfield;
    JTextField textfield2;
    double showcost;
    double showtime;
    String durationType;
    
    JTextField showcosttext;
    JTextField showtimetext;
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: Initialize StorePercentage
     * Input:
     * - textfield to show cost and time values after calculation
     * - textfields to get the percentage to store
     * - list for time, ThreePointEstimtates timeTemp and cost, ThreePointEstimates costTemp
     * Output: none
     */
    
    public StorePercentage(JTextField text,
            JTextField text2,
            JTextField text3,
            JTextField text4,
            ThreePointEstimates costTemp,
            ThreePointEstimates timeTemp, int durType) {
        super(new BorderLayout());
        textfield= text;
        textfield2=text2;
        showcosttext=text3;
        showtimetext=text4;
        costList=costTemp;
        timeList=timeTemp;
        
        switch (durType)
        {
            case 1:
                durationType="days";
                break;
            case 2:
                durationType="hours";
                break;
            case 3:
                durationType="years";
                break;
            default:
                System.out.println("error in naming");
                break;
                
        }
        
        button = new JButton("Calculate Estimated Values");
        button.setPreferredSize(new Dimension(200, 20));
        add(button, BorderLayout.CENTER);
        button.addActionListener(this);
        
        
    }
    
    
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process:
     * - calculate and display value for storing based on the percentage given
     * - validation checking on percentage entered
     * Input: action event (click button)
     * Output: none
     */
    public void actionPerformed(ActionEvent e) {
        
        
        try {
            cost = Double.parseDouble(textfield.getText());
            textfield.requestFocusInWindow();
            
            time = Double.parseDouble(textfield2.getText());
            textfield2.requestFocusInWindow();
            
        } catch (Exception z) {
            JOptionPane.showMessageDialog(this, "Please enter integer values for storing percentage",
                    "Error Message", JOptionPane.ERROR_MESSAGE);
            textfield.setText("");
            textfield.requestFocusInWindow();
            
            textfield2.setText("");
            textfield2.requestFocusInWindow();
            return;
        }
        
        showcost= costList.generatePercentageToStore(cost);
        showtime= timeList.generatePercentageToStore(time);
        
        System.out.println(showcost);
        
        DecimalFormat numberFormat= new DecimalFormat("##.#");
        showcosttext.setText(String.valueOf('$'+numberFormat.format(showcost)));
        showtimetext.setText(String.valueOf(numberFormat.format(showtime))+ durationType);
        
        showcosttext.setEditable(false);
        showtimetext.setEditable(false);
        
    }
    
    
}
