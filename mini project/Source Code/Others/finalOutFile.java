package projectcharter;
//Contribute by All
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.util.*;
import java.util.List;
import com.itextpdf.text.*;
import java.text.*;

import javax.swing.JOptionPane;

//To generate the PDF output file
public class finalOutFile extends PdfWriter
{
	private String ProjectName=new String();
	private String ProjectID=new String();
	private String ManagerName=new String();
	private String ContactNo=new String();
    private String CustomerName=new String();
    private String CustomerNo=new String();
	private String tmp=new String();
	private String Module3Time=new String();
	private String Module4Cost=new String();
	private String Module5Cost=new String();
	private String Module5Time=new String();
	private List<String> Activity=new ArrayList();
	
	//function:to generate the PDF file
    //input:filename
    //output:PDF file
	public void FOF(String ProjectFile) throws Exception
    {        
        CustomerName=JOptionPane.showInputDialog("Please key in the customer name");
        CustomerNo=JOptionPane.showInputDialog("Please key in the customer contact");
		
		this.inputInfo(ProjectFile);
        this.writePDF(ProjectFile);

    }
	
	//function:to read the information from input text file
    //input:input text file
    //output:all the information
	private void inputInfo(String ProjectFile) throws Exception
	{
		File Info=new File(ProjectFile);
		Scanner InfoIn=new Scanner(Info);
		int marker=0;
		
		ProjectID=InfoIn.nextLine();
		ProjectName=InfoIn.nextLine();
		ManagerName=InfoIn.nextLine();
		ContactNo=InfoIn.nextLine();
		tmp=InfoIn.nextLine();
		Module3Time=InfoIn.nextLine().split(":")[1];
		Module4Cost=InfoIn.nextLine().split(":")[1];
		Module5Cost=InfoIn.nextLine().split(":")[1];
		Module5Time=InfoIn.nextLine().split(":")[1];
                
        while(InfoIn.hasNextLine() && marker<2)
        {
            tmp=InfoIn.nextLine();
            if(tmp.equals("%%%%%"))
            {
            	marker++;
            	tmp=InfoIn.nextLine();
            	tmp=InfoIn.nextLine();
            	}
            if(marker==1)
            {
            	Activity.add(tmp.replace("*", ""));
            }
        }
        InfoIn.close();
	}
	
	//function:to generate the PDF file
    //input:filename, all of information
    //output:PDF file
	private void writePDF(String ProjectFile) throws Exception
	{
		File tmpF=new File("templateWithmarker.txt");
        Scanner Input=new Scanner(tmpF);
		
		String OutFile=ProjectFile.replace(".txt", ".pdf");
        Document D=new Document();
        PdfWriter.getInstance(D, new FileOutputStream(OutFile));
        
        Font normalfont=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL);
        Font boldfont=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
        Font italicfont=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.ITALIC);
        Paragraph G1=new Paragraph();
        Paragraph G2=new Paragraph();
        Paragraph G3=new Paragraph();
        G1.setFont(normalfont);
        G3.setFont(normalfont);
        Paragraph GALL=new Paragraph();
        
        D.open();
        
        Image img=Image.getInstance("Paradise.png");
        img.scaleAbsolute(100, 100);
        img.setAbsolutePosition(200f, 750f);
        
        D.add(img);
        D.add(new Paragraph("\n"));
        
        String line=new String();
        
        for(int a=0;a<70;a++)
        {
            line=line+"_";
        }
        
        D.add(new Paragraph(line));
        
        while(Input.hasNextLine())
        {
            tmp=Input.nextLine();
            
            if(tmp.contains("<Project_Name>"))
                tmp=tmp.replace("<Project_Name>", ProjectName);
            
            else if(tmp.contains("<Project_ID>"))
                tmp=tmp.replace("<Project_ID>", ProjectID);
            
            else if(tmp.contains("<Start_Date>"))
            {
                DateFormat DF=new SimpleDateFormat("dd/MM/yyyy");
                tmp=tmp.replace("<Start_Date>", DF.format(new Date()));
            }
            
            else if(tmp.contains("<End_Date>"))
            {
            	Date EndDate=new Date();
            	DateFormat DF=new SimpleDateFormat("dd/MM/yyyy");
            	Calendar c = Calendar.getInstance(); 
            	c.setTime(EndDate); 
            	c.add(Calendar.DATE, Integer.parseInt(Module3Time));
            	EndDate = c.getTime();
                tmp=tmp.replace("<End_Date>", DF.format(EndDate));
            }
            
            else if(tmp.contains("<Project_Scope>"))
            {
            	tmp="";
            	for(int a=0;a<Activity.size();a++)
            		tmp=tmp+"\n"+Activity.get(a);
            	tmp=tmp+"\n"+"\n";
            	normalfont=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
            }
            
            else if(tmp.contains("<Estimated_Cost>"))
            	tmp=tmp.replace("<Estimated_Cost>", "RM "+Module4Cost);
            
            else if(tmp.contains("<Estimated_Duration>"))
            	tmp=tmp.replace("<Estimated_Duration>", Module3Time+" days")+"\n"+"\n";
            
            if(tmp.contains("<Manager_Name>"))
            	tmp=tmp.replace("<Manager_Name>", ManagerName);
            
            if(tmp.contains("<Manager_Contact>"))
            	tmp=tmp.replace("<Manager_Contact>", ContactNo);
            
            if(tmp.contains("<Customer_Name>"))
            	tmp=tmp.replace("<Customer_Name>", CustomerName);
            
            if(tmp.contains("<Customer_Contact>"))
            	tmp=tmp.replace("<Customer_Contact>", CustomerNo);
            	
            if(tmp.contains("<i>"))
            {
                for(int a=0;a<tmp.split("<i>").length;a++)
                {
                	if(a==1)
                		D.add(new Chunk(tmp.split("<i>")[1],italicfont));
                	else if(a!=1)
                    	D.add(new Chunk(tmp.split("<i>")[a],normalfont));
                }
            }
            
            else if(tmp.contains("<b>"))
            {
                Paragraph P=new Paragraph();
                P.setAlignment(Element.ALIGN_CENTER);
            	for(int a=0;a<tmp.split("<b>").length;a++)
                {
                	if(a==1)
                		P.add(new Chunk(tmp.split("<b>")[1],boldfont));
                	else if(a!=1)
                    	P.add(new Chunk(tmp.split("<b>")[a],normalfont));
                }
            	D.add(P);
            	D.add(new Paragraph("\n"));
            }
            
            else
            	D.add(new Paragraph(tmp, normalfont));
            normalfont=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL);
        }
        
        D.close();
        Input.close();
        JOptionPane.showMessageDialog(null, "The Project Charter is printed already");
	}
}
