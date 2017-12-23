package projectcharter;
//Contribute by Ang Fan Yee

import java.io.*;
import java.util.*;

public class WriteFile {

	//function:to generate the input file for others module and update the project
	//input:list of project
	//output:files
    private int Findex = 0;
    private String Pname = "project ";
    private String mrk1 = "%%%%%";
    private String mrk2 = "###";
    private String mrk3 = "=.=";
    private String mrk4 = "$$";

   public void write(selection s) throws IOException {
        List<YearList> Year = new ArrayList();
        List<WSMList> WSM = new ArrayList();
        YearList Y = new YearList();
        WSMList W = new WSMList();
        File file = new File("project.txt");
        Scanner InputFile = new Scanner(file);
        String content = new String();
        int[] marker = {0, 0};
        while (InputFile.hasNextLine()) {
            content = InputFile.nextLine();

            Y.setFNo(Integer.parseInt(content));
            InputFile.nextLine();
            Y.setpn(content);
            InputFile.nextLine();
            Y.setMan(content);
            InputFile.nextLine();
            Y.setEmail(content);
            InputFile.nextLine();
            Y.sethp(content);
        }
        InputFile.close();

        Findex = Findex + Y.getFNo();
        Findex++;
        FileWriter FW = new FileWriter(Integer.toString(Findex) + ".txt");
        PrintWriter writer = new PrintWriter(FW);
        writer.println(Integer.toString(Findex));
        writer.println(Pname + (char) (64 + Findex));
        writer.println(s.Yuser.getMan());
        writer.println(s.Yuser.gethp());
        writer.println(s.Yuser.getEmail());
        writer.println("Module3:0");
        writer.println("Module4:0");
        writer.println("Module5C:0");
        writer.println("Module5T:0");
        writer.println();
        writer.println(mrk1);
        writer.println(mrk1);
        writer.println();
        writer.println(mrk2);
        writer.println(mrk2);
        writer.println();
        writer.println(mrk3);
        writer.println(mrk3);
        writer.println();
        writer.println(mrk4);
        writer.println(mrk4);
        writer.close();
        FileWriter FW1 = new FileWriter("project.txt", true);
        PrintWriter writer1 = new PrintWriter(FW1);
        writer1.println(Integer.toString(Findex));
        writer1.println(Pname + (char) (64 + Findex));
        writer1.println(s.Yuser.getMan());
        writer1.println(s.Yuser.gethp());
        writer1.println(s.Yuser.getEmail());
        writer1.close();
        

    }
    
}
