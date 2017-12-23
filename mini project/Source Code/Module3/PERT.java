package projectcharter;
//Contribute by Chin Kian Zhong

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PERT {

    protected double optimistic, pessimistic, mostlikely, total;
    protected int Pert;
    protected static int max;
    protected String predecessor, paths, str, possible_path,tempS = "" ;
    protected static String Critical = "";
    protected static ArrayList<String> Path_list = new ArrayList<>();
    protected static ArrayList<String> Possible_Path_list = new ArrayList<>();
    protected static ArrayList<String> A_list = new ArrayList<>();
    protected static ArrayList<String> Precedence_list = new ArrayList<>();
    protected static ArrayList<String> Succesor_list = new ArrayList<>();
    protected static ArrayList<Integer> TotalSlack = new ArrayList<>(), FreeSlack = new ArrayList<>(), Earliest = new ArrayList<>(), Latest = new ArrayList<>();

    protected PERT() {
    }

    //function:to search the index
    //input:number
    //output:the index had been found
    public int searchIndex(String s) {
        int k = -1;
        for (int a = 0; a < A_list.size(); a++) {
            if (s.equals(A_list.get(a))) {
                k = a;
                break;
            }
        }
        return k;
    }

    //function:to round the figure
    //input:number
    //output:the number has been round
    private int RoundFigure(double d) {
        int x;
        if (d > Math.floor(d)) {
            x = (int) d + 1;
        } else {
            x = (int) d;
        }
        return x;
    }

  //function:the constructor is to create the PERT
  //input:-
  //output:all the data
    public PERT(String paths, double optimistic, double mostlikely, double pessimistic)//constructor 
    {
        this.paths = paths;
        this.optimistic = optimistic;
        this.mostlikely = mostlikely;
        this.pessimistic = pessimistic;
        this.Pert = RoundFigure(((4 * mostlikely) + pessimistic + optimistic) / 6);
    }

  //function:the read the input file for activity
  //input:input file
  //output:all the activity
    public static void InFile() {
        String[] k;
        File WBS = new File("wbs.txt");
        try {
            try (Scanner in = new Scanner(WBS)) {
                int marker1=0;
                String str = in.nextLine();
                while(in.hasNextLine() && marker1<2)
                {
                    if(in.nextLine().equals("%%%%%"))
                    {
                        marker1++;
                    }
                    
                    if(marker1==1)
                    {
                        str = in.nextLine();
                        int number = Integer.parseInt(str);
                        for (int a = 0; a < number; a++) {
                            Succesor_list.add(in.nextLine());
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        for (int a = 0; a < Succesor_list.size(); a++) {
            String kstr = Succesor_list.get(a);
            if (kstr.charAt(0) == '*') {
                k = kstr.replace("*", "").split(" ");
                A_list.add(k[0]);

            }
        }
        Succesor_list.clear();
    }

    //function:to read the input file for three point estimation
    //input:input file
    //output:all the activity's three point estimation
    private static void Input() {
        String[] token;
        int cnt=1;
        File hello = new File("wbs.txt");
        try{
            try (Scanner in = new Scanner(hello)) {
                while (!in.hasNext("=.=")) 
                {
                    String str = in.nextLine();

                    if (str.length() == 3 && str.equals("###")&&cnt<2) {
                        while (!in.hasNext("###")) {
                            str = in.nextLine();
                            token = str.split(" ");
                            PERT p = new PERT(token[0], Double.parseDouble(token[1]), Double.parseDouble(token[2]), Double.parseDouble(token[3]));
                            first.P_list.add(p);
                        }
                        cnt++;
                    }
                }
            
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    //function:to store the successor list
    //input:precedence list
    //output:successor list
    public void SuccessorList() {
        String[] temp;
        String temp1;
        for (int a = 0; a < A_list.size(); a++) {
            temp1 = "";
            for (int b = 0; b < Precedence_list.size(); b++) {
                temp = Precedence_list.get(b).split("/");
                for (int c = 0; c < temp.length; c++) {
                    if (A_list.get(a).equals(temp[c])) {
                        temp1 += A_list.get(b) + "/";
                        break;
                    }
                }
            }
            Succesor_list.add(temp1);
        }
        Succesor_list.add("null");
    }

    //function:to store the result into file
    //input:result
    //output:input file
    public void outPDf() throws FileNotFoundException {
        File critical = new File("wbs.txt");
        int cnt = 1;
        PrintWriter oFile = new PrintWriter("2.txt");
         try {
            try (Scanner in = new Scanner(critical)) {
                while (in.hasNext()) {
                   
                    for(int a=0;a<5;a++){
                        str=in.nextLine();
                        oFile.println(str);
                    }
                    oFile.println("Module3:"+PERT.max);
                    in.nextLine();
                    while(in.hasNext())
                    {
                        str=in.nextLine();
                        oFile.println(str);
                    }
                    break;
                   
                       
                }
            }
            oFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        //delete and rename file
        boolean success = (new File("wbs.txt")).delete();
        if (success) {
            System.out.println("The file has been successfully deleted");
        }
        File TempFile = new File("2.txt");
        File oldFile = new File("wbs.txt");
        if (TempFile.renameTo(oldFile)) {
            System.out.println("renamed");
        } else {
            System.out.println("Error");
        }
        
    }

    //function:to store all the data into file
    //input:data
    //output:input file
    public void output() throws FileNotFoundException {
        File critical = new File("wbs.txt");
        //cnt to make sure the loop will not revise the marker twice
        int cnt = 1;
        PrintWriter oFile = new PrintWriter("2.txt");

        try {
            try (Scanner in = new Scanner(critical)) {
                while (in.hasNext()) {
                    str = in.nextLine();
                    oFile.println(str);
                    //find marker and do somethings inside IF
                    if (str.length() == 3 && str.equals("###") && cnt < 2) {
                        if(in.hasNext("###")){
                            for (int a = 0; a < first.P_list.size(); a++) {
                                str = first.P_list.get(a).paths + " " + first.P_list.get(a).optimistic + " " + first.P_list.get(a).mostlikely + " " + first.P_list.get(a).pessimistic ;
                                oFile.println(str);
                            }
                            cnt++;}
                        else if(in.hasNext())
                        {
                            for (int a = 0; a < first.P_list.size(); a++) {
                                str = first.P_list.get(a).paths + " " + first.P_list.get(a).optimistic + " " + first.P_list.get(a).mostlikely + " " + first.P_list.get(a).pessimistic ;
                                oFile.println(str);
                            }
                            cnt++;
                            while(!in.hasNext("###"))
                                 str = in.nextLine();
                        }
                    
                    }
                }
            }
            oFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        //delete and rename file
        boolean success = (new File("wbs.txt")).delete();
        if (success) {
            System.out.println("The file has been successfully deleted");
        }
        File TempFile = new File("2.txt");
        File oldFile = new File("wbs.txt");
        if (TempFile.renameTo(oldFile)) {
            System.out.println("renamed");
        } else {
            System.out.println("Error");
        }
    }

    //function:to generate path list
    //input:precedence list
    //output:path list
    public void GeneratePath_list() {

        if(A_list.isEmpty())
            InFile();
        
        for (int a = 0; a < A_list.size(); a++) {
            if (Precedence_list.get(a).equals("null")) {
                tempS = a + " ";
            } else if (!Precedence_list.get(a).contains("/")) {
                Path_list.add(Precedence_list.get(a) + "#" + A_list.get(a));
            } else {
                String[] token = Precedence_list.get(a).split("/");
                for (int b = 0; b < token.length; b++) {
                    Path_list.add(token[b] + "#" + A_list.get(a));
                }
            }
        }
    }

    //function:to determine the possible path
    //input:precedence
    //output:possible path list
    public void PathDetermination() {
        int position;
        String str2;
        for (int a = 0; a < A_list.size(); a++) 
            if (Precedence_list.get(a).equals("null")) {
                tempS = a + " ";
            }
        String[] token = tempS.split(" ");
        int cnt = token.length;
        
        for (int a = 0; a < cnt; a++) {
            position = Integer.parseInt(token[a]);
            for (int b = 0; b < Path_list.size(); b++) {
                if (Path_list.get(b).substring(0, Path_list.get(b).indexOf("#")).equals(A_list.get(position).trim())) {
                    Possible_Path_list.add(Path_list.get(b));
                }
            }
        }
        int d = 1;
        while (d == 1) {
            int stopCnt = 0;
            int listCnt = Possible_Path_list.size();
            for (int a = 0; a < listCnt; a++) {
                str = Possible_Path_list.get(a).substring(Possible_Path_list.get(a).lastIndexOf("#") + 1, Possible_Path_list.get(a).length());
                for (int b = 0; b < Path_list.size(); b++) {

                    if (str.equals(Path_list.get(b).substring(0, Path_list.get(b).indexOf("#")))) {
                        str2 = Possible_Path_list.get(a).concat(Path_list.get(b).substring(Path_list.get(b).indexOf("#"), Path_list.get(b).length()));
                        Possible_Path_list.add(str2);
                    }
                }
            }
            str = A_list.get(A_list.size() - 1);

            for (int c = 0, cnt1 = 0; c < listCnt; c++) {
                str2 = Possible_Path_list.get(cnt1).substring(Possible_Path_list.get(cnt1).lastIndexOf("#") + 1, Possible_Path_list.get(cnt1).length());
                if (str.equals(str2)) {
                    cnt1++;
                } else {
                    Possible_Path_list.remove(cnt1);
                }
            }

            for (int c = 0; c < Possible_Path_list.size(); c++) {
                if (Possible_Path_list.get(c).endsWith(str)) {
                    stopCnt++;
                }
            }
            if (stopCnt == Possible_Path_list.size()) {
                break;
            }
        }
      
    }

    //function:to store the critical path
    //input:possible path list
    //output:critical path
    public void CriticalPath() {
        
        String[] token;
        int sum = 0;

        token = Possible_Path_list.get(0).split("#");
        for (int b = 0; b < token.length - 1; b++) {
            str = token[b] + "#" + token[b + 1];
            for (int c = 0; c < first.P_list.size(); c++) {
                if (str.equals(first.P_list.get(c).paths)) {
                    sum += first.P_list.get(c).Pert;
                    break;
                }
            }
        }
        max = sum;
        for (int a = 1; a < Possible_Path_list.size(); a++) {
            sum = 0;
            token = Possible_Path_list.get(a).split("#");
            for (int b = 0; b < token.length - 1; b++) {
                str = token[b] + "#" + token[b + 1];

                for (int c = 0; c < first.P_list.size(); c++) {
                    if (str.equals(first.P_list.get(c).paths)) {
                        sum += first.P_list.get(c).Pert;
                        break;
                    }
                }
            }
            if (max < sum) {
                max = sum;
            }
        }
        //output all critical path if any
        for (int a = 0; a < Possible_Path_list.size(); a++) {
            sum = 0;
            token = Possible_Path_list.get(a).split("#");
            for (int b = 0; b < token.length - 1; b++) {
                str = token[b] + "#" + token[b + 1];

                for (int c = 0; c < first.P_list.size(); c++) {
                    if (str.equals(first.P_list.get(c).paths)) {
                        sum += first.P_list.get(c).Pert;
                        break;
                    }
                }
            }
            if (max == sum) {
                Critical += a + " ";
            }

        }
        
    }

    //function:to calculate the earliest time
    //input:precedence list
    //output:earliest duration
    public void EarlistEventTime() {
        int max = 0, temp;
        String[] token;
        Earliest.add(0);

        while (true) {
            for (int a = 1; a < A_list.size(); a++) {
                token = Precedence_list.get(a).split("/");
                if (token.length == 1) {
                    for (int b = 0; b < A_list.size(); b++) {
                        if (token[0].equals(A_list.get(b))) {
                            for (int c = 0; c < Path_list.size(); c++) {
                                if ((token[0] + "#" + A_list.get(a)).equals(Path_list.get(c))) {
                                    Earliest.add(a, first.P_list.get(c).Pert + Earliest.get(b));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                } else {
                    for (int b = 0; b < token.length; b++) {
                        for (int c = 0; c < A_list.size(); c++) {
                            if (token[b].equals(A_list.get(c))) {
                                temp = Earliest.get(c);
                                for (int d = 0; d < Path_list.size(); d++) {
                                    if ((token[b] + "#" + A_list.get(a)).equals(Path_list.get(d))) {
                                        temp += first.P_list.get(d).Pert;
                                        break;
                                    }
                                }
                                if (temp > max) {
                                    max = temp;
                                }
                                break;
                            }
                        }
                    }
                    Earliest.add(a, max);
                }
            }
            if (A_list.size() == Earliest.size()) {
                break;
            }
        }
       
    }

    //function:to calculate the latest time
    //input:precedence list
    //output:latest duration  
    public void LatestEventTime() {
        ArrayList<Integer> temp1 = new ArrayList<>();
        String[] token;
        int min = 65536, temp;
        temp1.add(Earliest.get(Earliest.size() - 1));
        while (true) {
            for (int a = A_list.size() - 2; a >= 0; a--) {
                token = Succesor_list.get(a).split("/");
                if (token.length == 1) {
                    for (int b = A_list.size() - 1; b >= 0; b--) {
                        if (token[0].equals(A_list.get(b))) {
                            for (int c = 0; c < Path_list.size(); c++) {
                                if ((A_list.get(a) + "#" + token[0]).equals(Path_list.get(c))) {
                                    temp1.add(A_list.size() - 1 - a, temp1.get((A_list.size() - 1 - b)) - first.P_list.get(c).Pert);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                } else {
                    for (int b = 0; b < token.length; b++) {
                        for (int c = 0; c < A_list.size(); c++) {
                            if (token[b].equals(A_list.get(c))) {
                                temp = temp1.get((A_list.size() - 1 - c));
                                for (int d = 0; d < Path_list.size(); d++) {
                                    if ((A_list.get(a) + "#" + token[b]).equals(Path_list.get(d))) {
                                        temp -= first.P_list.get(d).Pert;
                                        break;
                                    }
                                }
                                if (temp < min) {
                                    min = temp;
                                }
                                break;
                            }
                        }
                    }
                    temp1.add(A_list.size() - 1 - a, min);
                }
            }
            if (A_list.size() == temp1.size()) {
                break;
            }
        }
        for (int a = temp1.size() - 1; a >= 0; a--) {
            Latest.add(temp1.get(a));
        }
    }

    //function:to calculate the total slack
    //input:path list
    //output:total slack
    public void TotalSlack() {
        String[] token;
       
        for (int a = 0; a < Path_list.size(); a++) {
            token = Path_list.get(a).split("#");
            TotalSlack.add(Latest.get(searchIndex(token[1])) - Earliest.get(searchIndex(token[0])) - first.P_list.get(a).Pert);
        }
    }

    //function:to calculate the free slack
    //input:total slack
    //output:free slack
    public void Freeslack() {
        String[] token1, token2;
        int counter;
        for (int a = 0; a < TotalSlack.size(); a++) {
            counter = 1;
            if (TotalSlack.get(a) != 0) {
                token2 = Path_list.get(a).split("#");
                token1 = Critical.split(" ");
                int cnt = token1.length;
                for (int b = 0; b < cnt; b++) {
                    if (Possible_Path_list.get(Integer.parseInt(token1[b])).contains(token2[1])) {
                        FreeSlack.add(TotalSlack.get(a));
                        counter = 0;
                        break;
                    }
                }
                if (counter != 0) {
                    FreeSlack.add(0);
                }
            } else {
                FreeSlack.add(0);
            }
        }
       
    }
    
    //function:to execute all the function
    //input:-
    //output:execute allthe function
    public void batch() throws FileNotFoundException
    {
        PathDetermination();
        CriticalPath();
        EarlistEventTime();
        SuccessorList();
        LatestEventTime();
        TotalSlack();
        Freeslack();
        output();
        outPDf();  
    }
    
}
