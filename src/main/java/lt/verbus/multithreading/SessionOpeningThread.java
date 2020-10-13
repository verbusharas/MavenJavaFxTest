package lt.verbus.multithreading;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionOpeningThread extends Thread {

    private Session session;

    public SessionOpeningThread() {
    }

    @Override
    public void run(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        synchronized (session) {
            session = factory.openSession();
        }

    }

    public Session getSession() {
        synchronized (session) {
            return new Configuration().configure().buildSessionFactory().openSession();
        }
    }
}
