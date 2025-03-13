package jdbc_programs;

import java.sql.*;

public class JDBC_Insert_3 {
    public static void main(String args[]) throws Exception {
        Connection con = factory.DBConn.con();
        Statement stmt = con.createStatement();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO register VALUES (?,?,?,?,?,?,?)");

        String fname = args[0];
        String lname = args[1];
        String email = args[2];
        String pass = args[3];
        long mobile = Long.parseLong(args[4]);
        String address = args[5];

        // Generate primary key (regid)
        int regid = 0;
        ResultSet rs = stmt.executeQuery("SELECT MAX(regid) FROM register");
        if (rs.next()) {
            regid = rs.getInt(1);
        }
        regid++;

        // Insert record
        pstmt.setInt(1, regid);
        pstmt.setString(2, fname);
        pstmt.setString(3, lname);
        pstmt.setString(4, email);
        pstmt.setString(5, pass);
        pstmt.setLong(6, mobile);
        pstmt.setString(7, address);
        int i = pstmt.executeUpdate();

        if (i == 1)
            System.out.println(i + " record inserted");

        rs.close();
        pstmt.close();
        stmt.close();
        con.close();
    }
}
