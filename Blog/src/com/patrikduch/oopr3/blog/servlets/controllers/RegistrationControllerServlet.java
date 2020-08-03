package com.patrikduch.oopr3.blog.servlets.controllers;
import com.patrikduch.oopr3.blog.model.Role;
import com.patrikduch.oopr3.blog.model.User;
import com.patrikduch.oopr3.blog.repository.UserRepository;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "RegistrationControllerServlet", urlPatterns = "/register")
public class RegistrationControllerServlet extends HttpServlet {

    private UserRepository _userRep;

    public RegistrationControllerServlet() {

        _userRep =  new UserRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String username = request.getParameter("usernameInput");
        String password = request.getParameter("firstPasswordInput");
        String email = request.getParameter("emailInput");

        // Datova vrstva (vytvoreni uživatele)
        _userRep.registerUser(new User(username, password, email, new Date()), new Role("user"));

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_register.jsp");
        requestDispatcher.forward(request,response);
    }
}
