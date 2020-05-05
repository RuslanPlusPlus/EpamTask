package by.me;

public class Waiter {
	
	public static boolean isReady = false;
	
	public static synchronized void  set() {
		
		isReady = true;
		
	}
	
}
