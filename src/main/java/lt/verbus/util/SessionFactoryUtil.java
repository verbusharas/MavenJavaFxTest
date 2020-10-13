package lt.verbus.util;

import lt.verbus.exception.EmptyRepositoryException;
import lt.verbus.exception.NumberOfQuestionsMismatchException;
import lt.verbus.multithreading.SessionOpeningThread;
import lt.verbus.multithreading.UserSaveThread;
import lt.verbus.service.InitialValidatorService;
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
            Runnable persistance =
                    () -> {
                    session = SessionFactoryUtil.getFactory().openSession();
                    };
            Thread sessionOpeningThread = new Thread(persistance);
            sessionOpeningThread.start();
            System.out.println("starting session");
//            new SessionOpeningThread(session).start();
        }
        return session;
    }

}