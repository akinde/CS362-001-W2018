package calendar;
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Calendar;
public class TimeTableTest {

	@Test
	public void testGetApptRange() throws Exception {
		TimeTable t = new TimeTable();
		Calendar rightnow = Calendar.getInstance();
	 	int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
	    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		GregorianCalendar firstday = new GregorianCalendar(thisYear, thisMonth, thisDay);
		GregorianCalendar lastday =(GregorianCalendar)firstday.clone();
		lastday.add(Calendar.DAY_OF_MONTH, 1);
		int startMinute = 30;
		int startHour = 16;
		int startDay = thisDay+1;
		int startMonth = thisMonth+1;
		int startYear = thisYear;
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, "Title", "Default");
	    startHour=14;
	    startMinute=30;
	    startDay=thisDay;  	
		startMonth=thisMonth; 	
		startYear=thisYear;
		Appt appt2 = new Appt(startHour, startMinute, startDay, startMonth, startYear, "Title", "Default");
		LinkedList<Appt> cal = new LinkedList<Appt>();
		cal.add(appt);
		cal.add(appt2);
		assertNotNull(t.getApptRange(cal, firstday, lastday));
		
	}
	
	@Test
	public void testDeletAppts() {
		TimeTable t = new TimeTable();
		LinkedList<Appt> cal = new LinkedList<Appt>();
		Appt A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		A = null;
		cal = null;
		assertNull(t.deleteAppt(cal, A)); //both null
		A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		assertNull(t.deleteAppt(cal, A)); //cal is null, A is not.
		A = null;
		cal = new LinkedList<Appt>();
		Appt b = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		cal.add(b);
		assertNull(t.deleteAppt(cal,A)); //cal is not null, A is null
		A = new Appt(13, 5, 5, 5, 2017, "Test", "Default");
		assertNull(t.deleteAppt(cal, A));
		A = new Appt(15, 5, 5, 5, 2017, "Test", "Default");
		cal = new LinkedList<Appt>();
		cal.add(A);
		assertNull(t.deleteAppt(cal, b));
		Calendar rightnow = Calendar.getInstance();
	 	int thisMonth = rightnow.get(Calendar.MONTH)+1;
		int thisYear = rightnow.get(Calendar.YEAR);
	    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		GregorianCalendar firstday = new GregorianCalendar(thisYear, thisMonth, thisDay);
		GregorianCalendar lastday =(GregorianCalendar)firstday.clone();
		lastday.add(Calendar.DAY_OF_MONTH, 1);
		int startMinute = 30;
		int startHour = 16;
		int startDay = thisDay+1;
		int startMonth = thisMonth+1;
		int startYear = thisYear;
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, "Title", "Default");
		cal.add(appt);
		assertNull(t.deleteAppt(cal, appt));
	}

}
