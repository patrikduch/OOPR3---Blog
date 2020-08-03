package com.patrikduch.oopr3.blog.servlets.controllers.errors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UnauthorizedContentServlet", urlPatterns = "/unauthorized")
public class UnauthorizedContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        String status = (String) httpSession.getAttribute("auth");


        switch (status) {

            case "not-logged":
                request.setAttribute("errorMessage", "Pro přístup do této oblasti musíte být příhlášen.");
                break;

            case "not-admin":
                request.setAttribute("errorMessage", "Tahle oblast je přistupná pouze administrátorům.");
                break;
        }


        // Ziskání request dispatcheru
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/errors/view_unauthorized_login.jsp");

        // Přepooslání do JSP
        requestDispatcher.forward(request, response);
    }
}
