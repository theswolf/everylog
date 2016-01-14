package core.september.everylog.config;

import com.google.gson.TypeAdapterFactory;

import core.september.everylog.engine.EveryLogPublishAgent;
import core.september.everylog.engine.iface.DataStore;
import core.september.everylog.engine.iface.PublishAgent;



public class Configurer {

	private static Configurer instance;
	private static final Object lock = new Object();
	private DataStore dataStore;
	private PublishAgent publishAgent;
	private TypeAdapterFactory typeAdapterFactory;
	private Configurer() {
		
	}
	
	
	
	public static Configurer getInstance() {
		Configurer r = instance;
		    if (r == null) {
		        synchronized (lock) {    // While we were waiting for the lock, another 
		            r = instance;        // thread may have instantiated the object.
		            if (r == null) {  
		                r = new Configurer();
		                instance = r;
		            }
		        }
		    }
		    return r;
	}
	
	public Configurer configure(DataStore dataStore) {
		this.dataStore = dataStore;
		return instance;
	}
	



	public  DataStore getDataStore() {
		return dataStore;
	}
	
	public  PublishAgent getPublishAgent() {
		if(publishAgent == null) {
			publishAgent = new EveryLogPublishAgent();
		}
		return publishAgent;
	}



	public TypeAdapterFactory getTypeAdapterFactory() {
		return typeAdapterFactory;
	}



	public void setTypeAdapterFactory(TypeAdapterFactory typeAdapterFactory) {
		this.typeAdapterFactory = typeAdapterFactory;
	}
	
	



	
	
	
	

	
	
	
	
	
	
}
