/**
 * 
 */
package com.phillips.producerconsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class uses an executor service to spawn multiple threads that process
 * the the {@link Item} objects stored in itemQueue. 
 * <br>
 * When an {@link Item} is consumed, the processing is done by an {@link ItemProcessor} 
 * which is in turn stored in the jobList. This list can be used to cancel all of the 
 * outstanding consumer jobs.
 * <br> 
 * It is interesting to note that to solve the producer-consumer problem, we can only
 * address the consumer side. We have no way of controlling the production of items by
 * the producers. We can control how the consumer accepts the items.
 * 
 * @author Bryan Phillips <phillips.bryan.j@gmail.com>
 *
 */
public class ConsumerImpl implements Consumer {

	private BlockingQueue<Item> itemQueue = new LinkedBlockingQueue<>();
	
	private ExecutorService executorService = Executors.newCachedThreadPool();
	
	private List<ItemProcessor> jobList = new LinkedList<>();
	
	// TODO: this doesn't do anything...
	private volatile boolean shutDownCalled = false;
	
	// Create thread pool. Wait for Items to process.
	public ConsumerImpl(int poolSize){
		
		for(int i = 0; i < poolSize; i++){
			ItemProcessor jobThread = new ItemProcessor(itemQueue);
			
			jobList.add(jobThread);
			executorService.submit(jobThread);
		}
	}
	
	@Override
	public boolean consume(Item item) {
		
		if(!shutDownCalled){
			try {
				// This will wait to perform the operation (space available)
				itemQueue.put(item);
			}
			catch(InterruptedException interrupt){
				Thread.currentThread().interrupt();
				return false;
			}
		}
		return true;
	}

	@Override
	public void finishConsumption() {
		
		for(ItemProcessor job : jobList){
			job.cancelExecution();
		}
		
		executorService.shutdown();
	}

}
