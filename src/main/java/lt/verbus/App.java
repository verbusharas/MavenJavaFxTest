package lt.verbus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class App extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.close();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;
        primaryStage.setResizable(false);

        loadWelcomeScreen();

        primaryStage.show();


    }



    public static void loadWelcomeScreen() throws IOException {
        Parent welcomeRoot = FXMLLoader.load(App.class.getResource("/fxml/welcome.fxml"));
        primaryStage.setScene(new Scene(welcomeRoot));
    }

    public static void loadQuizScreen() throws IOException {
        Parent quizRoot = FXMLLoader.load(App.class.getResource("/fxml/quiz.fxml"));
        primaryStage.setScene(new Scene(quizRoot));
    }

    public static void loadResultScreen() throws IOException {
        Parent resultRoot = FXMLLoader.load(App.class.getResource("/fxml/result.fxml"));
        primaryStage.setScene(new Scene(resultRoot));
        System.out.println("switching to results");
    }


}

