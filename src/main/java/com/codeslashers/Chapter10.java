package com.codeslashers;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

public class Chapter10 {

	public static void main(String[] args) {
		
		// increasing a month using Calendar
		Calendar nextMonth = Calendar.getInstance();
		nextMonth.add(Calendar.MONTH, 1);
		
		// increasing a month using LocalDate
		LocalDate nextMonth2 = LocalDate.now().plusMonths(1);
		
		// decreasing a month using LocalDate
		LocalDate lastYear = LocalDate.now().minusYears(1);
		
		// LocalDateTime
		
		LocalDateTime now = LocalDateTime.now(); 
		System.out.println(now);

		// contructing a LocalDateTime from an instance of LocalDate
		LocalDateTime todayAtMidday = LocalDate.now().atTime(12,0);

		// contructing a LocalDateTime merging a LocalDate with a LocalTime
		
		LocalTime now2 = LocalTime.now();
		LocalDate today = LocalDate.now();
		LocalDateTime dateAndTime = today.atTime(now2);

		// adding timezone information to get a ZonedDateTime.
		ZonedDateTime dateTimeAndTimezone = 
			dateAndTime.atZone(ZoneId.of("America/Los_Angeles"));

		// from ZonedDateTime to LocalDateTime
		
		LocalDateTime withoutTimeZone = dateTimeAndTimezone.toLocalDateTime();

		// creating from factory method *of*
		
		LocalDate date = LocalDate.of(2014, 12, 25);
		LocalDateTime dateTime = LocalDateTime.of(2014, 12, 25, 10, 30);

		// using *with* methods to add values
		
		LocalDate longTimeAgo = LocalDate.now().withYear(1988);

		System.out.println(longTimeAgo.getYear());
		
		// comparing dates using *is* methods

		LocalDate tomorrow = LocalDate.now().plusDays(1);

		System.out.println(today.isBefore(tomorrow));
		System.out.println(today.isAfter(tomorrow));
		System.out.println(today.isEqual(tomorrow));

		// month day from MonthDay class
		
		System.out.println("The current day of month is: "+ MonthDay.now().getDayOfMonth());
		
		// Month enum
		
		System.out.println(LocalDate.of(2014, 12, 25)); 
		System.out.println(LocalDate.of(2014, Month.DECEMBER, 25));	
		
		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
		System.out.println(Month.DECEMBER.plus(2)); 
		System.out.println(Month.DECEMBER.minus(1));

		// formating and displying date models
		
		Locale pt = new Locale("pt");

		System.out.println(Month.DECEMBER
			.getDisplayName(TextStyle.FULL, pt));

		System.out.println(Month.DECEMBER
			.getDisplayName(TextStyle.SHORT, pt));

		now.format(DateTimeFormatter.ISO_LOCAL_TIME);

		now.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

		// parsing from String to LocalDate
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String result = now.format(formatter);
		LocalDate now3 = LocalDate.parse(result, formatter);

		// formatting using Calendar
		
		Calendar instante = Calendar.getInstance();
		instante.set(2014, Calendar.FEBRUARY, 30);		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		System.out.println(dateFormat.format(instante.getTime()));

		// invalid date and time
		
		LocalDate.of(2014, Month.FEBRUARY, 30);

		LocalDateTime invalidHour = LocalDate.now().atTime(25, 0);

		// diff of days using Calendar
		
		Calendar calendar = Calendar.getInstance();

		Calendar anotherCalendar = Calendar.getInstance();
		anotherCalendar.set(1988, Calendar.JANUARY, 25);

		long diff = calendar.getTimeInMillis() - anotherCalendar.getTimeInMillis();

		long dayMilliseconds = 1000 * 60 * 60 * 24;

		long days = diff / dayMilliseconds;

		// diff of days using ChronoUnit
		
		LocalDate now4 = LocalDate.now();
		LocalDate anotherDate = LocalDate.of(1989, Month.JANUARY, 25);
		long days2 = ChronoUnit.DAYS.between(anotherDate, now4);
		
		// diff of months and years
		
		long months = ChronoUnit.MONTHS.between(anotherDate, now4);
		long years = ChronoUnit.YEARS.between(anotherDate, now4);
		System.out.printf("%s days, %s months e %s years", days2, months, years);

		// Period between local dates
		
		LocalDate anotherDate2 = LocalDate.of(1989, Month.JANUARY, 25);
		Period period = Period.between(anotherDate2, now4);
		System.out.printf("%s days, %s months e %s years", 
			period.getDays(), period.getMonths(), period.getYears());

		// negating period values 
		
		Period period2 = Period.between(anotherDate2, now4);

		if (period2.isNegative()) {
			period2 = period2.negated();
		}

		System.out.printf("%s days, %s months e %s years", 
			period2.getDays(), period2.getMonths(), period2.getYears());

		// the same, but using Duration
		
		LocalDateTime now5 = LocalDateTime.now();
		LocalDateTime nowPlusOneHour = now5.plusHours(1);
		Duration duration = Duration.between(now5, nowPlusOneHour);

		if (duration.isNegative()) {
			duration = duration.negated();
		}

		System.out.printf("%s days, %s minutes e %s seconds", 
			duration.toHours(), duration.toMinutes(), duration.getSeconds());
	}
}