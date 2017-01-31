package keepInTouch;

import java.io.*;
import java.util.Date;
import java.util.Calendar;
import java.util.Optional;
import java.util.Scanner;

public class KeepInTouch {

    public static void printPerson(Person i){
        System.out.println(i.getName() + ' ' +  i.getFrequencyDays() + ' ' + i.getLastTime());
    }

    public static void printHelp(){

        System.out.println("help\n" +
                "\tprint command help\n" +
                "contact <Name> <Contact Date>\n" +
                "\tUpdate last time contact day. Contact Date 20121228, use 0 for today\n" +
                "get <Name>\n" +
                "\tprint the information of a person\n" +
                "get list\n" +
                "\tprint all the people stored\n" +
                "schedule\n" +
                "\tprint the person that need to contact in the most recent future\n" +
                "add <Name> <Contact frequency> <Last time contact>\n" +
                "\tadd a new person\n" +
                "update <Name> <Contact frequency> <Last time contact>\t\n" +
                "\tchange information of a person. add a new on if doesn't exist\n");
    }

    public static void main(String[] args) {

        Database db = new MemoryListDatabase();

        // read object from file
        try {
            FileInputStream fis = new FileInputStream("people.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            db = (Database) ois.readObject();
            ois.close();

        }
        catch(FileNotFoundException e)  {
            System.out.println("can't find the file.");
        }
        catch(IOException e) {

        }
        catch(ClassNotFoundException e ) {

        }

        if (args.length == 1 && args[0].equals("schedule")) {
            Scheduler scheduler = new Scheduler();
            Optional<Person> nextPerson = scheduler.contactNext(db.getPeople());

            if(nextPerson.isPresent()) {
                printPerson(nextPerson.get());
                CalendarCalculator cc = new CalendarCalculator();
                System.out.println(cc.nextDate(nextPerson.get()));
            }
            else {
                System.out.println("Database is empty.");
            }

        } else if (args.length == 1 && (args[0].equals("add") || args[0].equals("update"))) {
            System.out.println("Input Name, Frequency of Contact and Last Time of Contact.");
            Scanner userInput = new Scanner(System.in);
            int returnedValue;
            returnedValue = db.insertPerson(new Person(userInput.next(), userInput.nextInt(), userInput.nextInt()));
            if(returnedValue == 1){
                System.out.println("Updated.");
            }
            else if(returnedValue == 2){
                System.out.println("Added.");
            }
        }
        else if (args.length > 1 && args[0].equals("contact")) {
            CalendarCalculator cc = new CalendarCalculator();
            Optional<Person> person = db.getPersonByName(args[1]);
            if(person.isPresent()) {
                db.insertPerson(cc.contact(person.get(), Integer.parseInt(args[1])));
                System.out.println("Contacted!");
            }
            else {
                System.out.println("Person doesn't exist.");
            }

        }
        else if (args.length == 2 && args[0].equals("get")) {
            for(Person i : db.getPeople()) {
                if(i.getName() == args[1]) {
                    printPerson(i);
                    return;
                }
            }
            System.out.println("Person is not found.");
        }
        else if (args.length == 1 && args[0].equals("list")) {
            for(Person i: db.getPeople()){
                KeepInTouch.printPerson(i);
            }
        }
        else if (args.length == 1 && args[0].equals("help")){
            printHelp();
        }
        else {
            printHelp();
        }

        try {
        FileOutputStream fos = new FileOutputStream("people.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(db);
        oos.close();
        System.out.println("file updated.");
        }
        catch(IOException e) {

        }
    }
}
