/**
 * 
 */
package com.phillips.producerconsumer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main program acting as the producer in the producer/consumer scenario.
 * <br>
 * The main method reads a file, specified on the command line and stores each line 
 * in a print job ({@link PrintJob}. These print jobs are consume (and subsequently printed in order)
 * by the {@link ConsumerImpl} object, that initialized with a thread pool size.
 * <br>
 * The consumer executes the print jobs in multi-threaded processes.
 * 
 * @author Bryan Phillips <phillips.bryan.j@gmail.com>
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Consumer consumer = new ConsumerImpl(10);
		
		try (
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								new FileInputStream(
										new File(args[0]))));
				){
			
			String line = "";

			// Here we 'produce' lines from a file to 'consume'.
			while((line = br.readLine()) != null){
				System.out.println(  
			               "Producer producing: " + line);  
			              consumer.consume(new PrintJob(line));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Signal the consumer that no more jobs are available.
		consumer.finishConsumption();
	}

}
