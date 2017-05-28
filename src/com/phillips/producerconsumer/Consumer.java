/**
 * 
 */
package com.phillips.producerconsumer;

/**
 * Outline methods for a generic consumer that processes {@link Item} objects.
 * 
 * @author Bryan Phillips <phillips.bryan.j@gmail.com>
 *
 */
public interface Consumer {

	
	/**
	 * Process the specified {@link Item} object.
	 * 
	 * @param item {@code Item}
	 * @return boolean true if the item was processed successfully, false otherwise.
	 */
	boolean consume(Item item);
	
	/**
	 * Indicate the the {@link Consumer} object that it should no longer consume {@link Item} objects.
	 */
	void finishConsumption();
}
