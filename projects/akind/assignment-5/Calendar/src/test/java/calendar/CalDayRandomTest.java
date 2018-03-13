package calendar;


import org.junit.Test;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
	   /**
     * Generate Random Tests that tests CalDay Class.
     */
    private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
    private static final int NUM_TESTS = 100;

    public static GregorianCalendar getRandomGregorianCalendar(Random random) {
        int year = ValuesGenerator.RandInt(random);
        int month = ValuesGenerator.getRandomIntBetween(random, 1, 11);
        int day = ValuesGenerator.RandInt(random);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);
        return gregorianCalendar;
    }

    public static Appt getRandomGoodAppt(Random random) {
        int startHour = ValuesGenerator.getRandomIntBetween(random, 14, 22);
        int startMinute = ValuesGenerator.RandInt(random);
        int startDay = ValuesGenerator.RandInt(random);
        int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
        int startYear = ValuesGenerator.RandInt(random);
        String title = "Birthday Party";
        String description = "This is my birthday party.";
        //Construct a new Appointment object with the initial data
        Appt appt = new Appt(startHour,
                startMinute,
                startDay,
                startMonth,
                startYear,
                title,
                description);
        return appt;
    }

    public static Appt getRandomBadAppt(Random random) {
        int startHour = ValuesGenerator.getRandomIntBetween(random, 0, 50);
        int startMinute = ValuesGenerator.getRandomIntBetween(random, 0, 100);
        int startDay = ValuesGenerator.getRandomIntBetween(random, 0, 60);
        int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
        int startYear = ValuesGenerator.RandInt(random);
        String title = "Birthday Party";
        String description = "This is my birthday party.";
        //Construct a new Appointment object with the initial data
        Appt appt = new Appt(startHour,
                startMinute,
                startDay,
                startMonth,
                startYear,
                title,
                description);
        return appt;
    }

    @Test
    public void randomtest() throws Throwable {
        long startTime = Calendar.getInstance().getTimeInMillis();
        long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

        System.out.println("Start testing...");
        try {
            for (int iteration = 0; iteration < TestTimeout; iteration++) {
                long randomseed = System.currentTimeMillis(); //10
                //			System.out.println(" Seed:"+randomseed );
                Random random = new Random(randomseed);

                GregorianCalendar gregorianCalendar = getRandomGregorianCalendar(random);
                CalDay calDay = new CalDay(gregorianCalendar);
                LinkedList<Appt> appts = new LinkedList<Appt>();
                System.out.println("Checkpoint three\n");
                boolean goodAppt;
                for (int i = 0; i < 10; i++) {
                    goodAppt = ValuesGenerator.getBoolean((float) .5, random);
                    if (goodAppt) {
                        appts.addFirst(getRandomGoodAppt(random));
                    } else {
                        appts.addFirst(getRandomBadAppt(random));
                    }
                }
                for (int i = 0; i < NUM_TESTS; i++) {
                    int randInt = ValuesGenerator.getRandomIntBetween(random, 0, 9);
                    calDay.addAppt(appts.get(randInt));
                }
            }
        } catch (NullPointerException e) {

        }
        System.out.println("Done testing...");
    }
}
