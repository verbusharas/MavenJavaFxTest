package lt.verbus.repository;

import lt.verbus.model.User;
import lt.verbus.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepository {

    public void save(User user) {
        Session session = SessionFactoryUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }
}
