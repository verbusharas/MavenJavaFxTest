package lt.verbus;

import javafx.application.Application;
import javafx.stage.Stage;
import lt.verbus.controller.stage_controllers.StageController;
import lt.verbus.exception.EmptyRepositoryException;
import lt.verbus.exception.NumberOfQuestionsMismatchException;
import lt.verbus.service.FakeInitial;
import lt.verbus.service.InitialValidatorService;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {



        StageController.setPrimaryStage(stage);
        StageController.loadWelcomeScreen();
        StageController.getPrimaryStage().show();
        FakeInitial fakeSaver = new FakeInitial();
        fakeSaver.fakeCreateAndSave();


    }
}

