package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String ADD_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS users  (
            id SERIAL PRIMARY KEY ,
            name VARCHAR(255) NOT NULL ,
            lastname VARCHAR(255) NOT NULL ,
            age INT NOT NULL )
            """;
    private static final String DROP_TABLE_SQL = """
            DROP TABLE IF EXISTS users
            """;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery(ADD_TABLE_SQL);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            if (transaction !=null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try  (var session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery(DROP_TABLE_SQL);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            if (transaction !=null){
                transaction.rollback();
                }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try  (var session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            System.out.printf("User %s добавлен в таблицу\n", name);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            if (transaction !=null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try  (var session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null)
                session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            if (transaction !=null){
                transaction.rollback();
            }
        }
    }


    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> list = null;
        try  (var session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User", User.class);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction !=null){
                transaction.rollback();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        try (var session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(" delete from User");
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction !=null){
                transaction.rollback();
            }
        }
    }
}
