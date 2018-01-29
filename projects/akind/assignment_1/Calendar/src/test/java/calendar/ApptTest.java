package calendar;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApptTest {

	@Test
	public void testAppt() {
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		assertEquals(A.getStartHour(), 15);
		assertEquals(A.getStartMinute(), 5);
		assertEquals(A.getStartMonth(), 5);
		assertEquals(A.getStartDay(), 5);
		assertEquals(A.getStartYear(), 2017);
	}

}
