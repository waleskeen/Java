package projectcharter;
//Contribute by Chan Chee Keen
import javax.swing.*;

public class Module4ActivityMonth extends Module4Month
{    
    private double RP=0;
    private double CV=0;
    private double SV=0;
    private double CPI=0;
    private double SPI=0;
    
    public Module4ActivityMonth()
    {
        super();
        RP=0;
        CV=0;
        SV=0;
        CPI=0;
        SPI=0;
    }
 
    public double getRP()
    {
        return RP;
    }
    
    public void setRP(double RP)
    {
        if(RP>=0)
            this.RP=RP;
        else
        	JOptionPane.showMessageDialog(null, "ERROR");
    }

    //function:to calculate the earned value
    //input:planned value, rate of performance
    //output:earned value
    public double calculateEV()
    {
        EV=PV*RP/100;
        return Math.round(EV*100)/100;
    }
    
    //function:to calculate the cost variance
    //input:earned value, actual cost
    //output:cost variance
    public double calculateCV()
    {
        CV=calculateEV()-AC;
        return Math.round(CV*100)/100;
    }
    
    //function:to calculate the schedule variance
    //input:earned value, planned value
    //output:schedule variance
    public double calculateSV()
    {
        SV=calculateEV()-PV;
        return Math.round(SV*100)/100;
    }
    
    //function:to calculate the cost performance index
    //input:earned value, actual cost
    //output:cost performance index
    public double calculateCPI()
    {
        try
        {
            CPI=calculateEV()/AC;
        }
        catch(Exception e)
        {
            CPI=0;
        }
        return Math.round(CPI*10000)/100;
    }
    
    //function:to calculate the schedule performance index
    //input:earned value, actual cost
    //output:schedule performance index
    public double calculateSPI()
    {
        try
        {
            SPI=calculateEV()/PV;
        }
        catch(Exception e)
        {
            SPI=0;
        }
        return Math.round(SPI*10000)/100;
    }
}
