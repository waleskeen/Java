package projectcharter;
//Contribute by Chan Chee Keen
import java.util.*;
import java.text.*;

public class Module4ActivityYear extends Module4Year implements Module4calculation
{
    public List<Module4ActivityMonth> Month=new ArrayList();
    
    public Module4ActivityYear()
    {
        super();
        Month=new ArrayList();
    }
    
    //function:to calculate the total budget cost per year per activity
    //input:all of total budget cost per month
    //output:total budget cost per year
    public double calculateTotalBudgetCost()
    {
        TotalBudgetCostPerYear=0;
        for(int a=0;a<Month.size();a++)
        {
            TotalBudgetCostPerYear=TotalBudgetCostPerYear+Month.get(a).getBudgetCost();
        }
        return Math.round(TotalBudgetCostPerYear*100)/100;
    }
}
