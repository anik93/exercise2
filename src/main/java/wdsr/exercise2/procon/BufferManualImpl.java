package wdsr.exercise2.procon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

/**
 * Task: implement Exchange interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {
	
	List<Order> listOfOrder = Collections.synchronizedList(new LinkedList<>()); 
	
	public synchronized void submitOrder(Order order) throws InterruptedException {
		listOfOrder.add(order);
	}
	
	public synchronized Order consumeNextOrder() throws InterruptedException {
		if(listOfOrder.isEmpty()){
			return null;
		}else{
			return listOfOrder.remove(0);
		}
	}
}
