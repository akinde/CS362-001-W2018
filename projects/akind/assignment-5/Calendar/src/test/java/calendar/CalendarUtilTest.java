package calendar;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalendarUtilTest {

	@Test
	public void testIsLeapYear() {
		assertTrue(CalendarUtil.IsLeapYear(29200));
		assertFalse(CalendarUtil.IsLeapYear(73));
		assertFalse(CalendarUtil.IsLeapYear(146));
		assertTrue(CalendarUtil.IsLeapYear(4));
		assertFalse(CalendarUtil.IsLeapYear(5));
	}
	
	@Test
	public void testNumDaysInMonth() {
		assertEquals(29, CalendarUtil.NumDaysInMonth(4, 1));
		assertEquals(28, CalendarUtil.NumDaysInMonth(5, 1));
		assertEquals(31, CalendarUtil.NumDaysInMonth(5, 2));
		assertEquals(31, CalendarUtil.NumDaysInMonth(4, 2));
	}

}
