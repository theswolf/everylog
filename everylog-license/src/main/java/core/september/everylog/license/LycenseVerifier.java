package core.september.everylog.license;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;

public class LycenseVerifier {
	
	private static final BaseEncoding coder =  BaseEncoding.base64Url();
	
	public static String calculateLicenseNumber(String inputParameter) {
		String encoded = new BCryptPasswordEncoder().encode(inputParameter);
		return coder.encode(encoded.getBytes());
	}
	
	public static boolean licenseValid(String inputParameter,String license) {
		String decodedLicense = new String(coder.decode(license));
		return new BCryptPasswordEncoder().matches(inputParameter, decodedLicense);
	}
	
	
}
