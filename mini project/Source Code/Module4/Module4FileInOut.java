package projectcharter;
//Contribute by Chan Chee Keen
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.text.*;

public class Module4FileInOut 
{
    private File F;
    private File tmpFile = new File("tmp.txt");

    private List<List<Module4Activity>> activityListList;
    private List<Module4Activity> activityList;
    private Module4Activity activity;
    private Module4ActivityYear year;
    private Module4ActivityMonth month;
    private String tmp = new String();
    private int[] marker = {0, 0, 0};
    public static List<Module4Activity> EList=new ArrayList();
    private List<Module4Activity> FList=new ArrayList();
    public static int maxYear=0;

    //function:to get the data from input file
    //input:File Name
    //output:all the activity and the cost
    public void filein(Module4Project P, String FileName) throws IOException 
    {
        F=new File(FileName);
    	Scanner IF = new Scanner(F);
        marker[0]=0;
        marker[1]=0;
        activityListList = new ArrayList();
        activityList = new ArrayList();
        maxYear=0;
        
        while(IF.hasNext())
        {
            tmp=IF.nextLine();

            if(tmp.equals("%%%%%"))
            {
                marker[0]++;
            }
            
            else if(tmp.equals("=.="))
            {
                marker[1]++;
            }
            
            while(IF.hasNext() && marker[0]==1)
            {
                int size=IF.nextInt();
                tmp = IF.nextLine();
                tmp = IF.nextLine();
                
                for(int a=0;a<size;a++)
                {
                    activity = new Module4Activity();
                    activity.setActivityID(tmp.split(" ", 0)[0]);
                    activity.setActivityName(tmp.substring(tmp.indexOf(" ") + 1));
                    tmp = IF.nextLine();
                    
                    addlist();
                }
                    
                if (tmp.equals("%%%%%")) 
                {
                    marker[0]++;

                   changetoprojectlist(P);
                }
            }
            
            while(IF.hasNext() && marker[1]==1)
            {
                activity=new Module4Activity();
                activityList=new ArrayList();
                activityListList=new ArrayList();
                EList=new ArrayList();        
                                
                DFS(P);
                
                for(int a=0;a<EList.size();a++)
                {
                    EList.get(a).subActivity.clear();
                }
                
                tmp=IF.next();
                
                for(int a=0;a<EList.size();a++)
                {
                    activity=EList.get(a);
                    if(activity.getActivityID().replace("*", "").equals(tmp))
                    { 
                        marker[2]=0;
                        tmp=IF.next();
                        while (IF.hasNext() && marker[2] == 0) 
                        {                            
                            year = new Module4ActivityYear();
                            year.setYear(Integer.parseInt(tmp));
                            
                            if(maxYear<year.getYear())
                                maxYear=year.getYear();

                            for (int b = 0; b <= 12; b++) 
                            {
                                tmp = IF.next();

                                if (tmp.equals("--")) 
                                {
                                    break;
                                } 

                                else 
                                {
                                    month = new Module4ActivityMonth();
                                    month.setMonth(Integer.parseInt(tmp));                                    
                                    year.Month.add(month);
                                }
                            }

                            for (int b = 0; b < year.Month.size(); b++) 
                            {
                                tmp = IF.next();
                                year.Month.get(b).setBudgetCost(Double.parseDouble(tmp));
                            }

                            for (int b = 0; b < year.Month.size(); b++) 
                            {
                                tmp = IF.next();
                                year.Month.get(b).setAC(Double.parseDouble(tmp));
                            }

                            for (int b = 0; b < year.Month.size(); b++) 
                            {
                                tmp = IF.next();
                                year.Month.get(b).setRP(Double.parseDouble(tmp));
                            }

                            activity.Year.add(year);
                            
                            tmp = IF.next();

                            if (tmp.equals("==")) 
                            {
                                marker[2]++;
                                tmp = IF.next();
                            }
                        }
                    }
                    addlist();
                }
                
                changetoprojectlist(P);
                
                if(tmp.equals("=.="))
                {
                    marker[1]++;
                }
            }
        }
        IF.close();
        JOptionPane.showMessageDialog(null, "This file is read into system.");
    }
    
