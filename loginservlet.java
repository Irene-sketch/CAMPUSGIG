package com.campusgig.servlets;

import com.campusgig.model.User;
import com.campusgig.utils.FileHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = FileHandler.validateUser(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            response.sendRedirect("dashboard.html");
        } else {
            response.sendRedirect("login.html?error=Invalid credentials");
        }
    }
}
