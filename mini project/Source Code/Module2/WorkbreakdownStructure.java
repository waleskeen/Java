package projectcharter;

import java.util.*;
import java.io.*;

public class WorkbreakdownStructure {
static ArrayList<String> W_list = new ArrayList<String>();
static ArrayList<String> A_list = new ArrayList<String>();
static ArrayList<String> G_list = new ArrayList<String>();

//function:to execute the wbs function
//input:-
//output:list of activity
    public void wbs() {
         
        

        try {
            InFile();
            //OutFile();
        } catch (Exception e) {

        }
       // Function();
        //insertdata();
    }

	//function:to read the input file
    //input:file name
    //output:list of activity
    public static void InFile() {
       
        File WBS = new File("wbs.txt");
        try {
            Scanner in = new Scanner(WBS);

            String str = in.nextLine();
            str = in.nextLine();
            int number = Integer.parseInt(str);
            for (int a = 0; a < number; a++) {
                W_list.add(in.nextLine());
            }

            in.close();
            //////////////////////////////////////
            for (int i = 0; i < W_list.size(); i++) {

                String kstr = W_list.get(i);
                 //System.out.println(kstr);

            }
            //////////////////////////////////////
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
            


            for (int a = 0; a < W_list.size(); a++) {
                //System.out.println("File not found");
                String kstr = W_list.get(a);
                if (kstr.charAt(0)=='*') {

                   A_list.add(kstr.replace("*", ""));
                   
                } else 
                   G_list.add(kstr);
                    
            }
            
        for (int a = 0; a < G_list.size(); a++)
            System.out.println(G_list.get(a));
        for (int b= 0; b < A_list.size(); b++)
            System.out.println(A_list.get(b));
            

    }
    
	//function:to store date into input file
    //input:list of activity
    //output:input file
private static void OutFile() throws IOException{

File F= new File("projectchosen.txt");
Scanner in;
in = new Scanner(F);
PrintWriter outputFile;
outputFile= new PrintWriter("tmp.txt");
int marker = 0;
String tmp= new String();
String temp= new String();

while (in.hasNextLine()) {
    if (marker != 1){
    tmp = in.nextLine();
    outputFile.println(tmp);
    
    }
 if (tmp.equals("%%%%%"))
 {
     marker++;
     tmp = in.nextLine();
     if (marker == 2)
         outputFile.println("%%%%%");
     outputFile.println();
 }
    
}
if (marker == 1)
if (tmp.equals("%%%%%")){
    System.out.println("please select for your parents");
    System.out.println(W_list);
    //temp.add();
    ;
}
}

}