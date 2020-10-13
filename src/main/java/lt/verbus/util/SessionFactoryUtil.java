package lt.verbus.util;

import lt.verbus.exception.EmptyRepositoryException;
import lt.verbus.exception.NumberOfQuestionsMismatchException;
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
                        InitialValidatorService validator = new InitialValidatorService();
                        try {
                            validator.validateUserRepository();
                            System.out.println("INFO: USERS FOUND");
                        } catch (EmptyRepositoryException e) {
                            System.out.println("ERROR: No users found in database.");
                        }

                        try {
                            validator.validateQuestionRepository();
                            System.out.println("INFO: NUMBER OF QUESTION MATCHES ANSWERS");
                        } catch (NumberOfQuestionsMismatchException e) {
                            System.out.println("ERROR: Number of questions differs from number of answers in database.");
                        }
                    };
            Thread sessionOpeningThread = new Thread(persistance);
            sessionOpeningThread.start();
        }
        return session;
    }

}