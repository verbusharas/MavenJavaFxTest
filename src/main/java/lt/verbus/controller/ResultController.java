package lt.verbus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import lt.verbus.domain.entity.Answer;
import lt.verbus.domain.entity.User;
import lt.verbus.domain.model.Question;
import lt.verbus.service.QuestionService;
import lt.verbus.service.StatisticsService;
import lt.verbus.service.UserServiceSingleton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    @FXML
    private Text txtStatistics;

    @FXML
    private Text txtUserInfo;

    private QuestionService questionService;
    private UserServiceSingleton userServiceSingleton;
    private StatisticsService statisticsService;

    private User user;
    private List<Question> questions;
    private List<Answer> userAnswers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        injectServices();

        questions = questionService.findAll();
        user = userServiceSingleton.getUser();
        userAnswers = user.getAnswers();

        showResults();
        saveResults();
    }

    private void injectServices() {
        questionService = new QuestionService();
        statisticsService = new StatisticsService();
        userServiceSingleton = UserServiceSingleton.getInstance();
    }

    private void showResults(){
        StringBuilder results = new StringBuilder();
        userAnswers.forEach(answer -> {
            results.append("Klausimo nr: ")
                    .append(answer.getQuestionNumber()+1)
                    .append(" Pasirinktas atsakymas: ")
                    .append(answer)
                    .append(" Teisingas atsakymas: ")
                    .append(questions.get(answer.getQuestionNumber()).getCorrectAnswer())
                    .append("\n");
        });
        txtUserInfo.setText("Vartotojo: " + user + " atsakymai:");
        txtStatistics.setText(results.toString());
    }

    private void saveResults() {
        statisticsService.save(user);
        userServiceSingleton.save(user);
    }

}
