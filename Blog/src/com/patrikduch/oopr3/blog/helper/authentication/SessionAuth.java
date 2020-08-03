package com.patrikduch.oopr3.blog.helper.authentication;

import com.patrikduch.oopr3.blog.model.User;
import com.patrikduch.oopr3.blog.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionAuth {


    public SessionAuth() {

    }

    public static void setAdmin(HttpSession _httpSession, User user, UserRepository _userRep) {
        _httpSession.setAttribute("actualUser", user);
        _httpSession.setAttribute("isAdmin",
                _userRep.isUserAdmin((User)_httpSession.getAttribute("actualUser")));
    }

    public static void userLogout(HttpSession session, HttpServletResponse response) throws IOException {

        if(isUserLogged(session)) {

            session.setAttribute("actualUser", null);
            response.sendRedirect("/");
        }

    }

    public static boolean isUserLogged(HttpSession httpSession) {
        return httpSession.getAttribute("actualUser")!= null;
    }

}
