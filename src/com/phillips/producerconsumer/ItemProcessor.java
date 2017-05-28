/**
 * 
 */
package com.phillips.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Used by {@code ConsumerImpl} to process the incoming items.
 *  
 * @author Bryan Phillips <phillips.bryan.j@gmail.com>
 *
 */
public class ItemProcessor implements Runnable {

	// Job queue is a reference to the shared items for processing between threads.
	private BlockingQueue<Item> jobQueue;
	
	// TODO: why is this volatile? It does not seem to be shared btwn threads. Because in run() method?
	/**
	 *  Note that long or double are not atomic on read/write. Others guaranteed
	 *  reads even during concurrent modification without synchronization. Still
	 *  no guarantees on value visibility between threads so synchronization required  
	 *  <br>
	 *  Use volatile inform compiler field may have concurrent access and synchronization 
	 */
	private volatile boolean keepProcessing;
	
	/**
	 * 
	 * @param queue {@link BlockingQueue} holds the shared queue of {@link Item} objects.
	 * 			Reference is passed by value so can be changed by other threads. 
	 */
	public ItemProcessor(BlockingQueue<Item> queue) {
		
		jobQueue = queue;
		keepProcessing = true;
	}
	
	@Override
	public void run() {
	
		// Threads will wait here until an item is placed in the shared queue
		// Consumption continues until the queue is empty, even after cancellation
		while(keepProcessing || !jobQueue.isEmpty()){
			try {
				// Can return null if waiting time is elapsed.
				Item item = jobQueue.poll(10, TimeUnit.SECONDS);
				
				if(item != null){
					item.process();
				}
			}
			catch(InterruptedException interrupt){
				Thread.currentThread().interrupt();
				return;
			}
		}
	}
	
	public void cancelExecution(){
		keepProcessing = false;
	}

}
