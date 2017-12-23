/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcharter;

//Contributed by Brenda Lee Hooi Fern

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.InputVerifier;

import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import static projectcharter.JTableMain.time;

public class JTableMain extends JApplet {
    private JTextArea txt = new JTextArea(4, 20);
    static ActivityList list = new ActivityList();
    static ThreePointEstimates cost ;
    static ThreePointEstimates time;
    static boolean flag=true;
    static int numofloops;
    static int countcheck;
    static double storedCost;
    static double storedTime;
    static int selection=1;
    
    // The TableModel controls all the data:
    class DataModel extends AbstractTableModel {
        
        Object[][] data;
        
        /*
         * Contributed by: Brenda Lee Hooi Fern
         * Process:
         * - saves changed data into the container
         * Input: table content is changed
         * Output: none
         */
        class TML implements TableModelListener {
            public void tableChanged(TableModelEvent e)
            {
                for (int i = 0; i < data.length; i++)
                {
                    time.getPointEstimate(i).set(0, Double.parseDouble(String.valueOf(data[i][2])));
                    time.getPointEstimate(i).set(1, Double.parseDouble(String.valueOf(data[i][3])));
                    time.getPointEstimate(i).set(2, Double.parseDouble(String.valueOf(data[i][4])));
                    time.setprobability(i, Double.parseDouble(String.valueOf(data[i][5])));
                    cost.getPointEstimate(i).set(0, Double.parseDouble(String.valueOf(data[i][6])));
                    cost.getPointEstimate(i).set(1, Double.parseDouble(String.valueOf(data[i][7])));
                    cost.getPointEstimate(i).set(2, Double.parseDouble(String.valueOf(data[i][8])));
                    cost.setprobability(i, Double.parseDouble(String.valueOf(data[i][9])));
                    
                }
            }
        }
        
        public DataModel(Object[][] input) {
            data=input;
            addTableModelListener(new TML());
        }
        
        private final String[] tableHeaders = {
            "Activity No",
            "Activity Name",
            "Best Time",
            "Most Likely Time",
            "Worst Time",
            "Time Probability, x%",
            "Best Cost",
            "Most Likely Cost",
            "Worst Cost",
            "Cost Probability, x%"
        };
        
        
        /*
         * Contributed by: Brenda Lee Hooi Fern
         * Process:get column name
         * Input: the location of the column
         * Output: header name of the column
         */
        @Override
        public String getColumnName(int columnIndex) {
            return tableHeaders[columnIndex];
        }
        
        /*
         * Contributed by: Brenda Lee Hooi Fern
         * Process:get number of columns
         * Input: none
         * Output: number of columns
         */
        public int getColumnCount() {
            return data[0].length;
        }
        
        /*
         * Contributed by: Brenda Lee Hooi Fern
         * Process:get number of rows
         * Input: none
         * Output: number of rows
         */
        public int getRowCount() {
            return data.length;
        }
        
        /*
         * Contributed by: Brenda Lee Hooi Fern
         * Process:get value at a location
         * Input: row and column to get the value from
         * Output: value at the specified location
         */
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
        
        /*
         * Contributed by: Brenda Lee Hooi Fern
         * Process:get the class of the column
         * Input: the column to get the class from
         * Output: the class of the column
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        
        /*
         * Contributed by: Brenda Lee Hooi Fern
         * Process:validate if cell is editable
         * Input: row and column to edit
         * Output: if cell is editable
         */
        
        public boolean isCellEditable(int row, int col) {
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
        
        /*
         * Contributed by: Brenda Lee Hooi Fern
         * Process:set value at s specified location
         * Input: the new value, the row and column of the cell to be changed
         * Output: none
         */
        
        public void setValueAt(Object value, int row, int col) {
            
            if ((col>2&&col<=4)|| (col>5&&col<=8))
            {
                
                if (Double.parseDouble(String.valueOf(value))<=Double.parseDouble(String.valueOf(data[row][col-1])))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a larger value");
                    return;
                }
            }
            data[row][col] = value;
            
            fireTableCellUpdated(row, col);
            
        }
    }
    
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process:to verify the cell content
     * Input: the cell to modify
     * Output: if cell content does not violate conditions
     */
    final InputVerifier iv = new InputVerifier() {
        
        @Override
        public boolean verify(JComponent input) {
            JTextField field = (JTextField) input;
            String temp = field.getText();
            if ( isNumeric(temp))
            {
                if(Double.parseDouble(temp) <=0)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a value more than 0");
                    return false;
                }
                else
                    return true;
            }
            else
                JOptionPane.showMessageDialog(null, "Please enter a numeric value");
            return false;
        }
        
