package projectcharter;
//Contribute by Chan Chee Keen
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.List;
import java.util.*;

public class Module4Table extends JFrame implements ActionListener, MouseListener
{
    private String Filename=new String();
	private Container C;
    private Module4FileInOut F=new Module4FileInOut();
    private DecimalFormat DF2=new DecimalFormat("0.00");
    private JPanel PA=new JPanel();
    private JPanel PTA=new JPanel();
    private List<JPanel> LPA=new ArrayList();
    private JPanel PC=new JPanel();
    private JPanel PTC=new JPanel();
    private JPanel PTTC=new JPanel();
    private List<JPanel> LPTC=new ArrayList();
    private JPanel PCost=new JPanel();
    private List<JPanel> LPCost=new ArrayList();
    private JPanel PMonth=new JPanel();
    private List<JPanel> LPMonth=new ArrayList();
    private JPanel PAC=new JPanel();
    private List<JPanel> LPAC=new ArrayList();
    private JPanel PALL=new JPanel();
    private JTextField LActivity=new JTextField();
    private List<JTextField> LTActivity=new ArrayList();
    private JTextField TY=new JTextField();
    private List<JTextField> LY=new ArrayList();
    private JTextField[] TM=new JTextField[13];
    private List<JTextField[]> LM=new ArrayList();
    private JTextField[] Cost=new JTextField[13];
    private List<JTextField[]> LCost=new ArrayList();
    private JTextField TCost=new JTextField();
    private List<JTextField> TTCost=new ArrayList();
    private Font fo;
    private final int widthA=400;
    private final int height=25;
    private final int widthC=100;
    private final int widthT=150;
    private Module4Project P;
    private String[][][] rowData=new String[F.maxYear][F.EList.size()][13];
    private JTextField[][] CostText=new JTextField[F.EList.size()][13];
    private List<JTextField[][]> LCostText=new ArrayList();
    private JTextField[] TotalCostActivityText=new JTextField[F.EList.size()+1];
    private JTextField[] TotalCostMonthText=new JTextField[13];
    private List<JTextField[]> LTotalCostMonthText=new ArrayList();
    private JPanel PTable=new JPanel();
    private JButton SAVE=new JButton("SAVE");
    private JPopupMenu Menu=new JPopupMenu();
    private JMenuItem addYear=new JMenuItem("Add Year");

