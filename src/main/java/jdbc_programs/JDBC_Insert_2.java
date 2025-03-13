package jdbc_programs;

import java.util.*;
import java.sql.*;

public class JDBC_Insert_2 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String fname = sc.nextLine();
        System.out.println("Enter Last Name:");
        String lname = sc.nextLine();
        System.out.println("Enter Email:");
        String email = sc.nextLine();
        System.out.println("Enter Password:");
        String pass = sc.nextLine();
        System.out.println("Enter Mobile:");
        long mobile = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter Address:");
        String address = sc.nextLine();

        Connection con = factory.DBConn.con();
        Statement stmt = con.createStatement();

        // Generate primary key (regid)
        int regid = 0;
        ResultSet rs = stmt.executeQuery("SELECT MAX(regid) FROM register");
        if (rs.next()) {
            regid = rs.getInt(1);
        }
        regid++;

        // Insert into database
        int i = stmt.executeUpdate("INSERT INTO register VALUES (" + regid + ",'" + fname + "','" + lname + "','" +
                email + "','" + pass + "'," + mobile + ",'" + address + "')");

        if (i == 1)
            System.out.println("Record inserted successfully.");
        else
            System.out.println("Insert failed.");

        rs.close();
        stmt.close();
        con.close();
        sc.close();
    }
}
