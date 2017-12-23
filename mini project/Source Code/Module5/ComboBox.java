/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcharter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Contributed by Brenda Lee Hooi Fern

    public class ComboBox extends JPanel
                          implements ActionListener {
    JTextField textfield;
    JTextField textfield2;
    JTextField textfield3;
    JTextField textfield4;
    String selectedItem;
    int selection;

    //public ComboBox(JTextField text,JTextField text2, JTextField text3, JTextField text4) {
    public ComboBox() {
        super(new BorderLayout());

        /*textfield=text;
        textfield2=text2;
        textfield3=text3;
        textfield4=text4;
        */
        String[] dropDown = {"days", "hours","years"};

        JComboBox dropDownList = new JComboBox(dropDown);
        dropDownList.setSelectedIndex(0); 
        dropDownList.addActionListener(this);

        add(dropDownList, BorderLayout.PAGE_START);
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        selectedItem = (String)cb.getSelectedItem();
        updateLabel(selectedItem);
    }

    protected void updateLabel(String type) {
        /*
        textfield.setText(type);
        textfield2.setText(type); 
        double newtime= Double.parseDouble(textfield3.getText());
        double newtime2= Double.parseDouble(textfield4.getText());
        */
        if (type.equals("hours"))
        {
            /*
            textfield3.setText(String.valueOf(newtime*24));
            textfield4.setText(String.valueOf(newtime2*24));
            */
            selection=1;
        }
        else if (type.equals("years"))
        {
            /*
            textfield3.setText(String.valueOf(newtime/365));
            textfield4.setText(String.valueOf(newtime2/365));
            */
            selection=2;
        }
        else if (type.equals("days"))
        {
            /*
            textfield3.setText(String.valueOf(newtime));
            textfield4.setText(String.valueOf(newtime2));
            */
            selection=3;
        }
        else
            selection=1;
    }
    
   
}
    