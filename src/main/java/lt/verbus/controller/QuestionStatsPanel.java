package lt.verbus.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import lt.verbus.domain.entity.Answer;
import lt.verbus.domain.entity.User;
import lt.verbus.domain.model.Question;
import lt.verbus.service.QuestionService;
import lt.verbus.service.UserServiceSingleton;
import lt.verbus.service.UserStatisticsService;

import java.util.List;

public class QuestionStatsPanel extends GridPane {

    private QuestionService questionService;
    private UserServiceSingleton userServiceSingleton;
    private UserStatisticsService userStatisticsService;

    private User user;
    private List<Answer> userAnswers;

    private int questionIndex;
    private Label lblQuestionNumber;
    private Label lblUserAnswer;
    private Label lblCorrectAnswer;
    private Label lblPercentageToCorrectAnswer;
    private Label lblPercentageToAverageAnswer;
    private Label lblComparativeStringToCorrectAnswer;
    private Label lblComparativeStringToAverageAnswer;
    private Button btRemindQuestion;

    public QuestionStatsPanel(int questionIndex) {
        injectServices();
        injectNodes();
        this.questionIndex = questionIndex;
        user = userServiceSingleton.getUser();
        userAnswers = user.getAnswers();
        drawPanel();
    }

    private void injectServices() {
        questionService = new QuestionService();
        userStatisticsService = new UserStatisticsService();
        userServiceSingleton = UserServiceSingleton.getInstance();
    }

    private void injectNodes() {
        lblQuestionNumber = new Label();
        lblUserAnswer = new Label();
        lblCorrectAnswer = new Label();
        lblPercentageToCorrectAnswer = new Label();
        lblPercentageToAverageAnswer = new Label();
        lblComparativeStringToCorrectAnswer = new Label();
        lblComparativeStringToAverageAnswer = new Label();
        btRemindQuestion = new Button();
    }

    private void drawPanel() {
        populateGridWithNodes();

        Question question = questionService.getQuestionByIndex(questionIndex);
        Answer userAnswer = user.getAnswers().get(questionIndex);

        StringBuilder userAnswerText = new StringBuilder("Tu pasirinkai: ");
        userAnswerText.append(userAnswer.getAnswer());

        StringBuilder correctAnswerText = new StringBuilder("Mes prognozuojame: ");
        correctAnswerText.append(question.getCorrectAnswer());


    }

    private void populateGridWithNodes() {
        add(lblQuestionNumber, 0, 0);
        add(btRemindQuestion, 0, 0);
        add(lblUserAnswer, 1,0);
        add(lblCorrectAnswer, 1,1);
        add(lblPercentageToCorrectAnswer, 2,0);
        add(lblPercentageToAverageAnswer, 2,1);
        add(lblComparativeStringToCorrectAnswer, 3,0);
        add(lblComparativeStringToAverageAnswer, 3,1);
    }

    private String buildPercentageString(double ratio, String comparativeSuffix) {
        int roundedRatio = (int) Math.round(ratio * 100);
        if (roundedRatio > 0) {
            return ("▼ " + Math.abs(roundedRatio) + "% pesimistiškesnis nei " + comparativeSuffix);
        }
        if (roundedRatio < 0) {
            return ("▲ " + Math.abs(roundedRatio) + "% optimistiškenis nei " + comparativeSuffix);
        } else return ("▬ manai lygiai taip, kaip ir " + comparativeSuffix);
    }

    private String buildComparativeLabel(double ratio, String comparativeSuffix) {
        int roundedRatio = (int) Math.round(ratio * 100);
        if (roundedRatio > 0) {
            return ("▼ " + Math.abs(roundedRatio) + "% pesimistiškesnis nei " + comparativeSuffix);
        }
        if (roundedRatio < 0) {
            return ("▲ " + Math.abs(roundedRatio) + "% optimistiškenis nei " + comparativeSuffix);
        } else return ("▬ manai lygiai taip, kaip ir " + comparativeSuffix);
    }


}
