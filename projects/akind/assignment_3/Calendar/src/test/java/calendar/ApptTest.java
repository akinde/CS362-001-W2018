package calendar;

import static org.junit.Assert.*;


import org.junit.Test;

public class ApptTest {

	@Test
	public void testGetters() {
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		assertEquals(A.getStartHour(), 15);
		assertEquals(A.getStartMinute(), 5);
		assertEquals(A.getStartMonth(), 5);
		assertEquals(A.getStartDay(), 5);
		assertEquals(A.getStartYear(), 2017);
		assertEquals(A.getTitle(), "Test");
		assertEquals(A.getDescription(), "Default");
		assertEquals(A.getRecurNumber(), 0);
		assertEquals(A.getRecurBy(), 2);
		assertFalse(A.isRecurring());
		assertEquals(A.getRecurIncrement(), 0);
	}
	@Test
	public void testSetters() {
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		A.setStartHour(16);
		A.setStartMinute(6);
		A.setStartMonth(6);
		A.setStartDay(6);
		A.setStartYear(2018);
		A.setTitle("SetterTest");
		A.setDescription("Default2");
		assertEquals(A.getStartHour(), 16);
		assertEquals(A.getStartMinute(), 6);
		assertEquals(A.getStartMonth(), 6);
		assertEquals(A.getStartDay(), 6);
		assertEquals(A.getStartYear(), 2018);
		assertEquals(A.getTitle(), "SetterTest");
		assertEquals(A.getDescription(), "Default2");
		A.setTitle(null);
		assertEquals(A.getTitle(), "");
		A.setDescription(null);
		assertEquals(A.getDescription(), "");
	}
	

	@Test
	public void testHour() {
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		A.setStartHour(13);
		assertFalse(A.getValid());
		A.setStartHour(25);
		assertFalse(A.getValid());
	}
	
	@Test
	public void testMinute() {
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		A.setStartMinute(-1);
		assertFalse(A.getValid());
		A.setStartMinute(61);
		assertFalse(A.getValid());
	}
	
	@Test
	public void TestMonth() {
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		A.setStartDay(0);
		assertFalse(A.getValid());
		A.setStartDay(33);
		assertFalse(A.getValid());
	}
	
	@Test
	public void TestYear() {
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		assertTrue(A.getValid());
	}
	@Test
	public void testRecurrance() {
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		int[] recurringDays = new int[0];
		assertEquals(A.getRecurNumber(), 0);
		assertEquals(A.getRecurBy(), 2);
		assertFalse(A.isRecurring());
		assertEquals(A.getRecurIncrement(), 0);
		assertArrayEquals(A.getRecurDays(), recurringDays);
		recurringDays = null;
        A.setRecurrence(recurringDays, 0, 0, 2);
        recurringDays = new int[0];
		assertArrayEquals(A.getRecurDays(), recurringDays); 
		assertTrue(A.isRecurring());
	}
	@Test
	public void testToString() {
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		A.setStartHour(13);
		assertNull(A.toString());
		A.setStartHour(15);
		assertNotNull(A.toString());
	}
	
	@Test
	public void testIsValid() {
	Appt A = new Appt(12, 5, 5, 5, 2018, "Test", "Test");
	assertFalse(A.getValid());
	A = new Appt(14, 5, 5, 5, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(16, 5, 5, 5, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(20, 5, 5, 5, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(23, 5, 5, 5, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(25, 5, 5, 5, 2018, "Test", "Test");
	assertFalse(A.getValid());
	A = new Appt(27, 5, 5, 5, 2018, "Test", "Test");
	assertFalse(A.getValid());
	
	A = new Appt(16, -2, 5, 5, 2018, "Test", "Test");
	assertFalse(A.getValid());
	A = new Appt(16, -1, 5, 5, 2018, "Test", "Test");
	assertFalse(A.getValid());
	A = new Appt(16, 1, 5, 5, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(16, 25, 5, 5, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(16, 35, 5, 5, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(16, 45, 5, 5, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(16, 59, 5, 5, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(16, 60, 5, 5, 2018, "Test", "Test");
	assertFalse(A.getValid());
	A = new Appt(16, 75, 5, 5, 2018, "Test", "Test");
	assertFalse(A.getValid());
	
	A = new Appt(16, 25, -10, 1, 2018, "Test", "Test");
	assertFalse(A.getValid());
	A = new Appt(16, 25, -1, 1, 2018, "Test", "Test");
	assertFalse(A.getValid());
	A = new Appt(16, 25, 1, 1, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(16, 25, 10, 1, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(16, 25, 20, 1, 2018, "Test", "Test");
	assertTrue(A.getValid());
	A = new Appt(16, 25, 32, 1, 2018, "Test", "Test");
	assertFalse(A.getValid());
	A = new Appt(16, 25, 60, 2, 2018, "Test", "Test");
	assertFalse(A.getValid());
	
	}
	
}





