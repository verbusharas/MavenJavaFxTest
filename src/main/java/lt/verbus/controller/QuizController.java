package lt.verbus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import lt.verbus.App;
import lt.verbus.service.QuestionService;
import lt.verbus.service.UserServiceSingleton;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class QuizController implements Initializable {


    @FXML
    private Text txtQuestion;

    @FXML
    private Button btNextQuestion;

    @FXML
    private Slider yearSlider;

    @FXML
    private Text txtSliderIndicator;

    private QuestionService questionService;
    private UserServiceSingleton userService;

    private int currentQuestionNo;
    private int totalQuestions;

    private boolean hasRemainingQuestions;

    private int currentYear;
    private int maxYear;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionService = new QuestionService();
        userService = UserServiceSingleton.getInstance();
        currentQuestionNo = 1;
        totalQuestions = questionService.getTotalQuestionsCount();

        currentQuestionNo = 1;
        totalQuestions = questionService.getTotalQuestionsCount();

        txtQuestion.setText(questionService.getQuestionByNumber(currentQuestionNo));
        formatYearSlider();
    }

    public void formatYearSlider() {
        currentYear = LocalDate.now().getYear();
        maxYear = currentYear + 110;

        // 1. Binding Text to slider value
        // 2. Rounding slider value representation to the closest multiple of 5
        yearSlider.valueProperty().addListener((obs, oldval, newVal) ->
                txtSliderIndicator.setText(String.valueOf(5 * Math.round(newVal.doubleValue() / 5))));

        yearSlider.setValue(currentYear);

        // User can choose from 110 year span starting with current year
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
        boolean hasRemainingQuestions = currentQuestionNo <= totalQuestions;
        if (hasRemainingQuestions) {
            int userAnswer = Integer.parseInt(txtSliderIndicator.getText());
            userService.addAnswer(userAnswer);
            showNextQuestion();
        } else {
            App.loadResultScreen();
        }
    }

    private void showNextQuestion() {
        txtQuestion.setText(questionService.getQuestionByNumber(currentQuestionNo++));
        yearSlider.setValue(currentYear);
    }

}
