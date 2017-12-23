package projectcharter;
//Contribute by Ang Fan Yee
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReadFile {

	//function:to read the input file
    //input:file name
    //output:list of project
    public void read(List<selection> SL) throws IOException {

        File file = new File("module1.txt");
        Scanner InputFile = new Scanner(file);
        if (file != null) {
            System.out.println("File Exists");
        }
        selection s = new selection();
        List<project> proj = new ArrayList();
        List<YearList> Year = new ArrayList();
        project P = new project();
        YearList Y = new YearList();
        WSMList W = new WSMList();
        String content = new String();
        int cnt = 1;

        while (InputFile.hasNext()) {
            int[] marker = {0, 0, 0, 0, 0, 0};
            //System.out.println("OK");
            content = InputFile.nextLine();
            if (content.equals("9/11/2013")) {
                marker[0]++;
                content = InputFile.next();
                //System.out.println(content);
            }

            //if(marker[0]==1){

            if (marker[0] == 1) {
                while (InputFile.hasNext() && marker[0] < 2) {
                    Y = new YearList();
                    Y.setsy(Integer.parseInt(content));
                    content = InputFile.next();
                    //System.out.println(Y.getsy());
                    Y.setb(Double.parseDouble(content));
                    content = InputFile.next();
                    //System.out.println(Y.getb());
                    Y.setc(Double.parseDouble(content));
                    content = InputFile.next();
                    //System.out.println(Y.getc());
                    if (content.equals("++")) {
                        marker[0]++;
                    }
                    Year.add(Y);

                    s.F.YL = Year;
                    //Year.get(0);
                }
            }
            //System.out.println(Year.get(1).getsy());
            if (content.equals("!")) {
                marker[1]++;
                content = InputFile.nextLine();
                //System.out.println(content);
            }
            if (marker[1] == 1) {
                while (InputFile.hasNext() && marker[1] < 2) {
                    s.F.dr = Double.parseDouble(content);
                    content = InputFile.nextLine();
                    //System.out.println(Y.getdr());
                    if (content.equals("!")) {
                        marker[1]++;
                        //Year.add(Y);
                    }
                    //Year.get(0);
                    //System.out.println(Year.get(0).getdr());
                }
            }
            if (content.equals("--")) {
                marker[2]++;
                content = InputFile.nextLine();
            }
            if (marker[2] == 1) {
                while (InputFile.hasNext() && marker[2] < 2) {
                    content = content.replace("$", "");
                    W = new WSMList();
                    W.setcri(content);
                    content = InputFile.nextLine();
                    //System.out.println(W.getcri());
                    if (content.equals("--")) {
                        marker[2]++;

                    }
                    s.W.add(W);
                    //WSM.get(0);
                    //System.out.println(WSM.get(0).getcri());
                }
            }
            if (content.equals("^")) {
                marker[3]++;
                content = InputFile.nextLine();
                //System.out.println(content);
            }
            if (marker[3] == 1) {
                    for (int a = 0; a < s.W.size(); a++) {
                        W = s.W.get(a);
                        content = content.replace("$", "");
                        W.setwei(Double.parseDouble(content));
                        content = InputFile.nextLine();
                        //System.out.println(W.getwei());
                        if (content.equals("^")) {
                            marker[3]++;
                        }
                        //WSM.get(0);
                        //System.out.println(WSM.get(0).getwei());
                    }
                
            }
            if (content.equals("=")) {
                marker[4]++;
                content = InputFile.nextLine();
                //System.out.println(content);
            }
            if (marker[4] == 1) {
                    for (int a = 0; a < s.W.size(); a++) {
                        W = s.W.get(a);
                        content = content.replace("$", "");
                        W.setm(Double.parseDouble(content));
                        content = InputFile.nextLine();
                        //System.out.println(W.getm());
                        if (content.equals("=")) {
                            marker[4]++;

                        }
                        //WSM.get(0);
                        //System.out.println(WSM.get(0).getm());
                    }
                
            }
            if (content.equals("%")) {
                marker[5]++;
                content = InputFile.next();
                //System.out.println(content);
            }
            if (marker[5] == 1) {
                while (InputFile.hasNext() && marker[5] < 2) {
                    P.setNPV(Double.parseDouble(content));
                    content = InputFile.next();
                    //System.out.println(P.getNPV());
                    P.setROI(Double.parseDouble(content));
                    content = InputFile.next();
                    //System.out.println(P.getROI());
                    P.setIRR(Double.parseDouble(content));
                    content = InputFile.next();
                    //System.out.println(P.getIIR());
                    P.setPAYBACK(Double.parseDouble(content));
                    content = InputFile.next();
                    //System.out.println(P.getPAYBACK());
                    P.setPWSM(Double.parseDouble(content));
                    content = InputFile.next();
                    //System.out.println(P.getPWSM());
                    if (content.equals("%")) {
                        marker[5]++;
                    }
                    s.P = P;
                    SL.add(s);
                    //System.out.println(proj.get(0).getPWSM());
                }
                // }
            }
        }
    }
}
