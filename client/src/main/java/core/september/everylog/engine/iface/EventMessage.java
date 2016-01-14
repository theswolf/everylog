package core.september.everylog.engine.iface;

public abstract class EventMessage<T> {

	public enum MessageType{
		STOP,
		SERIALIZATION
	}
	
	public enum MessageWhen{
		BEFORE,
		AFTER
	}
	
	public EventMessage(T t,MessageWhen when){
		
	}
	
	public abstract MessageType getType();
	
	
}
