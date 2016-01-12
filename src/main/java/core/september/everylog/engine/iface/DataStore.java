package core.september.everylog.engine.iface;

import java.util.Collection;

public interface DataStore {

	
	public boolean storeData(EventMessage<?> event);
	public Collection<EventMessage<?>> getData();
}