    //function:the constructor is to create the main GUI for module4
    //input:all of activity, FileName
    //output:the result for cost budgeting
    public Module4Table(Module4Project P, String Filename)
    {      
        this.Filename=Filename;
    	this.P=P;
        C=this.getContentPane();
        PTA.setLayout(new BoxLayout(PTA,BoxLayout.Y_AXIS));
        PTTC.setLayout(new BoxLayout(PTTC,BoxLayout.Y_AXIS));
        PALL.setLayout(new BoxLayout(PALL,BoxLayout.Y_AXIS));
        
        Menu.add(addYear);
        
        this.add(Menu);
        
        PTable.add(PTA);
        PTable.add(PC);
        PTable.add(PTTC);

        PALL.add(PTable);
        PALL.add(SAVE);

        JScrollPane SPAll=new JScrollPane(PALL, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        C.add(SPAll);
        
        PALL.addMouseListener(this);
        addYear.addActionListener(this);
        SAVE.addActionListener(this);

        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.settingcalculationBudgetCost(P);

        pack();
    }
    
    //function:to set the GUI component according the number of activity and the number of year
    //input:all of activity
    //output:to display the activity and the cost
    public void settingcalculationBudgetCost(Module4Project P)
    {        
        for(int a=0;a<P.Activity.size();a++)
        {
            P.Activity.get(a).calculateTotalBudegetCostPerMonth();
        }
        
        F.DFS(P);
        
        LActivity=new JTextField();
        LActivity.setEditable(false);
        LActivity.setText("Activity");
        LActivity.setHorizontalAlignment(JTextField.CENTER);
        LActivity.setPreferredSize(new Dimension(widthA, height*2));
        fo=new Font(TCost.getFont().getFamily(), Font.PLAIN, TCost.getFont().getSize()*2);
        LActivity.setFont(fo);
        PA.add(LActivity);
        LPA.add(PA);
        PTA.add(PA);
        LTActivity.add(LActivity);
        for(int a=0;a<F.EList.size();a++)
        {
            LActivity=new JTextField();
            PA=new JPanel();
            LActivity.setEditable(false);
            LActivity.setText(F.EList.get(a).getActivityID().replace("*", "")+" "+F.EList.get(a).getActivityName());
            LActivity.setPreferredSize(new Dimension(widthA, height));
            PA.add(LActivity);
            LPA.add(PA);
            PTA.add(PA);
            LTActivity.add(LActivity);
        }
        LActivity=new JTextField();
        PA=new JPanel();
        LActivity.setEditable(false);
        LActivity.setText("Total");
        LActivity.setPreferredSize(new Dimension(widthA, height));
        PA.add(LActivity);
        LPA.add(PA);
        PTA.add(PA); 
        LTActivity.add(LActivity);
        
        for(int a=1;a<=F.maxYear;a++)
        {
            PCost=new JPanel();
            PCost.setLayout(new BoxLayout(PCost,BoxLayout.Y_AXIS));
            TY=new JTextField();
            TY.setHorizontalAlignment(JTextField.CENTER);
            TY.setText(Integer.toString(a));
            TY.setPreferredSize(new Dimension(widthC*12+widthT, height));
            TY.setEditable(false);
            LY.add(TY);
            PCost.add(TY);
            PMonth=new JPanel();
            for(int b=1;b<=12;b++)
            {
                TM[b-1]=new JTextField();
                TM[b-1].setText(Integer.toString(b));
                TM[b-1].setPreferredSize(new Dimension(widthC, height));
                TM[b-1].setHorizontalAlignment(JTextField.CENTER);
                TM[b-1].setEditable(false);
                PMonth.add(TM[b-1]);
            }
            TM[12]=new JTextField();
            TM[12].setText("Total Cost Per Year");
            TM[12].setPreferredSize(new Dimension(widthT, height));
            TM[12].setHorizontalAlignment(JTextField.CENTER);
            TM[12].setEditable(false);
            PMonth.add(TM[12]);
            LM.add(TM);
            LPMonth.add(PMonth);
            PCost.add(PMonth);
            LPCost.add(PCost);
            PC.add(PCost);
        }
        
        for(int a=0;a<F.maxYear;a++)
        {
            CostText=new JTextField[F.EList.size()][];
            for(int b=0;b<CostText.length;b++)
            {
                CostText[b]=new JTextField[13];
                for(int c=0;c<CostText[b].length;c++)
                {
                    CostText[b][c]=new JTextField(); 
                }
            }
            LCostText.add(CostText);
        }
            
        for(int a=0;a<F.EList.size();a++)
        {
            for(int b=0;b<F.EList.get(a).Year.size();b++)
            {
                PAC=new JPanel();
                for(int c=0;c<F.EList.get(a).Year.get(b).Month.size();c++)
                {
                    LCostText.get(b)[a][c].setText(DF2.format(F.EList.get(a).Year.get(b).Month.get(c).getBudgetCost()));
                    LCostText.get(b)[a][c].setPreferredSize(new Dimension(widthC, height));
                    LCostText.get(b)[a][c].setHorizontalAlignment(JTextField.RIGHT);
                    if(!F.EList.get(a).getActivityID().contains("*"))
                    {
                        LCostText.get(b)[a][c].setEditable(false);
                        fo=new Font(TCost.getFont().getFamily(), Font.BOLD, TCost.getFont().getSize());
                        LCostText.get(b)[a][c].setFont(fo);
                    }
                    else
                    {
                        LCostText.get(b)[a][c].addActionListener(this);
                        LCostText.get(b)[a][c].addMouseListener(this);
                    }
                    PAC.add(LCostText.get(b)[a][c]);
                }
                LCostText.get(b)[a][12].setText(DF2.format(F.EList.get(a).Year.get(b).calculateTotalBudgetCost()));
                LCostText.get(b)[a][12].setPreferredSize(new Dimension(widthT, height));
                LCostText.get(b)[a][12].setEditable(false);
                LCostText.get(b)[a][12].setHorizontalAlignment(JTextField.RIGHT);
                fo=new Font(TCost.getFont().getFamily(), Font.BOLD, TCost.getFont().getSize());
                LCostText.get(b)[a][12].setFont(fo);
                PAC.add(LCostText.get(b)[a][12]);
                LPAC.add(PAC);
                LCost.add(Cost);
                LPCost.get(b).add(PAC);
            }
        }
        
        for(int a=0;a<F.maxYear;a++)
        {
            TotalCostMonthText=new JTextField[13];
            for(int b=0;b<TotalCostMonthText.length;b++)
                TotalCostMonthText[b]=new JTextField();
            LTotalCostMonthText.add(TotalCostMonthText);
        }
        
        for(int a=0;a<P.TotalCostPerYear.size();a++)
        {
            PAC=new JPanel();
            for(int b=0;b<P.TotalCostPerYear.get(a).TotalCostPerMonth.size();b++)
            {
                LTotalCostMonthText.get(a)[b].setText(DF2.format(P.TotalCostPerYear.get(a).TotalCostPerMonth.get(b).calculateTotalBudgetCost(P, a+1)));
                LTotalCostMonthText.get(a)[b].setPreferredSize(new Dimension(widthC, height));
                LTotalCostMonthText.get(a)[b].setHorizontalAlignment(JTextField.RIGHT);
                LTotalCostMonthText.get(a)[b].setEditable(false);
                fo=new Font(TCost.getFont().getFamily(), Font.BOLD, TCost.getFont().getSize());
                LTotalCostMonthText.get(a)[b].setFont(fo);
                PAC.add(LTotalCostMonthText.get(a)[b]);
            }
            LTotalCostMonthText.get(a)[12].setText(DF2.format(P.TotalCostPerYear.get(a).calculateTotalBudgetCost(P)));
            LTotalCostMonthText.get(a)[12].setPreferredSize(new Dimension(widthT, height));
            LTotalCostMonthText.get(a)[12].setEditable(false);
            LTotalCostMonthText.get(a)[12].setHorizontalAlignment(JTextField.RIGHT);
            fo=new Font(TCost.getFont().getFamily(), Font.BOLD, TCost.getFont().getSize());
            LTotalCostMonthText.get(a)[12].setFont(fo);
            PAC.add(LTotalCostMonthText.get(a)[12]);
            LPAC.add(PAC);
            LCost.add(Cost);
            LPCost.get(a).add(PAC);
        }
        
        PTC=new JPanel();
        TCost=new JTextField();
        TCost.setText("Total");
        TCost.setEditable(false);
        TCost.setHorizontalAlignment(JTextField.CENTER);
        TCost.setPreferredSize(new Dimension(widthC, height*2));
        fo=new Font(TCost.getFont().getFamily(), Font.PLAIN, TCost.getFont().getSize()*2);
        TCost.setFont(fo);
        PTC.add(TCost);
        TTCost.add(TCost);
        PTTC.add(PTC);        
        for(int a=0;a<F.EList.size();a++)
        {
            PTC=new JPanel();
            TotalCostActivityText[a]=new JTextField();
            TotalCostActivityText[a].setText(DF2.format(F.EList.get(a).calculateTotalBudgetCost()));
            TotalCostActivityText[a].setPreferredSize(new Dimension(widthC, height));
            TotalCostActivityText[a].setEditable(false);
            TotalCostActivityText[a].setHorizontalAlignment(JTextField.RIGHT);
            fo=new Font(CostText[0][0].getFont().getFamily(), Font.BOLD, CostText[0][0].getFont().getSize());
            TotalCostActivityText[a].setFont(fo);
            PTC.add(TotalCostActivityText[a]);
            TTCost.add(TotalCostActivityText[a]);
            PTTC.add(PTC);
        }
        PTC=new JPanel();
        TotalCostActivityText[F.EList.size()]=new JTextField();
        TotalCostActivityText[F.EList.size()].setText(DF2.format(P.calculateTotalBudgetCost()));
        TotalCostActivityText[F.EList.size()].setPreferredSize(new Dimension(widthC, height));
        TotalCostActivityText[F.EList.size()].setEditable(false);
        TotalCostActivityText[F.EList.size()].setHorizontalAlignment(JTextField.RIGHT);
        fo=new Font(CostText[0][0].getFont().getFamily(), Font.BOLD, CostText[0][0].getFont().getSize());
        TotalCostActivityText[F.EList.size()].setFont(fo);
        PTC.add(TotalCostActivityText[F.EList.size()]);
        TTCost.add(TotalCostActivityText[F.EList.size()]);
        PTTC.add(PTC);
    }

    //function:to perform the action perform for GUI
    //input:button click, mouse right click
    //output:update the data
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==SAVE)
        {
            try
            {
                F.fileout(P, Filename);
                JOptionPane.showMessageDialog(null, "The file is saved");
                this.setVisible(false);
            }
            catch(Exception ex)
            {
                
            }
        }
        
