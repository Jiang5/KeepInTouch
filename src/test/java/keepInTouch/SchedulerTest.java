package keepInTouch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Assert;


public class SchedulerTest {

    @Test
    public void normalContactNext() {
        Person earliestPerson = new Person("ame4", 10, 20121010);

        List<Person> input = new ArrayList<Person>();
        input.add(new Person("name1", 10, 20131010));
        input.add(new Person("name2", 10, 20141010));
        input.add(new Person("name3", 10, 20151010));
        input.add(earliestPerson);
        input.add(new Person("name5", 10, 20161010));
        
        Optional<Person> expectedOutput = Optional.of(earliestPerson);

        Scheduler scheduler = new Scheduler();

        Assert.assertEquals( expectedOutput, scheduler.contactNext(input));
    }

    @Test
    public void sameContactNext() {
        Person firstPerson = new Person("name4", 10, 20121010);
        Person secondPerson = new Person("name5", 10, 20121010);

        List<Person> input = new ArrayList<Person>();
        input.add(new Person("name1", 10, 20131010));
        input.add(new Person("name2", 10, 20141010));
        input.add(new Person("name3", 10, 20151010));
        input.add(firstPerson);
        input.add(secondPerson);

        Scheduler scheduler = new Scheduler();
        Optional<Person> result = scheduler.contactNext(input);

        Assert.assertTrue(result.get().equals(firstPerson) || result.get().equals(secondPerson));
    }

    @Test
    public void emptyContactNext() {
        List<Person> input = new ArrayList<Person>();
        Optional<Person> expectedOutput = Optional.empty();

        Scheduler scheduler = new Scheduler();

        Assert.assertEquals(scheduler.contactNext(input), expectedOutput);
    }
}