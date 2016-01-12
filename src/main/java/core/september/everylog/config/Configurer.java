package core.september.everylog.config;

import core.september.everylog.engine.iface.DataStore;



public class Configurer {

	private static Configurer instance;
	private static final Object lock = new Object();
	private DataStore dataStore;
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



	
	
	
	

	
	
	
	
	
	
}
