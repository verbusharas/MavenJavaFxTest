package lt.verbus.controller.stage_controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lt.verbus.controller.fxml_controllers.RemindQuestionsController;
import lt.verbus.domain.model.Question;

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

    public static void loadWelcomeScreen() {
        primaryStage.setScene(getStyledScene("welcome.fxml", "default_theme.css"));
    }

    public static void loadQuizScreen() {
        primaryStage.setScene(getStyledScene("quiz.fxml", "default_theme.css"));
    }

    public static void loadResultScreen() {
        primaryStage.setScene(getStyledScene("result.fxml", "default_theme.css"));
    }

    public static void popUpQuestionReminder(Question question) {
        FXMLLoader loader = getLoader("remind_questions.fxml");
        Scene scene = addStyleAndCreateScene(loader, "pop-up-theme.css");

        RemindQuestionsController controller = loader.getController();
        controller.initData(question);

        Stage stage = createPopUpStage(scene);
        stage.setTitle("Klausimas");
        stage.showAndWait();
    }

    public static void popUpQuestionRepositoryException() {
        Scene scene = getStyledScene("question_repository_exception.fxml", "pop-up-theme.css");

        Stage stage = createPopUpStage(scene);
        stage.setOnCloseRequest(e -> System.exit(1));
        stage.setTitle("Dėmesio!");
        stage.showAndWait();
    }

    private static Stage createPopUpStage(Scene scene) {
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        return stage;
    }

    private static FXMLLoader getLoader(String fxmlFileName) {
        return new FXMLLoader(StageController.class.getResource("/fxml/" + fxmlFileName));
    }

    private static Scene getStyledScene(String fxmlFileName, String cssFileName) {
        FXMLLoader loader = getLoader(fxmlFileName);
        return addStyleAndCreateScene(loader, cssFileName);
    }

    private static Scene addStyleAndCreateScene(FXMLLoader loader, String cssFileName) {
        try {
            Parent root = loader.load();
            root.getStylesheets().add(StageController.class.getResource("/style/" + cssFileName).toExternalForm());
            return new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
