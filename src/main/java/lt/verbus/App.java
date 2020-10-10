package lt.verbus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lt.verbus.dao.UserDao;
import lt.verbus.dao.UserStatisticsDao;

import java.io.IOException;

public class App extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setResizable(true);
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


    public static void popUpQuestionReminder() throws IOException {
        Parent remindQuestionsRoot = FXMLLoader.load(App.class.getResource("/fxml/remind_questions.fxml"));
        Stage popUpstage = new Stage(StageStyle.UTILITY);
        popUpstage.initModality(Modality.APPLICATION_MODAL);
        popUpstage.setTitle("Klausimai");
        Scene scene = new Scene(remindQuestionsRoot);
        popUpstage.setScene(scene);
        popUpstage.showAndWait();
    }

}

