package wdsr.exercise2.procon;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task: implement Exchange interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();
	
	List<Order> listOfOrder = Collections.synchronizedList(new LinkedList<>()); 
	
	public void submitOrder(Order order) throws InterruptedException {
		lock.lock();
		try{
			while(listOfOrder.size()==100000){
				notFull.await();
			}
			notEmpty.signal();
			listOfOrder.add(order);
		} finally {
			lock.unlock();
		}
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		lock.lock();
		try{
			while(listOfOrder.isEmpty()){
				notEmpty.await();
			}
			notFull.signal();
			return listOfOrder.remove(0);
		} finally {
			lock.unlock();
		}
	}
}
