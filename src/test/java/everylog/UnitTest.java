package everylog;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import core.september.everylog.config.Configurer;
import everylog.app.DummyDataStore;

public class UnitTest {
	
	

	@Before
    public void setUp() throws Exception {
		
		Configurer.getInstance().configure(new DummyDataStore());
		
    }
 
   
	

	@Test
	public void test() throws InterruptedException {
		SimpleService sserv = new SimpleServiceImpl();
		((SimpleServiceImpl)sserv).setName("Name123456789901234567890");
		((SimpleServiceImpl)sserv).getName();
		((SimpleServiceImpl)sserv).checkName();
		sserv.sayHello("hello");
		
		
		assertThat("Number of event in store should be pair", Configurer.getInstance().getDataStore().getData().size()%2, equalTo(0));
	}

}
