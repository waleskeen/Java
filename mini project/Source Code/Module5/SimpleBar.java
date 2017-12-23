/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcharter;

//Contributed by Brenda Lee Hooi Fern

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class SimpleBar extends JFrame{
    
    static ArrayList<ArrayList<Double>> count;
    static String seriesKey;
    static String title;
    static String domain;
    
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: Initialize the graphs
     * Input:
     * - list of data to display, ArrayList<ArrayList<Double>> temp
     * - title of the graph, String tempTitle
     * - estimated time/cost, String tempSerieskey
     * Output: none
     */
    
    public SimpleBar(ArrayList<ArrayList<Double>> temp, String tempTitle, String tempSerieskey) {
        super(tempTitle);
        count=temp;
        title=tempTitle;
        seriesKey=domain=tempSerieskey;
        
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        setContentPane(chartPanel);
    }
    
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: Create the dataset for graph
     * Input: none
     * Output: CatagoryDataset for the data in the graph
     */
    
    private static CategoryDataset createDataset() {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int a=0; a<count.size(); a++)
        {
            dataset.addValue(count.get(a).get(2), seriesKey, String.valueOf(count.get(a).get(0)));
        }
        
        return dataset;
    }
    
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: Creating the graph
     * Input: data set to display in the graph, CategoryDataset dataset
     * Output: the graph JFreeChart
     */
    
    private static JFreeChart createChart(final CategoryDataset dataset) {
        
        JFreeChart chart = ChartFactory.createBarChart(
                title,  // chart title
                domain,                  // domain axis label
                "Percentage",              // range axis label
                dataset,                     // data
                PlotOrientation.HORIZONTAL,  // orientation
                true,                        // include legend
                true,
                false
                );
        
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryMargin(0.0);
        categoryAxis.setUpperMargin(0.02);
        categoryAxis.setLowerMargin(0.02);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.10);
        
        return chart;
    }
    
    /*
     * Contributed by: Brenda Lee Hooi Fern
     * Process: Create the area for the graph to display
     * Input: none
     * Output: JPanel area for the graph
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
}