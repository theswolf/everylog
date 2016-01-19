package core.september.everylog.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

import core.september.everylog.config.Configurer;

public class Serializer {
	
	private static GsonBuilder gsonBuilder;
	private static Serializer serializer;
	
	private Serializer() {
		gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(new ClassTypeAdapterFactory());
		if(Configurer.getInstance().getTypeAdapterFactory() != null) {
			gsonBuilder.registerTypeAdapterFactory(Configurer.getInstance().getTypeAdapterFactory());
		}
	}
	
	public static Serializer getInstance() {
		if(serializer == null) {
			serializer = new Serializer();
		}
		return serializer;
	}
	
	public <T> String serialize(T input) {
		return gsonBuilder
				.setExclusionStrategies(exclusionForJoinPoint())
				.create()
				.toJson(input);
	}
	
	private ExclusionStrategy exclusionForJoinPoint() {
		return new ExclusionStrategy() {
			
			private List<String> excludedFields = Arrays.asList("head");
			
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return  excludedFields.contains(f.getName());
			}
			
			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		};
	}
	
	public static void main(String[] args) {
		Boolean myVal = false;
		int earn = 0;
		int lost = 0;
		int bet = 1;
		Random r = new Random();
		for(int i = 0; i < 10000; i++) {
			boolean val = r.nextInt(2) == 0;
			lost+=bet;
			if(myVal == val) { //ho vinto
				earn+=(bet*2);
				myVal=!myVal;
				bet=1;
			}
			else {
				bet*=2;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("EARN:").append(earn).append("\n");
		sb.append("LOST:").append(lost).append("\n");
		sb.append("TRUE:").append(earn-lost).append("\n");
		System.out.println(sb.toString());
	}
	

}
