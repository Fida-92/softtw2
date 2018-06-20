/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class UserDAO {

    public static void main(String[] args) {
        System.out.println(checkCredentials("", ""));
    }
    static Connection c = null;
    static Statement stmt = null;
    static ResultSet rSet = null;
    static User users = new User();

    public static User checkCredentials(String userId, String password) {
//        String userId = userbean.getUserId();
//        String password = userbean.getPassword();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\Documents\\NetBeansProjects\\sqlliteProjekt\\carsharing.db");
            stmt = c.createStatement();
            String sql = "select * from usertest u where u.user=user and u.password=password;";
            rSet = stmt.executeQuery(sql);
            System.err.println("----------------------DAO----------");
            System.out.println("DB-Aktion ist erfolgreich durchgeführt");

            while (rSet.next()) {

                String user = rSet.getString(1);
                String pass = rSet.getString(2);
                System.err.println("Hallo " + user + " : " + pass + " ");
                if (userId.equals(user) && password.equals(pass)) {
                    User.isRegisteriert = true;
                }
                System.out.println(user + " : " + pass);
                System.out.println(userId + " :bean " + password);
//                userbean.setUserId(user);
//                userbean.setPassword(pass);

            }
            System.err.println("----------------------DAO----------");

            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        return null;

    }

}
/*   
         "insert into users (userId,password) values('fida','password');" 
        ; 
         drop table if exists users ; CREATE TABLE users " +
                        "(userId text unique NOT NULL," +
                        "password text NOT NULL check(length(password)>=6) )
                   stmt.executeUpdate(sql);



"drop table if exists usertest ; CREATE TABLE usertest " +
"                        (user text unique NOT NULL," +
"                        password text NOT NULL check(length(password)>=6) )" 
 */
