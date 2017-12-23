package projectcharter;
//Contribute by Ang Fan Yee

import java.io.*;
import java.util.*;

public class WriteInput {

	//function:to store the data into input file
	//input:list of project
	//output:input file
    public void write(List<selection> s) throws IOException {
        FileWriter WI = new FileWriter("test.txt");
        PrintWriter writer = new PrintWriter(WI);
        for(int a=0;a<s.size();a++)
        {
        writer.println("9/11/2013");
        writer.println(s.get(a).F.YL.get(0).getsy() + " " + s.get(a).F.YL.get(0).getb() + " " + s.get(a).F.YL.get(0).getc());
        writer.println(s.get(a).F.YL.get(1).getsy() + " " + s.get(a).F.YL.get(1).getb() + " " + s.get(a).F.YL.get(1).getc());
        writer.println(s.get(a).F.YL.get(2).getsy() + " " + s.get(a).F.YL.get(2).getb() + " " + s.get(a).F.YL.get(2).getc());
        writer.println(s.get(a).F.YL.get(3).getsy() + " " + s.get(a).F.YL.get(3).getb() + " " + s.get(a).F.YL.get(3).getc());
        writer.println(s.get(a).F.YL.get(4).getsy() + " " + s.get(a).F.YL.get(4).getb() + " " + s.get(a).F.YL.get(4).getc());


        writer.println("!");
        writer.println(s.get(a).F.dr);
        writer.println("!");
        writer.println("--");
        writer.println(s.get(a).W.get(0).getcri() + "$");
        writer.println(s.get(a).W.get(1).getcri() + "$");
        writer.println(s.get(a).W.get(2).getcri() + "$");
        writer.println(s.get(a).W.get(3).getcri() + "$");
        writer.println(s.get(a).W.get(4).getcri());
        writer.println("--");
        writer.println("^");

        writer.println(s.get(a).W.get(0).getwei() + "$");
        writer.println(s.get(a).W.get(1).getwei() + "$");
        writer.println(s.get(a).W.get(2).getwei() + "$");
        writer.println(s.get(a).W.get(3).getwei() + "$");
        writer.println(s.get(a).W.get(4).getwei());


        writer.println("^");
        writer.println("=");
        writer.println(s.get(a).W.get(0).getm() + "$");
        writer.println(s.get(a).W.get(1).getm() + "$");
        writer.println(s.get(a).W.get(2).getm() + "$");
        writer.println(s.get(a).W.get(3).getm() + "$");
        writer.println(s.get(a).W.get(4).getm());

        writer.println("=");
        writer.println("%");
        writer.println(s.get(a).P.getNPV() + " " + s.get(a).P.getROI() + " " + s.get(a).P.getIRR() + " " + s.get(a).P.getPAYBACK() + " " + s.get(a).P.getPWSM());
        writer.println("%");
        writer.println("&");
        writer.println(s.get(a).Yuser.getMan() + " " + s.get(a).Yuser.gethp() + " " + s.get(a).Yuser.getEmail());
        writer.println("&");
        }
        writer.close();
    }
}
