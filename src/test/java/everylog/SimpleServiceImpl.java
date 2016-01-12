package everylog;

import core.september.everylog.annotation.Traceable;

public class SimpleServiceImpl implements SimpleService {

	private String name;

	private int id;

	@Traceable
	public String getName() {
		return name;
	}

	@Traceable
	public void setName(String name) {
		this.name = name;
	}

	@Traceable
	public int getId() {
		return id;
	}

	@Traceable
	public void setId(int id) {
		this.id = id;
	}

	public void printNameId() {
		System.out.println("SimpleService : Method printNameId() : My name is " + name
			 + " and my id is " + id);
	}

	@Traceable
	public void checkName() {
		if (name.length() < 20) {
			throw new IllegalArgumentException();
		}
	}

	@Traceable
	public String sayHello(String message){
		System.out.println("SimpleService : Method sayHello() : Hello! " + message);
		return message;
	}
	
	

}
