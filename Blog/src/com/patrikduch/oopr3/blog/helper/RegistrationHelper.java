package com.patrikduch.oopr3.blog.helper;

public class RegistrationHelper {

    public static boolean areFieldsFilled(String username) {
        return username.length()>0;
    }
}
