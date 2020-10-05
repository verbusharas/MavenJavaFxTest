package lt.verbus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import lt.verbus.model.User;
import lt.verbus.service.QuestionService;
import lt.verbus.service.StatisticsService;
import lt.verbus.service.UserServiceSingleton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultController implements Initializable {

    @FXML
    private Text txtStatistics;

    @FXML
    private Text txtUserInfo;

    private QuestionService questionService;
    private UserServiceSingleton userServiceSingleton;
    private StatisticsService statisticsService;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder results = new StringBuilder();
        questionService = new QuestionService();
        userServiceSingleton = UserServiceSingleton.getInstance();
        user = userServiceSingleton.getUser();

        List<Integer> userAnswers = userServiceSingleton.getUser().getAnswers();
        statisticsService.save(user);
//        String userPortraitIndependent = statisticsService.getUserPortraitIndependent(user);
//        String userPortraitRelational = statisticsService.getUserPortraitRelational(user);

        AtomicInteger questionNumber = new AtomicInteger(1);
        userAnswers.forEach(answer -> {
            results.append("Klausimo nr: ")
                    .append(questionNumber.get())
                    .append(" Pasirinktas atsakymas: ")
                    .append(answer)
                    .append(" Teisingas atsakymas: ")
                    .append(questionService.getTrueAnswerByNumber(questionNumber.getAndIncrement()))
                    .append("\n");
        });
        txtUserInfo.setText("Vartotojo: " + userServiceSingleton.getUser().toString() + " atsakymai:");
        txtStatistics.setText(results.toString());
    }
}
