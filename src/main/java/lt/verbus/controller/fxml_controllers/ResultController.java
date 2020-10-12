package lt.verbus.controller.fxml_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import lt.verbus.controller.custom_panels.AnswerStatsPanel;
import lt.verbus.domain.entity.Answer;
import lt.verbus.service.UserServiceSingleton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    @FXML
    private VBox vboxFeed;

    private UserServiceSingleton userServiceSingleton;

    private List<Answer> userAnswers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        injectServices();
        userAnswers = userServiceSingleton.getUser().getAnswers();
        showResults();
        saveResults();
    }

    private void injectServices() {
        userServiceSingleton = UserServiceSingleton.getInstance();
    }

    private void showResults() {
        userAnswers.forEach(answer -> vboxFeed.getChildren().add(new AnswerStatsPanel(answer)));
    }

    private void saveResults() {
        userServiceSingleton.saveUser();
    }

}