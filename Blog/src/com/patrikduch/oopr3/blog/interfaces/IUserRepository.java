package com.patrikduch.oopr3.blog.interfaces;

import com.patrikduch.oopr3.blog.model.Role;
import com.patrikduch.oopr3.blog.model.User;

import java.util.List;

public interface IUserRepository {

    void registerUser(User user, Role role);
    User getUserbyID(int id);
    User getUserbyName(String username);
    List<User> getUserList();
    boolean isUserAdmin(User user);

}
