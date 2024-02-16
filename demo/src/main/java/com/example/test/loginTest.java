package com.example.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class loginTest {

	
	@Test
	void test_1() {
		login log = new login();
		User user = new User("123", "thisispineapple");
		User user2 = new User("123", "thisisanapple");
		log.register(user);
		assertThrows(IllegalArgumentException.class, ()-> log.register(user2));
	}
	
	@Test
	void test_2() {
		login log = new login();
		User user = new User("123", "thisispineapple");
		User user2 = new User("thisis", "thisisanapple");
		User user3 = new User("testing", "applesamsung");
		User user4 = new User("process", "downtowntoronto");
		User user5 = new User("hahahah", "fromyorkuniversity123");
		log.register(user);
		log.register(user2);
		log.register(user3);
		log.register(user4);
		log.register(user5);
		assertEquals(5, log.size());
		
	}
	
	@Test
	void test_3() {
		login log = new login();
		User user = new User("123", "thisispineapple");
		User user2 = new User("thisis", "thisisanapple");
		User user3 = new User("testing", "applesamsung");
		User user4 = new User("process", "downtowntoronto");
		User user5 = new User("hahahah", "fromyorkuniversity123");
		log.register(user);
		log.register(user2);
		log.register(user3);
		log.register(user4);
		log.register(user5);
		assertTrue(log.loginTo("hahahah", "fromyorkuniversity123"));
		
	}
	
	@Test
	void test_4() {
		login log = new login();
		User user = new User("123", "thisispineapple");
		User user2 = new User("12", "thisisanapple");
		log.register(user);
		assertFalse(log.loginTo("123", "thisisanapple"));
	}
	
	@Test
	void test_5() {
		login log = new login();
		User user = new User("123", "thisispineapple");
		assertThrows(IllegalArgumentException.class, ()-> log.loginTo("123", "thisispineapple"));
	}

}