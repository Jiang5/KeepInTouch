package keepInTouch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class MemoryListDatabase implements Database{
    private List<Person> table;

    public MemoryListDatabase() {
        this.table = new ArrayList<Person>();
    }

    public List<Person> getPeople() {
        return table;
    }

    public Optional<Person> getPersonByName(String name) {
        for (Person i : table) {
            if (i.getName().equals(name)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public int insertPerson(Person person) {
        for (Person i : table){
            if (i.getName().equals(person.getName())) {
                table.set(table.indexOf(i), person);
                return 1;
            }
        }
        table.add(person);
        return 2;
    }


}