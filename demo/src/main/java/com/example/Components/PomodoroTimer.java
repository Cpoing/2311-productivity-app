package com.example.Components;

public class PomodoroTimer {
	private long startSysTime;
	
	public PomodoroTimer() {
		startSysTime = System.currentTimeMillis();
	}
	public void waitASecond() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}
	public int secondsPassed(long currSysTime){
		long runTime = currSysTime - this.startSysTime;
		int timeInSeconds = ((int)runTime) / 1000;
		return timeInSeconds%60;
	}
	public int minutesPassed(long currSysTime) {
		long runTime = currSysTime - this.startSysTime;
		int timeInMinutes = ((int)runTime/1000)/60; 
		return timeInMinutes;
	}
}
// Below is an iterative approach for the timer in a main class

/**long startSysTime = System.currentTimeMillis();
for(int i = 0; i < 62; i++){
     try{
        Thread.sleep(1000);
    } catch(InterruptedException e){
        Thread.currentThread().interrupt();
    }
    long endSysTime = System.currentTimeMillis();
    long runTime = endSysTime - startSysTime;
    int timeInSeconds = ((int)runTime / 1000);
    int timeInMinutes = timeInSeconds / 60;
    System.out.println(timeInMinutes + ":" + timeInSeconds%60);
} **/