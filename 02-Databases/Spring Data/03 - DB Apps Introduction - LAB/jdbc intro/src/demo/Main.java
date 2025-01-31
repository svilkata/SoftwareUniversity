package demo;

import java.sql.*;

import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = sc.nextLine().trim();
        user = user.equals("") ? "root" : user;
        System.out.print("Enter password default (empty):");
        String password = sc.nextLine().trim();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        //1. Load jdbc driver - optional
        try {
            Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Driver loaded successfully");


        //2. Connect to DB - Пишем конекцията във формата даден в презентацията
//        Connection connection =
//                DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni?useSSL=false", props);
        //username root, след това двоеточие, след това парола, след това @
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://root:@localhost:3306/soft_uni?useSSL=false");
        System.out.println("Connected successfully");


        //3.
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?"); //SQL Query
        System.out.print("Enter minimal salary (default 20 000): ");
        String salaryStr = sc.nextLine().trim();
        double salary = salaryStr.equals("") ? 20000 : Double.parseDouble(salaryStr);
        stmt.setDouble(1, salary);
        ResultSet rs = stmt.executeQuery();//Runs the SQL statement and returns retrieved result

        //Iterating over the result:
        while (rs.next()) {
            System.out.printf("| %-15.15s | %-15.15s | %10.2f\n",
                    rs.getString("first_name"), //column label
                    rs.getString("last_name"), //column label
                    rs.getDouble("salary")); //get by column label
        }

        connection.close();
    }
}
