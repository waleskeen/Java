package projectcharter;
//Contribute by Chan Chee Keen
import java.util.*;

public class Module4Activity implements Module4calculation
{
    private String ActivityID;
    private String ActivityName;
    private double TotalBudgetCostPerActivity=0;
    public List<Module4ActivityYear> Year=new ArrayList();
    public List<Module4Activity> subActivity=new ArrayList();
    
    public String getActivityID()
    {
        return ActivityID;
    }
    
    public void setActivityID(String ActivityID)
    {
        this.ActivityID=ActivityID;
    }
    
    public String getActivityName()
    {
        return ActivityName;
    }
    
    public void setActivityName(String ActivityName)
    {
        this.ActivityName=ActivityName;
    }
    
    //function:to calculate the total budget cost/activity
    //input:subActivity/total budget cost per year
    //output:total budget cost per activity
    public double calculateTotalBudgetCost()
    {
        TotalBudgetCostPerActivity=0;
        if(!subActivity.isEmpty())
        {
            for(int a=0;a<subActivity.size();a++)
            {
                TotalBudgetCostPerActivity=TotalBudgetCostPerActivity+subActivity.get(a).calculateTotalBudgetCost();
            }
        }
        else
        {
            for(int a=0;a<Year.size();a++)
            {
                TotalBudgetCostPerActivity=TotalBudgetCostPerActivity+Year.get(a).calculateTotalBudgetCost();
            }
        }
        return Math.round(TotalBudgetCostPerActivity*100)/100;
    }
    
    //function:to calculate the total budget cost per month if they has sub activity
    //input:activity
    //output:total budget cost per month
    public void calculateTotalBudegetCostPerMonth()
    {
        if(!subActivity.isEmpty())
        {
            this.clearsubActivityBudgetCost();
            for(int a=0;a<subActivity.size();a++)
            {
                if(!subActivity.get(a).subActivity.isEmpty())
                {
                    subActivity.get(a).calculateTotalBudegetCostPerMonth();
                }
                for(int c=0;c<Year.size();c++)
                {                    
                    for(int d=0;d<Year.get(c).Month.size();d++)
                    {
                        Year.get(c).Month.get(d).setBudgetCost(Year.get(c).Month.get(d).getBudgetCost()
                                +subActivity.get(a).Year.get(c).Month.get(d).getBudgetCost());
                    }
                }
            }
        }
    }
    
    public void clearsubActivityBudgetCost()
    {
        if(!ActivityID.contains("*"))
        {
            for(int a=0;a<Year.size();a++)
            {
                for(int b=0;b<Year.get(a).Month.size();b++)
                {
                    Year.get(a).Month.get(b).setBudgetCost(0);
                }
                Year.get(a).calculateTotalBudgetCost();
            }
            this.calculateTotalBudgetCost();
        }
	}
}