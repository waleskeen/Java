package projectcharter;
//Contribute Ang Fan Yee

public class WSMList {

    private String criteria;
    private double weight;
    private double mark;
    public double res;
    public String getcri() {
        return criteria;
    }

    public void setcri(String cri) {
        criteria = cri;
    }

    public double getwei() {
        return weight;
    }

    public void setwei(double wei) {
        weight = wei;
    }

    public double getm() {
        return mark;
    }

    public void setm(double m) {
        mark = m;
    }
    
  //function:to calculate the requirement
  //input:weight, mark
  //output:requirement
    public double calrequirement(double w,double m){
        return w*m;
    
    }
}
