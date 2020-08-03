package com.patrikduch.oopr3.blog.repository;

import com.patrikduch.oopr3.blog.helper.hibernate.HibernateHelper;
import com.patrikduch.oopr3.blog.interfaces.IRoleRepository;
import com.patrikduch.oopr3.blog.model.Role;
import org.hibernate.Session;

import java.util.List;
import java.util.NoSuchElementException;

public class RoleRepository implements IRoleRepository {

    private HibernateHelper _hibernateHelper;

    public RoleRepository() {
        _hibernateHelper = HibernateHelper.self;
    }


    @Override
    public Role getRoleByName(String roleName) {

        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();

        // Počátek transakce
        hibernateSession.beginTransaction();

        // Prochazeni roli

        List<Role> roleList = hibernateSession.createQuery("from com.patrikduch.oopr3.blog.model.Role").getResultList();

        // Ziskani role

        Role role = null;

        try {

            role = roleList.stream().filter(c->c.getRoleName().equals(roleName)).findFirst().get();

        } catch (NoSuchElementException ex) {

            role = null;

        }

        // commitnutí transakce
        hibernateSession.getTransaction().commit();

        // zbaveni se zdroju
        hibernateSession.close();

        return role;
    }

    @Override
    public Role addRole(Role role) {

        // Pokud role už existuje nic nepřidávat
        if(getRoleByName(role.getRoleName()) != null) {
            return null;
        }

        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();

        // Počátek transakce
        hibernateSession.beginTransaction();

        // commitnutí transakce
        hibernateSession.getTransaction().commit();

        // zbaveni se zdroju
        hibernateSession.close();

        return role;

    }
}
