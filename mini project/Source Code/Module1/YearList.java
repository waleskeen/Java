package projectcharter;
//Contribute by Ang Fan Yee

public class YearList {

    private int FileNo;
    private String projectname;
    private String manager;
    private String email;
    private String hpnum;
    private int StarYear;
    private double benefit;
    private double cost;
    public double CF = 0.0;
    public double DF = 0.0;
    public double DB = 0.0;
    public double DC = 0.0;
    public double X = 0.0;
    public double CT = 0.0;
    public double CCF = 0.0;

    public String gethp() {
        return hpnum;
    }

    public void sethp(String hp) {
        hpnum = hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        email = Email;
    }

    public String getMan() {
        return manager;
    }

    public void setMan(String Man) {
        manager = Man;
    }

    public String getpn() {
        return projectname;
    }

    public void setpn(String pn) {
        projectname = pn;
    }

    public int getFNo() {
        return FileNo;
    }

    public void setFNo(int FNo) {
        FileNo = FNo;
    }

    public int getsy() {
        return StarYear;
    }

    public void setsy(int sy) {
        StarYear = sy;
    }

    public double getb() {
        return benefit;
    }

    public void setb(double b) {
        benefit = b;
    }

    public double getc() {
        return cost;
    }

    public void setc(double c) {
        cost = c;
    }

  //function:to calculate the cash flow
  //input:benefit, cost
  //output:cash flow
    public double calculateCF() {
        CF = benefit - cost;
        return CF;
    }
}
