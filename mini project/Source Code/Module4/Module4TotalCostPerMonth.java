package projectcharter;
//Contribute by Chan Chee Keen
public class Module4TotalCostPerMonth extends Module4Month
{      
    public Module4TotalCostPerMonth()
    {
        super();
    }

    //function:to calculate the total budget cost per month for all activity
    //input:all of activity, year
    //output:the total budget cost per month for all activity
    public double calculateTotalBudgetCost(Module4Project P, int Y) 
    {
        BudgetCost=0;
        for(int a=0;a<P.Activity.size();a++)
        {
            for(int b=0;b<P.Activity.get(a).Year.size();b++)
            {
                if(Y==P.Activity.get(a).Year.get(b).Year)
                {
                    for(int c=0;c<P.Activity.get(a).Year.get(b).Month.size();c++)
                    {
                        if(Month==P.Activity.get(a).Year.get(b).Month.get(c).Month)
                        {
                            BudgetCost=BudgetCost+P.Activity.get(a).Year.get(b).Month.get(c).BudgetCost;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return Math.round(BudgetCost*100)/100;
    }
}