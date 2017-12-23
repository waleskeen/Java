package projectcharter;
//Contribute by Chan Chee Keen
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;

public class Module4EVMResult extends JFrame implements ActionListener
{
    private Container C;
    private JPanel PALL=new JPanel();
    private JLabel LPV=new JLabel("PV: ");
    private JLabel LAC=new JLabel("AC: ");
    private JLabel LEV=new JLabel("EV: ");
    private JLabel LRP=new JLabel("Rate of Performance: ");
    private JLabel LCV=new JLabel("CV: ");
    private JLabel LSV=new JLabel("SV: ");
    private JLabel LCPI=new JLabel("CPI: ");
    private JLabel LSPI=new JLabel("SPI: ");
    private JTextField TPV=new JTextField();
    private JTextField TAC=new JTextField();
    private JTextField TEV=new JTextField();
    private JTextField TRP=new JTextField();
    private JTextField TCV=new JTextField();
    private JTextField TSV=new JTextField();
    private JTextField TCPI=new JTextField();
    private JTextField TSPI=new JTextField();
    private DecimalFormat DF2=new DecimalFormat("0.00");
    int Y;
    int A;
    int M;

    //function:the constructor to create the GUI for the EVM result
    //input:year, activity, month
    //output:all the EVM result
    public Module4EVMResult(int Y, int A, int M)
    {
        this.Y=Y;
        this.A=A;
        this.M=M;
        
        C=this.getContentPane();
        
        PALL.setLayout(new GridLayout(8,2));
        PALL.add(LPV);
        PALL.add(TPV);
        PALL.add(LAC);
        PALL.add(TAC);
        PALL.add(LRP);
        PALL.add(TRP);
        PALL.add(LEV);
        PALL.add(TEV);
        PALL.add(LCV);
        PALL.add(TCV);
        PALL.add(LSV);
        PALL.add(TSV);
        PALL.add(LCPI);
        PALL.add(TCPI);
        PALL.add(LSPI);
        PALL.add(TSPI);
        
        TEV.setEditable(false);
        TCV.setEditable(false);
        TSV.setEditable(false);
        TCPI.setEditable(false);
        TSPI.setEditable(false);
        
        TPV.addActionListener(this);
        TAC.addActionListener(this);
        TRP.addActionListener(this);
        
        C.add(PALL);
        
        calculation(Y, A, M);
        
        this.setVisible(true);
        
        pack();
    }
    
    //function:to calculate all the formula
    //input:year, activity, month
    //output:all the EVM result
    public void calculation(int Y, int A, int M)
    {
        TPV.setText(DF2.format(Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).getBudgetCost()));
        TAC.setText(DF2.format(Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).getAC()));
        TRP.setText(DF2.format(Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).getRP()));
        TEV.setText(DF2.format(Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).calculateEV()));
        TCV.setText(DF2.format(Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).calculateCV()));
        TSV.setText(DF2.format(Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).calculateSV()));
        TCPI.setText(DF2.format(Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).calculateCPI()));
        TSPI.setText(DF2.format(Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).calculateSPI()));
    }

    //function:to enter the textfield
    //input:-
    //output:update the result
    public void actionPerformed(ActionEvent e) 
    {
        Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).setBudgetCost(Double.parseDouble(TPV.getText()));
        Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).setAC(Double.parseDouble(TAC.getText()));
        Module4FileInOut.EList.get(A).Year.get(Y).Month.get(M).setRP(Double.parseDouble(TRP.getText()));
        this.calculation(Y, A, M);
    }
}