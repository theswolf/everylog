package core.september.everylog.engine;

import core.september.everylog.engine.iface.EventMessage;

public abstract class StopEventMessage extends EventMessage<Void> {


	
	public StopEventMessage(Void vod){
		super(vod,null);
	}
	
	@Override
	public MessageType getType() {
		return MessageType.STOP;
	}
	
	
}
