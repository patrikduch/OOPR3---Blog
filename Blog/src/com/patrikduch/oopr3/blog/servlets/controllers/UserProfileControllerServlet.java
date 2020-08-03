package com.patrikduch.oopr3.blog.servlets.controllers;

import com.patrikduch.oopr3.blog.helper.authentication.SessionAuth;
import com.patrikduch.oopr3.blog.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserProfileControllerServlet", urlPatterns = "/user/profile")
public class UserProfileControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Ziskání HTTP Session objektu
        HttpSession httpSession = request.getSession();

        if(!SessionAuth.isUserLogged(request.getSession())) {

            httpSession.setAttribute("auth", "not-logged");
            response.sendRedirect("/unauthorized");
        }

        else {
            // Ziskání request dispatcheru
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_profile.jsp");

            // Přepooslání do JSP
            requestDispatcher.forward(request, response);
        }

    }
}
