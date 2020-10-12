package lt.verbus.controller.custom_panels;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import lt.verbus.service.UserServiceSingleton;
import lt.verbus.service.UserStatisticsService;
import lt.verbus.util.StatsTextBuilder;

public class OverallStatsPanel extends HBox {

    private UserStatisticsService userStatisticsService;
    private UserServiceSingleton userServiceSingleton;

    private TextFlow txtStatisticsAgainstOverallAvg;

    public OverallStatsPanel() {
        injectServices();
        setNodeValues();
        formatPanel();
        populatePanelWithNodes();
    }

    private void injectServices() {
        userStatisticsService = new UserStatisticsService();
        userServiceSingleton = UserServiceSingleton.getInstance();
    }

    private void setNodeValues() {
        double ratioAgainstOverAllAvg = userStatisticsService
                .compareUserAvgToOverallAvg(userServiceSingleton.getUser());

        StatsTextBuilder builder = new StatsTextBuilder();
        builder.setPrefix("Tavo atsakymų vidurkis ");
        builder.setRatio(ratioAgainstOverAllAvg);
        builder.setSuffix("nei kitų apklausos dalyvių.");

        txtStatisticsAgainstOverallAvg = builder.build();
    }

    private void formatPanel() {
        setMinHeight(25);
        setSpacing(10);
        setPadding(new Insets(10, 10, 10, 10));
        setStyle("-fx-background-color: white;");
    }

    private void populatePanelWithNodes() {
        getChildren().add(txtStatisticsAgainstOverallAvg);
    }


}
