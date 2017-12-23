package projectcharter;
//Contribute by Chin Kian Zhong
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class first extends JFrame implements ActionListener {

    protected static ArrayList<PERT> P_list = new ArrayList<>();
    ArrayList<JTextField> TextList = new ArrayList();
    ArrayList<JTextField> TextAreaList = new ArrayList();
    JTextField Text;
    JTextField TextArea;
    JButton generate = new JButton("Generate");
    JLabel precedence = new JLabel("Precedence ");
    JLabel activity = new JLabel("Activities  ");
    JLabel condition = new JLabel("Ps: used \"/\" to seperate");
    JPanel P1 = new JPanel();
    JPanel P2 = new JPanel();
    JPanel P3 = new JPanel();
    JPanel P4 = new JPanel();
    Container C;
    
    public first() {

       PERT.InFile();
        C = this.getContentPane();

        C.setLayout(new BoxLayout(C, BoxLayout.Y_AXIS));
        P3.setLayout(new FlowLayout(10));
        P4.setLayout(new BoxLayout(P4, BoxLayout.Y_AXIS));
        P1.setLayout(new BoxLayout(P1, BoxLayout.Y_AXIS));
        P2.setLayout(new BoxLayout(P2, BoxLayout.Y_AXIS));

        P2.add(precedence);
        P1.add(activity);
        for (int a = 0; a < PERT.A_list.size(); a++) {
            Text = new JTextField();
            Text.setText(PERT.A_list.get(a));
            Text.setEditable(false);

            TextArea = new JTextField(15);
            TextArea.setEditable(true);

            TextList.add(Text);
            TextAreaList.add(TextArea);

            P1.add(Text);
            P2.add(TextArea);

        }

        TextAreaList.get(0).setEditable(false);
        generate.addActionListener(this);

        P3.add(P1);
        P3.add(P2);
        P4.add(P3);
        P4.add(condition);
        P4.add(generate);
        C.add(P4);

        this.setVisible(true);

        pack();
    }

    //function:to generate the precedence list
    //input:-
    //output:precedence list
    public boolean generatePrecedencelist() {
        boolean cnt = false;
        
        for (int a = 0; a < TextAreaList.size(); a++) {
            
            if (!TextAreaList.get(a).getText().isEmpty()) {
                PERT.Precedence_list.add(TextAreaList.get(a).getText().trim());
            }
            if(a==TextAreaList.size()-1&&PERT.Precedence_list.size()==TextAreaList.size()-1)
                PERT.Precedence_list.add(0,"null");
        }
        if (PERT.Precedence_list.size() == TextAreaList.size()) {
            cnt = true;
        }

        return cnt;
    }

    //function:to perform action perform for generate result
    //input:button
    //output:all the result
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generate) {
            PERT p = new PERT();
            
            generatePrecedencelist();

            if (PERT.Precedence_list.size() == 0) {
                JOptionPane.showMessageDialog(null, "No precedence is set");
            } else {
                p.GeneratePath_list();
                second S = new second();
            }
        }
    }
}
