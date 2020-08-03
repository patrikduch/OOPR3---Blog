package com.patrikduch.oopr3.blog.servlets.services.Category;

import com.patrikduch.oopr3.blog.model.Category;
import com.patrikduch.oopr3.blog.model.User;
import com.patrikduch.oopr3.blog.repository.CategoryRepository;
import com.patrikduch.oopr3.blog.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryCreationServlet", urlPatterns = "/admin/category/add")
public class CategoryCreationServlet extends HttpServlet {

    private CategoryRepository _categoryRep;
    private UserRepository _userRep;

    public CategoryCreationServlet() {

        _categoryRep = new CategoryRepository();
        _userRep = new UserRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Parametry pro vytvoření kategorie
        String description = request.getParameter("categoryDescriptionInput");
        String name = request.getParameter("categoryNameInput");
        String visibility = request.getParameter("visibilitySelectInput");


        // Formulář nebyl vyplněn
        if(description.equals("") || name.equals("")) {

            response.sendRedirect("/admin/category/add");
            // Přeposlání zpět na seznam kategorii

        } else { // Uspesne vytvoření kategorie

            boolean isVisible = visibility.equals("Ano") ? true : false;

            // Vytvoření nové kategorie
            Category newCategory = new Category(name, description, isVisible);
            _categoryRep.addCategory(newCategory);

            // Přeposlání zpět na seznam kategorii
            response.sendRedirect("/admin/categories");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if(request.getSession().getAttribute("actualUser") == null)  { // Nepřihlášený uživatel
            response.sendRedirect("/");
        } else {

            // Uživatel bez administrátorských práv nemá v administraci co dělat
            if(!_userRep.isUserAdmin((User)request.getSession().getAttribute("actualUser"))) {
                response.sendRedirect("/");
            } else {
                // Ziskani request dispatcheru
                RequestDispatcher requestDispatcher =
                        request.getRequestDispatcher("/views/admin/view_admin_add_category.jsp");

                requestDispatcher.forward(request, response);
            }
        }




    }
}
