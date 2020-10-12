package lt.verbus.controller.stage_controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StageController {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setResizable(false);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void loadWelcomeScreen() throws IOException {
        Parent welcomeRoot = FXMLLoader.load(StageController.class.getResource("/fxml/welcome.fxml"));
        welcomeRoot.getStylesheets().add(StageController.class.getResource("/style/default_theme.css").toExternalForm());
        primaryStage.setScene(new Scene(welcomeRoot));
    }

    public static void loadQuizScreen() throws IOException {
        Parent quizRoot = FXMLLoader.load(StageController.class.getResource("/fxml/quiz.fxml"));
        quizRoot.getStylesheets().add(StageController.class.getResource("/style/default_theme.css").toExternalForm());
        primaryStage.setScene(new Scene(quizRoot));
    }

    public static void loadResultScreen() throws IOException {
        Parent resultRoot = FXMLLoader.load(StageController.class.getResource("/fxml/result.fxml"));
        primaryStage.setScene(new Scene(resultRoot));
    }

    public static void popUpQuestionReminder() throws IOException {
        Parent remindQuestionsRoot = FXMLLoader.load(StageController.class.getResource("/fxml/remind_questions.fxml"));
        Stage popUpstage = new Stage(StageStyle.UTILITY);
        popUpstage.initModality(Modality.APPLICATION_MODAL);
        popUpstage.setTitle("Klausimai");
        Scene scene = new Scene(remindQuestionsRoot);
        popUpstage.setScene(scene);
        popUpstage.showAndWait();
    }
}
