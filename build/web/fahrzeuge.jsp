<%@ page contentType="text/html" %>
<%@ page import="java.sql.*" %>
<%@ page import="org.sqlite.*" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SQLite Demo</title>
    </head>
    <body>
        <table>
            <thead style="background-color: antiquewhite;">
                <tr>
                    <th>Username</th>
                    <th>was anderes</th>

                </tr>
            </thead>
            <tbody>
                <%
                    Class.forName("org.sqlite.JDBC");
                    Connection conn
                            = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\Documents\\NetBeansProjects\\sqlliteProjekt\\carsharing.db");
                    Statement stat = conn.createStatement();
                    ResultSet rs = stat.executeQuery("select * from usertest");
                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rs.getString(1) + "</td>");
                        out.println("<td>" + rs.getString(2) + "</td>");
                        out.println("</tr>");
                    }
                    rs.close();
                    conn.close();
                %>
            </tbody>
        </table>
    </body>
</html>