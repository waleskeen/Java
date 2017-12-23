package projectcharter;
//Contribute By Foong Wai Lap
/**
 *
 * @author Foong Wai Lap
 */
/////////////////////////////insredata into string/////////////////////////////
import java.util.Scanner;
import java.io.*;

public class insertdata {
String tmp;
   
//function:to display all of result
//input:project list
//output:result
public void insert() {
    Scanner I = new Scanner(System.in);
   InfileOutfile insert=new InfileOutfile();
System.out.println("select for the parents");
for (int a=0; a<insert.allList.size(); a++){
System.out.println(insert.allList.get(a).ActivityID);
}
}

}


