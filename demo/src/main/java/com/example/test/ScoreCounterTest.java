package main.java.com.example.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.example.Components.ScoreCounter;

class ScoreCounterTest {

	@Test
	void test_1() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("Low");
		assertEquals(100, sc.getCounter());
	}
	
	@Test
	void test_2() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("Medium");
		assertEquals(300, sc.getCounter());
	}
	
	@Test
	void test_3() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("High");
		assertEquals(500, sc.getCounter());
	}
	
	@Test
	void test_4() {
		ScoreCounter sc = new ScoreCounter();
		sc.subtractScore("Low");
		assertEquals(-100, sc.getCounter());
	}
	
	@Test
	void test_5() {
		ScoreCounter sc = new ScoreCounter();
		sc.subtractScore("Medium");
		assertEquals(-300, sc.getCounter());
	}
	
	@Test
	void test_6() {
		ScoreCounter sc = new ScoreCounter();
		sc.subtractScore("High");
		assertEquals(-500, sc.getCounter());
	}
	
	@Test
	void test_7() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("High");
		sc.addScore("Medium");
		sc.subtractScore("High");
		assertEquals(300, sc.getCounter());
	}

    @Test
	void test_8() {
		ScoreCounter sc = new ScoreCounter();
		sc.addScore("High");
		sc.addScore("Low");
		sc.subtractScore("High");
		assertEquals(100, sc.getCounter());
	}
}
