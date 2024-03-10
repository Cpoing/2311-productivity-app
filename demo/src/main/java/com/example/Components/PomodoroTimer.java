package com.example.Components;
/**
 * Pomodoro Timer is the class that describes a pomodoro timer.
 * 
 * startSystime is the initial System time as a long variable.
 */
public class PomodoroTimer {
	private long startSysTime;
	
	/**
	 * Constructor for the pomodoro timer.
	 * Initializes the initial system time.
	 */
	public PomodoroTimer() {
		startSysTime = System.currentTimeMillis();
	}
	/**
	 * waitASecond sleeps for exactly 1 second.
	 */
	public void waitASecond() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}
	/**
	 * secondsPassed returns the time in seconds.
	 * @param currSysTime is the current system time at which you would like to check seconds passed from initializing.
	 * @return time in seconds (0-59).
	 */
	public int secondsPassed(long currSysTime){
		long runTime = currSysTime - this.startSysTime;
		int timeInSeconds = ((int)runTime) / 1000;
		return timeInSeconds%60;
	}
	/**
	 * minutesPassed returns the time in minutes.
	 * @param currSysTime is the current system time at which you would like to check minutes passed from initializing.
	 * @return time in minutes excluding seconds. Ex. (1 minute and 2 seconds have passed, returns 1 minute).
	 */
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