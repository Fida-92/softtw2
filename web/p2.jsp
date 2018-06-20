<%-- 
    Document   : p2
    Created on : 19.06.2018, 19:16:15
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% request.getSession();
            if (session.getAttribute("userId")==null) {
                response.sendRedirect("p1.jsp");
            }
            %>
        <h1>Hello World!</h1>
    </body>
</html>
