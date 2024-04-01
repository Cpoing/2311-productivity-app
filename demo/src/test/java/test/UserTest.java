package test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.example.Components.User;

public class UserTest {
	@Test
	public void test_1() {
		User user = new User("12345", "Morethan101");
		assertEquals("12345", user.getUsername());
	}
	
	@Test
	public void test_2() {
		User user = new User("12345", "Morethan101");
		assertEquals("Morethan101", user.getPassword());
	}
}