package sample;

import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.Clock.ClockSkinType;
import eu.hansolo.medusa.ClockBuilder;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    private Clock clock;
    private long lastTimerCall;
    private AnimationTimer timer;


    @FXML
    private Label digit;

    @FXML
    private VBox hboxForClock;

    @FXML
    private ComboBox<ClockSkinType> combo;

    @FXML
    void changeType(Event event) {

        if (combo.getValue() != null) {
            clock.setSkinType(combo.getValue());
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < ClockSkinType.values().length; i++) {
            combo.getItems().add(ClockSkinType.values()[i]);
        }

        clock = ClockBuilder.create()
                .skinType(ClockSkinType.SLIM)
                .backgroundPaint(Color.web("#a31d57"))
                .borderPaint(Color.web("#e09a16"))
                .borderWidth(4.7)
                .dateColor(Color.YELLOWGREEN)
                .secondColor(Color.web("#d2fa7d"))
                .dateVisible(true)
                .discreteHours(false)
                .discreteMinutes(false)
                .discreteSeconds(false)
                .hourColor(Color.CORAL)
                .minuteColor(Color.BLUE)
                .hourTickMarkColor(Color.AQUA)
                .hourTickMarksVisible(true)
                .knobColor(Color.MAGENTA)
                .running(true)
                .prefSize(400, 400)
                .locale(new Locale("en", "EN"))
                .build();
        clock.setSecondsVisible(true);


        hboxForClock.getChildren().add(clock);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH : mm : ss");

//        Platform.runLater(() -> {
            AnimationTimer timer1 = new AnimationTimer() {
                @Override
                public void handle(long now) {

                    digit.setText(formatter.format(LocalDateTime.now()));

                }
            };

            timer1.start();

//        });

    }

}
