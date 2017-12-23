/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcharter;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
 
public class LoadImageApp extends Component {
           
    BufferedImage img;
 
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: paint image
 * Input: the picture
 * Output: none
 */
    public void paint(Graphics g) {
        g.drawImage(img,5, 5, null);
    }
 
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: Initialize LoadImageApp by loading picture
 * Input: none 
 * Output: none
 */
    public LoadImageApp() {
       try {
           img = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\3pntest.jpg"));
       } catch (IOException e) {
       }
 
    }
 
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: set size of the image
 * Input: none
 * Output: dimenstion of the image
 */
    
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
}