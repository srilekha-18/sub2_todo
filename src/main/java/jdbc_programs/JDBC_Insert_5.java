package jdbc_programs;

import java.sql.*;
import java.util.*;

public class JDBC_Insert_5 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Task Name:");
        String taskName = sc.nextLine();
        System.out.println("Enter Task Date (dd-mm-yyyy):");
        String taskDate = sc.nextLine();
        System.out.println("Enter Task Status (1: Not Started, 2: In Progress, 3: Completed):");
        int taskStatus = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Registration ID:");
        int regID = sc.nextInt();
        sc.nextLine();

        Connection con = factory.DBConn.con();
        Statement stmt = con.createStatement();
        PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO tasks VALUES (?,?,?,?,?)");
        PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO taskid_pks VALUES (?,?)");
        PreparedStatement pstmt3 = con.prepareStatement("UPDATE taskid_pks SET taskid=? WHERE regid=?");

        int taskID = 0;
        boolean isNew = true;
        int i = 0;
        int j = 0;

        // Start transaction
        con.setAutoCommit(false);
        ResultSet rs = stmt.executeQuery("SELECT taskid FROM taskid_pks WHERE regid=" + regID);
        if (rs.next()) {
            taskID = rs.getInt(1);
            isNew = false;
        }
        taskID++;

        // Insert into `tasks` table
        pstmt1.setInt(1, taskID);
        pstmt1.setString(2, taskName);
        pstmt1.setString(3, taskDate);
        pstmt1.setInt(4, taskStatus);
        pstmt1.setInt(5, regID);
        i = pstmt1.executeUpdate();

        // Insert into `taskid_pks`
        if (isNew) {
            pstmt2.setInt(1, regID);
            pstmt2.setInt(2, taskID);
            j = pstmt2.executeUpdate();
        } else {
            pstmt3.setInt(1, taskID);
            pstmt3.setInt(2, regID);
            j = pstmt3.executeUpdate();
        }

        if (i == 1 && j == 1) {
            con.commit();
            System.out.println("Task Added Successfully");
        } else {
            con.rollback();
            System.out.println("Transaction Failed");
        }

        rs.close();
        stmt.close();
        pstmt3.close();
        pstmt2.close();
        pstmt1.close();
        con.close();
        sc.close();
        
    }
}
