package projectcharter;
//Contribute by Chin Kian Zhong
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import static projectcharter.PERT.Critical;
import javax.swing.*;
import javax.swing.table.*;
public class third extends JFrame {
    
    JLabel PossiblePath = new JLabel("Possible Path");
    JLabel CriticalPath = new JLabel("Critical Path");
    JLabel Duration = new JLabel("Duration of Critical Path");
    JLabel Paths = new JLabel("Paths");
    JLabel EarliestEventTime = new JLabel("Earliest Event Time ");
    JLabel LatestEventTime = new JLabel("Latest Event Time");
    JLabel TotalSlack = new JLabel("Total Slack");
    JLabel FreeSlack = new JLabel("Free Slack");
    JLabel Activities= new JLabel("Activities");
    JLabel Pert= new JLabel("Duration");
    
    JTextField PossiblePathTField;
    JTextField CriticalPathTField;
    JTextField DurationTField;
    JTextField PathsTField;
    JTextField EarliestEventTimeTField;
    JTextField LatestEventTimeTField;
    JTextField TotalSlackTField;
    JTextField FreeSlackTField;
    JTextField ActivitiesTField;
    JTextField PertTField;
    
    JPanel P1 = new JPanel();
    JPanel P2 = new JPanel();
    JPanel P3 = new JPanel();
    JPanel P4 = new JPanel();
    JPanel P5 = new JPanel();
    JPanel P6 = new JPanel();
    JPanel P7 = new JPanel();
    JPanel P8 = new JPanel();
    
    Container C;
    
	//function:the constructor is to create the component of GUI
    //input:component of GUI
    //output:the GUI is created
    public third()
    {
        C = this.getContentPane();
        C.setLayout(new FlowLayout(10));
        
        P1.setLayout(new BoxLayout(P1, BoxLayout.Y_AXIS));
        P2.setLayout(new BoxLayout(P2, BoxLayout.Y_AXIS));
        P3.setLayout(new BoxLayout(P3, BoxLayout.Y_AXIS));
        P4.setLayout(new BoxLayout(P4, BoxLayout.Y_AXIS));
        P5.setLayout(new BoxLayout(P5, BoxLayout.Y_AXIS));
        P6.setLayout(new BoxLayout(P6, BoxLayout.Y_AXIS));
        P7.setLayout(new BoxLayout(P7, BoxLayout.Y_AXIS));
        P8.setLayout(new BoxLayout(P8, BoxLayout.Y_AXIS));
        
        P1.add(PossiblePath);
        P2.add(Paths);
        P3.add(EarliestEventTime);
        P4.add(LatestEventTime);
        P5.add(TotalSlack);
        P6.add(FreeSlack);
        P7.add(Activities);
        P8.add(Pert);
        /////P1///////////
        for(int a=0;a<PERT.Possible_Path_list.size();a++)
        {
            PossiblePathTField = new JTextField();
            PossiblePathTField.setText(PERT.Possible_Path_list.get(a).replaceAll("#", "-->"));
            PossiblePathTField.setEditable(false);
            P1.add(PossiblePathTField);
        }
        
        P1.add(CriticalPath);
        String[] token = Critical.split(" ");        
        int cnt= token.length;
        for(int a=0;a<cnt;a++)
        {
            CriticalPathTField = new JTextField();
            CriticalPathTField.setText(PERT.Possible_Path_list.get(Integer.parseInt(token[a])).replaceAll("#", "-->"));
            CriticalPathTField.setEditable(false);
            P1.add(CriticalPathTField);
        }
        
        P1.add(Duration);
        DurationTField=new JTextField();
        DurationTField.setText(PERT.max+"");
        DurationTField.setEditable(false);
        P1.add(DurationTField);
        
        ////////P2/////////////
        P2.add(Paths);
        for(int a=0;a<PERT.Path_list.size();a++)
        {
            PathsTField = new JTextField();
            PathsTField.setText(PERT.Path_list.get(a).replaceAll("#", "-->"));
            PathsTField.setEditable(false);
            P2.add(PathsTField);
        }
        ////////P3////////////
        P3.add(EarliestEventTime);
        for(int a=0;a<PERT.Earliest.size();a++)
        {
            EarliestEventTimeTField = new JTextField();
            EarliestEventTimeTField.setText(PERT.Earliest.get(a)+"");
            EarliestEventTimeTField.setEditable(false);
            P3.add(EarliestEventTimeTField);
        }
        /////////P4//////////////
        P4.add(LatestEventTime);
        for(int a=0;a<PERT.Earliest.size();a++)
        {
            LatestEventTimeTField = new JTextField();
            LatestEventTimeTField.setText(PERT.Latest.get(a)+"");
            LatestEventTimeTField.setEditable(false);
            P4.add(LatestEventTimeTField);
        }    
        ///////P5//////////////
        P5.add(TotalSlack);
        for(int a=0;a<PERT.TotalSlack.size();a++)
        {
            TotalSlackTField = new JTextField();
            TotalSlackTField.setText(PERT.TotalSlack.get(a)+"");
            TotalSlackTField.setEditable(false);
            P5.add(TotalSlackTField);
        }   
        ///////P6//////////////
        P6.add(FreeSlack);
        for(int a=0;a<PERT.FreeSlack.size();a++)
        {
            FreeSlackTField = new JTextField();
            FreeSlackTField.setText(PERT.FreeSlack.get(a)+"");
            FreeSlackTField.setEditable(false);
            P6.add(FreeSlackTField);
        }   
         ///////P7//////////////
        
        for(int a=0;a<PERT.A_list.size();a++)
        {
            ActivitiesTField = new JTextField();
            ActivitiesTField.setText(PERT.A_list.get(a));
            ActivitiesTField.setEditable(false);
            P7.add(ActivitiesTField);
        }   
        ///////P8//////////////
        for(int a=0;a<first.P_list.size();a++)
        {
            PertTField = new JTextField();
            PertTField.setText(first.P_list.get(a).Pert+"");
            PertTField.setEditable(false);
            P8.add(PertTField);
        }   
        
        
        C.add(P1);
       
        C.add(P7);
        C.add(P3);
        C.add(P4);
        
        C.add(P2);
        C.add(P8);
        C.add(P5);
        C.add(P6);
       
        this.setVisible(true);

        pack();
    }

    
}
