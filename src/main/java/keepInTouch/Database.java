package keepInTouch;

import java.util.Optional;
import java.util.List;

interface Database {
    List<Person> getPeople();

    Optional<Person> getPersonByName(String name);

    // If a person with the same name already exists, this will overwrite that Person entry
    int insertPerson(Person person);
}