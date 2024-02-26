package test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.example.Components.PomodoroTimer;


public class PomodoroTimerTest {

	// Test seconds value at 00:59
		@Test 
		public void test_seconds_1() {
			
			long startSysTime = System.currentTimeMillis();
			int timeInSeconds = 0, timeInMinutes = 0;
			for(int i = 0; i < 59; i++){
			     try{
			        Thread.sleep(1000);
			    } catch(InterruptedException e){
			        Thread.currentThread().interrupt();
			    }
			    long endSysTime = System.currentTimeMillis();
			    long runTime = endSysTime - startSysTime;
			    timeInSeconds = ((int)runTime / 1000);
			    timeInMinutes = timeInSeconds / 60;
			}
			
			PomodoroTimer timer = new PomodoroTimer();
			for(int i = 0; i < 59; i++) {
				timer.waitASecond();
			}
			
			int seconds = timer.secondsPassed(System.currentTimeMillis());
			assertEquals(timeInSeconds, seconds);
		}
		
		// Test seconds value at 1:00 (Should display 0 not 60)
		@Test
		public void test_seconds_2() {
			
			long startSysTime = System.currentTimeMillis();
			int timeInSeconds = 0, timeInMinutes = 0;
			for(int i = 0; i < 60; i++){
			     try{
			        Thread.sleep(1000);
			    } catch(InterruptedException e){
			        Thread.currentThread().interrupt();
			    }
			    long endSysTime = System.currentTimeMillis();
			    long runTime = endSysTime - startSysTime;
			    timeInSeconds = ((int)runTime / 1000);
			    timeInMinutes = timeInSeconds / 60;
			}
			
			PomodoroTimer timer = new PomodoroTimer();
			for(int i = 0; i < 60; i++) {
				timer.waitASecond();
			}
			
			int seconds = timer.secondsPassed(System.currentTimeMillis());
			assertEquals(timeInSeconds, seconds);
		}
		
		// Test minutes value at 00:59 
		@Test
		public void test_minutes_1() {
			
			long startSysTime = System.currentTimeMillis();
			int timeInSeconds = 0, timeInMinutes = 0;
			for(int i = 0; i < 59; i++){
			     try{
			        Thread.sleep(1000);
			    } catch(InterruptedException e){
			        Thread.currentThread().interrupt();
			    }
			    long endSysTime = System.currentTimeMillis();
			    long runTime = endSysTime - startSysTime;
			    timeInSeconds = ((int)runTime / 1000);
			    timeInMinutes = timeInSeconds / 60;
			}
			
			PomodoroTimer timer = new PomodoroTimer();
			for(int i = 0; i < 59; i++) {
				timer.waitASecond();
			}
			
			int minutes = timer.minutesPassed(System.currentTimeMillis());
			assertEquals(timeInMinutes, minutes);
		}
		// Test minutes value at 1:00
		public void test_minutes_2() {
			
			long startSysTime = System.currentTimeMillis();
			int timeInSeconds = 0, timeInMinutes = 0;
			for(int i = 0; i < 60; i++){
			     try{
			        Thread.sleep(1000);
			    } catch(InterruptedException e){
			        Thread.currentThread().interrupt();
			    }
			    long endSysTime = System.currentTimeMillis();
			    long runTime = endSysTime - startSysTime;
			    timeInSeconds = ((int)runTime / 1000);
			    timeInMinutes = timeInSeconds / 60;
			}
			
			PomodoroTimer timer = new PomodoroTimer();
			for(int i = 0; i < 60; i++) {
				timer.waitASecond();
			}
			
			int minutes = timer.minutesPassed(System.currentTimeMillis());
			assertEquals(timeInMinutes, minutes);
		}

}