    //function:to store data into file
    //input:activity, file name, cost
    //output:the file has been saved
    public void fileout(Module4Project P, String FileName) throws Exception
    {                        
        F=new File(FileName);
    	Scanner InputFile;
        InputFile = new Scanner(F);
        
        marker[0]=0;
        
        PrintWriter OutputFile;
        OutputFile = new PrintWriter(tmpFile);
        
        activityListList=new ArrayList();
        activityList=new ArrayList();
        activity=new Module4Activity();
        
        DFS(P);
        
        for(int a=0;a<EList.size();a++)
        {
            EList.get(a).subActivity.clear();
        }
        
        while (InputFile.hasNext()) 
        {            
            if(tmp.equals("=.="))
            {
                marker[0]++;
            }
            
            if (marker[0] == 1) 
            {                
                for(int a=0;a<EList.size();a++)
                {
                    if(EList.get(a).getActivityID().contains("*"))
                    {
                        OutputFile.println(EList.get(a).getActivityID().replace("*", ""));
                        
                        for(int b=0;b<EList.get(a).Year.size();b++)
                        {
                            OutputFile.println(EList.get(a).Year.get(b).getYear());
                            
                            for(int c=0;c<EList.get(a).Year.get(b).Month.size();c++)
                            {
                                OutputFile.print(EList.get(a).Year.get(b).Month.get(c).getMonth() + " ");
                            }
                            
                            OutputFile.println("--");
                            
                            for(int c=0;c<EList.get(a).Year.get(b).Month.size();c++)
                            {
                                OutputFile.print(EList.get(a).Year.get(b).Month.get(c).getBudgetCost() + " ");
                            }
                            
                            OutputFile.println();
                            
                            for(int c=0;c<EList.get(a).Year.get(b).Month.size();c++)
                            {
                                OutputFile.print(EList.get(a).Year.get(b).Month.get(c).getAC() + " ");
                            }
                            
                            OutputFile.println();
                            
                            for(int c=0;c<EList.get(a).Year.get(b).Month.size();c++)
                            {
                                OutputFile.print(EList.get(a).Year.get(b).Month.get(c).getRP() + " ");
                            }
                            
                            OutputFile.println();
                        }
                        OutputFile.println("==");
                        OutputFile.println();
                    }
                }
                
                tmp=InputFile.nextLine();
                
                while(!tmp.equals("=.="))
                {
                    tmp=InputFile.nextLine();
                }
                OutputFile.println("=.=");
            }
            
            else if(marker[0] != 1)
            {
                tmp = InputFile.nextLine();
                
                if(tmp.contains("Module4:"))
                {
                    String[] tmpArray=new String[2];
                    tmpArray=tmp.split(":");
                    DecimalFormat DF2=new DecimalFormat("0.00");
                    tmpArray[1]=DF2.format(P.calculateTotalBudgetCost());
                    tmp=tmpArray[0]+":"+tmpArray[1];
                }
                
                OutputFile.println(tmp);
            }
        }
        InputFile.close();
        OutputFile.close();
        
        try
        {
            F.delete();
        }
        catch(Exception e)
        {
            System.out.println("ERROR");
        }
        
        try
        {
            tmpFile.renameTo(F);
        }
        catch(Exception e)
        {
            System.out.println("ERROR");
        }
    }
    
    //function:to add the list from activity
    //input:activity
    //output:the activity has been added
    public void addlist()
    {
        if (activityListList.isEmpty()) 
        {
            activityList = new ArrayList();
            activityList.add(activity);
            activityListList.add(activityList);
        } 
                  
        else if (activity.getActivityID().length()-activity.getActivityID().replace(".", "").length()==0) 
        {
            activityListList.get(0).add(activity);
            for (int b = 0; b < activityListList.get(0).size(); b++) 
            {
                try 
                {
                    int i = Integer.parseInt(activityListList.get(0).get(b).getActivityID());
                }
                catch (Exception e)
                {
                    //activityListList.get(0).remove(b);
                }
            }
        } 
                    
        else
        {
            activityList = new ArrayList();
            activityList.add(activity);
            activityListList.add(activityList);
        }
    }
    
