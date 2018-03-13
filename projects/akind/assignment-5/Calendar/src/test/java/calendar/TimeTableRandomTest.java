package calendar;

//import java.util.Random;
import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Calendar;

/**
 * Random Test Generator for TimeTable class.
 */

public class TimeTableRandomTest {
	/**
	 * Generate Random Tests that tests TimeTable Class.
	 */
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 10;
	private static Calendar rightnow = Calendar.getInstance();

	public static String RandomSelectMethod(Random random) {
		String[] methodArray = new String[] { "deleteAppt" };// The list of the of methods to be tested in the Appt
																// class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and methodArray.length
													// (exclusive)

		return methodArray[n]; // return the method name
	}

	public static Appt getRandomGoodAppt(Random random) {
		int startHour = ValuesGenerator.getRandomIntBetween(random, 14, 22);
		int startMinute = ValuesGenerator.getRandomIntBetween(random, 1, 60);
		int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
		int startYear = ValuesGenerator.getRandomIntBetween(random, 1, 2020);
		int startDay = ValuesGenerator.getRandomIntBetween(random, 1, 28);
		String title = "Birthday Party";
		String description = "This is my birthday party.";
		// Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		return appt;
	}

	public static Appt getRandomBadAppt(Random random) {
		int startHour = ValuesGenerator.getRandomIntBetween(random, 0, 48);
		int startMinute = ValuesGenerator.getRandomIntBetween(random, 0, 100);
		int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 60);
		int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
		int startYear = ValuesGenerator.RandInt(random);
		String title = "Birthday Party";
		String description = "This is my birthday party.";
		// Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
		return appt;
	}

	@Test
	public void randomtest() throws Throwable {
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");
		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				// for (int iteration = 0; iteration < 5; iteration++) {
				long randomseed = System.currentTimeMillis(); // 10
				// System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

				LinkedList<Appt> appts = new LinkedList<Appt>();
				boolean goodAppt;
				goodAppt = ValuesGenerator.getBoolean((float) .5, random);

				if (goodAppt) {
					for (int i = 0; i < 15; i++) {
						goodAppt = ValuesGenerator.getBoolean((float) .5, random);
						if (goodAppt)
							appts.addFirst(getRandomGoodAppt(random));
						else
							appts.addFirst(getRandomBadAppt(random));
					}
				} else
					appts = null;

				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = RandomSelectMethod(random);
					TimeTable timeTable = new TimeTable();
					if (methodName.equals("deleteAppt")) {
						LinkedList<Appt> holdAppt = new LinkedList<Appt>();
						if (appts != null) {
							int idx = ValuesGenerator.getRandomIntBetween(random, 0, appts.size() - 1);
							try {
								holdAppt = timeTable.deleteAppt(appts, appts.get(idx));
							} catch (IndexOutOfBoundsException e) {

							}
						} else {
							Appt appt = null;
							holdAppt = timeTable.deleteAppt(appts, appt);
						}
					}
				}

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if ((iteration % 10000) == 0 && iteration != 0)
					System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);
			}
		} catch (NullPointerException e) {
			System.out.println("Caught a NullPointerException");
		}
		System.out.println("Done testing...");
	}

	@Test
	public void randomGetApptRangeTest() throws DateOutOfRangeException {
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		int thisMonth, thisYear, thisDay, recurBy, recurInc, recurNum;
		int NumDaysInMonth = CalendarUtil.NumDaysInMonth(rightnow.get(Calendar.YEAR), 0);
		int[] recurDay = new int[0];
		GregorianCalendar firstDay;
		Appt appt;
		boolean recur;
		System.out.println("Testing GetApptRange...");
		try {
			TimeTable Tical = new TimeTable();
			LinkedList<Appt> appts;
			LinkedList<GregorianCalendar> g;
			long randomseed = System.currentTimeMillis();
			Random random = new Random(randomseed);
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				appts = new LinkedList<Appt>();
				g = new LinkedList<GregorianCalendar>();
				for (int creation = 0; creation < 100; creation++) {
					recur = ValuesGenerator.getBoolean((float) .25, random);
					appt = getRandomGoodAppt(random);
					if (recur) {
						recurBy = ValuesGenerator.getRandomIntBetween(random, 0, 50);
						recurInc = ValuesGenerator.getRandomIntBetween(random, 0, 50);
						recurNum = ValuesGenerator.getRandomIntBetween(random, 0, 50);
						appt.setRecurrence(recurDay, recurBy, recurInc, recurNum);
					}
					appts.addFirst(appt);
				}
				for (int greg = 0; greg < 2; greg++) {
					thisMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
					thisYear = rightnow.get(Calendar.YEAR);
					NumDaysInMonth = CalendarUtil.NumDaysInMonth(thisYear, thisMonth);
					thisDay = ValuesGenerator.getRandomIntBetween(random, 1, NumDaysInMonth);
					firstDay = new GregorianCalendar(thisYear, thisMonth, thisDay);
					g.addFirst(firstDay);
				}
				try {
					if (!g.get(0).before(g.get(1))) {
						Tical.getApptRange(appts, g.get(1), g.get(0));
					} else {
						Tical.getApptRange(appts, g.get(0), g.get(1));
					}
				} catch (IndexOutOfBoundsException I) { 
					System.out.print("IndexOutOfBoundsExceptions caught....\n");
				}
			}

		} catch (DateOutOfRangeException d) {
			System.out.println("Caught a DateOutOfRangeException....");
		}
	}
}
