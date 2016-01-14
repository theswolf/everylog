package everylog.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import core.september.everylog.config.Configurer;
import core.september.everylog.engine.iface.DataStore;
import core.september.everylog.engine.iface.EventMessage;

public class ReactiveDataStore implements DataStore {
	
	private Queue<EventMessage<?>>  inputqueue;
	private List<EventMessage<?>> outputList;
	private static Logger logger = LoggerFactory.getLogger(ReactiveDataStore.class);
	private Object lock = new Object();
	private final static int limit = 5;
	private ExecutorService executorServiceForObservables = Executors.newFixedThreadPool(4);
	private ExecutorService executorServiceForSubscribers = Executors.newFixedThreadPool(2);
	private Subscriber<? super EventMessage> subscriber;
	public ReactiveDataStore() {
		inputqueue = new ConcurrentLinkedQueue();
		outputList = Collections.synchronizedList(new ArrayList<EventMessage<?>>());
	}
	
	

	@Override
	public boolean storeData(final EventMessage<?> event) {
		inputqueue.add(event);
		if(inputqueue.size() >= limit) {
			Observable.from(inputqueue)
			.subscribeOn(Schedulers.from(executorServiceForSubscribers))
			.observeOn(Schedulers.from(executorServiceForObservables))
			.subscribe((next) -> {
				logger.debug("Removed "+next);
				if(outputList.add(next)) inputqueue.remove(next);
			});
		}
		
		
		
		return true;
	}

	
	

	@Override
	public Collection<EventMessage<?>> getData() {
		return outputList;
	}
	
	public boolean jobDone() {
		return  inputqueue.size() == 0;
	}


	@Override
	public void publish(EventMessage<?> event) {
		Configurer.getInstance().getPublishAgent().publish(event);
	
	}
	

}
