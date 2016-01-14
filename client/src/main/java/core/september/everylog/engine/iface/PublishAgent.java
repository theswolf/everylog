package core.september.everylog.engine.iface;

public interface PublishAgent {

	void publish(EventMessage<?> event);
}
