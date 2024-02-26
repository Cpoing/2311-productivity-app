package test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.example.Components.PomodoroTimer;


public class PomodoroTimerTest {

	// Test seconds value at 00:59
		@Test 
		public void test_seconds_1() {
			
			int timeInSeconds = 59;
			int timeInMinutes = 0;
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
			
			int timeInSeconds = 0;
			int timeInMinutes = 1;
			
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
			
			int timeInSeconds = 59;
			int timeInMinutes = 0;
			
			PomodoroTimer timer = new PomodoroTimer();
			for(int i = 0; i < 59; i++) {
				timer.waitASecond();
			}
			
			int minutes = timer.minutesPassed(System.currentTimeMillis());
			assertEquals(timeInMinutes, minutes);
		}
		// Test minutes value at 1:00
		@Test
		public void test_minutes_2() {
			
			int timeInSeconds = 0;
			int timeInMinutes = 1;
			
			PomodoroTimer timer = new PomodoroTimer();
			for(int i = 0; i < 60; i++) {
				timer.waitASecond();
			}
			
			int minutes = timer.minutesPassed(System.currentTimeMillis());
			assertEquals(timeInMinutes, minutes);
		}

}