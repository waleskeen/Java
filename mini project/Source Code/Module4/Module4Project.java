package projectcharter;
//Contribute by Chan Chee Keen
import java.util.*;

public class Module4Project 
{
    private double TotalBudgetCost=0;
    public List<Module4Activity> Activity=new ArrayList();
    public List<Module4TotalCostPerYear> TotalCostPerYear=new ArrayList();
     
    //function:to calculate the total budget cost
    //input:total budget cost per year
    //output:total budget cost
    public double calculateTotalBudgetCost()
    {
        TotalBudgetCost=0;
        for(int a=0;a<Activity.size();a++)
        {
            TotalBudgetCost=TotalBudgetCost+Activity.get(a).calculateTotalBudgetCost();
        }
        return Math.round(TotalBudgetCost*100)/100;
    }
}