package projectcharter;
//Contribute by Ang Fan Yee
import java.util.*;
import java.text.DecimalFormat;


//class to Store the data for financial analysis
public class FA {

    public double dr = 0;
    public List<YearList> YL = new ArrayList();
    public CalNPV CAL = new CalNPV();
    public double initialNPV = 0.0;
    public double TotalDB = 0.0;
    public double TotalDC = 0.0;
    public double ROI = 0.0;
    public double IRR = 0.0;
    public double currentNPV = 0.0;
      public double payback = 0.0;
    DecimalFormat round1 = new DecimalFormat("0.00");
    DecimalFormat round11 = new DecimalFormat("0.0000");

    //function:calculate the discount factor
    //input:discount rate, year
    //output:discount factor
    public void discountfactor() {
        for (int a = 0; a < YL.size(); a++) {
            YL.get(a).DF = CAL.Discountfactor(dr, a + 1);
        }
    }
    
    //function:calculate the total discount cost
    //input:all of cost
    //output:total discount cost
    public double calculateTotalDC() {
        TotalDC = 0;
        for (int a = 0; a < YL.size(); a++) {
            YL.get(a).DC = CAL.calculateDC(CAL.Discountfactor(dr, a + 1), YL.get(a).getc());
            TotalDC = TotalDC + YL.get(a).DC;
        }
        return TotalDC;
    }
    
    //function:calculate the total discount benefit
    //input:all of benefit
    //output:total discount benefit
    public double calculateTotalDB() {
        TotalDB = 0;
        for (int a = 0; a < YL.size(); a++) {
            YL.get(a).DB = CAL.calculateDB(CAL.Discountfactor(dr, a + 1), YL.get(a).getb());
            TotalDB = TotalDB + YL.get(a).DB;
        }
        return TotalDB;
    }
    
    //function:calculate the subtraction for discount benefit and discount cost
    //input:all of discount benefit and discount cost
    //output:the subtraction for discount benefit and discount cost
    public void calculateX() {
        for (int a = 0; a < YL.size(); a++) {
            YL.get(a).X = CAL.calculateX(CAL.calculateDB(CAL.Discountfactor(dr, a + 1), YL.get(a).getb()), CAL.calculateDC(CAL.Discountfactor(dr, a + 1), YL.get(a).getc()));
            //System.out.println(round1.format(YL.get(a).X));
        }
    }
    
    //function:calculate the cumulative total
    //input:discount rate, year
    //output:cumulative total, NPV
    public void calculateCT(double R) {
        YL.get(0).CT = CAL.calculateX(CAL.calculateDB(CAL.Discountfactor(R, 1), YL.get(0).getb()), CAL.calculateDC(CAL.Discountfactor(R, 1), YL.get(0).getc()));
        //System.out.println(round1.format(YL.get(0).CT));
        
        for (int a = 1; a < YL.size(); a++) {
            YL.get(a).CT = CAL.calculateCT(YL.get(a - 1).CT,
                    CAL.calculateX(CAL.calculateDB(CAL.Discountfactor(R, a + 1), YL.get(a).getb()),
                    CAL.calculateDC(CAL.Discountfactor(R, a + 1), YL.get(a).getc())));
            //System.out.println(round1.format(YL.get(a).CT));
        }
        
        currentNPV = YL.get(YL.size() - 1).CT;
        //System.out.println(round1.format(currentNPV));
    }
    
    //function:calculate the ROI
    //input:total discount benefit, discount cost
    //output:ROI
    public double calculateROI() {
        ROI = (calculateTotalDB() - calculateTotalDC()) / calculateTotalDC();
        return ROI;
    }
    
    //function:calculate the IRR
    //input:NPV, change or discount rate
    //output:IRR
    public void calculateIRR() {
        IRR = dr;
        initialNPV = currentNPV;
        if (currentNPV <=0 || currentNPV >=0) {
            while (currentNPV > 1) {
                IRR = IRR + 0.00001;
                calculateCT(IRR);
            }
        } else {
            while (currentNPV < -1) {
                IRR = IRR - 0.00001;
            }
        }
        //System.out.println("IRR: "+round11.format(IRR));
    }
    
    //function:calculate the payback period
    //input:all of cash flow
    //output:payback period
    public void calculatePayBack() {
        for (int a = 0; a < YL.size(); a++) {
            YL.get(a).calculateCF();
        }
        YL.get(0).CCF = YL.get(0).CF;
        for (int a = 1; a < YL.size(); a++) {
            YL.get(a).CCF = YL.get(a - 1).CCF + YL.get(a).CF;
        }
        for (int a = 0; a < YL.size(); a++) {
            if (YL.get(a).CCF > 0) {
                payback=(a)+ Math.abs(YL.get(a-1).CCF/YL.get(a).CF);
                //System.out.println(YL.get(a-1).CCF);
                //System.out.println(YL.get(a).CF);
                //System.out.println("Payback period: "+round1.format(payback));
                break;
            }
        }
    }
    
}
