package wdsr.exercise2.procon;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Task: implement Exchange interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {
	
	Queue<Order> listOfOrder = new ArrayDeque<>(); 
	
	public synchronized void submitOrder(Order order) throws InterruptedException {
		listOfOrder.add(order);
	}
	
	public synchronized Order consumeNextOrder() throws InterruptedException {
		return listOfOrder.remove();
	}
}
