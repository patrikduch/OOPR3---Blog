package com.patrikduch.oopr3.blog.servlets.services;

import com.patrikduch.oopr3.blog.helper.RegistrationConstraint;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@WebServlet(name = "RegisterServiceServlet", urlPatterns = "/registerChecker")
public class RegisterServiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Nastaveni typu odpovědi
        response.setContentType("application/json");
        // Kodovací sada pro odpověd
        response.setCharacterEncoding("UTF-8");
        // Ziskání instance pro zpracovávání odpovědi
        PrintWriter out = response.getWriter();
        // Vytvoření JSON objektu
        JSONObject jo = new JSONObject();
        // Procházení post dat z requestu
        Map<String, String[]> o = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : o.entrySet())
        {
            Map.Entry<String, String[]> tmp = entry;
            String actual = String.join(",", entry.getValue());

            if(entry.getKey().equals("username")) {
                try {
                    jo.put("usernameLengthTest", RegistrationConstraint.checkFieldLength("username", actual));
                    jo.put("usernameExistsTest",RegistrationConstraint.userExists(actual, request.getSession()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (entry.getKey().equals("password")) { // Kontrola prvně zadaného hesla

                try {
                    jo.put("passwordLengthTest",RegistrationConstraint.checkFieldLength("password", actual));
                    jo.put("passwordSpecialCharacter",RegistrationConstraint.checkFieldLength("password", actual));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            else if (entry.getKey().equals("email")) {

                try {
                    jo.put("emailLengthTest", RegistrationConstraint.checkFieldLength("email", actual));
                    jo.put("emailFormatTest", RegistrationConstraint.emailFormatChecker(actual));
                    jo.put("emailExistsTest",RegistrationConstraint.emailExists(actual, request.getSession()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }













        out.print(jo.toString());
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
