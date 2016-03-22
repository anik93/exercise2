package wdsr.exercise2.procon;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Task: implement Exchange interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {
	
	List<Order> listOfOrder = Collections.synchronizedList(new LinkedList<>()); 
	
	public void submitOrder(Order order) throws InterruptedException {
		listOfOrder.add(order);
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		if(listOfOrder.isEmpty()){
			return null;
		}else{
			return listOfOrder.remove(0);
		}
	}
}
