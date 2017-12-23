package projectcharter;
//Contribute By Foong Wai Lap
/**
 *
 * @author Foong Wai Lap
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//To create the GUI to add the activitiy
public class CreateAndShowUI {

	//function:the constructor is to create the main GUI for WBS
	//input:table GUI
	//output:display the table GUI
 private static void createAndShowUI()
 {
      
         JPanel panel = new JPanel();
 
        WBS Workbreakdown = new WBS();
        JTabbedPane tab = new JTabbedPane();
        Container cont= new Container();
        JFrame frame = new JFrame("WBS");
        tab.add("Work Breakdown Structure", Workbreakdown);
        frame.add(tab, BorderLayout.CENTER);
        frame.setSize(800,500);
        frame.setVisible(true);
  }
 
 //function:the main function for module2
 //input:-
 //output:execute the module2
    public void main2() 
    {
               java.util.List<savestring> activity = new ArrayList();
        InfileOutfile I = new InfileOutfile();
        insertdata ID = new insertdata();
      
        try {
            I.InFile(activity);
        } catch (Exception e) {
          
        }
            ID.insert();
       java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() { 
            
            createAndShowUI();
        
  
         }
      });
WBS B= new WBS();
B.removemarker();
    }
 
}
