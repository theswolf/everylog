package everylog;

import core.september.everylog.annotation.Traceable;

public interface SimpleService {
	
	public void printNameId();

	public void checkName() throws Exception;

	public String sayHello(String message);

}
