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

public class DummyDataStore implements DataStore {
	
	private Queue<EventMessage<?>>  list;
	private static Logger logger = LoggerFactory.getLogger(DummyDataStore.class);
	public DummyDataStore() {
		list = new ConcurrentLinkedQueue();
	}
	
	

	@Override
	public boolean storeData(final EventMessage<?> event) {
		return list.add(event);
		
		
	}

	
	

	@Override
	public Collection<EventMessage<?>> getData() {
		return list;
	}



	@Override
	public void publish(EventMessage<?> event) {
			Configurer.getInstance().getPublishAgent().publish(event);
	}
	

}
