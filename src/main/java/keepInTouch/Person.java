package keepInTouch;

class Person {
    final private String name;
    final private int frequencyDays;
    final private int lastTime; //yyyymmdd

    public Person(String name, int frequencyDays, int lastTime) {
        this.name = name;
        this.frequencyDays = frequencyDays;
        this.lastTime = lastTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Person)) {
            return false;
        }

        Person person = (Person) other;

        return name.equals(person.getName())
            && frequencyDays == person.getFrequencyDays()
            && lastTime == person.getLastTime();
    }

    @Override
    public String toString() {
        // Person(name, frequencyDays, lastTime)
        return "Person(\""
                + name
                + "\", "
                + ((Integer) lastTime).toString()
                + ", " + ((Integer) frequencyDays).toString()
                + ")";
    }

    String getName() {
        return name;
    }

    int getFrequencyDays() {
        return frequencyDays;
    }

    int getLastTime() {
        return lastTime;
    }

    Person withName(String name) {
        return new Person(name, frequencyDays, lastTime);
    }

    Person withFrequencyDays(int frequencyDays) {
        return new Person(name, frequencyDays, lastTime);
    }

    Person withLastTime(int lastTime) {
        return new Person(name, frequencyDays, lastTime);
    }



    // toString
    // equals
}