package lt.verbus.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

    public static SessionFactory factory = null;
    private static Session session = null;

    private static SessionFactory getFactory() {
        if (factory == null) {
            factory = new Configuration().configure().buildSessionFactory();
        }
        return factory;
    }

    public static Session getSession()  {
        if (session == null || !session.isOpen()) {
            session = SessionFactoryUtil.getFactory().openSession();
        }
        return session;
    }

}