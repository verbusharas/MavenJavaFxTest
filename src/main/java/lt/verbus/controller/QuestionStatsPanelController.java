package lt.verbus.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class QuestionStatsPanelController {

    private int number;
    private boolean biasDirty;

    @FXML
    private Label lblQuestionNumber;

    @FXML
    private Label lblUserAnswer;

    @FXML
    private Label lblCorrectAnswer;

    @FXML
    private Label lblPercentageToCorrectAnswer;

    @FXML
    private Label lblPercentageToAverageAnswer;

    @FXML
    private Label lblComparativeStringToCorrectAnswer;

    @FXML
    private Label lblComparativeStringToAverageAnswer;

    @FXML
    private Button btRemindQuestion;

}
