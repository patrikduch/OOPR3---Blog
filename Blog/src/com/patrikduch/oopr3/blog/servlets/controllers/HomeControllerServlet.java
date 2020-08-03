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
import java.util.List;

@WebServlet(name = "HomeControllerServlet", urlPatterns = "/")
public class HomeControllerServlet extends HttpServlet {

    private UserRepository _userRepository;

    public HomeControllerServlet() {

        _userRepository = new UserRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = null;

        // Vytvoření administrátora
        if(_userRepository.getUserbyName("patrikduch") == null) {

            user = new User("patrikduch", "heslo007", "patrikduch@gmail.com", new Date());
            _userRepository.registerUser(user, new Role("Admin"));
        }


        // Ziskani uživatelů z databáze
        List<User> users = _userRepository.getUserList();


        // Předání do požadavku
        request.setAttribute("userList", users);

        // Ziskání request dispatcheru
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/view_home.jsp");

        // Přepooslání do JSP
        requestDispatcher.forward(request, response);

    }
}
