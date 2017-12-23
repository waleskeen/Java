package projectcharter;
//Contribute by Ang Fan Yee

import java.util.*;
import java.text.DecimalFormat;

public class WSM {

    DecimalFormat round2 = new DecimalFormat("0.00");
    public double res = 0;
    public double total = 0;

  //function:to calculate the total of WSM
  //input:the list of WSM
  //output:total value of WSM
    public void wsm(List<WSMList> WL) {
        for (int a = 0; a < WL.size(); a++) {
            WL.get(a).res = WL.get(a).calrequirement(WL.get(a).getwei(), WL.get(a).getm());
        }
        total = (WL.get(0).res+WL.get(1).res+WL.get(2).res+WL.get(3).res+WL.get(4).res)/100;
       /* for (int a = 0; a < WL.size(); a++) {
            System.out.println("Criteria: " + WL.get(a).getcri() + "\n Weight of criteria: " + WL.get(a).getwei() + "\n Mark: " + WL.get(a).getm());
        }
        System.out.println("Total value of weighted scoring model: " + round2.format(total)+"%");*/
    }
}
