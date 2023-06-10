# Insertion of automobile data into SQL database
## Brief overview:

This project is the combination of two different programs utilizing Java in combination with SQL/ mySQL

The first, **autofile**, utilizes a Java program in order to insert a large amount of data from a seperate file into a SQL database using the avalible mysql jdbc connector

The second part of this project named **gui** reads data from the SQL database that was filled out due to **autofile** and provides a gui interface to search for and display the names and stats of different vehicles in the dataset based on name or the combination of MPG and Horse Power

This data is from : https://archive.ics.uci.edu/dataset/9/auto+mpg

## How to run:
There are prerequisites for this program to work
- mysql needs to have already been installed 
- at least one user with read and write permissions has to have been created

Moving on, open the project file **NOT THE SRC FILE BUT THE FILE CONTAINING THE SRC FILE** in a terminal

For example:
If our src file was stored in another file named **TEST**, we would have to have the current directory be set as **TEST**


**autofile.java** must be ran first in order to create a database that gui.java would later access
- This is done through running the command ``java -cp src/mysql-connector-j-8.0.33.jar src/autofile.java`` in the terminal

This should then prompt the user to enter the login information of the sql user to be accessed in addition to the database that is to be modified

After this, the program should then run and the database will have a new table named "Cars" filled within the information from the autonote.txt file


In order to run the GUI program, we run a similar command but instead of using the autofile.java, we use gui.java:
``java -cp src/mysql-connector-j-8.0.33.jar src/gui.java``

