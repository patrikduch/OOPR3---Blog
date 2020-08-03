package com.patrikduch.oopr3.blog.servlets.controllers;

import com.patrikduch.oopr3.blog.model.User;
import com.patrikduch.oopr3.blog.repository.UserRepository;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserListControllerServlet", urlPatterns = "/admin/users")
public class UserListControllerServlet extends HttpServlet {

    private UserRepository _userRep;

    public UserListControllerServlet() {

        _userRep = new UserRepository();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Ziskání HTTP Session objektu
        HttpSession httpSession = request.getSession();
        RequestDispatcher requestDispatcher = null;

        boolean isAdmin = false;

        if(httpSession.getAttribute("actualUser")!= null) {

            requestDispatcher =
                    request.getRequestDispatcher("/views/admin/view_users.jsp");

            isAdmin = _userRep.isUserAdmin((User)httpSession.getAttribute("actualUser"));

            if(!isAdmin) { // Přihlašený neadministrátorský učet

                httpSession.setAttribute("auth", "not-admin");
                response.sendRedirect("/unauthorized");


            } else { // Administrátor


                request.setAttribute("userList", _userRep.getUserList());

                requestDispatcher =
                        request.getRequestDispatcher("/views/admin/view_users.jsp");
                requestDispatcher.forward(request,response);
            }

        } else { // Nepřihlášen

            httpSession.setAttribute("auth", "not-logged");

            response.sendRedirect("/unauthorized");

        }

    }
}
