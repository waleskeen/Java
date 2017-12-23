package projectcharter;
//Contribute by Chin Kian Zhong

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class second extends JFrame implements ActionListener {

    ArrayList<JTextField> TextList = new ArrayList();
    ArrayList<JTextField> Opti_list = new ArrayList();
    ArrayList<JTextField> Most_list = new ArrayList();
    ArrayList<JTextField> Pessi_list = new ArrayList();
    String[] unknown;
    JTextField Text;
    JTextField O;
    JTextField M;
    JTextField P;
    JLabel Paths = new JLabel("Paths");
    JLabel Optimistic = new JLabel("Optimistic");
    JLabel Most = new JLabel("Most Likely");
    JLabel Pess = new JLabel("Pessimistic");
    JButton generate = new JButton("Generate");
    JPanel P1 = new JPanel();
    JPanel P2 = new JPanel();
    JPanel P3 = new JPanel();
    JPanel P4 = new JPanel();
    JPanel P5 = new JPanel();
    JPanel P6 = new JPanel();
    Container C;

	//function:the constructor is to create the GUI
    //input:-
    //output:the GUI is created
    public second() {
        C = this.getContentPane();
        P5.setLayout(new FlowLayout(10));
        P6.setLayout(new BoxLayout(P6, BoxLayout.Y_AXIS));
        P1.setLayout(new BoxLayout(P1, BoxLayout.Y_AXIS));
        P2.setLayout(new BoxLayout(P2, BoxLayout.Y_AXIS));
        P3.setLayout(new BoxLayout(P3, BoxLayout.Y_AXIS));
        P4.setLayout(new BoxLayout(P4, BoxLayout.Y_AXIS));
        P1.add(Paths);
        P2.add(Optimistic);
        P3.add(Most);
        P4.add(Pess);
        for (int a = 0; a < PERT.Path_list.size(); a++) {

            Text = new JTextField();
            unknown = PERT.Path_list.get(a).split("#");
            Text.setText(unknown[0] + "--->" + unknown[1]);
            Text.setEditable(false);

            O = new JTextField(4);
            M = new JTextField(4);
            P = new JTextField(4);

            O.setEditable(true);
            M.setEditable(true);
            P.setEditable(true);

            TextList.add(Text);
            Opti_list.add(O);
            Most_list.add(M);
            Pessi_list.add(P);

            P1.add(Text);
            P2.add(O);
            P3.add(M);
            P4.add(P);
        }
        P5.add(P1);
        P5.add(P2);
        P5.add(P3);
        P5.add(P4);
        P6.add(P5);
        P6.add(generate);
        generate.addActionListener(this);

       
        C.add(P6);
        this.setVisible(true);
        pack();
    }

	//function:to execute the GUI
    //input:-
    //output:the GUI is created
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	//function:to perform the action perform
    //input:component from the GUI
    //output:the action is performed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generate) {
            PERT p=new PERT();
            generatePlist();
            try {
                p.batch();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(second.class.getName()).log(Level.SEVERE, null, ex);
            }
            third S = new third();

        }
    }

	//function:to generate the path list
    //input:path list
    //output:updated path list
    public boolean generatePlist() {
        boolean cnt = false;

        for (int a = 0; a < TextList.size(); a++) {
            first.P_list.add(new PERT(PERT.Path_list.get(a), Double.parseDouble(Opti_list.get(a).getText()), Double.parseDouble(Most_list.get(a).getText()), Double.parseDouble(Pessi_list.get(a).getText())));
        }
        if (first.P_list.size() == TextList.size()) {
            cnt = true;
        }
        return cnt;
    }

	//function:the main function in this class
    //input:-
    //output:the main function is executed
    public void main2() {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(second.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(second.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(second.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(second.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new second().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
