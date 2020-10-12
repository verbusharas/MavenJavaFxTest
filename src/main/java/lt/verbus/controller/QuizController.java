package lt.verbus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import lt.verbus.domain.entity.Answer;
import lt.verbus.domain.model.Question;
import lt.verbus.service.QuestionService;
import lt.verbus.service.UserServiceSingleton;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class QuizController implements Initializable {

    @FXML
    private Text txtQuestionNumber;

    @FXML
    private Text txtQuestion;

    @FXML
    private Slider yearSlider;

    @FXML
    private Text txtSliderIndicator;

    @FXML
    private Button btNextQuestion;

    private QuestionService questionService;
    private UserServiceSingleton userService;

    List<Question> questions;
    private int currentQuestionIndex;
    private int totalQuestions;

    private int currentYear;
    private int maxYear;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        injectServices();
        questions = questionService.findAll();
        currentQuestionIndex = 0;
        totalQuestions = questions.size();
        formatYearSlider();
        showNextQuestion();
    }

    private void injectServices() {
        questionService = new QuestionService();
        userService = UserServiceSingleton.getInstance();
    }

    private void formatYearSlider() {
        currentYear = LocalDate.now().getYear();
        maxYear = currentYear + 110;
        yearSlider.valueProperty().addListener((obs, oldval, newVal) ->
                txtSliderIndicator.setText(String.valueOf(5 * Math.round(newVal.doubleValue() / 5))));
        yearSlider.minProperty().setValue(currentYear);
        yearSlider.maxProperty().setValue(maxYear);
        yearSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                return String.valueOf(n.intValue());
            }

            @Override
            public Double fromString(String s) {
                return Double.valueOf(s);
            }
        });
    }

    public void btNextQuestionClicked() throws IOException {
        setUserAnswer(Integer.parseInt(txtSliderIndicator.getText()));
        boolean hasRemainingQuestions = ++currentQuestionIndex < totalQuestions;
        if (hasRemainingQuestions) {
            showNextQuestion();
        } else {
            btNextQuestion.setDisable(true);
            StageController.loadResultScreen();
        }
    }

    private void setUserAnswer(int userAnswerValue) {
        Answer userAnswer = new Answer();
        userAnswer.setQuestionIndex(currentQuestionIndex);
        userAnswer.setAnswer(userAnswerValue);
        userService.addAnswer(userAnswer);
    }

    private void showNextQuestion() {
        Question question = questions.get(currentQuestionIndex);
        txtQuestion.setText(question.getText());
        txtQuestionNumber.setText(String.format("Klausimas %d iš %d", currentQuestionIndex+1, totalQuestions));
        yearSlider.setValue(currentYear);
    }


}
