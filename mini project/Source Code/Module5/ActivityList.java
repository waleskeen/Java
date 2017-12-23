/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcharter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ActivityList {
    private ArrayList <String> activityNo = new ArrayList<String>();
    private ArrayList <String> activityName = new ArrayList<String>();
    // private ArrayList <String> neededActivityName= new ArrayList<String>();
    private double totalPertDuration;
    private double totalAbcCost;
    
    
/*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: To get the ActivityNo size
 * Input: none
 * Output: size of activityNo (int)
 */
    public int getActivityNoSize()
    {
        return activityNo.size();
    }

    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: To get the ActivityNo
 * Input: none
 * Output: the activityNo (ArrayList<String>)
 */
    public ArrayList <String> getActivityNo ()
    {
        return activityNo;
    }
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: To get the ActivityName
 * Input: none
 * Output: list of the ActivityName (ArrayList<String>)
 */
    public ArrayList <String> getActivityName ()
    {
        return activityName;
    }
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: To read in textfile into respective containers
 * Input: 
 * - to store the cost values, ThreePointEstimates costEst
 * - to store the project duration values, ThreePointEstimates projBur
 * Output: boolean value (true/false) if read file is successful or not
 */
    
    public boolean readFile(ThreePointEstimates costEst, ThreePointEstimates projDur) throws FileNotFoundException
    {
        Scanner keyboard = new Scanner(System.in);
        
        String filename = "wbs.txt";
        File file = new File(filename);
        Scanner inputFile = new Scanner (file);
        
        Boolean marker = false;
        ArrayList<String> activityNo2 = new ArrayList ();
        int mark=0;
        int mark1=0;
        int mark2=0;
        
        while (inputFile.hasNext())
        {
            String temp = inputFile.next();
            
             if(temp.contains("Module3"))
            {
                String[] tmpArray=new String[2];
                tmpArray=temp.split(":");
                projDur.setValue(Double.parseDouble(tmpArray[1]));
                temp = inputFile.next();
            }
            
            if(temp.contains("Module4:"))
            {
                String[] tmpArray=new String[2];
                tmpArray=temp.split(":");
                costEst.setValue(Double.parseDouble(tmpArray[1]));
                temp = inputFile.next();
            }
           
            if (temp.equals("%%%%%"))
                marker=true;
            
            while (marker)
            {
                temp= inputFile.next();
                
                if (temp.equals("%%%%%")||mark==2)
                {
                    marker=false;
                    mark++;
                    break;
                }
                
                if (temp.contains("*"))
                {
                    temp = temp.replace("*","");
                    activityNo.add(temp);
                    temp= inputFile.nextLine();
                    
                    activityName.add(temp);
                }
                else
                {
                    temp= inputFile.nextLine();
                }
            }
            
            if (temp.equals("$$"))
            {
                marker=true;
                temp= inputFile.next();
                if (temp.equals("$$"))
                {
                    for (int a=0; a<activityNo.size(); a++)
                    {
                        activityNo2.add(activityNo.get(a));
                        ArrayList<Double> duration = new ArrayList<Double>();
                        for (int z=0; z<3; z++)
                        {
                            duration.add(0.0);
                            
                        }
                        projDur.addPointEstimate(duration);
                        System.out.println(duration);
                        
                        projDur.addProbability(0.0);
                        
                        ArrayList<Double> cost = new ArrayList<Double>();
                        
                        for (int z=0; z<3; z++)
                        {
                            cost.add(0.0);
                        }
                        
                        costEst.addPointEstimate(cost);
                        costEst.addProbability(0.0);
                    }
                }
                
                else
                {
                    while (marker)
                    {
                        
                        if (temp.equals("$$")|| mark==2)
                        {
                            marker=false;
                            mark2++;
                            break;
                        }
                        
                        activityNo2.add(temp);
                        ArrayList<Double> duration = new ArrayList<Double>();
                        for (int z=0; z<3; z++)
                        {
                            temp=inputFile.next();
                            duration.add(Double.valueOf(temp));
                        }
                        
                        projDur.addPointEstimate(duration);
                        
                        
                        
                        temp=inputFile.next();
                        projDur.addProbability(Double.valueOf(temp));
                        
                        
                        ArrayList<Double> cost = new ArrayList<Double>();
                        
                        for (int a=0; a<=2; a++)
                        {
                            temp=inputFile.next();
                            cost.add(Double.valueOf(temp));
                            
                        }
                        
                        costEst.addPointEstimate(cost);
                        
                        temp=inputFile.next();
                        costEst.addProbability(Double.valueOf(temp));
                        temp=inputFile.next();
                        
                    }
                }
            }
        }
        
        // Close the file.
        inputFile.close();
        
        //Show to screen
        for (int a=0; a<activityNo.size(); a++)
        {
            String b = activityNo.get(a);
            String c= activityName.get(a);
            System.out.print(b);
            System.out.println(c);
        }
        
        for (int b=0; b<costEst.getSizePointEstimate(); b++)
        {
            for (int c=0; c<costEst.getPointEstimate(b).size(); c++)
            {
                System.out.print(costEst.getPointEstimate(b).get(c));
                System.out.print(" ");
            }
            
            System.out.print(" ");
            
            System.out.println(costEst.getprobability(b));
        }
        
        for(int d=0; d<projDur.getSizePointEstimate(); d++)
        {
            for (int e=0; e<projDur.getPointEstimate(d).size(); e++)
            {
                System.out.print(projDur.getPointEstimate(d).get(e));
                System.out.print(" ");
            }
            System.out.print(" ");
            System.out.println(projDur.getprobability(d));
            
        }
        if ((activityNo.size()==activityName.size()) && (activityNo.size()==projDur.getSizePointEstimate()) && (activityNo.size()==costEst.getSizePointEstimate()))
            return true;
        else
            return false;
        
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: To write out from containers into the textfile
 * Input: 
 * - to store the cost values, ThreePointEstimates costEst
 * - to store the project duration values, ThreePointEstimates projBur
 * Output: boolean value (true/false) if write to file is successful or not
 */
    public boolean writeFile(ThreePointEstimates costEst, ThreePointEstimates projDur) throws FileNotFoundException
    {
        Scanner keyboard2 = new Scanner(System.in);
        
        String filename2 = "wbs.txt";
        
        File file2 = new File(filename2);
        Scanner inputFile2 = new Scanner (file2);
        boolean mark1 =true;
        
        
        PrintWriter oFile = new PrintWriter("tmp.txt");
        
        String temp2 = inputFile2.nextLine();
        while (inputFile2.hasNext())
        {
            if (temp2.equals("$$"))
            {
                
                oFile.println(temp2);
                
                temp2 = inputFile2.nextLine();
                if(temp2.equals("$$"))
                {
                    for (int b=0; b<costEst.getSizePointEstimate(); b++)
                    {
                        for (int c=0; c<costEst.getPointEstimate(b).size(); c++)
                        {
                            
                            oFile.print(costEst.getPointEstimate(b).get(c));
                            
                            oFile.print(" ");
                        }
                        
                        oFile.print(" ");
                        
                        
                        oFile.println(costEst.getprobability(b));
                    }
                    
                    
                    
                    for(int d=0; d<projDur.getSizePointEstimate(); d++)
                    {
                        for (int e=0; e<projDur.getPointEstimate(d).size(); e++)
                        {
                            
                            oFile.print(projDur.getPointEstimate(d).get(e));
                            oFile.print(" ");
                        }
                        oFile.print(" ");
                        
                        
                        oFile.println(projDur.getprobability(d));
                    }
                }
            }
            
            
            oFile.println(temp2);
            if (inputFile2.hasNext())
                temp2 = inputFile2.nextLine();
            
        }
        inputFile2.close();
        oFile.close();
        
        
        File oldName = new File("tmp.txt");
        File newName = new File("wbs.txt");
        if(oldName.renameTo(newName)) {
            //System.out.println("renamed");
        } else {
            System.out.println("Error");
        }
        return true;
        
    }
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: To edit textfile 
 * Input: 
 * - to store the cost values, ThreePointEstimates costEst
 * - to store the project duration values, ThreePointEstimates projBur
 * Output: boolean value (true/false) if edit file is successful or not
 */
    public boolean editFile(ThreePointEstimates costEst, ThreePointEstimates projDur) throws FileNotFoundException
    {
        Scanner keyboard2 = new Scanner(System.in);
        String filename2 = "wbs.txt";
        File file2 = new File(filename2);
        Scanner inputFile2 = new Scanner (file2);
        boolean mark1 =true;
        DecimalFormat numberFormat= new DecimalFormat("##.#");
        
        PrintWriter oFile = new PrintWriter("tmp.txt");
        
        String temp2 = inputFile2.nextLine();
        while (inputFile2.hasNext())
        {
            if (temp2.contains("Module5C:"))
            {
               
                String[] tmpArray=new String[2];
                tmpArray=temp2.split(":");
                tmpArray[1]=String.valueOf(numberFormat.format(costEst.getValue()));
                  temp2=tmpArray[0]+":"+tmpArray[1];
                oFile.println(temp2);
                 temp2 = inputFile2.nextLine();
                
            }
            
            if (temp2.contains("Module5T:"))
            {
                String[] tmpArray=new String[2];
                tmpArray=temp2.split(":");
                tmpArray[1]=String.valueOf(numberFormat.format(projDur.getValue()));
                  temp2=tmpArray[0]+":"+tmpArray[1];
                oFile.println(temp2);
                temp2 = inputFile2.nextLine();
            }
            
            if (temp2.equals("$$"))
            {
                oFile.println(temp2);
                
                temp2 = inputFile2.nextLine();
                if(temp2.equals("$$"))
                {
                    for (int b=0; b<costEst.getSizePointEstimate(); b++)
                    {
                        oFile.print(activityNo.get(b));
                        oFile.print(" ");
                        
                        
                        for (int e=0; e<projDur.getPointEstimate(b).size(); e++)
                        {
                            
                            oFile.print(projDur.getPointEstimate(b).get(e));
                            oFile.print(" ");
                        }
                        
                        oFile.print(projDur.getprobability(b));
                        oFile.print(" ");
                        
                        for (int c=0; c<costEst.getPointEstimate(b).size(); c++)
                        {
                            
                            oFile.print(costEst.getPointEstimate(b).get(c));
                            
                            oFile.print(" ");
                        }
                        oFile.print(costEst.getprobability(b));
                        oFile.println();
                        
                    }
                    oFile.println();
                }
                else
                {
                    while(!temp2.equals("$$"))
                    {
                        temp2 = inputFile2.nextLine();
                    }
                    for (int b=0; b<costEst.getSizePointEstimate(); b++)
                    {
                        oFile.print(activityNo.get(b));
                        oFile.print(" ");
                        
                        for (int e=0; e<projDur.getPointEstimate(b).size(); e++)
                        {
                            oFile.print(projDur.getPointEstimate(b).get(e));
                            oFile.print(" ");
                        }
                        
                        oFile.print(projDur.getprobability(b));
                        oFile.print(" ");
                        for (int c=0; c<costEst.getPointEstimate(b).size(); c++)
                        {
                            oFile.print(costEst.getPointEstimate(b).get(c));
                            oFile.print(" ");
                        }
                        oFile.print(costEst.getprobability(b));
                        
                        oFile.println();
                    }
                }
            }
            
            oFile.println(temp2);
            if (inputFile2.hasNext())
                temp2 = inputFile2.nextLine();
        }
        
        // Close the file.
        inputFile2.close();
        oFile.close();
        boolean success = (new File
                ("wbs.txt")).delete();
        
        if (success)
        {
            System.out.println("The file has been successfully deleted");
        }
        
        File oldName = new File("tmp.txt");
        File newName = new File("wbs.txt");
        if(oldName.renameTo(newName)) {
            System.out.println("renamed");
        } else {
            System.out.println("Error");
        }
        return true;
        
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: To display contents in respective containers
 * Input: 
 * - to store the cost values, ThreePointEstimates costEst
 * - to store the project duration values, ThreePointEstimates projBur
 * Output: none
 */ 
    public void displayContents(ThreePointEstimates costEst, ThreePointEstimates projDur)
    {
        for (int a=0; a<activityNo.size(); a++)
        {
            String b = activityNo.get(a);
            String c= activityName.get(a);
            System.out.print(b);
            System.out.println(c);
        }
        
        for (int b=0; b<costEst.getSizePointEstimate(); b++)
        {
            for (int c=0; c<costEst.getPointEstimate(b).size(); c++)
            {
                System.out.print(costEst.getPointEstimate(b).get(c));
                System.out.print(" ");
            }
            
            System.out.print(" ");
            
            System.out.println(costEst.getprobability(b));
        }
        
        for(int d=0; d<projDur.getSizePointEstimate(); d++)
        {
            for (int e=0; e<projDur.getPointEstimate(d).size(); e++)
            {
                System.out.print(projDur.getPointEstimate(d).get(e));
                System.out.print(" ");
            }
            System.out.print(" ");
            System.out.println(projDur.getprobability(d));
            
        }
    }
    
}
