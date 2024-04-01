package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.Components.User;
import com.example.Components.login;
import com.example.DB.DB;

public class loginTest {
	private DB db;
	private login log;

	@Before
	public void init(){
		this.db = new DB();
		db.init();
		this.log = new login();
	}

	@After
	public void after(){
		db.deleteLogin_table();
	}

	@Test
	public void test_1() {
		User user = new User("123", "thisispineapple");
		User user2 = new User("123", "thisisanapple");
		log.register(user);
		assertThrows(IllegalArgumentException.class, ()-> log.register(user2));
	}
	
	@Test
	public void test_2() {
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
	public void test_3() {
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
	public void test_4() {
		User user = new User("123", "thisispineapple");
		User user2 = new User("12", "thisisanapple");
		log.register(user);
		assertFalse(log.loginTo("123", "thisisanapple"));
	}
	
	@Test
	public void test_5() {
		User user = new User("123", "thisispineapple");
		assertFalse(log.loginTo("123", "thisispineapple"));
	}

}