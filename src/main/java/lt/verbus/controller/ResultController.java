package lt.verbus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import lt.verbus.App;
import lt.verbus.domain.entity.Answer;
import lt.verbus.domain.entity.User;
import lt.verbus.domain.model.Question;
import lt.verbus.service.QuestionService;
import lt.verbus.service.UserStatisticsService;
import lt.verbus.service.UserServiceSingleton;

import java.io.IOException;
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
    private UserStatisticsService userStatisticsService;

    private User user;
    private List<Question> questions;
    private List<Answer> userAnswers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        injectServices();

        questions = questionService.findAll();
        user = userServiceSingleton.getUser();
        userAnswers = user.getAnswers();

        saveResults();
        showResults();
    }

    private void injectServices() {
        questionService = new QuestionService();
        userStatisticsService = new UserStatisticsService();
        userServiceSingleton = UserServiceSingleton.getInstance();
    }

    private void showResults() {
        StringBuilder results = new StringBuilder();

        userAnswers.forEach(answer -> {
            int questionIndex = answer.getQuestionNumber();
            Question question = questions.get(questionIndex);
            String correctAnswer = question.getCorrectAnswer();
            String allowedDeviation = question.getAllowedAnswerDeviationRange();

            double ratioToCorrectAnswer = userStatisticsService.compareUserAnswerToCorrectAnswer(questionIndex, answer.getAnswer());
            double ratioToAvgAnswer = userStatisticsService.compareUserAnswerToAverageAnswer(questionIndex, answer.getAnswer());

            results.append("Klausime nr: ")
                    .append(questionIndex + 1)
                    .append("\nPasirinkai atsakymą: ")
                    .append(answer)
                    .append(" (pagal mūsų prognozes ")
                    .append(correctAnswer)
                    .append(")\n")
                    .append("Šiuo klausimu tu:\n")
                    .append(buildComparativeString(ratioToCorrectAnswer, "prognozuojama.\n"))
                    .append(buildComparativeString(ratioToAvgAnswer, "dauguma vartotojų.\n\n"));
        });
        txtUserInfo.setText("Vartotojo: " + user + " atsakymai:");
        txtStatistics.setText(results.toString());
    }

    private String buildComparativeString(double ratio, String comparativeSuffix) {
        int roundedRatio = (int) Math.round(ratio * 100);
        if (roundedRatio > 0) {
            return ("▼ " + Math.abs(roundedRatio) + "% pesimistiškesnis nei " + comparativeSuffix);
        }
        if (roundedRatio < 0) {
            return ("▲ " + Math.abs(roundedRatio) + "% optimistiškenis nei " + comparativeSuffix);
        } else return ("▬ manai lygiai taip, kaip ir " + comparativeSuffix);
    }


    public void btShowQuestionsClicked() throws IOException {
        App.popUpQuestionReminder();
    }

    private void saveResults() {
        userServiceSingleton.save(user);
    }

}
