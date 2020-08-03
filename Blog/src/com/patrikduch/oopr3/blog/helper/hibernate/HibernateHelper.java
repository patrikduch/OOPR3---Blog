package com.patrikduch.oopr3.blog.helper.hibernate;

import com.patrikduch.oopr3.blog.model.Category;
import com.patrikduch.oopr3.blog.model.Role;
import com.patrikduch.oopr3.blog.model.RoleUsers;
import com.patrikduch.oopr3.blog.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(Role.class)
            .addAnnotatedClass(Category.class)
            .buildSessionFactory();

    private HibernateHelper() {

    }

    private HibernateHelper hibernateHelper;


    public static final HibernateHelper self = new HibernateHelper();


    public SessionFactory getFactory() {

        return factory;
    }



}
