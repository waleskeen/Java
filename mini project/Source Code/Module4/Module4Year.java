package projectcharter;
//Contribute by Chan Chee Keen
public abstract class Module4Year 
{
    protected int Year=0;
    protected double TotalBudgetCostPerYear=0;
    protected double TotalACPerYear=0;
    protected double TotalEVPerYear=0;
    
    public Module4Year()
    {
        TotalBudgetCostPerYear=0;
        TotalACPerYear=0;
        TotalEVPerYear=0;
    }
    
    public Module4Year(int Year)
    {
        this.Year=Year;
    }
    
    public int getYear()
    {
        return Year;
    }
    
    public void setYear(int Year)
    {
        this.Year=Year;
    }
}