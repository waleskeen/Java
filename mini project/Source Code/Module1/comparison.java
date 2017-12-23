package projectcharter;
//Contribute by Ang Fan Yee
import java.text.DecimalFormat;
import java.util.*;

//class to compare and select different projects
public class comparison {

    public double MAX;
    public double higher;
    public final double maxpercentage = 100;
    public project ratio = new project();
    public WSM W = new WSM();
    DecimalFormat round1 = new DecimalFormat("0.00");

    //function to calculate the mark for NPV for all projects
    //input:list of project
    //output:mark for NPV for all projects
    public void compNPV(List<selection> s) {
        s.get(0).F.calculateCT(s.get(0).F.dr);
        MAX = s.get(0).F.currentNPV;

        for (int a = 1; a < s.size(); a++) {
            s.get(a).F.calculateCT(s.get(a).F.dr);
            if (MAX < s.get(a).F.currentNPV) {
                MAX = s.get(a).F.currentNPV;
            }
        }
        ratio.setNPV(MAX / maxpercentage);

        for (int a = 0; a < s.size(); a++) {
            s.get(a).F.calculateCT(s.get(a).F.dr);
            s.get(a).P.valueNPV = Math.abs(s.get(a).F.currentNPV / ratio.getNPV());
            s.get(a).P.sumNPV = s.get(a).P.valueNPV * (s.get(a).P.getNPV() / 100);
            //System.out.println(s.get(a).P.sumNPV);
        }
    }

    //function to calculate the mark for ROI for all projects
    //input:list of project
    //output:mark for ROI for all projects
    public void compROI(List<selection> s) {
        MAX = s.get(0).F.calculateROI();

        for (int a = 1; a < s.size(); a++) {
            if (MAX < s.get(a).F.calculateROI()) {
                MAX = s.get(a).F.calculateROI();
            }
        }
        ratio.setROI(MAX / maxpercentage);
        for (int a = 0; a < s.size(); a++) {
            s.get(a).P.valueROI = Math.abs(s.get(a).F.calculateROI() / ratio.getROI());
            s.get(a).P.sumROI = s.get(a).P.valueROI * (s.get(a).P.getROI() / 100);
            //System.out.println(s.get(a).P.sumROI);
        }
    }

    //function to calculate the mark for IRR for all projects
    //input:list of project
    //output:mark for IRR for all projects
    public void compIRR(List<selection> s) {
        s.get(0).F.calculateCT(s.get(0).F.dr);
        s.get(0).F.calculateIRR();
        MAX = s.get(0).F.IRR;
        //System.out.println(MAX);

        for (int a = 1; a < s.size(); a++) {
            s.get(a).F.calculateCT(s.get(a).F.dr);
            s.get(a).F.calculateIRR();
            if (MAX < s.get(a).F.IRR) {
                MAX = s.get(a).F.IRR;
            }
        }
        ratio.setIRR(MAX / maxpercentage);
        for (int a = 0; a < s.size(); a++) {
            s.get(a).F.calculateCT(s.get(a).F.dr);
            s.get(a).F.calculateIRR();
            s.get(a).P.valueIRR = Math.abs(s.get(a).F.IRR / ratio.getIRR());
            s.get(a).P.sumIRR = s.get(a).P.valueIRR * (s.get(a).P.getIRR() / 100);
            //System.out.println(s.get(a).P.sumIRR);
        }
    }

    //function to calculate the mark for payback for all projects
    //input:list of project
    //output:mark for payback for all projects
    public void compPAYBACK(List<selection> s) {
        s.get(0).F.calculatePayBack();
        MAX = s.get(0).F.payback;
        for (int a = 1; a < s.size(); a++) {
            s.get(a).F.calculatePayBack();
            if (MAX < s.get(a).F.payback) {
                MAX = s.get(a).F.payback;
            }
        }
        ratio.setPAYBACK(MAX / maxpercentage);
        for (int a = 0; a < s.size(); a++) {
            s.get(a).F.calculatePayBack();
            s.get(a).P.valuePAYBACK = Math.abs(s.get(a).F.payback / ratio.getPAYBACK());
            s.get(a).P.sumPAYBACK = s.get(a).P.valuePAYBACK * (s.get(a).P.getPAYBACK() / 100);
            //System.out.println(s.get(a).P.sumPAYBACK);
        }
    }

    //function to calculate the mark for WSM for all projects
    //input:list of project
    //output:mark for WSM for all projects
    public void compWSM(List<selection> s) {
        W.wsm(s.get(0).W);
        MAX = W.total;
        for (int a = 1; a < s.size(); a++) {
            W = new WSM();
            W.wsm(s.get(a).W);
            if (MAX < W.total) {
                MAX = W.total;
            }
        }
        ratio.setPWSM(MAX / maxpercentage);
        for (int a = 0; a < s.size(); a++) {
            W = new WSM();
            W.wsm(s.get(a).W);
            s.get(a).P.valueWSM = Math.abs(W.total / ratio.getPWSM());
            s.get(a).P.sumWSM = s.get(a).P.valueWSM * (s.get(a).P.getPWSM() / 100);
            //System.out.println(s.get(a).P.sumWSM);
        }
    }

    //function to calculate all the mark inside the project and compare the highest mark
    //and then store into file
    //input:list of project
    //output:the highest mark of the project
    public void addMark(List<selection> s) {
        selection SUPERHIGH=new selection();
        for (int a = 0; a < s.size(); a++) {
            s.get(a).P.totalmark = s.get(a).P.sumNPV + s.get(a).P.sumROI + s.get(a).P.sumIRR + s.get(a).P.sumPAYBACK + s.get(a).P.sumWSM;
            //System.out.println(s.get(a).P.totalmark);
            if (higher < s.get(a).P.totalmark) {
                higher = s.get(a).P.totalmark;
                SUPERHIGH=s.get(a);
                //System.out.println(s.get(a).P.totalmark);
                

            }

        }
       WriteFile WTF = new WriteFile();

                try {
                    WTF.write(SUPERHIGH);
                } catch (Exception e) {
                    //System.out.println(e.getMessage());
                }

    }
}
