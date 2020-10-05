package lt.verbus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lt.verbus.App;
import lt.verbus.exception.EmptyFieldException;
import lt.verbus.model.User;
import lt.verbus.service.UserServiceSingleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class WelcomeController implements Initializable {

    @FXML
    private Button btStartQuiz;

    @FXML
    private Label lblName;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblException;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfCountry;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblException.setVisible(false);
    }

    public void btStartQuizClicked() throws IOException {
        try {
            validateTextFields();
            User currentUser = new User();
            currentUser.setName(tfName.getText());
            currentUser.setCity(tfCity.getText());
            currentUser.setCountry(tfCountry.getText());
            UserServiceSingleton userServiceSingleton = UserServiceSingleton.getInstance();
            userServiceSingleton.setUser(currentUser);
            App.loadQuizScreen();
        } catch (EmptyFieldException e) {
            lblException.setText(e.getMessage());
            lblException.setVisible(true);
        }
    }

    private void validateTextFields() throws EmptyFieldException {
        if (tfName.getText().length() < 1 || tfCity.getText().length() < 1 || tfCountry.getText().length() < 1) {
            throw new EmptyFieldException("Neskubėkite. Būtina užpildyti visus laukelius");
        }
    }


}
