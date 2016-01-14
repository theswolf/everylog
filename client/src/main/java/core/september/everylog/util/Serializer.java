package core.september.everylog.util;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;

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
	

}
