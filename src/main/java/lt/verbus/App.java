package lt.verbus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lt.verbus.repository.QuestionRepository;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;

public class App extends Application {

    private static Parent welcomeRoot;
    private static Parent sampleRoot;
    private static Parent quizRoot;
    private static Parent resultRoot;

    private static Stage primaryStage;

    public static void main(String[] args) {
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
        welcomeRoot = FXMLLoader.load(App.class.getResource("/fxml/welcome.fxml"));
        primaryStage.setScene(new Scene(welcomeRoot));
    }

    public static void loadSampleScreen() throws IOException {
        sampleRoot = FXMLLoader.load(App.class.getResource("/fxml/sample.fxml"));
        primaryStage.setScene(new Scene(sampleRoot));
    }

    public static void loadQuizScreen() throws IOException {
        quizRoot = FXMLLoader.load(App.class.getResource("/fxml/quiz.fxml"));
        primaryStage.setScene(new Scene(quizRoot));
    }

    public static void loadResultScreen() throws IOException {
        resultRoot = FXMLLoader.load(App.class.getResource("/fxml/result.fxml"));
        primaryStage.setScene(new Scene(resultRoot));
        System.out.println("switching to results");
    }


}

