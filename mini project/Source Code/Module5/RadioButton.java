/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcharter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class RadioButton extends JPanel
implements ActionListener {
    static String average = "Average";
    static String max = "Max";
    static String min = "Min";
    static String montecarlo = "Monte Carlo";
    static String value="Cost";
    JTextField showtimetext;
    JTextField showcosttext;
    JTextField textfield;
    double showValue;
    double showtime;
    double showcost;
    
    double cost;
    double time;
    ThreePointEstimates costList ;
    ThreePointEstimates timeList;
    ActivityList tempList;
    Double costValue;
    double timeValue;
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: Initialize radioButton for cost
     * Input:
     * - cost value from other modules
     * - list for cost, ThreePointEstimates costTemp and time, ThreePointEstimates costTemp
     * - activitylist list
     * - textfield for getting percentage of Cost to store, JTextField percentageCost
     * - textfield for showing the cost to store, JTextField showCost
     * Output: none
     */
    
    public RadioButton(Double valueOfValue,
            JTextField percentageCost,
            JTextField showCost,
            ThreePointEstimates timeTemp,
            ThreePointEstimates costTemp,
            ActivityList list
            ) {
        super(new BorderLayout());
        textfield= percentageCost;
        showcosttext=showCost;
        costValue=valueOfValue;
        costList=costTemp;
         timeList=timeTemp;
        tempList=list;
        
        JRadioButton averageButton = new JRadioButton(average);
        averageButton.setActionCommand(average);
        
        JRadioButton maxButton = new JRadioButton(max);
        maxButton.setActionCommand(max);
        
        JRadioButton minButton = new JRadioButton(min);
        minButton.setActionCommand(min);
        
        JRadioButton monteCarloButton = new JRadioButton(montecarlo);
        monteCarloButton.setActionCommand(montecarlo);
        
        JRadioButton valueButton = new JRadioButton(value);
        valueButton.setActionCommand(value);
        
        ButtonGroup group = new ButtonGroup();
        group.add(averageButton);
        group.add(maxButton);
        group.add(minButton);
        group.add(monteCarloButton);
        group.add(valueButton);
        
        averageButton.addActionListener(this);
        maxButton.addActionListener(this);
        minButton.addActionListener(this);
        monteCarloButton.addActionListener(this);
        valueButton.addActionListener(this);
        
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(averageButton);
        radioPanel.add(maxButton);
        radioPanel.add(minButton);
        radioPanel.add(monteCarloButton);
        radioPanel.add(valueButton);
        
        add(radioPanel, BorderLayout.LINE_START);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: action performed after selecting a radiobutton
     * Input: user selects a radiobutton
     * Output: none
     */
    public void actionPerformed(ActionEvent e) {
        DecimalFormat numberFormat= new DecimalFormat("##.#");
        cost = Double.parseDouble(textfield.getText());
        showcost= costList.generatePercentageToStore(cost);
        
        if (e.getActionCommand().equals("Average"))
        {
            showValue=(showcost+ costValue )/2;
        }
        else if(e.getActionCommand().equals("Min"))
        {
            showValue=Math.min(costValue, showcost);
        }
        else if (e.getActionCommand().equals("Max"))
        {
            showValue=Math.max(costValue, showcost);
        }
        else if (e.getActionCommand().equals("Monte Carlo"))
        {
            showValue=showcost;
        }
        else if (e.getActionCommand().equals("Cost"))
        {
            showValue=costValue;
        }
        else
            System.out.println("RadioButton error");
        
        showcosttext.setText('$'+String.valueOf(numberFormat.format(showValue)));
        showcosttext.setEditable(false);
        
         
        costList.setValue(showValue);
       
        try {
            tempList.editFile(costList, timeList);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JTableMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


