import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.lang.System;



public class TestMessage {
	
	@Test
	public void test() {
		long t = System.currentTimeMillis();
		Message m = new Message ("hello", t, "oliver");
		assertTrue(m.getUser().equals("oliver"));
		assertTrue(m.getTime() == t);
		assertTrue(m.getData().equals("hello"));
		System.out.println(m);
	}

}
