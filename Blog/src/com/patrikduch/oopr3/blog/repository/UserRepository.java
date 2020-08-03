package com.patrikduch.oopr3.blog.repository;
import com.patrikduch.oopr3.blog.helper.encryption.MD5Crypt;
import com.patrikduch.oopr3.blog.helper.hibernate.HibernateHelper;
import com.patrikduch.oopr3.blog.interfaces.IUserRepository;
import com.patrikduch.oopr3.blog.model.Role;
import com.patrikduch.oopr3.blog.model.User;
import org.hibernate.Session;

import javax.jws.soap.SOAPBinding;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;


public class UserRepository implements IUserRepository {

    private HibernateHelper _hibernateHelper;
    private RoleRepository _roleRep;

    public UserRepository() {

        _hibernateHelper = HibernateHelper.self;
        _roleRep = new RoleRepository();


    }


    @Override
    public void registerUser(User user, Role role) {

        // Přidání role, pokud ještě neexistuje
        role = _roleRep.addRole(role);

        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();

        // Počátek transakce
        hibernateSession.beginTransaction();

        // Šifrování hesla
        if(user != null) {
            user.setPassword(MD5Crypt.cryptWithMD5(user.getPassword()));
        }

        // Přidání role přislušnému uživateli
        if(role != null) {

            user.getRoles().add(role);
        }

        // uloženi uživatele
        hibernateSession.save(user);

        // commitnutí transakce
        hibernateSession.getTransaction().commit();

        // zbaveni se zdroju
        hibernateSession.close();

    }

    /// Ziskání uživatele z DB pomocí prímárního klíče
    @Override
    public User getUserbyID(int id) {
        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();
        // Počátek transakce
        hibernateSession.beginTransaction();
        User user = hibernateSession.get(User.class, id);
        // commitnutí transakce
        hibernateSession.getTransaction().commit();
        // zbaveni se zdroju
        hibernateSession.close();
        return user;
    }


    //  Ziskani uživatele na základě jeho přihlašovacího jména
    @Override
    public User getUserbyName(String username) {

        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();

        // Počátek transakce
        hibernateSession.beginTransaction();

        // Tvorba dotazu
        String query = String.format("From com.patrikduch.oopr3.blog.model.User U WHERE U.username='%s'", username);

        User res = null;

        try {
            res = (User) hibernateSession.createQuery(query).getSingleResult();

        } catch (NoResultException ex) {
            res = null;
        }

        // commitnutí transakce
        hibernateSession.getTransaction().commit();

        // zbaveni se zdroju
        hibernateSession.close();


        return res;
    }

    @Override
    public List<User> getUserList() {
        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();
        // Počátek transakce
        hibernateSession.beginTransaction();
        List<User> userList = hibernateSession.createQuery("from com.patrikduch.oopr3.blog.model.User").getResultList();
        // commitnutí transakce
        hibernateSession.getTransaction().commit();
        // zbaveni se zdroju
        hibernateSession.close();
        return userList;
    }

    @Override
    public boolean isUserAdmin(User user) {

        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();
        // Počátek transakce
        hibernateSession.beginTransaction();

        // Tvorba dotazu
        String query = String.format("From com.patrikduch.oopr3.blog.model.User" +
                        " U WHERE U.username='%s'",
                user.getUsername(), user.getEmail());

        User userRes = (User) hibernateSession.createQuery(query).getSingleResult();


        List<Role> roles = userRes.getRoles();

        boolean isAdmin = false;
        for(Role role : roles) {


            if(role.getRoleName().equals("Admin")) {

                isAdmin = true;

            }
        }


        // commitnutí transakce
        hibernateSession.getTransaction().commit();
        // zbaveni se zdroju
        hibernateSession.close();





        return isAdmin;
    }

}
