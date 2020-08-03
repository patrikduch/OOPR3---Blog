package com.patrikduch.oopr3.blog.servlets.controllers;
import com.patrikduch.oopr3.blog.helper.authentication.SessionAuth;
import com.patrikduch.oopr3.blog.helper.encryption.MD5Crypt;
import com.patrikduch.oopr3.blog.model.User;
import com.patrikduch.oopr3.blog.repository.UserRepository;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebServlet(name = "LoginControllerServlet", urlPatterns = "/login")
public class LoginControllerServlet extends HttpServlet {

    private RequestDispatcher requestDispatcher;
    private UserRepository _userRep;

    public LoginControllerServlet() {

        _userRep = new UserRepository();


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Ziskani dat z formuláře
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validace přihlašovacího formuláře
        if(email.equals("") || password.equals("") ) {
            requestDispatcher = request.getRequestDispatcher("/views/view_login.jsp");
            requestDispatcher.forward(request,response);
        }

        User user = null;

        try{

            user = _userRep.getUserList().stream()
                    .filter(c->c.getEmail().equals(email))
                    .filter(c-> c.getPassword().equals(MD5Crypt.cryptWithMD5(password)))
                    .findFirst().get();

        } catch (NoSuchElementException ex) {

            user = null;
        }

        if(user != null) {

            SessionAuth.setAdmin(
                    request.getSession(), // Session objekt
                    user, // Instance uživatele
                    _userRep // Instance uživatelského repozitáře
            );
            response.sendRedirect("/user/profile");

        }  else {

            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Přihlašený uživatel nemá přístup k pohledu tohoto controlleru
        if(SessionAuth.isUserLogged(request.getSession())) {
            response.sendRedirect("/");
        } else {

            requestDispatcher = request.getRequestDispatcher("/views/view_login.jsp");
            requestDispatcher.forward(request,response);
        }
    }
}
