package core.september.everylog.util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class ClassTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if(Class.class.isAssignableFrom(typeToken.getRawType())) {
        	return (TypeAdapter<T>) new ClassTypeAdapter();
        }
       /* if(JoinPoint.class.isAssignableFrom(typeToken.getRawType())) {
        	return (TypeAdapter<T>) new JoinPointTypeAdapter();
        }*/
        return null;
        
    }
}