        @Override
        public boolean shouldYieldFocus(JComponent input) {
            boolean valid = verify(input);
            return valid;
        }
        
    };
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process:check if value is numeric
     * Input: content to check
     * Output: if value is numeric
     */
    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    
    
    
    DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
        {
            getComponent().setInputVerifier(iv);
        }
        
        @Override
        public boolean stopCellEditing() {
            if (!iv.shouldYieldFocus(getComponent())) return false;
            return super.stopCellEditing();
        }
        
        @Override
        public JTextField getComponent() {
            return (JTextField) super.getComponent();
        }
        
    };
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process:initialize the applet
     * Input: none
     * Output: none
     */
    public void init() {
        
        ArrayList<ArrayList<String>> tempStore= new ArrayList<ArrayList<String>>();
        for (int a=0; a<list.getActivityNoSize(); a++)
        {
            ArrayList<String> temp= new ArrayList<String>();
            temp.add(list.getActivityNo().get(a));
            temp.add(list.getActivityName().get(a));
            for (int b=0;b<time.getPointEstimate(a).size(); b++ )
                temp.add(String.valueOf(time.getPointEstimate(a).get(b)));
            
            temp.add(String.valueOf(time.getprobability(a)));
            for (int b=0;b<cost.getPointEstimate(a).size(); b++ )
                temp.add(String.valueOf(cost.getPointEstimate(a).get(b)));
            temp.add(String.valueOf(cost.getprobability(a)));
            
            tempStore.add(temp);
        }
        
        // Create some data
        String[][] array = new String[tempStore.size()][];
        for (int i = 0; i < tempStore.size(); i++) {
            ArrayList<String> row = tempStore.get(i);
            array[i] = row.toArray(new String[row.size()]);
        }
        
        Container cp = getContentPane();
        JTable table = new JTable(new JTableMain.DataModel(array));
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
        table.getColumn("Best Time").setCellRenderer( rightRenderer );
        
        table.getColumn("Most Likely Time").setCellRenderer( rightRenderer );
        table.getColumn("Worst Time").setCellRenderer( rightRenderer );
        table.getColumn("Time Probability, x%").setCellRenderer( rightRenderer );
        table.getColumn("Best Cost").setCellRenderer( rightRenderer );
        table.getColumn("Most Likely Cost").setCellRenderer( rightRenderer );
        table.getColumn("Worst Cost").setCellRenderer( rightRenderer );
        table.getColumn("Cost Probability, x%").setCellRenderer( rightRenderer );
        table.setDefaultEditor(Object.class, editor);
        
        DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
        singleclick.setClickCountToStart(1);
        
        //set the editor as default on every column
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.setDefaultEditor(table.getColumnClass(i), singleclick);
        }
        
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(table);
        tca.adjustColumns();
        
        cp.setPreferredSize(new Dimension (1070,300));
        cp.add(BorderLayout.SOUTH, new JScrollPane (table));
        
        list.displayContents(cost, time);
        
    }
    
   
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process:main function to run program
     * Input: start program
     * Output: none
     */
    public void main5() throws FileNotFoundException {
        list = new ActivityList();
        cost = new ThreePointEstimates();
        time= new ThreePointEstimates();
        try {
            if (list.readFile(cost, time))
                System.out.println("Success");
            else
                System.out.println("Unsuccessful");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JTableMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
        run(new JTableMain(), 1100, 700);
    }
    
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process:
     * - inserts all GUI compenents into the frame
     * - provides the GUI interface for user to enter and view data
     * Input: dimensions of the frame and its content
     * Output: none
     */
    public static void run(JApplet applet, int width, int height) {
        
        //boolean flag;
        JFrame frame = new JFrame("Monte Carlo Simulator");
        Container contentPane = frame.getContentPane();
        JTextField textField = new JTextField("",5);
        
        JComponent newContentPane = new GenerateGraph(textField, cost, time);
        //static boolean set= false;;
        frame.pack();
        frame.setSize(width, height);
        
        SpringLayout layout = new SpringLayout();
        // frame.add(new FileChooser());
        
        //Set up the content pane.
        
        contentPane.setLayout(layout);
        
        //Create and add the components.
        JLabel label = new JLabel("Number of loops: ");
        
        contentPane.add(label);
        contentPane.add(textField);
        
        
        //Adjust constraints for the label so it's at (5,5).
        layout.putConstraint(SpringLayout.WEST, label,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, label,
                5,
                SpringLayout.NORTH, contentPane);
        
        //Adjust constraints for the text field so it's at
        //(<label's right edge> + 5, 5).
        layout.putConstraint(SpringLayout.WEST, textField,
                5,
                SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, textField,
                5,
                SpringLayout.NORTH, contentPane);
        JComponent save=new SaveFile(cost, time, list);
        contentPane.add(save);
        layout.putConstraint(SpringLayout.EAST, save,
                -5,
                SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, save,
                -5,
                SpringLayout.SOUTH, contentPane);
        
        /*
         * JButton back= new JButton();
         * contentPane.add(back);
         * layout.putConstraint(SpringLayout.EAST, back,
         * -5,
         * SpringLayout.WEST, save);
         * layout.putConstraint(SpringLayout.SOUTH, back,
         * -5,
         * SpringLayout.SOUTH, contentPane);
         */
        JLabel note = new JLabel("Please ensure all values are numeric and more than 0. ");
        
        contentPane.add(note);
        layout.putConstraint(SpringLayout.WEST, note,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, note,
                22,
                SpringLayout.SOUTH, label);
        
        contentPane.add(newContentPane);
        layout.putConstraint(SpringLayout.WEST, newContentPane,
                5,
                SpringLayout.EAST, textField);
        layout.putConstraint(SpringLayout.NORTH, newContentPane,
                5,
                SpringLayout.NORTH, contentPane);
        
        JTextField percentageCostStore= new JTextField ("",5);
        contentPane.add(percentageCostStore);
        
        layout.putConstraint(SpringLayout.EAST, percentageCostStore,
                50,
                SpringLayout.WEST, save);
        layout.putConstraint(SpringLayout.NORTH, percentageCostStore,
                400,
                SpringLayout.NORTH, contentPane);
        
        JLabel percentageCostLabel = new JLabel("Percentage for Cost: ");
        
        contentPane.add(percentageCostLabel);
        
        layout.putConstraint(SpringLayout.EAST, percentageCostLabel,
                -20,
                SpringLayout.WEST,percentageCostStore );
        layout.putConstraint(SpringLayout.NORTH, percentageCostLabel,
                400,
                SpringLayout.NORTH, contentPane);
        
        JTextField percentageTimeStore= new JTextField ("",5);
        contentPane.add(percentageTimeStore);
        
        layout.putConstraint(SpringLayout.EAST, percentageTimeStore,
                50,
                SpringLayout.WEST, save);
        layout.putConstraint(SpringLayout.NORTH, percentageTimeStore,
                370,
                SpringLayout.NORTH, contentPane);
        
        JTextField percentageCostStored= new JTextField ("",5);
        contentPane.add(percentageCostStored);
        
        layout.putConstraint(SpringLayout.EAST, percentageCostStored,
                50,
                SpringLayout.WEST,save);
        layout.putConstraint(SpringLayout.NORTH, percentageCostStored,
                470,
                SpringLayout.NORTH, contentPane);
        
        
        JLabel percentageCostLabelStored = new JLabel("Monte Carlo Estimated Cost: ");
        
        contentPane.add(percentageCostLabelStored);
        
        layout.putConstraint(SpringLayout.EAST, percentageCostLabelStored,
                -20,
                SpringLayout.WEST,percentageCostStored );
        layout.putConstraint(SpringLayout.NORTH, percentageCostLabelStored,
                470,
                SpringLayout.NORTH, contentPane);
        
        JTextField percentageTimeStored= new JTextField ("",5);
        contentPane.add(percentageTimeStored);
        
        layout.putConstraint(SpringLayout.EAST, percentageTimeStored,
                50,
                SpringLayout.WEST, save);
        layout.putConstraint(SpringLayout.NORTH, percentageTimeStored,
                500,
                SpringLayout.NORTH, contentPane);
        
        percentageCostStored.setEditable(false);
        percentageTimeStored.setEditable(false);
        
        
        JLabel percentageTimeLabelStored = new JLabel("Monte Carlo Project Duration: ");
        
        contentPane.add(percentageTimeLabelStored);
        
        layout.putConstraint(SpringLayout.EAST, percentageTimeLabelStored,
                -20,
                SpringLayout.WEST,percentageTimeStored );
        layout.putConstraint(SpringLayout.NORTH, percentageTimeLabelStored,
                500,
                SpringLayout.NORTH, contentPane);
        
        
        
        
        
        class DurationType extends JPanel
        implements ActionListener {
            String days = "days";
            String hours = "hours";
            String months = "months";
            String years = "years";
            
            
            /*
             * Contributed by: Brenda Lee Hooi Fern
             * Process: Initialize radioButton for durationtype
             * Input: none
             * Output: none
             */
            
            public DurationType() {
                super(new BorderLayout());
                
                JRadioButton daysButton = new JRadioButton(days);
                daysButton.setActionCommand(days);
                daysButton.setSelected(true);
                
                JRadioButton hoursButton = new JRadioButton(hours);
                hoursButton.setActionCommand(hours);
                
                JRadioButton monthsButton = new JRadioButton(months);
                monthsButton.setActionCommand(months);
                
                JRadioButton yearsButton = new JRadioButton(years);
                yearsButton.setActionCommand(years);
                
                
                ButtonGroup group = new ButtonGroup();
                group.add(daysButton);
                group.add(hoursButton);
                group.add(monthsButton);
                group.add(yearsButton);
                
                daysButton.addActionListener(this);
                hoursButton.addActionListener(this);
                monthsButton.addActionListener(this);
                yearsButton.addActionListener(this);
                
                JPanel radioPanel = new JPanel(new GridLayout(0, 1));
                radioPanel.add(daysButton);
                radioPanel.add(hoursButton);
                radioPanel.add(monthsButton);
                radioPanel.add(yearsButton);
                
                add(radioPanel, BorderLayout.LINE_START);
                setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            }
            
            /*
             * Contributed by: Brenda Lee Hooi Fern
             * Process: action performed after selecting a radiobutton
             * Input: user selects a radiobutton
             * Output: none
             */
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("days"))
                {
                   selection=1;
                }
                else if(e.getActionCommand().equals("hours"))
                {
                    selection=2;
                }
                else if (e.getActionCommand().equals("months"))
                {
                    selection=3;
                }
                else if (e.getActionCommand().equals("years"))
                {
                    selection=4;
                }
                else
                    System.out.println("DurationType error");
            }
        }
        
        JComponent durationtype = new DurationType();
        durationtype.setOpaque(true);
        contentPane.add(durationtype);
        
        
        layout.putConstraint(SpringLayout.EAST, durationtype,
                50,
                SpringLayout.WEST,percentageTimeStored );
        layout.putConstraint(SpringLayout.NORTH, durationtype,
                500,
                SpringLayout.NORTH, contentPane);
        
        
        JComponent percentageToStore= new StorePercentage
                (percentageCostStore, percentageTimeStore,
                percentageCostStored, percentageTimeStored,
                cost, time, selection);
        
        JLabel percentageTimeLabel = new JLabel("Percentage for Time: ");
        
        contentPane.add(percentageTimeLabel);
        
        layout.putConstraint(SpringLayout.EAST, percentageTimeLabel,
                -20,
                SpringLayout.WEST,percentageTimeStore );
        layout.putConstraint(SpringLayout.NORTH, percentageTimeLabel,
                370,
                SpringLayout.NORTH, contentPane);
        
        contentPane.add(percentageToStore);
        
        layout.putConstraint(SpringLayout.EAST, percentageToStore,
                50,
                SpringLayout.WEST, save);
        layout.putConstraint(SpringLayout.NORTH, percentageToStore,
                430,
                SpringLayout.NORTH, contentPane);
        
        frame.getContentPane().add(applet);
        layout.putConstraint(SpringLayout.WEST, applet,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, applet,
                50,
                SpringLayout.NORTH, newContentPane);
        
        applet.init();
        applet.start();
        
        //get data from other modules
        JTextField storeCostValue= new JTextField ("",5);
        contentPane.add(storeCostValue);
        
        layout.putConstraint(SpringLayout.EAST, storeCostValue,
                -140,
                SpringLayout.WEST,percentageTimeStored );
        layout.putConstraint(SpringLayout.NORTH, storeCostValue,
                575,
                SpringLayout.NORTH, contentPane);
        
        JTextField storeTimeValue= new JTextField ("",5);
        contentPane.add(storeTimeValue);
        
        layout.putConstraint(SpringLayout.EAST, storeTimeValue,
                -260,
                SpringLayout.WEST,percentageTimeStored );
        layout.putConstraint(SpringLayout.NORTH, storeTimeValue,
                480,
                SpringLayout.NORTH, contentPane);
        
        storeCostValue.setEditable(false);
        storeTimeValue.setEditable(false);
        
     
        JComponent costRadioButton = new RadioButton(
                cost.getValue(),
                percentageCostStore, 
                storeCostValue, 
                time,cost,list);
        costRadioButton.setOpaque(true);
        
        contentPane.add(costRadioButton);
        
        layout.putConstraint(SpringLayout.EAST, costRadioButton,
                -200,
                SpringLayout.WEST,percentageTimeStored );
        layout.putConstraint(SpringLayout.NORTH, costRadioButton,
                500,
                SpringLayout.NORTH, contentPane);
        
        JComponent timeRadioButton = new RadioButton2(
                time.getValue(), 
                percentageTimeStore,
                storeTimeValue, 
                time, cost, list,selection);
        timeRadioButton.setOpaque(true);
        contentPane.add(timeRadioButton);
        
        layout.putConstraint(SpringLayout.EAST, timeRadioButton,
                -200,
                SpringLayout.WEST,percentageTimeStored );
        layout.putConstraint(SpringLayout.NORTH, timeRadioButton,
                340,
                SpringLayout.NORTH, contentPane);
        /*
        JTextField showDurationType= new JTextField ("",5);
        contentPane.add(showDurationType);
        
        layout.putConstraint(SpringLayout.EAST, showDurationType,
                -200,
                SpringLayout.WEST,percentageTimeStored );
        layout.putConstraint(SpringLayout.NORTH, showDurationType,
                480,
                SpringLayout.NORTH, contentPane);
        
        showDurationType.setEditable(false);
        
        JTextField showDurationType2= new JTextField ("",5);
        contentPane.add(showDurationType2);
        
        layout.putConstraint(SpringLayout.EAST, showDurationType2,
                120,
                SpringLayout.WEST,percentageTimeStored );
        layout.putConstraint(SpringLayout.NORTH, showDurationType2,
                500,
                SpringLayout.NORTH, contentPane);
        
        
        showDurationType2.setEditable(false);
        */
        
        JLabel setDurationType = new JLabel("Duration Type: ");
        
        contentPane.add(setDurationType);
        
        layout.putConstraint(SpringLayout.EAST, setDurationType,
                -65,
                SpringLayout.WEST,percentageTimeStored );
        layout.putConstraint(SpringLayout.NORTH, setDurationType,
                555,
                SpringLayout.NORTH, contentPane);
        
        
        
        LoadImageApp image=new LoadImageApp();
        contentPane.add(image);
        layout.putConstraint(SpringLayout.WEST, image,
                40,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, image,
                -40,
                SpringLayout.SOUTH, contentPane);
        
        frame.setVisible(true);
        
    }
}


