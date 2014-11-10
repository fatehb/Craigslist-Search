/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muhammad Bajwa
 */
import java.sql.*;

public class connectSQL {

    public static Connection ConnectSQL() {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Success loading Mysql Driver!");

        } catch (Exception e) {

            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }

        try {
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/phase2", "root", "root");
            System.out.println("Success connect Mysql server!");
            return connect;
        } catch (Exception e) {
            System.out.print("Error loading Mysql server!");

            return null;
        }
    }
}
