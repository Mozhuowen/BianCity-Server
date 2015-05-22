package tools;

public class LogUtil{
	
	public static final int VERBOSE = 1;
	
	public static final int DEBUG = 2;
	
	public static final int INFO = 3;
	
	public static final int WARN = 4;
	
	public static final int ERROR = 5;
	
	public static final int NOTHING = 6;
	
	public static final int LEVEL = 6;
	
	public static void v(String msg) {
		if (LEVEL <= VERBOSE) {
			System.out.println(msg);
		}
	}
}