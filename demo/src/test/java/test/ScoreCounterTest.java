package test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.example.Components.ScoreCounter;

public class ScoreCounterTest {

	@Test
	public void test_1() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("Low");
		assertEquals(100, sc.getCounter());
	}
	
	@Test
	public void test_2() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("Medium");
		assertEquals(300, sc.getCounter());
	}
	
	@Test
	public void test_3() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("High");
		assertEquals(500, sc.getCounter());
	}
	
	@Test
	public void test_4() {
		ScoreCounter sc = new ScoreCounter();
		sc.subtractScore("Low");
		assertEquals(-100, sc.getCounter());
	}
	
	@Test
	public void test_5() {
		ScoreCounter sc = new ScoreCounter();
		sc.subtractScore("Medium");
		assertEquals(-300, sc.getCounter());
	}
	
	@Test
	public void test_6() {
		ScoreCounter sc = new ScoreCounter();
		sc.subtractScore("High");
		assertEquals(-500, sc.getCounter());
	}
	
	@Test
	public void test_7() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("High");
		sc.addScore("Medium");
		sc.subtractScore("High");
		assertEquals(300, sc.getCounter());
	}

    @Test
	public void test_8() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("High");
		sc.addScore("Low");
		sc.subtractScore("High");
		assertEquals(100, sc.getCounter());
	}
}
