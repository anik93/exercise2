package wdsr.exercise2.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Marek on 05.03.2016.
 * 
 * Task: use {@see java.util.concurrent.atomic.AtomicInteger} to make CountingFacadeWithAtomicTest pass. 
 */
public class CountingFacadeWithAtomic implements CountingFacade {
	private final BusinessService businessService;
	private static AtomicInteger invocationCounter = new AtomicInteger();
	private int counter = 0; 
	public CountingFacadeWithAtomic(BusinessService businessService) {
		this.businessService = businessService;
	}
		
	public void countAndInvoke() {
		invocationCounter.getAndIncrement();
		businessService.executeAction();
	}
	
	public int getCount() {
		return invocationCounter.get();
		//return counter;
	}
}
