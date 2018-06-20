package com;

 

import DAOs.User;
import DAOs.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class UserloginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("userId");
        String pass = req.getParameter("password");

        User userBean = new User();
        userBean.setUserId(user);
        userBean.setPassword(pass);
        boolean v = User.isRegisteriert;
        System.err.println("getParam " + user + " : " + pass);
        userBean = UserDAO.checkCredentials(user,pass);
   
        if (new User().isIsRegisteriert()) {
           
             HttpSession session = req.getSession();
            session.setAttribute("userId", user);
            session.setAttribute("password", pass);
             resp.sendRedirect("loggedin.jsp"); 
             System.err.println("Angaben richtig "+ User.isRegisteriert);

           
        } 
        else {
            resp.sendRedirect("invalid.jsp");
            System.err.println("Angaben sind falsch");

        }

    }

}
