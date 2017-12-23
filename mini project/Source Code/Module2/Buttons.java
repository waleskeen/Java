package projectcharter;
//Contribute By Foong Wai Lap
/**
 *
 * @author Foong Wai Lap
 */
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.*;
import java.awt.*;
import static projectcharter.InfileOutfile.activityListList;
import static projectcharter.InfileOutfile.allList;
 
 ////////////////////create add button, save button and display button GUI/////////////////////////
class Buttons extends JPanel
{
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    private JButton addButton = new JButton("Add");
    private JButton saveButton = new JButton("Save");
    private JButton displayButton = new JButton("Display");
   
  
 
   public Buttons()
    {  
               
        panel.setLayout(new FlowLayout());
        panel.add(addButton);
        panel.add(saveButton);
        panel.add(displayButton);
        add(panel);
         
         
    }
 /////////////////////////////create add button listener///////////////////////////////////
   //input:listener
   //output:the listener is added to button
     public void addInfoBtnAddActionListener(ActionListener listener)
    {
        addButton.addActionListener(listener);
           
    }
      /////////////////////////////create save button listener///////////////////////////////////
     //input:listener
     //output:the listener is added to button
     public void addInfoBtnSaveActionListener(ActionListener listener)
    {
        saveButton.addActionListener(listener);
           
    }
       /////////////////////////////create display button listener///////////////////////////////////
     //input:listener
     //output:the listener is added to button  
     public void addInfoBtnDisplayActionListener(ActionListener listener)
    {
        displayButton.addActionListener(listener);
           
    }

         
    }

//Create class to store data into table
 ////////////////////////////////////////////////////////////////
      class ActivityInformation extends JPanel
{
    
    JPanel panel = new JPanel();
    // Declare JLabels
    private JLabel label1 = new JLabel("Activity No");
    private JLabel label2 = new JLabel("Activity Name");
    public String ActivityID;
    
    // Declare JTextFields
    private JTextField text1 = new JTextField(10);
    private JTextField text2 = new JTextField(10);
  
     
     //function:the constructor is to create the GUI input
    //Input:-
    //output:Display the GUI input
    public ActivityInformation()
    { 

        panel.setLayout( new GridLayout(5, 2));
         
   
        panel.add(label1);
        panel.add(text1);
        panel.add(label2);
        panel.add(text2);
        
         
        add(panel);     
    
    }
     ////////////////////////////get data from ActivityNo/////////////////////////////////// 
    //function:to get the ActivityNo
    //input:textfield
    //output:the ActivityNo data
    public String getActivityNo()
    {
       
        return text1.getText();
       
    }
     ////////////////////////////get data from ActivityName///////////////////////////////////  
    //function:to get the ActivityName
    //input:textfield
    //output:the ActivityName data
    public String getActivityName()
    {
        return text2.getText();
        
    }
   
  }
     
//To execute the remove marker and add activity into list
class WBS extends JPanel
{ 
  static ArrayList<String> insert_list = new ArrayList<String>();
    Buttons buttons = new Buttons();
    ActivityInformation Activity = new ActivityInformation();
    ActivityDisplay display = new ActivityDisplay();
    
    //function:the constructor is to create the GUI button
    //input:-
    //output:Display the GUI button
    public WBS()
    {
        JPanel panel = new JPanel();       
        panel.setLayout(new BorderLayout());
        panel.add(buttons, BorderLayout.NORTH);
        panel.add(Activity, BorderLayout.CENTER);
        panel.add(display, BorderLayout.SOUTH);
         
        add(panel);
         
        //function:to perform the add button action listener
        //input:activityNo, activityName
        //output:the activity is added into table
        buttons.addInfoBtnAddActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String activityNo = Activity.getActivityNo();
                String activityName = Activity.getActivityName();
               
                insert_list.add("*"+activityNo+" "+activityName);
                display.addRow(activityNo, activityName);
               System.out.println(insert_list);
            }
            
            
        });
        
        //function:to perform the save button action listener
        //input:activityList
        //output:the file is saved
        buttons.addInfoBtnSaveActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                removemarker();
                try
                {
                InfileOutfile.OutFile(insert_list);
                }
                catch(Exception EX)
                {
                    
                }
            }
            
            
        });
        
        //function:to perform the display button action listener
        //input:-
        //output:the activity is displayed from table
        buttons.addInfoBtnDisplayActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DisplayWBSTable WBST=new DisplayWBSTable(insert_list);
            }
            
            
        });

    }    
    //function:to remove the unnecessary marker
    //input:activitylist
    //output:the unnecessary marker is removed
        public void removemarker() {        
        
            for (int b = 0; b < insert_list.size()-1; b++) {
            
                if (((insert_list.get(b).split(" ")[0].length()
                        - insert_list.get(b).split(" ")[0].replace(".", "").length())
                        - (insert_list.get(b+1).split(" ")[0].length()
                        - insert_list.get(b+1).split(" ")[0].replace(".", "").length()) == -1)
)
                {
                    insert_list.set(b, insert_list.get(b).replace("*", ""));
                }
                
               
        }
        }
 }