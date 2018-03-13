package calendar;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Test;
public class CalDayTest {

	@Test
	public void testConstruct() {
		GregorianCalendar cal = new GregorianCalendar();
		CalDay c = new CalDay(cal);
		assertNotNull(c.getDay());
		assertNotNull(c.getMonth());
		assertNotNull(c.getYear());
	}
	@Test
	public void testItr() {
		CalDay c = new CalDay();
		assertNull(c.iterator());
		GregorianCalendar cal = new GregorianCalendar();
		CalDay b = new CalDay(cal);	
		assertNotNull(b.iterator());
	}
	@Test
	public void testToStrin() {
		CalDay c = new CalDay();
		assertEquals(c.toString(), "");
		GregorianCalendar cal = new GregorianCalendar();
		CalDay b = new CalDay(cal);
		assertNotSame(b.toString(), "");
	}
	@Test
	public void testSizeandLink() {
		GregorianCalendar cal = new GregorianCalendar();
		CalDay b = new CalDay(cal);
		assertNotNull(b.getSizeAppts());
		assertNotNull(b.getAppts());
	}
	@Test
	public void testAddAppt() {
		GregorianCalendar cal = new GregorianCalendar();
		CalDay c = new CalDay(cal);
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		c.addAppt(A);
		assertNotNull(c.getAppts());
		A = new Appt(10, 5, 5, 5, 2018, "Test", "Test");
		c.addAppt(A);
		/*LinkedList<Appt> cale = new LinkedList<Appt>();
		assertNotNull(c.getAppts());*/
	}
}