        else if(e.getSource()==addYear)
        {
            Module4ActivityYear Y;
            Module4ActivityMonth M;
            Module4TotalCostPerYear TCY=new Module4TotalCostPerYear();
            TCY.setYear(F.maxYear+1);
            Module4TotalCostPerMonth TCM;
            for(int a=0;a<12;a++)
            {
                TCM=new Module4TotalCostPerMonth();
                TCM.setMonth(a+1);
                TCY.TotalCostPerMonth.add(TCM);
            }
            P.TotalCostPerYear.add(TCY);
            
            for(int a=0;a<F.EList.size();a++)
            {
                Y=new Module4ActivityYear();
                Y.setYear(F.maxYear+1);
                F.EList.get(a).Year.add(Y);
                for(int b=0;b<12;b++)
                {
                    M=new Module4ActivityMonth();
                    M.setMonth(b+1);
                    F.EList.get(a).Year.get(Y.getYear()-1).Month.add(M);
                }
            }
            
                PCost=new JPanel();
                PCost.setLayout(new BoxLayout(PCost,BoxLayout.Y_AXIS));
                TY=new JTextField();
                TY.setHorizontalAlignment(JTextField.CENTER);
                TY.setText(Integer.toString(F.maxYear+1));
                TY.setPreferredSize(new Dimension(widthC*12+widthT, height));
                TY.setEditable(false);
                LY.add(TY);
                PCost.add(TY);
                PMonth=new JPanel();
                for(int b=1;b<=12;b++)
                {
                    TM[b-1]=new JTextField();
                    TM[b-1].setText(Integer.toString(b));
                    TM[b-1].setPreferredSize(new Dimension(widthC, height));
                    TM[b-1].setHorizontalAlignment(JTextField.CENTER);
                    TM[b-1].setEditable(false);
                    PMonth.add(TM[b-1]);
                }
                TM[12]=new JTextField();
                TM[12].setText("Total Cost Per Year");
                TM[12].setPreferredSize(new Dimension(widthT, height));
                TM[12].setHorizontalAlignment(JTextField.CENTER);
                TM[12].setEditable(false);
                PMonth.add(TM[12]);
                LM.add(TM);
                LPMonth.add(PMonth);
                PCost.add(PMonth);
                LPCost.add(PCost);
                PC.add(PCost);
            
            
            CostText=new JTextField[F.EList.size()][];
            for(int b=0;b<CostText.length;b++)
            {
                CostText[b]=new JTextField[13];
                for(int c=0;c<CostText[b].length;c++)
                {
                    CostText[b][c]=new JTextField(); 
                }
            }
            LCostText.add(CostText);
            
            for(int a=0;a<F.EList.size();a++)
            {
                    PAC=new JPanel();
                    for(int c=0;c<F.EList.get(a).Year.get(F.maxYear).Month.size();c++)
                    {
                        LCostText.get(F.maxYear)[a][c].setText(DF2.format(F.EList.get(a).Year.get(F.maxYear).Month.get(c).getBudgetCost()));
                        LCostText.get(F.maxYear)[a][c].setPreferredSize(new Dimension(widthC, height));
                        LCostText.get(F.maxYear)[a][c].setHorizontalAlignment(JTextField.RIGHT);
                        if(!F.EList.get(a).getActivityID().contains("*"))
                        {
                            LCostText.get(F.maxYear)[a][c].setEditable(false);
                            fo=new Font(CostText[0][0].getFont().getFamily(), Font.BOLD, CostText[0][0].getFont().getSize());
                            LCostText.get(F.maxYear)[a][c].setFont(fo);
                        }
                        else
                        {
                            LCostText.get(F.maxYear)[a][c].addActionListener(this);
                            LCostText.get(F.maxYear)[a][c].addMouseListener(this);
                        }
                        PAC.add(LCostText.get(F.maxYear)[a][c]);
                    }
                    LCostText.get(F.maxYear)[a][12].setText(DF2.format(F.EList.get(a).Year.get(F.maxYear).calculateTotalBudgetCost()));
                    LCostText.get(F.maxYear)[a][12].setPreferredSize(new Dimension(widthT, height));
                    LCostText.get(F.maxYear)[a][12].setEditable(false);
                    LCostText.get(F.maxYear)[a][12].setHorizontalAlignment(JTextField.RIGHT);
                    fo=new Font(CostText[0][0].getFont().getFamily(), Font.BOLD, CostText[0][0].getFont().getSize());
                    LCostText.get(F.maxYear)[a][12].setFont(fo);
                    PAC.add(LCostText.get(F.maxYear)[a][12]);
                    LPAC.add(PAC);
                    LCost.add(Cost);
                    LPCost.get(F.maxYear).add(PAC);
            }
            TotalCostMonthText=new JTextField[13];
            for(int b=0;b<TotalCostMonthText.length;b++)
                TotalCostMonthText[b]=new JTextField();
            LTotalCostMonthText.add(TotalCostMonthText);
            
            for(int a=0;a<P.TotalCostPerYear.size();a++)
            {
                PAC=new JPanel();
                for(int b=0;b<P.TotalCostPerYear.get(a).TotalCostPerMonth.size();b++)
                {
                    LTotalCostMonthText.get(a)[b].setText(DF2.format(P.TotalCostPerYear.get(a).TotalCostPerMonth.get(b).calculateTotalBudgetCost(P, a+1)));
                    LTotalCostMonthText.get(a)[b].setPreferredSize(new Dimension(widthC, height));
                    LTotalCostMonthText.get(a)[b].setHorizontalAlignment(JTextField.RIGHT);
                    LTotalCostMonthText.get(a)[b].setEditable(false);
                    fo=new Font(CostText[0][0].getFont().getFamily(), Font.BOLD, CostText[0][0].getFont().getSize());
                    LTotalCostMonthText.get(a)[b].setFont(fo);
                    PAC.add(LTotalCostMonthText.get(a)[b]);
                }
                LTotalCostMonthText.get(a)[12].setText(DF2.format(P.TotalCostPerYear.get(a).calculateTotalBudgetCost(P)));
                LTotalCostMonthText.get(a)[12].setPreferredSize(new Dimension(widthT, height));
                LTotalCostMonthText.get(a)[12].setEditable(false);
                LTotalCostMonthText.get(a)[12].setHorizontalAlignment(JTextField.RIGHT);
                fo=new Font(CostText[0][0].getFont().getFamily(), Font.BOLD, CostText[0][0].getFont().getSize());
                LTotalCostMonthText.get(a)[12].setFont(fo);
                PAC.add(LTotalCostMonthText.get(a)[12]);
                LPAC.add(PAC);
                LCost.add(Cost);
                LPCost.get(a).add(PAC);
            }
            if(F.maxYear==1)
            {   
                for(int a=0;a<F.EList.size();a++)
                {
                    PTC=new JPanel();
                    TotalCostActivityText[a]=new JTextField();
                    TotalCostActivityText[a].setText(DF2.format(F.EList.get(a).calculateTotalBudgetCost()));
                    TotalCostActivityText[a].setPreferredSize(new Dimension(widthC, height));
                    TotalCostActivityText[a].setEditable(false);
                    TotalCostActivityText[a].setHorizontalAlignment(JTextField.RIGHT);
                    fo=new Font(CostText[0][0].getFont().getFamily(), Font.BOLD, CostText[0][0].getFont().getSize());
                    TotalCostActivityText[a].setFont(fo);
                    PTC.add(TotalCostActivityText[a]);
                    TTCost.add(TotalCostActivityText[a]);
                    PTTC.add(PTC);
                }
                PTC=new JPanel();
                TotalCostActivityText[F.EList.size()]=new JTextField();
                TotalCostActivityText[F.EList.size()].setText(DF2.format(P.calculateTotalBudgetCost()));
                TotalCostActivityText[F.EList.size()].setPreferredSize(new Dimension(widthC, height));
                TotalCostActivityText[F.EList.size()].setEditable(false);
                TotalCostActivityText[F.EList.size()].setHorizontalAlignment(JTextField.RIGHT);
                fo=new Font(CostText[0][0].getFont().getFamily(), Font.BOLD, CostText[0][0].getFont().getSize());
                TotalCostActivityText[F.EList.size()].setFont(fo);
                PTC.add(TotalCostActivityText[F.EList.size()]);
                TTCost.add(TotalCostActivityText[F.EList.size()]);
                PTTC.add(PTC);
            }
        
            Module4FileInOut.maxYear++;
        }
        
