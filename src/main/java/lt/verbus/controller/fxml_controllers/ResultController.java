package lt.verbus.controller.fxml_controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lt.verbus.controller.custom_panels.AnswerStatsPanel;
import lt.verbus.controller.custom_panels.OverallStatsPanel;
import lt.verbus.domain.entity.Answer;
import lt.verbus.domain.entity.User;
import lt.verbus.service.UserServiceSingleton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    @FXML
    private VBox vboxFeed;

    @FXML
    private ScrollPane scrollFeed;

    @FXML
    private VBox vboxFeedTitle;

    @FXML
    private Text txtUserInfo;

    private UserServiceSingleton userServiceSingleton;

    private List<Answer> userAnswers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        injectServices();
        formatVisualElements();
        showResults();
        saveResults();
    }

    private void formatVisualElements() {
        User user = userServiceSingleton.getUser();
        userAnswers = user.getAnswers();
        txtUserInfo.setText(user.toString());
        scrollFeed.setContent(vboxFeed);
        scrollFeed.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollFeed.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        vboxFeed.setStyle("-fx-background-color: #342f37");
        scrollFeed.setStyle("-fx-background-color: transparent");
    }


    private void injectServices() {
        userServiceSingleton = UserServiceSingleton.getInstance();
    }

    private void showResults() {
        vboxFeedTitle.getChildren().add(new OverallStatsPanel());
        userAnswers.forEach(answer -> vboxFeed.getChildren().add(new AnswerStatsPanel(answer)));
    }

    private void saveResults() {
        userServiceSingleton.saveUser();
    }

}