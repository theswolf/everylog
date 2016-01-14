package core.september.everylog.engine.iface;

import java.util.Collection;

public interface DataStore {

	
	boolean storeData(EventMessage<?> event);
	Collection<EventMessage<?>> getData();
	void publish(EventMessage<?> event);
}
