package com.example.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {
	
	@Test
	void test_1() {
		assertThrows(IllegalArgumentException.class, ()-> new User("12345", "lessthan1"));
	}
	
	@Test
	void test_2() {
		User user = new User("12345", "Morethan101");
		assertEquals("12345", user.getUsername());
	}
	
	@Test
	void test_3() {
		User user = new User("12345", "Morethan101");
		assertEquals("Morethan101", user.getPassword());
	}
}