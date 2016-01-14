package core.september.everylog.util;

import java.io.IOException;

import org.aspectj.lang.JoinPoint;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JoinPointTypeAdapter extends TypeAdapter<JoinPoint> {
	public JoinPoint read(JsonReader reader) throws IOException {
		return null;
	}

	public void write(JsonWriter writer, JoinPoint obj) throws IOException {
		if (obj == null) {
			writer.nullValue();
			return;
		}
		writer.value(obj.toString());
	}
}

