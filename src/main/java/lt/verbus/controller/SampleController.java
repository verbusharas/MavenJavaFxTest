package lt.verbus.controller;

import javafx.scene.control.Button;
import lt.verbus.App;

import java.io.IOException;

public class SampleController {

    public Button button1;

    public void button1Clicked() throws IOException {
        System.out.println("Hello hello, hello...");
        button1.setText("Stop touching me");
        App.loadQuizScreen();
    }

}
