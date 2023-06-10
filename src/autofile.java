import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.io.FileInputStream;
public class autofile {
    public static void main(String[] args)
            throws SQLException, ClassNotFoundException, IOException {
//initializing database stuff
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");
        String db = null;
        String user = null;
        String pass = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the user:");
        user = sc.nextLine();
        System.out.println("Please enter the password for this user:");
        pass = sc.nextLine();
        System.out.println("Please enter the name of the Database you wish to insert the file data into:");
        db = sc.nextLine();

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/"+db,user,pass);
        System.out.println("Database connected");
        Statement statement = connection.createStatement();
        //field
        FileInputStream filebyte = null;
        Scanner scan = null;
        String mpg;
        int cyl;
        float displacement;
        String hp;
        float weight;
        float acel;
        int year;
        int origin;
        String name;

//opening file
        System.out.println("opening the auto simplified");
        String filename = "src/autonote.txt";
        filebyte = new FileInputStream(filename);
        scan = new Scanner(filebyte);

        //deleting previous attmepts and creating a new database
        //makes it so that if this code is run multiple times there is no issue with preexisting tables
        String newattempt = ("create table Cars(\n" +
                "\tmpg varchar(5),\n" +
                "\tcylinders int,\n" +
                "\tdisplacement float,\n" +
                "\thorsepower varchar(5) DEFAULT 'NA',\n" +
                "\tweight float,\n" +
                "\tacceleration float,\n" +
                "\tmodelYear int,\n" +
                "\torigin int,\n" +
                "\tcarName varchar(50)\n" +
                ");");
        String delprev = ("drop table cars;");

        try {
            statement.execute(delprev);

        }
        catch (SQLSyntaxErrorException e) {
            statement.execute(newattempt);

        }

//scanning for the file content and adding it into using sql
        while(scan.hasNext()) {
            mpg = scan.next();
            cyl = (int) scan.nextFloat();
            displacement = scan.nextFloat();
            hp = scan.next();
            weight = scan.nextFloat();
            acel = scan.nextFloat();
            year = (int) scan.nextFloat();
            origin = (int) scan.nextFloat();
            name = scan.next() + scan.nextLine();

            String sql = ("insert into Cars (mpg, cylinders, displacement, horsepower, weight, acceleration, modelYear, origin, carName)" +
                    "values(\""+ mpg+"\","+cyl+","+displacement+",\""+hp+"\","+weight+","+acel+","+year+","+origin+","+name+");");
            System.out.println(sql);
            statement.execute(sql);
        }
        System.out.println("done");
        filebyte.close();
    }
}