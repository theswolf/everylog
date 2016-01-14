import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.september.everylog.license.LycenseVerifier;


public class UnitTest {
	
	private final Logger logger = LoggerFactory.getLogger(UnitTest.class);
	@Test
	public void testLicenseCreator() {
		String credentials = "credentials";
		String license = LycenseVerifier.calculateLicenseNumber(credentials);
		logger.info(license);
		assertThat(license, notNullValue());
	}
	
	@Test
	public void testLicenseValidation() {
		String credentials = "credentials";
		String license = LycenseVerifier.calculateLicenseNumber(credentials);
		logger.info(license);
		assertThat(LycenseVerifier.licenseValid(credentials, license),is(true) );
	}
}
