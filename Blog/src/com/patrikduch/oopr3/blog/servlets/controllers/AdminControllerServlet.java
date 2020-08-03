package com.patrikduch.oopr3.blog.servlets.controllers;

import com.patrikduch.oopr3.blog.model.User;
import com.patrikduch.oopr3.blog.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminControllerServlet", urlPatterns = "/admin")
public class AdminControllerServlet extends HttpServlet {

    private UserRepository _userRep;

    public AdminControllerServlet() {

        _userRep = new UserRepository();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession().getAttribute("actualUser") == null)  { // Nepřihlášený uživatel
            response.sendRedirect("/");
        } else {

            // Uživatel bez administrátorských práv nemá v administraci co dělat
            if(!_userRep.isUserAdmin((User)request.getSession().getAttribute("actualUser"))) {
                response.sendRedirect("/");
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/view_admin_home.jsp");
                requestDispatcher.forward(request, response);

            }
        }
    }
}
