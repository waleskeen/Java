package projectcharter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static projectcharter.RadioButton.value;

public class RadioButton2 extends JPanel
implements ActionListener {
    static String average = "Average";
    static String max = "Max";
    static String min = "Min";
    static String montecarlo = "Monte Carlo";
    static String value="Time";
    JTextField showtimetext;
    JTextField showcosttext;
    // JTextField showtext;
    JTextField textfield;
    JTextField textfield2;
   public double showValue;
    double showtime;
    
    double cost;
    double time;
    
    ThreePointEstimates costList ;
    ThreePointEstimates timeList;
    ActivityList tempList;
    double costValue;
    Double timeValue;
    String durationType;
   
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Initialize radioButton for time
 * Input: 
 * - time value from other modules
 * - list for time, ThreePointEstimates timeTemp and cost, ThreePointEstimates costTemp
 * - textfield for getting percentage of Time to store, JTextField percentageCost
 * - textfield for showing the time to store, JTextField showTime
 * - activitylist, list
 * - selection for type of duration
 * Output: none
 */
    
    public RadioButton2( Double valueOfValue2,
            JTextField percentageCost,
            JTextField showTime,
            ThreePointEstimates timeTemp,
            ThreePointEstimates costTemp,
            ActivityList list,
            int select
            ) {
        super(new BorderLayout());
        textfield2=percentageCost;
        showtimetext=showTime;
        timeValue=valueOfValue2;
        timeList=timeTemp;
        costList=costTemp;
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
        
         switch (select)
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
    }
   
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: action performed after selecting a radiobutton
 * Input: user selects a radiobutton
 * Output: none
 */   
    public void actionPerformed(ActionEvent e) {
        DecimalFormat numberFormat= new DecimalFormat("##.#");
        time = Double.parseDouble(textfield2.getText());
        
        showtime= timeList.generatePercentageToStore(time);
        String temp=e.getActionCommand();
        
        if (temp.equals(average))
        {
            showValue=(showtime+ timeValue )/2;
        }
        else if(temp.equals("Min"))
        {
            showValue=Math.min(timeValue, showtime);
        }
        else if (temp.equals("Max"))
        {
            showValue=Math.max(timeValue, showtime);
        }
        else if (temp.equals("Monte Carlo"))
        {
            showValue=showtime;
        }
        else if (temp.equals("Time"))
        {
            showValue=timeValue;
        }
        else
            System.out.println("RadioButton error");
        
        showtimetext.setText(String.valueOf(numberFormat.format(showValue)+ durationType));
        showtimetext.setEditable(false);
        
        timeList.setValue(showValue);
       
        try {
            tempList.editFile(costList, timeList);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JTableMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    