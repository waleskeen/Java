package projectcharter;
//Contribute by Ang Fan Yee

import java.util.List;
import java.util.*;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Module1 {

    //function:to execute the main function for module1
    //input:-
    //output:execute the main function
    public void main1() {
        //ReadFile RF = new ReadFile();
        newReadFile n = new newReadFile();
        comparison c = new comparison();
        FA F = new FA();
        WSM W = new WSM();
        List<YearList> Year = new ArrayList();
        List<WSMList> WSM = new ArrayList();
        List<project> proj = new ArrayList();
        List<selection> s = new ArrayList();
        DecimalFormat round = new DecimalFormat("0.00");
        DecimalFormat roundd = new DecimalFormat("0.0000");
       
  
     try {
            n.r(s);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        InputInterface test1=new InputInterface(s);
        test1.main9();
    }
}
