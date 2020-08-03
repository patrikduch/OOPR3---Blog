package com.patrikduch.oopr3.blog.servlets.controllers;

import com.patrikduch.oopr3.blog.model.Category;
import com.patrikduch.oopr3.blog.model.User;
import com.patrikduch.oopr3.blog.repository.CategoryRepository;
import com.patrikduch.oopr3.blog.repository.UserRepository;

import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryControllerServlet", urlPatterns = "/admin/categories")
public class CategoryControllerServlet extends HttpServlet {

    private CategoryRepository _categoryRep;
    private UserRepository _userRep;

    public CategoryControllerServlet() {

        _categoryRep = new CategoryRepository();
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
                List<Category> categoryList = _categoryRep.getCategories();

                if(categoryList == null) {
                    categoryList = new ArrayList<>();
                }
                request.setAttribute("categoryList", categoryList);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/view_admin_categories.jsp");
                requestDispatcher.forward(request, response);

            }
        }





    }
}
