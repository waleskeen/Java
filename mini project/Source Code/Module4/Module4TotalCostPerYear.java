package projectcharter;
//Contribute by Chan Chee Keen
import java.util.*;

public class Module4TotalCostPerYear extends Module4Year
{
    public List<Module4TotalCostPerMonth> TotalCostPerMonth=new ArrayList();
    
    public Module4TotalCostPerYear()
    {
        super();
        TotalCostPerMonth=new ArrayList();
    }

    //function:to calculate the total budget cost per year for all activity
    //input:all of activity
    //output:the total budget cost per year for all activity
    public double calculateTotalBudgetCost(Module4Project P) 
    {
        TotalBudgetCostPerYear=0;
        for(int a=0;a<TotalCostPerMonth.size();a++)
        {
            TotalBudgetCostPerYear=TotalBudgetCostPerYear+TotalCostPerMonth.get(a).calculateTotalBudgetCost(P, Year);
        }
        return Math.round(TotalBudgetCostPerYear*100)/100;
    }
}