package com.csquared.csgo.liveui.ui.window;

import com.csquared.csgo.liveui.core.GSIActivityImpl;
import com.csquared.csgo.liveui.core.GSICore;
import com.csquared.csgo.liveui.ui.component.scoreboard.Scoreboard;
import com.csquared.csgo.liveui.ui.component.spectatingpanel.SpectatingPanel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uk.oczadly.karl.csgsi.state.BombState;
import uk.oczadly.karl.csgsi.state.components.DeserializedEnum;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainController {
    @FXML private Label lbDebugInfo;
    @FXML private Scoreboard scoreboard;
    @FXML private SpectatingPanel spectatingPanel;

    @FXML
    private void initialize() {
        registerGSICore();

        scoreboard.setTeamAName("TEAM CHASELTH");
        scoreboard.setTeamBName("TEAM FRANKFORT");
        scoreboard.setTeamAScore(5);
        scoreboard.setTeamBScore(10);
    }

    private void registerGSICore() {
        GSIActivityImpl impl = new GSIActivityImpl()
                .debugLabel(lbDebugInfo)
                .spectatingPanel(spectatingPanel);
        GSICore core = new GSICore(impl);
        core.start();
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
