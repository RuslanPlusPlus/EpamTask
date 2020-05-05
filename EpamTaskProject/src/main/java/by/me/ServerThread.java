package by.me;

import by.me.model.NumberUtil;

public class ServerThread implements Runnable{
	
	Thread thread;
	String number;
	boolean task = false;
	
	public String getNumber() {
		return this.number;
	}
	
	public ServerThread() {
		this.thread = new Thread(this, "Server thread");
		this.thread.start();
	}

	@Override
	public void run() {
		
		while (!this.thread.isInterrupted()) {
			
			if (this.task == true) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.number = NumberUtil.generateStringNumber();
				Waiter.set();
				this.task = false;
			}
		}
		System.out.println("Server was terminated");
	}
	
	public void generateNumber() {
		this.task = true;
	}
	
	
	public Thread getThread() {
		return this.thread;
	}
	
}
