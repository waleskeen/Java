package projectcharter;
//Contribute by Ang Fan Yee

import java.io.*;
import java.util.*;

public class newReadFile {

	//function:to read the input file
    //input:file name
    //output:list of project
    public void r(List<selection> SL) throws IOException {
        File file = new File("module1.txt");
        Scanner InputFile = new Scanner(file);
        project P;
        YearList Y;
        WSMList W;
        selection s;
        String content;
        int[] marker = new int[7];
        int cnt = 1;

        while (InputFile.hasNext()) {
            marker[0] = 0;
            s = new selection();
            content = InputFile.next();

            if (content.equals("9/11/2013")) {
                marker[0]++;
                content = InputFile.next();
            }

            if (marker[0] == 1) {
                while (InputFile.hasNext() && marker[0] == 1) {
                    marker[1] = 0;
                    Y = new YearList();
                    Y.setsy(Integer.parseInt(content));
                    Y.setb(Double.parseDouble(InputFile.next()));
                    Y.setc(Double.parseDouble(InputFile.next()));
                    s.F.YL.add(Y);

                    content = InputFile.next();

                    if (content.equals("!")) {
                        marker[1]++;
                        content = InputFile.next();
                    }

                    while (InputFile.hasNext() && marker[1] == 1) {
                        marker[2] = 0;
                        s.F.dr = Double.parseDouble(content);

                        content = InputFile.next();

                        if (content.equals("!")) {
                            marker[1]++;
                            content = InputFile.next();
                        }

                        if (content.equals("--")) {
                            marker[2]++;
                            content = InputFile.nextLine();
                        }

                        while (InputFile.hasNext() && marker[2] == 1) {
                            marker[3] = 0;
                            W = new WSMList();
                            if (content.length() != 0) {
                                W.setcri(content.replace("$", ""));
                                s.W.add(W);
                            }

                            content = InputFile.nextLine();

                            if (content.equals("--")) {
                                marker[2]++;
                                content = InputFile.next();
                            }

                            if (content.equals("^")) {
                                marker[3]++;
                                content = InputFile.next();
                            }

                            while (InputFile.hasNext() && marker[3] == 1) {
                                marker[4] = 0;
                                for (int a = 0; a < s.W.size(); a++) {
                                    s.W.get(a).setwei(Double.parseDouble(content.replace("$", "")));
                                    content = InputFile.next();
                                }

                                if (content.equals("^")) {
                                    marker[3]++;
                                    content = InputFile.next();
                                }

                                if (content.equals("=")) {
                                    marker[4]++;
                                    content = InputFile.next();
                                }

                                while (InputFile.hasNext() && marker[4] == 1) {
                                    marker[5] = 0;
                                    for (int a = 0; a < s.W.size(); a++) {
                                        s.W.get(a).setm(Double.parseDouble(content.replace("$", "")));
                                        content = InputFile.next();
                                    }

                                    if (content.equals("=")) {
                                        marker[4]++;
                                        content = InputFile.next();
                                    }

                                    if (content.equals("%")) {
                                        marker[5]++;
                                        content = InputFile.next();
                                    }

                                    while (InputFile.hasNext() && marker[5] == 1) {
                                        marker[6]=0;   
                                        P = new project();
                                        P.setNPV(Double.parseDouble(content));
                                        P.setROI(Double.parseDouble(InputFile.next()));
                                        P.setIRR(Double.parseDouble(InputFile.next()));
                                        P.setPAYBACK(Double.parseDouble(InputFile.next()));
                                        P.setPWSM(Double.parseDouble(InputFile.next()));

                                        s.P = P;

                                        content = InputFile.next();

                                        if (content.equals("%")) {
                                            marker[5]++;
                                            content = InputFile.next();
                                        }
                                        
                                        if (content.equals("&")) {
                                            marker[6]++;
                                            content = InputFile.next();
                                        }

                                        while (InputFile.hasNext() && marker[6] == 1) {
                                            Y=new YearList();
                                
                                            Y.setMan(content);
                                            Y.sethp(InputFile.next());
                                            Y.setEmail(InputFile.next());
                                            s.Yuser=Y;

                                            content = InputFile.next();

                                            if (content.equals("&")) {
                                                marker[6]++;
                                                content = InputFile.next();
                                            }
                                        }
                                        SL.add(s);
                                    }
                                }
                            }
                        }
                    }
                    if (content.equals("9/11/2013")) {
                            marker[0]++;  
                        }
                }
            }
        }
    }
}
