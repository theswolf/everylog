package core.september.everylog.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.september.everylog.config.Configurer;
import core.september.everylog.engine.JoinPointEventMessage;
import core.september.everylog.engine.iface.EventMessage;
import core.september.everylog.engine.iface.EventMessage.MessageWhen;
import core.september.everylog.util.Serializer;

@Aspect
public  abstract class TraceableAspect {
	
	private static Logger logger = LoggerFactory.getLogger(TraceableAspect.class);
	
	@Before(value = "@within(core.september.everylog.annotation.Traceable) || @annotation(core.september.everylog.annotation.Traceable)")
	public void before(JoinPoint joinPoint) throws Throwable {
		handleJp(joinPoint,MessageWhen.BEFORE);

	}
	
	@After(value = "@within(core.september.everylog.annotation.Traceable) || @annotation(core.september.everylog.annotation.Traceable)")
	public void after(JoinPoint joinPoint) throws Throwable {
		handleJp(joinPoint,MessageWhen.AFTER);

	}
	
	private void handleJp(JoinPoint joinPoint,MessageWhen when) throws Throwable {
	
		EventMessage<JoinPoint> event = new JoinPointEventMessage(joinPoint,when);
		logger.debug(event.toString());
		Configurer.getInstance().getDataStore().storeData(event);
		
	}
}
