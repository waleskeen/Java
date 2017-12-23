package projectcharter;
//Contribute By Foong Wai Lap
/**
 *
 * @author Foong Wai Lap
 */

import java.util.*;
import java.io.*;
//import static projectcharter.WBS.insert_list;

//Create class to execute the file function
public class InfileOutfile {

    static ArrayList<String> W_list = new ArrayList<String>();
    public static List<List<savestring>> activityListList = new ArrayList();
    public List<savestring> activityList = new ArrayList();
    public static List<savestring> allList = new ArrayList();
    public savestring Activity;
    String tmp;
    String str;

    //function:To store the file into program
    //input:input text file
    //output:activity list
    public void InFile(List<savestring> AV) {

        File WBS = new File("wbs.txt");
        try {
            Scanner in = new Scanner(WBS);
            
            
            while(in.hasNextLine())
            {
                tmp=in.nextLine();
            if (tmp.equals( "%%%%%"))
            {
           str= in.nextLine();

            int number = Integer.parseInt(str);
            for (int a = 0; a < number; a++) {
                W_list.add(in.nextLine());
            }
        }

            in.close();
           
            //////////////////////////////////////
            for (int i = 0; i < W_list.size(); i++) {
                String kstr = W_list.get(i);
                Activity = new savestring();
                Activity.ActivityID = kstr;
                addlist(Activity);
            }
                            savelist();
        }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    

    //functionTo execute adding activity into list
    //input:every activity
    //output:the activity is added according the sequence
    public void addlist(savestring activity) {
        if (activityListList.isEmpty()) {
            activityList = new ArrayList();
            activityList.add(activity);
            activityListList.add(activityList);
        } else if (activity.ActivityID.split("\\.")[1].charAt(0)=='0') {
            activityListList.get(0).add(activity);
        } else {
            activityList = new ArrayList();
            activityList.add(activity);
            activityListList.add(activityList);
        }
    }

    //functionTo manage the activityList
    //input:all the activity list haven't put into activity group
    //output:the activity list has been put into activity group
    public void savelist() {
       for(int a=0;a<activityListList.size();a++)
        {
            allList=activityListList.get(0);
        }
        
        
        for (int b = 1; b < activityListList.size() - 1; b++) {
            if ((activityListList.get(b).get(0).ActivityID.length()
                    - activityListList.get(b).get(0).ActivityID.replace(".", "").length())
                    - (activityListList.get(b + 1).get(0).ActivityID.length()
                    - activityListList.get(b + 1).get(0).ActivityID.replace(".", "").length())
                    == -1) {
                activityListList.get(b).get(0).subActivity.add(activityListList.get(b + 1).get(0));
                activityListList.remove(b + 1);
                b--;
            }
        }

        for (int b = 0; b < activityListList.get(0).size(); b++) {
            for (int c = 1; c < activityListList.size(); c++) {
                if (((activityListList.get(0).get(b).ActivityID.length()
                        - activityListList.get(0).get(b).ActivityID.replace(".", "").length())
                        - (activityListList.get(c).get(0).ActivityID.length()
                        - activityListList.get(c).get(0).ActivityID.replace(".", "").length()) == 0)
                        && activityListList.get(0).get(b).ActivityID.split("\\.")[0].replace("*", "")
                        .equals(activityListList.get(c).get(0).ActivityID.split("\\.")[0].replace("*", ""))) {
                    activityListList.get(0).get(b).subActivity.add(activityListList.get(c).get(0));
                    activityListList.remove(c);
                    c--;
                }
            }
        }
    }
    
    //function:To store the system into file
    //input:activity list
    //output:the text file
    public static void OutFile(ArrayList<String> insert_list) throws IOException {
        File F = new File("1.txt");
        Scanner in;
        in = new Scanner(F);
        PrintWriter outputFile;
        outputFile = new PrintWriter("tmp.txt");
        int marker = 0;
        String tmp = new String();

        while (in.hasNext()) {
            if(tmp.equals("%%%%%"))
                marker++;
            if(marker==1)
            {
                outputFile.println(insert_list.size());
                for(int a=0;a<insert_list.size();a++)
                {
                    outputFile.println(insert_list.get(a));
                }
                tmp=in.nextLine();
                
                while(!tmp.equals("%%%%%"))
                    tmp=in.nextLine();
                outputFile.println("%%%%%");
            }
            else if(marker!=1)
            {
                tmp=in.nextLine();
                outputFile.println(tmp);
            }
}
        in.close();
        outputFile.close();
        boolean success = (new File("1.txt")).delete();

        if (success) {
            System.out.println("The file has been successfully deleted");
        }

        File oldName = new File("tmp.txt");
        File newName = new File("1.txt");
        if (oldName.renameTo(newName)) {
            System.out.println("renamed");
        } else {
            System.out.println("Error");
        }
    }}