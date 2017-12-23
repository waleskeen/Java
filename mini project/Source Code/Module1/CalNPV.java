package projectcharter;
//Contribute by Ang Fan Yee
//class to calculate NPV
public class CalNPV {

    //function to calculate discount factor
	//input:discout rate, year
	//output:discount factor
    public double Discountfactor(double dr, int year) {
        return 1 / Math.pow(1 + dr, year);
    }

    //function to calculate Discounted Cost
    //input:discount factor, cost
    //output:discount cost
    public double calculateDC(double DF, double C) {
        return DF * C;
    }

    //function to calculate Discounted Benefit
    //input:discount factor, benefit
    //output:discount benefit
    public double calculateDB(double DF, double B) {
        return DF * B;
    }

    //function to calculate the subtraction Discounted Benefit and Discounted Cost
    //input:discounted benefit, discounted cost
    //output:the subtraction Discounted Benefit and Discounted Cost
    public double calculateX(double DB, double DC) {
        return DB - DC;
    }

  	//function to calculate cumulative total
    //input:the original cumulative total,
    //the subtraction Discounted Benefit and Discounted Cost
    //output:the new cumulative total
    public double calculateCT(double CT, double X) {
        return CT + X;
    }
}
