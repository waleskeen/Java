package projectcharter;
//Contribute by Chan Chee Keen
import javax.swing.*;

public abstract class Module4Month 
{
    protected int Month=0;
    protected double BudgetCost=0;
    protected double PV=BudgetCost;
    protected double AC=0;
    protected double EV=0;
    
    public Module4Month()
    {
        BudgetCost=0;
        PV=BudgetCost;
        AC=0;
        EV=0;
    }
        
    public Module4Month(int Month)
    {
        this.Month=Month;
    }
    
    public Module4Month(int Month, double BudgetCost)
    {
        this.Month=Month;
        this.BudgetCost=BudgetCost;
        PV=BudgetCost;
    }
    
    public Module4Month(int Month, double BudgetCost, double AC)
    {
        this.Month=Month;
        this.BudgetCost=BudgetCost;
        PV=BudgetCost;
        this.AC=AC;
    }
    
    public int getMonth()
    {
    	return Month;
    }
    
    public void setMonth(int Month)
    {
    	this.Month=Month;
    }
    
    public double getBudgetCost()
    {
    	return Math.round(BudgetCost*100)/100;
    }
    
    public void setBudgetCost(double BudgetCost)
    {
    	if(BudgetCost>=0)
        {
            this.BudgetCost=BudgetCost;
            PV=BudgetCost;
        }
        else
            JOptionPane.showMessageDialog(null, "ERROR");
    }
    
    public double getAC()
    {
    	return Math.round(AC*100)/100;
    }
    
    public void setAC(double AC)
    {
    	if(AC>=0)
            this.AC=AC;
        else
        	JOptionPane.showMessageDialog(null, "ERROR");
    }
}