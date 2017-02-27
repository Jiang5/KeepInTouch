package keepInTouch;

import java.io.*;
import java.util.Date;
import java.util.Calendar;
import java.util.Optional;
import java.util.Scanner;

/**
$ keepintouch help
keepintouch get database
keepintouch get {contacted,frequency} NAME...
keepintouch set contacted [YYYY-MM-DD] NAME...
keepintouch set frequency DAYS NAME...
keepintouch schedule

$ keepintouch set contacted Theresa May
New contact: How often, in days, would you like to contact “Theresa May”?
10
$ keepintouch get database
2017-01-02,Theresa May,10

$ keepintouch set contacted 2016-12-01 Theresa May
Set last contacted date for “Theresa May” to 2016-12-01
$ keepintouch get contacted Theresa May
2016-12-01


$ keepintouch set frequency 11 Gladis
New contact: When was the last time you contacted “Gladis” (format YYYY-MM-DD)?
2017-01-01 
$ keepintouch get database
2017-12-01,Theresa May,10
2017-01-01,Gladis,11

$ keepintouch set frequency 12 Gladis
Set frequency for “Gladis” to every 12 days
$ keepintouch get frequency Gladis
12

$ keepintouch schedule
“Theresa May” is 22 days overdue (last contacted 2016-12-01, frequency: 10 days)
*/
public class KeepInTouch {

    public static void printPerson(Person i){
        System.out.println(i.getName() + ' ' +  i.getFrequencyDays() + ' ' + i.getLastTime());
    }

    public static void printHelp(){
        System.out.println(
            "keepintouch get database\n"
            "keepintouch get {contacted,frequency} NAME...\n"
            "keepintouch set contacted [YYYY-MM-DD] NAME...\n"
            "keepintouch set frequency DAYS NAME...\n"
            "keepintouch schedule"
        )
    }

    public static void main(String[] args) {

        Database db = new MemoryListDatabase();

        // // read object from file
        // try {
        //     FileInputStream fis = new FileInputStream("people.ser");
        //     ObjectInputStream ois = new ObjectInputStream(fis);
        //     db = (Database) ois.readObject();
        //     ois.close();

        // }
        // catch(FileNotFoundException e)  {
        //     System.out.println("can't find the file.");
        // }
        // catch(IOException e) {

        // }
        // catch(ClassNotFoundException e ) {

        // }

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

        } else if (args.length >= 3 && (args[0].equals("set"))) {
            // Contacted
            if (args[1].equals("contacted")) {
                Optional<Date> optDate = CalendarCalculator.parseDate(args[2]);
                
                String[] nameParts;
                Date date;
                
                // Date supplied
                if (optDate.isPresent()) {
                    nameParts = Arrays.copyOfRange(args, 3, args.length - 1);
                    date = optDate.get();
                    
                } else {
                    nameParts = Arrays.copyOfRange(args, 2, args.length - 1);
                    date = Calendar.getInstance().getTime();

                }    
                String  name = new String();
                for(String i : nameParts){
                    name += i + " ";
                }
                name = name.trim();
                db.getPerson(name);
                Optional<Person> person = db.getPersonByName(name);
                if(person.isPresent()) {
                db.insertPerson(new Person(name, person.getFrequencyDays, date);
                System.out.println("set");
                }
                else {
                System.out.println("Person doesn't exist.");
                }
                // name = ...
                // db.getPerson(name)
                // ...
                // db.insertPerson()
            }
            
            // Frequency
            else if (args[1].equals("frequency")) {
                
            }
            
            else {
                printHelp();
                System.exit(1);
            }
            
            
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
