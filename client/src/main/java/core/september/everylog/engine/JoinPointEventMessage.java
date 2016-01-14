package core.september.everylog.engine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;

import core.september.everylog.engine.iface.EventMessage;
import core.september.everylog.util.Serializer;

public class JoinPointEventMessage extends EventMessage<JoinPoint>{
	

	public enum Kind{
		CALL,
		EXECUTION;
		
		public static Kind fromJP(JoinPoint joinPoint) {
			Kind myKind = null;
			switch (joinPoint.getKind()) {
			case "method-call":
				myKind = Kind.CALL;
				break;
			case "method-execution":
				myKind = Kind.EXECUTION;
				break;

			default:
				break;
			}
			return myKind;
		}
		
	}
	
	public class FileSource{
		
		private final String name;
		private final int line;

		public FileSource(JoinPoint jp) {
			this.name = jp.getSourceLocation().getFileName();
			this.line = jp.getSourceLocation().getLine();
			
			
		}

		public String getName() {
			return name;
		}

		public int getLine() {
			return line;
		}

		@Override
		public String toString() {
			return "FileSource [name=" + name + ", line=" + line + "]";
		}
		
		
		
		
	}

	protected final Kind kind;
	private MessageWhen when;
	protected final int id;
	protected final Class callee;
	protected final FileSource fileSource;
	private String className;
	private String methodName;
	private Map<Integer, String> arguments;
	private String serialized;
	
	
	public JoinPointEventMessage(JoinPoint joinPoint, MessageWhen when) {
		super(joinPoint,when);
		this.kind = Kind.fromJP(joinPoint);
		this.id=joinPoint.hashCode();
		this.when = when;
		this.callee = joinPoint.getSourceLocation().getWithinType();
		this.fileSource = new FileSource(joinPoint);
		this.className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		this.methodName = joinPoint.getSignature().getName(); 
		this.arguments = extractArgs(joinPoint);
		this.serialized = Serializer.getInstance().serialize(joinPoint);
	}
	
	protected Map<Integer,String> extractArgs(JoinPoint jp) {
		Map<Integer,String> retMap = new HashMap<Integer,String>();
		//Arrays.asList(jp.getArgs()).forEach(arg->retMap.put(retMap.keySet().size(), Serializer.getInstance().serialize(arg)));
		for(Object arg:jp.getArgs()) {
			retMap.put(retMap.keySet().size(), Serializer.getInstance().serialize(arg));
		}
		return retMap;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Map<Integer, String> getArguments() {
		return arguments;
	}

	public void setArguments(Map<Integer, String> arguments) {
		this.arguments = arguments;
	}

	public String getSerialized() {
		return serialized;
	}

	public void setSerialized(String serialized) {
		this.serialized = serialized;
	}

	public Kind getKind() {
		return kind;
	}

	public int getId() {
		return id;
	}

	public Class getCallee() {
		return callee;
	}

	public FileSource getFileSource() {
		return fileSource;
	}

	@Override
	public MessageType getType() {
		return MessageType.SERIALIZATION;
	}

	@Override
	public String toString() {
		return "JoinPointEventMessage [kind=" + kind + ", when=" + when + ", id=" + id
				+ ", callee=" + callee + ", fileSource=" + fileSource
				+ ", className=" + className + ", methodName=" + methodName
				+ ", arguments=" + arguments + ", serialized=" + serialized
				 + "]";
	}
	
	
	
	

}
