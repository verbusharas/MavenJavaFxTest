package lt.verbus.controller.custom_panels;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lt.verbus.controller.stage_controllers.StageController;
import lt.verbus.domain.entity.Answer;
import lt.verbus.domain.model.Question;
import lt.verbus.service.QuestionService;
import lt.verbus.service.UserStatisticsService;

import java.io.IOException;

public class AnswerStatsPanel extends HBox {

    private final Answer userAnswer;

    private QuestionService questionService;
    private UserStatisticsService userStatisticsService;

    private Text txtQuestionNumber;
    private Text txtUserAnswer;
    private Text txtCorrectAnswer;
    private TextFlow txtStatisticsAgainstCorrect;
    private TextFlow txtStatisticsAgainstSingleAvg;
    private Hyperlink btRemindQuestion;

    public AnswerStatsPanel(Answer userAnswer) {
        this.userAnswer = userAnswer;
        injectServices();
        injectNodes();
        setNodeValues();
        formatPanel();
        populatePanelWithNodes();
    }

    private void formatPanel() {
        setMinHeight(50);
        setSpacing(10);
        setPadding(new Insets(10,10,10,10));
        setStyle("-fx-background-color: white;");
    }

    private void injectServices() {
        questionService = new QuestionService();
        userStatisticsService = new UserStatisticsService();
    }

    private void injectNodes() {
        txtQuestionNumber = new Text();
        btRemindQuestion = new Hyperlink();
        txtUserAnswer = new Text();
        txtCorrectAnswer = new Text();
    }

    private void setNodeValues() {
        int questionIndex = userAnswer.getQuestionIndex();
        Question question = questionService.getQuestionByIndex(questionIndex);

        txtQuestionNumber.setText("KLAUSIME NR. " + (questionIndex + 1));
        btRemindQuestion.setText("Pamiršai klausimą?");
        btRemindQuestion.setOnAction(e -> btRemindQuestionClicked());

        txtUserAnswer.setText("Tu pasirinkai: " + userAnswer.getAnswer());
        txtCorrectAnswer.setText("Mes prognozuojame: " + question.getCorrectAnswer());

        double ratioAgainstCorrect = userStatisticsService
                .compareUserAnswerToCorrectAnswer(questionIndex, userAnswer.getAnswer());
        double ratioAgainstSingleAvg = userStatisticsService
                .compareUserAnswerToAverageAnswer(questionIndex, userAnswer.getAnswer());
        txtStatisticsAgainstCorrect =
                buildStatisticalText(ratioAgainstCorrect, "prognozuojama.");
        txtStatisticsAgainstSingleAvg =
                buildStatisticalText(ratioAgainstSingleAvg, "dauguma vartotojų.");
    }

    private void btRemindQuestionClicked() {
        try {
            StageController.popUpQuestionReminder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populatePanelWithNodes() {
        VBox questionColumn = new VBox();
        VBox answerColumn = new VBox();
        VBox statsColumn = new VBox();

        questionColumn.getChildren().addAll(txtQuestionNumber, btRemindQuestion);
        answerColumn.getChildren().addAll(txtUserAnswer, txtCorrectAnswer);
        statsColumn.getChildren().addAll(txtStatisticsAgainstCorrect, txtStatisticsAgainstSingleAvg);

        getChildren().addAll(questionColumn, answerColumn, statsColumn);
    }

    private TextFlow buildStatisticalText(double ratio, String comparativeSuffix) {

        Text percentage = new Text();
        Text comparativeString = new Text();

        int roundedRatio = (int) Math.round(ratio * 100);
        if (roundedRatio > 0) {
            percentage.setFill(Color.RED);
            percentage.setText("▼ " + Math.abs(roundedRatio) + "%");
            comparativeString.setText(" pesimistiškesnis nei " + comparativeSuffix);
        } else if (roundedRatio < 0) {
            percentage.setFill(Color.GREEN);
            percentage.setText("▲ " + Math.abs(roundedRatio) + "%");
            comparativeString.setText(" optimistiškenis nei " + comparativeSuffix);
        } else {
            percentage.setText("▬");
            comparativeString.setText("manai lygiai taip, kaip ir " + comparativeSuffix);
        }
        return new TextFlow(percentage, comparativeString);
    }

}
