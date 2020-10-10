package lt.verbus.repository;

import lt.verbus.domain.entity.User;
import lt.verbus.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepository implements Repository<User> {

    private CriteriaBuilder builder;
    private Root<User> root;
    private Session session;

    @Override
    public List<User> findAll() {
        CriteriaCustomizer<User> criteriaCustomizer =
                (criteria) -> {
                    criteria.select(root);
                    return criteria;
                };
        return createCustomQuery(criteriaCustomizer).getResultList();
    }

    @Override
    public User findById(int id) {
        CriteriaCustomizer<User> criteriaCustomizer =
                (criteria) -> {
                    criteria.select(root).where(builder.gt(root.get("id"), id));
                    return criteria;
                };
        return createCustomQuery(criteriaCustomizer).getSingleResult();
    }

    @Override
    public void save(User user) {
        session = SessionFactoryUtil.getSession();
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

    @Override
    public void delete(User user) {
        //TODO: delete user entry;
    }

    private Query<User> createCustomQuery(CriteriaCustomizer<User> criteriaCustomizer) {
        session = SessionFactoryUtil.getSession();
        builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        root = criteria.from(User.class);
        criteriaCustomizer.customize(criteria);
        return session.createQuery(criteria);
    }

}
