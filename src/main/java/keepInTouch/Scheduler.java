package keepInTouch;

import java.util.List;
import java.util.Optional;

public class Scheduler {

    Optional<Person> contactNext(List<Person> people) {
        CalendarCalculator cc = new CalendarCalculator();
        if(people.size() != 0) {
            int latestDate = Integer.MAX_VALUE;
            int latestIndex = 0;
            for(Person i : people) {
                if(latestDate > cc.nextDate(i)) {
                    latestDate = cc.nextDate(i);
                    latestIndex = people.indexOf(i);
                }
            }

            return Optional.of(people.get(latestIndex));
        }
        else{
            return Optional.empty();
        }
    }
}
