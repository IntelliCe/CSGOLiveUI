package com.csquared.csgo.liveui.ui.window;

import com.csquared.csgo.liveui.ui.component.scoreboard.Scoreboard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import uk.oczadly.karl.csgsi.state.BombState;
import uk.oczadly.karl.csgsi.state.components.DeserializedEnum;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainController {
    @FXML private Scoreboard scoreboard;

    @FXML
    private void initialize() {
        scoreboard.setTeamAName("TYLOO");
        scoreboard.setTeamBName("VICI GAMING");
        scoreboard.setTeamAScore(5);
        scoreboard.setTeamBScore(10);
    }

    private int s = 0;
    @FXML
    private void debug() {
        /*
        // debug section for firepower
        Random random = new Random();
        scoreboard.setFirepowerRatio(random.nextFloat() * 2);


        if (s == 0) {
            scoreboard.setBombStatus(new DeserializedEnum<>(BombState.BombStatus.PLANTED, ""));
            s = 1;
        } else if (s == 1) {
            scoreboard.setBombStatus(new DeserializedEnum<>(BombState.BombStatus.CARRIED, ""));
            s = 0;
        }*/

        scoreboard.setRound(15, 30);
        scoreboard.setBombStatus(new DeserializedEnum<>(BombState.BombStatus.PLANTED, ""));
        long millis = System.currentTimeMillis();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                long time = 10000 - (System.currentTimeMillis() - millis);
                if (time <= 5000) {
                    Platform.runLater(() -> scoreboard.setBombStatus(new DeserializedEnum<>(BombState.BombStatus.DEFUSED, "")));
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> scoreboard.setBombStatus(new DeserializedEnum<>(BombState.BombStatus.CARRIED, "")));
                        }
                    }, 3000);
                    cancel();
                }
                scoreboard.setBombCountdown(time / 1000.0);
            }
        };
        new Timer().schedule(task, 0, 50);


    }
}
