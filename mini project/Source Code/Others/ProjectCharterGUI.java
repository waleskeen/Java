package projectcharter;
//Contribute by Chan Chee Keen
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProjectCharterGUI extends JFrame implements ActionListener
{
    private String FileName=new String();
	private Container C;
    private JPanel P=new JPanel();
    private JButton Module1B=new JButton("Project Selection");
    private JButton Module2B=new JButton("WBS");
    private JButton Module3B=new JButton("PERT");
    private JButton Module4B=new JButton("Cost Budgeting");
    private JButton Module5B=new JButton("Monte Carlo");
    private JButton Module6B=new JButton("Print Project Charter");

    //function:the constructor is to put all the component into GUI
    //input:component of GUI
    //output:display all component of GUI
    public ProjectCharterGUI()
    {
        C=this.getContentPane();
        P.setLayout(new GridLayout(6,1));
        
        P.add(Module1B);
        P.add(Module2B);
        P.add(Module3B);
        P.add(Module4B);
        P.add(Module5B);
        P.add(Module6B);
        
        Module1B.addActionListener(this);
        Module2B.addActionListener(this);
        Module3B.addActionListener(this);
        Module4B.addActionListener(this);
        Module5B.addActionListener(this);
        Module6B.addActionListener(this);
        
        C.add(P);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        pack();
    }

    //function:to perform the action perform for GUI
    //input:action perform
    //output:to pass to another jframe
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==Module1B)
        {
            Module1 M1=new Module1();
            M1.main1();
        }
        else if(e.getSource()==Module2B)
        {
            CreateAndShowUI M2=new CreateAndShowUI();
            M2.main2();
        }
        else if(e.getSource()==Module3B)
        {
            first M3=new first();
        }
        else if(e.getSource()==Module4B)
        {
            FileName=JOptionPane.showInputDialog("Please type the file that you want to input");
        	Module4 M4=new Module4 ();
            M4.Module4(FileName);
        }
        else if(e.getSource()==Module5B)
        {
            try
            {
                JTableMain M5=new JTableMain();
                M5.main5();
            }
            catch(Exception ex)
            {
                
            }
        }
        else if(e.getSource()==Module6B)
        {
            try
            {
            	FileName=JOptionPane.showInputDialog("Please type the file that you want to input");
            	finalOutFile FOF=new finalOutFile();
                FOF.FOF(FileName);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        }
    }
    
    //function:to execute the main function for project charter
    //input:-
    //output:to display the selection of the project charter
    public static void main(String[] args)
    {
        ProjectCharterGUI GUI=new ProjectCharterGUI();
    }
}