        else
        {
            for(int a=0;a<LCostText.size();a++)
            {
                for(int b=0;b<LCostText.get(a).length;b++)
                {
                    for(int c=0;c<LCostText.get(a)[b].length;c++)
                    {
                        if(F.EList.get(b).getActivityID().contains("*") && 
                                F.EList.get(b).getActivityID().replace("*", "").equals(LTActivity.get(b+1).getText().toString().split(" ")[0]) &&
                                e.getSource()==LCostText.get(a)[b][c])
                        {
                            F.EList.get(b).Year.get(a).Month.get(c).setBudgetCost(Double.parseDouble(LCostText.get(a)[b][c].getText()));
                        }
                    }
                }
            }
            F.EListTOProject(P);
            this.storetotable(P);
        }
    }
    
  	//function:the store the data into table
    //input:all of activity, cost
    //output:the data has been updated
    public void storetotable(Module4Project P)
    {                
        F.DFS(P);          
        
        for(int a=0;a<P.Activity.size();a++)
        {
            P.Activity.get(a).calculateTotalBudegetCostPerMonth();
        }
        
        for(int a=0;a<F.EList.size();a++)
        {
            for(int b=0;b<F.EList.get(a).Year.size();b++)
            {
                for(int c=0;c<F.EList.get(a).Year.get(b).Month.size();c++)
                {
                    LCostText.get(b)[a][c].setText(DF2.format(F.EList.get(a).Year.get(b).Month.get(c).getBudgetCost()));
                }
                LCostText.get(b)[a][12].setText(DF2.format(F.EList.get(a).Year.get(b).calculateTotalBudgetCost()));
            }
        }
        
        for(int a=0;a<P.TotalCostPerYear.size();a++)
        {
            for(int b=0;b<P.TotalCostPerYear.get(a).TotalCostPerMonth.size();b++)
            {
                LTotalCostMonthText.get(a)[b].setText(DF2.format(P.TotalCostPerYear.get(a).TotalCostPerMonth.get(b).calculateTotalBudgetCost(P, a+1)));
            }
            LTotalCostMonthText.get(a)[12].setText(DF2.format(P.TotalCostPerYear.get(a).calculateTotalBudgetCost(P)));
        }
        
        for(int a=0;a<F.EList.size();a++)
        {
            TotalCostActivityText[a].setText(DF2.format(F.EList.get(a).calculateTotalBudgetCost()));
        }
        TotalCostActivityText[F.EList.size()].setText(DF2.format(P.calculateTotalBudgetCost()));
    }

    public void mouseClicked(MouseEvent e) 
    {
        if(e.getButton()==MouseEvent.BUTTON3 && e.getSource()==PALL)
            Menu.show(e.getComponent(), e.getX(), e.getY());
        else
        {
            for(int a=0;a<LCostText.size();a++)
            {
                for(int b=0;b<LCostText.get(a).length;b++)
                {
                    for(int c=0;c<LCostText.get(a)[b].length;c++)
                    {
                        if(e.getButton()==MouseEvent.BUTTON3 && e.getSource()==LCostText.get(a)[b][c])
                        {
                            Module4EVMResult EVM=new Module4EVMResult(a, b, c);
                            F.EListTOProject(P);
                            this.storetotable(P);
                        }
                    }
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) 
    {
        
    }

    public void mouseReleased(MouseEvent e) 
    {
        
    }

    public void mouseEntered(MouseEvent e) 
    {
        
    }

    public void mouseExited(MouseEvent e) 
    {
        
    }
}