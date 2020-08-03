package com.patrikduch.oopr3.blog.servlets.controllers;

import com.patrikduch.oopr3.blog.helper.authentication.SessionAuth;
import com.patrikduch.oopr3.blog.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutControllerServlet", urlPatterns = "/user/logout")
public class LogoutControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionAuth.userLogout(request.getSession(),response);
    }
}
