package everylog.app;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import core.september.everylog.engine.iface.DataStore;
import core.september.everylog.engine.iface.EventMessage;

public class DummyDataStore implements DataStore {
	
	private Queue<EventMessage<?>>  list;
	public DummyDataStore() {
		list = new ConcurrentLinkedQueue();
	}
	
	

	@Override
	public boolean storeData(EventMessage<?> event) {
		return list.add(event);
	}

	@Override
	public Collection<EventMessage<?>> getData() {
		return list;
	}

}
