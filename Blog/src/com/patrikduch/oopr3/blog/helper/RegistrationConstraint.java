package com.patrikduch.oopr3.blog.helper;
import com.patrikduch.oopr3.blog.model.User;
import com.patrikduch.oopr3.blog.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegistrationConstraint {

    private static UserRepository _userRep = new UserRepository();


    private RegistrationConstraint() {


    }

    public static final int PASSWORDLENGTH = 8;
    public static final int USERNAMELENGTH = 6;
    public static final int EMAILLENGTH = 6;


    public static String userExists(String username, HttpSession session) {

            List<User> userList = _userRep.getUserList();

            User returnedUser = null;
            try {
                returnedUser = userList.stream().filter(c->c.getUsername().equals(username)).findFirst().get();

            } catch (NoSuchElementException ex) {

                return "";
            }

            if (returnedUser == null) {

                return "";

            } else {

                return "Takový uživatel již existuje";
            }
    }


    public static String emailExists(String email, HttpSession session) {

        List<User> userList = _userRep.getUserList();
        User returnedUser = null;
            try {
                returnedUser = userList.stream().filter(c->c.getEmail().equals(email)).findFirst().get();

            } catch (NoSuchElementException ex) {

                return "";
            }


            if (returnedUser == null) {

                return "";

            } else {

                return "Tato e-mailová adresa je již zabraná";
            }


    }

    public static String emailFormatChecker(String candidate) {

        Pattern p = Pattern.compile("[a-z]@{1,1}[a-z]+[.][a-z]{2}");
        Matcher m = p.matcher(candidate);

        if (m != null){

            if(m.find()) {

                return "";
            } else {

                return "Zkontrolujte si formát vaši e-mailové adresy";
            }
        }


        return null;

    }


    public static String checkFieldLength(String fieldType, String fieldValue) {

        String res ="";

        int test = fieldValue.length();

        if(fieldValue == null)
            return "";

         switch (fieldType) {
            case "password":
                res = (fieldValue).length() >= PASSWORDLENGTH ?  "" : "Vaše heslo musí být alespon "+
                        PASSWORDLENGTH + " znaků dlouhé";
                break;
             case "username":
                 res = (fieldValue).length() > USERNAMELENGTH-1 ?  "" : "Vaše uživatelské jméno musí být alespon "+
                         USERNAMELENGTH + " znaků dlouhé";

                 break;


             case "email":
                 res = (fieldValue).length() > EMAILLENGTH-1 ?  "" : "Vaše e-mailová adresa musí být alespon "+
                         EMAILLENGTH + " znaků dlouhá";

                 break;

        }

        return res;
    }


    public static String passwordEquality(String password, String passwordConfirm) {

        if(password == null && passwordConfirm == null)
            return "";

        return password.equals(passwordConfirm) ? "" : "Zadané hesla se neshodují";
    }





}
