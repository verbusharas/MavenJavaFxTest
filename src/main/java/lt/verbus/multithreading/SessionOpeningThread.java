package lt.verbus.multithreading;
import lt.verbus.util.SessionFactoryUtil;
import org.hibernate.Session;

public class SessionOpeningThread extends Thread {

    private Session session;

    @Override
    public void run(){
        while (session == null) {
            try {
                Thread.sleep(1000);
                session = SessionFactoryUtil.getSession();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
