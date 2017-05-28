/**
 * 
 */
package com.phillips.producerconsumer;

/**
 * Simple implementation of an {@link Item} interface. 
 * <br>
 * Prints out process information for program execution monitoring.
 * 
 * @author Bryan Phillips <phillips.bryan.j@gmail.com>
 *
 */
public class PrintJob implements Item {

    private String line;  

    public PrintJob(String s)  
    {  
     line = s;  
    }  

    public void process()  
    {  
     System.out.println(  
      Thread.currentThread().getName() +   
      " consuming :" + line);  
    }  

}
