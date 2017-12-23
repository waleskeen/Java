/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcharter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

//Contributed by Brenda Lee Hooi Fern

public class ThreePointEstimates {
    protected ArrayList<ArrayList<Double>> pointEstimate = new ArrayList<ArrayList<Double>>();
    protected ArrayList <Double> probability = new ArrayList<Double>();
    protected ArrayList<ArrayList<Double>> Stats;
    protected  ArrayList<ArrayList<Double>> count;
    protected  ArrayList<Double> tempStats;
    protected Double value;
    protected  double store;
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: sets the value obtained from the other modules
 * Input: the value from other modules
 * Output: none
 */ 
    public void setValue(double temp)
    {
        value=temp;
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: gets the value obtained from the other modules
 * Input: none
 * Output: the value from other modules
 */ 
      public Double getValue()
    {
       return value;
    }
    
      /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: sets the value to be stored
 * Input: value to be stored
 * Output: none
 */ 
    public void setStore(double toStore)
    {
        store=toStore;
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: gets the value to be stored
 * Input: none
 * Output: value to be stored
 */ 
      public double getStore()
    {
       return store;
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: adds a new set of 3 point estimates
 * Input: new set of 3 point estimates
 * Output: none
 */    
     
    public void addPointEstimate(ArrayList<Double> a)
    {
        pointEstimate.add(a);
    }
    
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: gets all the calculated data
 * Input: none
 * Output: all the calculated data
 */    
    public ArrayList<ArrayList<Double>> getCount()
    {
        return count;
    }
    
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: gets one set of calculated data
 * Input: probability of a set of data
 * Output: none
 */  
    public void addProbability (double prob)
    {
        probability.add(prob);
    }
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: gets one set of 3-point estimates
 * Input: the location of the 3-point estimates to be retrieved
 * Output: a set of 3-point estimates
 */
    public ArrayList<Double> getPointEstimate(int index)
    {
        return pointEstimate.get(index);
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: gets one set of calculated data
 * Input: the location of the calculated data to be retrieved
 * Output: a set of calculated data
 */
    public ArrayList<Double> getCountPointEstimate(int index)
    {
        return count.get(index);
    }
    
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: gets the probability for a set of data
 * Input: location of the probability to retrieve
 * Output: the probability for a set of data
 */
    public double getprobability(int index)
    {
        return probability.get(index);
    }
    
   /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: sets the probability for a set of data
 * Input: location of the probability to retrieve
 * Output: the probability for a set of data
 */
    public void setprobability(int index, double value)
    {
        probability.set(index, value);
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: gets the size of the list point estimates
 * Input: none
 * Output: size of the list point estimates
 */
    
    public int getSizePointEstimate()
    {
        return pointEstimate.size();
    }
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: gets the size of the calculated data
 * Input: none
 * Output: size of the calculated data
 */
    public int getSizeCount()
    {
        return count.size();
    }
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: calculates the value for one activity
 * Input: the range to calculate the random number
 * Output: the random number produced
 */
    protected double calcTotal(double min, double max)
    {
        return (min + (Math.random()*((max-min)+1)));
        
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: calculates one pass for all the activities
 * Input: how many loops to make
 * Output: none
 */
    
    public void calculateOnePass(int numLoops)
    {
        tempStats= new ArrayList<Double>();
        for  (int numofloops=0; numofloops<numLoops; numofloops++)
        {
            double value1,value2,total,totalSum=0;
            
            for (int numofActivity=0; numofActivity<pointEstimate.size(); numofActivity++)
            {
                value1= calcTotal(pointEstimate.get(numofActivity).get(0), pointEstimate.get(numofActivity). get(1));
                value2= calcTotal(pointEstimate.get(numofActivity).get(1), pointEstimate.get(numofActivity). get(2));
                total= (value1*(probability.get(numofActivity))/100)+ (value2*((100-probability.get(numofActivity))/100));
                
                totalSum+=total;
            }
            tempStats.add(totalSum);
        }
        Collections.sort(tempStats);
    }
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: convert to the nearest .5 value
 * Input: value to convert
 * Output: converted value
 */
    protected double round5(double value)
    {
        if (value< (int)value+0.5 )
        {
            if (value>=value+0.3)
                return (int)value+0.5;
            else
                return (int) value;
        }
        else if (value>(int)value+0.5)
        {
            if (value<=(int)value+0.7)
                return (int)value+0.5;
            else
                return (int)value+1;
        }
        else
            return (int) value+0.5;
    }
    
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: generate the percentage to store for future comparison
 * Input: the percentage to store
 * Output: the value to store
 */
    public double generatePercentageToStore(double percentage)
    {
        return (percentage/100)*count.get(count.size()-1).get(0);
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: convert the whole list to respective conversions
 * Input: none
 * Output: none
 */
    public void convert()
    {
        Stats= new ArrayList<ArrayList<Double>>();
        for (int a=0; a<tempStats.size(); a++)
        {
            ArrayList<Double> calcValues = new ArrayList<Double>();
            
            calcValues.add(tempStats.get(a));
            calcValues.add((double)Math.round(tempStats.get(a)));
            calcValues.add(round5(tempStats.get(a)));
            
            
            Stats.add(calcValues);
        }
    }
    
    /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: calculate statistics for graph
 * Input: none
 * Output: none
 */
    public void calculateStatistics()
    {
        count=new ArrayList<ArrayList<Double>>();
        double min = Stats.get(0).get(1);
        double max= Stats.get(Stats.size()-1).get(1);
        
        double newLine= min;
        
        int range= (int)(Math.round((max-min)/5));
        
        while (newLine<=max)
        {
            ArrayList<Double> tempCount= new ArrayList<Double>();
            tempCount.add(newLine);
            tempCount.add(0.0);
            tempCount.add(0.0);
            
            count.add(tempCount);
            newLine+=range;
        }
        
        
        ArrayList<Double> tempCount= new ArrayList<Double>();
        tempCount.add(newLine);
        tempCount.add(0.0);
        tempCount.add(0.0);
        
        count.add(tempCount);
        
        
        for (int a=0; a<Stats.size(); a++)
        {
            for (int b=0; b<count.size(); b++)
            {
                if (count.get(b).get(0)>=Stats.get(a).get(1) )
                {
                    double tempNum=count.get(b).get(1);
                    tempNum++;
                    count.get(b).set(1, tempNum);
                }
            }
        }
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: calculate cumulative percentage for plotting of graph
 * Input: number of loops
 * Output: none
 */
    public void calculateCumulativePercentage(int numLoops)
    {
        for (int b=0; b<count.size(); b++)
        {
            double tempNum= (count.get(b).get(1)/numLoops)*100;
            count.get(b).set(2, tempNum);
        }
    }
    
     /*
 * Contributed by: Brenda Lee Hooi Fern
 * Process: display contents in the container
 * Input: number of loops 
 * Output: none
 */
    public void displayContents(int numLoops)
    {
        calculateOnePass(numLoops);
        convert();
        calculateStatistics();
        calculateCumulativePercentage(numLoops);
        
        for (int a=0; a<count.size(); a++)
        {
            for (int b=0; b<count.get(a).size(); b++)
            {
                System.out.print(count.get(a).get(b));
                System.out.print(" ");
            }
            System.out.println();
            
        }
    }
}



