package com.patrikduch.oopr3.blog.servlets.services.Category;
import com.patrikduch.oopr3.blog.model.User;
import com.patrikduch.oopr3.blog.repository.CategoryRepository;
import com.patrikduch.oopr3.blog.repository.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryRemovalServlet", urlPatterns = "/admin/category/remove")
public class CategoryRemovalServlet extends HttpServlet {

    private CategoryRepository _categoryRep;
    private UserRepository _userRep;

    public CategoryRemovalServlet() {

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
                if(request.getParameter("id") != null) {

                    // Ziskani poskytnutého id
                    int id = Integer.parseInt(request.getParameter("id"));
                    _categoryRep.deleteCategory(id);
                }
                response.sendRedirect("/admin/categories");
            }
        }
    }
}
