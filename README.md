Keep in Touch is Customer Relationship Management software, software that helps companies & organizations create and maintain relationships with customers.

Specifically, Keep In Touch is software that
Tracks when the last time is that you contacted people  and how often you’d like to contact them
Tells you who to contact next, upon request

The program should
Store the name of each person, how often we’d like to contact them, and the last date/time they were contacted
Store a new contact with a corresponding contact frequency, upon request
Update when a person was last contacted, upon request
Update how often you’d like to contact a person, upon request
Tell us who to contact next (by some policy), upon request

For this step, think up the interfaces you’d define and classes you’d implement to get the functionality for the following output. What aspects of this might you want to create abstractions for (for example, the user interface, the storage medium, etc).
Expected Output
Assume today is 2017/01/02.

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

Implement the command line above, using some file storage medium as the database.
