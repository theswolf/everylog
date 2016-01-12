package everylog;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import core.september.everylog.config.Configurer;
import everylog.app.DummyDataStore;
import everylog.app.ReactiveDataStore;

public class UnitTest {
	
	

	@Before
    public void setUp() throws Exception {
		
		Configurer.getInstance().configure(new ReactiveDataStore());
		
    }
 
   
	

	@Test
	public void test() throws InterruptedException {
		SimpleService sserv = new SimpleServiceImpl();
		((SimpleServiceImpl)sserv).setName("Name123456789901234567890");
		((SimpleServiceImpl)sserv).getName();
		((SimpleServiceImpl)sserv).setId(20);
		((SimpleServiceImpl)sserv).getId();
		((SimpleServiceImpl)sserv).checkName();
		sserv.sayHello("hello");
		
		//Thread.sleep(2000);
		
		assertThat("Number of event in store should be pair", Configurer.getInstance().getDataStore().getData().size()%2, equalTo(0));
	}
	
	@Test
	public void testReactive() throws InterruptedException {
		SimpleService sserv = new SimpleServiceImpl();
		((SimpleServiceImpl)sserv).setName("Name123456789901234567890");
		((SimpleServiceImpl)sserv).getName();
		((SimpleServiceImpl)sserv).setId(20);
		((SimpleServiceImpl)sserv).getId();
		((SimpleServiceImpl)sserv).checkName();
		sserv.sayHello("hello");
		
		//Thread.sleep(2000);
		
		if(ReactiveDataStore.class.isAssignableFrom(Configurer.getInstance().getDataStore().getClass())) {
			ReactiveDataStore rd = (ReactiveDataStore) Configurer.getInstance().getDataStore();
			assertThat("InputQueue should be void", rd.jobDone(), equalTo(true));

		}
	}

}
