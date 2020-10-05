package lt.verbus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import lt.verbus.service.QuestionService;
import lt.verbus.service.UserService;

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
    private UserService userService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder results = new StringBuilder();
        questionService = QuestionService.getInstance();
        userService = UserService.getInstance();
        List<Integer> userAnswers = userService.getUser().getAnswers();
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
        txtUserInfo.setText("Vartotojo: " + userService.getUser().toString() + " atsakymai:");
        txtStatistics.setText(results.toString());
    }
}
