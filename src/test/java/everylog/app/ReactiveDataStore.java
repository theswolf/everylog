package everylog.app;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import core.september.everylog.aspect.TraceableAspect;
import core.september.everylog.config.Configurer;
import core.september.everylog.engine.iface.DataStore;
import core.september.everylog.engine.iface.EventMessage;

public class ReactiveDataStore implements DataStore {
	
	private Queue<EventMessage<?>>  list;
	private static Logger logger = LoggerFactory.getLogger(ReactiveDataStore.class);
	private Object lock = new Object();
	private final static int limit = 5;
	private ExecutorService executorServiceForObservables = Executors.newFixedThreadPool(4);
	private ExecutorService executorServiceForSubscribers = Executors.newFixedThreadPool(2);
	private Subscriber<? super EventMessage> subscriber;
	public ReactiveDataStore() {
		list = new ConcurrentLinkedQueue();
	}
	
	

	@Override
	public boolean storeData(final EventMessage<?> event) {
		//return list.add(event);
		if(list.size() >= limit) {
			Observable.from(list)
			.subscribeOn(Schedulers.from(executorServiceForSubscribers))
			.observeOn(Schedulers.from(executorServiceForObservables))
			.subscribe((next) -> {
				logger.debug("Rempoved "+next);
				list.remove(next);
			});
		}
		
		
		
		return list.add(event);
	}

	
	

	@Override
	public Collection<EventMessage<?>> getData() {
		return list;
	}
	

}