    //function:to store the child list into the parent list
    //input:activity list
    //output:the activity list has been arranged
    public void changetoprojectlist(Module4Project P)
    {
        for (int b = 1; b < activityListList.size() - 1; b++) 
        {
            if(activityListList.get(b).get(0).Year.isEmpty())
            {
                for(int c=1;c<=maxYear;c++)
                {
                    Module4ActivityYear tmpY=new Module4ActivityYear();
                    Module4TotalCostPerYear tmpTY=new Module4TotalCostPerYear();
                    tmpY.setYear(c);
                    tmpTY.setYear(c);

                    for(int d=1;d<=12;d++)
                    {
                        Module4ActivityMonth tmpM=new Module4ActivityMonth();
                        Module4TotalCostPerMonth tmpTM=new Module4TotalCostPerMonth();
                        tmpM.setMonth(d);
                        tmpTM.setMonth(d);
                        tmpY.Month.add(tmpM);
                        tmpTY.TotalCostPerMonth.add(tmpTM);
                    }
                    
                    activityListList.get(b).get(0).Year.add(tmpY);
                    P.TotalCostPerYear.add(tmpTY);
                }
            }
            
            if ((activityListList.get(b).get(0).getActivityID().length()
                   - activityListList.get(b).get(0).getActivityID().replace(".", "").length())
                   - (activityListList.get(b + 1).get(0).getActivityID().length()
                   - activityListList.get(b + 1).get(0).getActivityID().replace(".", "").length())
                    == -1)
            {
                  activityListList.get(b).get(0).subActivity.add(activityListList.get(b + 1).get(0));
                  activityListList.remove(b + 1);
                  b--;
            }
        }

        for (int b = 0; b < activityListList.get(0).size(); b++) 
        {
            if(activityListList.get(0).get(b).Year.isEmpty())
            {
                for(int c=1;c<=maxYear;c++)
                {
                    Module4ActivityYear tmpY=new Module4ActivityYear();
                    tmpY.setYear(c);
                    
                    for(int d=1;d<=12;d++)
                    {
                        Module4ActivityMonth tmpM=new Module4ActivityMonth();
                        tmpM.setMonth(d);
                        tmpY.Month.add(tmpM);
                    }
                    
                    activityListList.get(0).get(b).Year.add(tmpY);
                }
            }
            
            for (int c = 1; c < activityListList.size(); c++)
            {
                if (((activityListList.get(0).get(b).getActivityID().length()
                        - activityListList.get(0).get(b).getActivityID().replace(".", "").length())
                        - (activityListList.get(c).get(0).getActivityID().length()
                        - activityListList.get(c).get(0).getActivityID().replace(".", "").length()) == -1)
                        && activityListList.get(0).get(b).getActivityID().replace("*", "")
                        .equals(activityListList.get(c).get(0).getActivityID().split("\\.")[0].replace("*", ""))) 
                {
                    activityListList.get(0).get(b).subActivity.add(activityListList.get(c).get(0));
                    activityListList.remove(c);
                    c--;
                }
            }
        }
        P.Activity = activityListList.get(0);
    }
    
    //function:to use the depth first search to take out all of the sub activity into 1 list
    //input:activity
    //output:all of the activity in 1 list
    public void DFS(Module4Project P)
    {
        FList=new ArrayList();
        EList=new ArrayList();
        for(int a=P.Activity.size()-1;a>=0;a--)
        {
            FList.add(P.Activity.get(a));
        }
              
        while(!FList.isEmpty())
        {
            activity=FList.get(FList.size()-1);
            EList.add(activity);
            FList.remove(FList.size()-1);
                    
            for(int a=activity.subActivity.size()-1;a>=0;a--)
            {
                FList.add(activity.subActivity.get(a));
            }
        }
    }
    
    public void EListTOProject(Module4Project P)
    {
        activityListList=new ArrayList();
        activityList=new ArrayList();
        for(int a=0;a<EList.size();a++)
        {
            EList.get(a).subActivity.clear();
            activity=new Module4Activity();
            activity=EList.get(a);
            addlist();
        }
        P.Activity.clear();
        changetoprojectlist(P);
    }
}