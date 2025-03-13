package jdbc_programs;

import java.sql.*;
import java.util.*;

public class JDBC_Select_4 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Email:");
        String email = sc.nextLine();
        System.out.println("Enter Password:");
        String pass = sc.nextLine();

        Connection con = factory.DBConn.con();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM register WHERE email='" + email + "' AND pass='" + pass + "'");

        if (rs.next()) {
            System.out.println("Login Success");
        } else {
            System.out.println("Login Failed");
        }

        rs.close();
        stmt.close();
        con.close();
        sc.close();
    }
}
