package projectcharter;

import javax.swing.JOptionPane;
//Contribute by Chan Chee Keen

public class Module4 
{
    //function:to perform the main function for module4
    //input:filename
    //output:execute the module4
    public void Module4(String FileName)
    {
        Module4FileInOut F=new Module4FileInOut();
        Module4Project P=new Module4Project();
        try
        {
            F.filein(P, FileName);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        
        try
        {
            Module4Table T=new Module4Table(P, FileName);
        }
        catch(Exception e)
        {
        	System.out.println("ERROR");
        }
    }
}