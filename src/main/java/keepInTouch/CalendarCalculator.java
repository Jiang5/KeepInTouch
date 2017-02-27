package keepInTouch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarCalculator {
    
    public static DateFormat dateFormat;
    
    static {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    //format: yyyymmdd
    //problem : time zone issue
    public int nextDate(Person person){
        Calendar cal = Calendar.getInstance();
        int start, days, nextDay, year, month, day;
        start = person.getLastTime();
        days = person.getFrequencyDays();
        year = start / 10000;
        month = (start / 100) % 100;
        day = start % 100;
        cal.set(year, month, day);
        cal.add(Calendar.DATE, days);
        nextDay = cal.get(Calendar.YEAR) * 10000 + cal.get(Calendar.MONTH) * 100 + cal.get(Calendar.DATE);
        return nextDay;
    }

    public Person contact(Person person, int date){
        if(date == 0) {
        Calendar cal = Calendar.getInstance();
        int newDate = cal.get(Calendar.YEAR) * 10000 + cal.get(Calendar.MONTH) * 100 + cal.get(Calendar.DATE);
        return new Person(person.getName(),newDate, person.getFrequencyDays());
        }
        else {
            return new Person(person.getName(), date, person.getFrequencyDays());
        }
    }


    // Empty return value means `dateString` was unparseable
    public static Optional<Date> parseDate(String dateString) {
       
        try { 
            Date date = dateFormat.parse(dateString);
            return Optional.of(date);
        
        } catch (ParseException pe) {
            return Optional.empty();
        }
    }
}
