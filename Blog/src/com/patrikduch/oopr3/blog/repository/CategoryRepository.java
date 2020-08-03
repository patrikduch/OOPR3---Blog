package com.patrikduch.oopr3.blog.repository;

import com.patrikduch.oopr3.blog.helper.hibernate.HibernateHelper;
import com.patrikduch.oopr3.blog.interfaces.ICategoryRepository;
import com.patrikduch.oopr3.blog.model.Category;
import com.patrikduch.oopr3.blog.model.User;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {

    private HibernateHelper _hibernateHelper;

    public CategoryRepository() {

        _hibernateHelper = HibernateHelper.self;
    }


    @Override
    public List<Category> getCategories() {

        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();
        // Počátek transakce
        hibernateSession.beginTransaction();

        // Zpracovani dotazu
        // Tvorba dotazu
        String query = String.format("From Category");

        List<Category> categoryList =  hibernateSession.createQuery(query).list();
        // commitnutí transakce
        hibernateSession.getTransaction().commit();
        // zbaveni se zdroju
        hibernateSession.close();

        return categoryList;

    }

    @Override
    public void addCategory(Category inputCategory) {

        // Zkontrolovani zda taková kategorie už neexistuje
        Category category = getCategoryByName(inputCategory.getName());

        if(category == null) {

            // Ziskani session factory
            Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();
            // Počátek transakce
            hibernateSession.beginTransaction();

            // Smazani ziskané kategorie z DB
            hibernateSession.save(inputCategory);

            // commitnutí transakce
            hibernateSession.getTransaction().commit();
            // zbaveni se zdroju
            hibernateSession.close();

        }


    }

    @Override
    public void deleteCategory(int id) {

        // Ziskani kategorie na základě primárního klíče (id)
        Category  category = getCategoryById(id);

        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();
        // Počátek transakce
        hibernateSession.beginTransaction();

        // Smazani ziskané kategorie z DB
        hibernateSession.remove(category);

        // commitnutí transakce
        hibernateSession.getTransaction().commit();
        // zbaveni se zdroju
        hibernateSession.close();

    }

    @Override
    public Category getCategoryByName(String categoryName) {

        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();
        // Počátek transakce
        hibernateSession.beginTransaction();

        String query = String.format("From com.patrikduch.oopr3.blog.model.Category C WHERE C.name='%s'", categoryName);
        Category category = null;

        try {

            category = (Category) hibernateSession.createQuery(query).getSingleResult();

        } catch (NoResultException ex) {

            category = null;
        }

        finally {

            // commitnutí transakce
            hibernateSession.getTransaction().commit();
            // zbaveni se zdroju
            hibernateSession.close();

        }




        return category;


    }

    @Override
    public Category getCategoryById(int id) {

        // Ziskani session factory
        Session hibernateSession = _hibernateHelper.getFactory().getCurrentSession();
        // Počátek transakce
        hibernateSession.beginTransaction();

        // Ziskani kategorie dle primárního klíče
        Category category = hibernateSession.get(Category.class, id);

        // commitnutí transakce
        hibernateSession.getTransaction().commit();
        // zbaveni se zdroju
        hibernateSession.close();

        return category;
    }


}
