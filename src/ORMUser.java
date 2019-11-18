import java.util.List;

import org.hibernate.Transaction;


import org.hibernate.Session;


public class ORMUser {

    public void addUser(User user) {
        Transaction transaction = null;
        try (Session session = ORMUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public User getUser(String username) {
        Transaction transaction = null;
        User user = null;
        try (Session session = ORMUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, username);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

}