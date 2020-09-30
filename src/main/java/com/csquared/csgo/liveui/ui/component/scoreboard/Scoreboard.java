package com.csquared.csgo.liveui.ui.component.scoreboard;

import com.csquared.csgo.liveui.ui.UIHelper;
import com.csquared.csgo.liveui.ui.component.ColorVal;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import uk.oczadly.karl.csgsi.state.BombState;
import uk.oczadly.karl.csgsi.state.components.DeserializedEnum;

import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("unused")
public class Scoreboard extends Pane {
    private static final int ANIMATION_DURATION = 300;

    @FXML private Pane root, paneBoard;
    @FXML private Label lbTeamAName, lbTeamBName, lbTeamAScore, lbTeamBScore, lbTime, lbRound, lbFirepower;
    @FXML private ImageView imgTeamALogo, imgTeamBLogo, imgBombStatus;
    @FXML private Rectangle rectFirepowerTeamA, rectFirepowerTeamB, rectBombCountdown;

    private int side = 0;

    public Scoreboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        setStyle("-fx-background-color: transparent;");

        // create clip for paneBoard
        final Rectangle clip = new Rectangle();
        paneBoard.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            clip.setHeight(newValue.getHeight());
            clip.setWidth(newValue.getWidth());
        });
        paneBoard.setClip(clip);


        // initialize firepower bar
        rectFirepowerTeamA.setWidth(390.0);
        rectFirepowerTeamA.setLayoutX(-195.0);
        rectFirepowerTeamB.setWidth(390.0);
        rectFirepowerTeamB.setLayoutX(195.0);

    }

    public void setTeamAName(String name) {
        lbTeamAName.setText(name);
    }

    public void setTeamBName(String name) {
        lbTeamBName.setText(name);
    }

    public void setTeamAScore(int score) {
        lbTeamAScore.setText(Integer.toString(score));
    }

    public void setTeamBScore(int score) {
        lbTeamBScore.setText(Integer.toString(score));
    }

    public void setTime(String time) {
        lbTime.setText(time);
    }

    public void setRound(int now, int total) {
        lbRound.setText("ROUND  " + now + " / " + total);
    }

    public void setBombStatus(DeserializedEnum<BombState.BombStatus> status) {
        if (status.getEnum() == BombState.BombStatus.PLANTED) {
            imgBombStatus.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("ic_bomb_planted.png"))));
            UIHelper.invisible(lbTime, lbRound);
            UIHelper.visible(imgBombStatus);
            TranslateTransition trans2 = new TranslateTransition(Duration.millis(ANIMATION_DURATION), rectFirepowerTeamA);
            TranslateTransition trans3 = new TranslateTransition(Duration.millis(ANIMATION_DURATION), rectFirepowerTeamB);
            trans2.setOnFinished(actionEvent -> {
                TranslateTransition trans1 = new TranslateTransition(Duration.millis(ANIMATION_DURATION), rectBombCountdown);
                trans1.setFromY(0.0);
                trans1.setToY(24.0);
                trans1.play();
            });
            trans2.setFromY(0); trans3.setFromY(0);
            trans2.setToY(-24.0); trans3.setToY(-24.0);
            trans2.play(); trans3.play();
            lbFirepower.setText("BOMB PLANTED");
        } else if (status.getEnum() == BombState.BombStatus.DEFUSED) {
            lbFirepower.setText("BOMB DEFUSED");
            imgBombStatus.setImage(new Image(String.valueOf(getClass().getClassLoader().getResource("ic_bomb_defused.png"))));
            UIHelper.visible(imgBombStatus);
        } else {
            UIHelper.invisible(imgBombStatus);
            UIHelper.visible(lbRound, lbTime);
            lbFirepower.setText("FIREPOWER");
            TranslateTransition trans1 = new TranslateTransition(Duration.millis(ANIMATION_DURATION), rectBombCountdown);
            trans1.setFromY(24.0);
            trans1.setToY(0.0);
            trans1.setOnFinished(actionEvent -> {
                TranslateTransition trans2 = new TranslateTransition(Duration.millis(ANIMATION_DURATION), rectFirepowerTeamA);
                TranslateTransition trans3 = new TranslateTransition(Duration.millis(ANIMATION_DURATION), rectFirepowerTeamB);
                trans2.setFromY(-24.0); trans3.setFromY(-24.0);
                trans2.setToY(0.0); trans3.setToY(0.0);
                trans2.play(); trans3.play();
            });
            trans1.play();
        }
    }

    public void setBombCountdown(double countdown) {
        rectBombCountdown.setLayoutX(- 390 + (countdown / 10) * 390);
    }

    private float relativePosA = 0.0f, relativePosB = 0.0f;
    public void setFirepowerRatio(float ratio) {
        System.out.println(ratio);

        int b = (int) (390 / (ratio + 1));
        int a = 390 - b;
        TranslateTransition transA = new TranslateTransition(Duration.millis(ANIMATION_DURATION), rectFirepowerTeamA);
        transA.setFromX(relativePosA);
        transA.setToX(-195.0 + a);
        transA.play();
        relativePosA = a - 195;

        TranslateTransition transB = new TranslateTransition(Duration.millis(ANIMATION_DURATION), rectFirepowerTeamB);
        transB.setFromX(relativePosB);
        transB.setToX(195 - b);
        transB.play();
        relativePosB = 195 - b;
    }

    public void switchSide() {
        if (side == 0) {
            // switch CT-T to T-CT
            lbTeamAScore.setTextFill(ColorVal.SCOREBOARD_SCORE_T);
            lbTeamBScore.setTextFill(ColorVal.SCOREBOARD_SCORE_CT);

            Stop[] stopsA = new Stop[] {
                    new Stop(0, ColorVal.SCOREBOARD_FIREPOWER_T_LIGHT),
                    new Stop(1, ColorVal.SCOREBOARD_FIREPOWER_T_DARK) };
            LinearGradient lgA = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stopsA);
            rectFirepowerTeamA.setFill(lgA);
            Stop[] stopsB = new Stop[] {
                    new Stop(0, ColorVal.SCOREBOARD_FIREPOWER_CT_DARK),
                    new Stop(1, ColorVal.SCOREBOARD_FIREPOWER_CT_LIGHT) };
            LinearGradient lgB = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stopsB);
            rectFirepowerTeamB.setFill(lgB);

            side = 1;
        } else {
            // switch T-CT to CT-T
            lbTeamAScore.setTextFill(ColorVal.SCOREBOARD_SCORE_CT);
            lbTeamBScore.setTextFill(ColorVal.SCOREBOARD_SCORE_T);

            Stop[] stopsA = new Stop[] {
                    new Stop(0, ColorVal.SCOREBOARD_FIREPOWER_CT_LIGHT),
                    new Stop(1, ColorVal.SCOREBOARD_FIREPOWER_CT_DARK) };
            LinearGradient lgA = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stopsA);
            rectFirepowerTeamA.setFill(lgA);
            Stop[] stopsB = new Stop[] {
                    new Stop(0, ColorVal.SCOREBOARD_FIREPOWER_T_DARK),
                    new Stop(1, ColorVal.SCOREBOARD_FIREPOWER_T_LIGHT) };
            LinearGradient lgB = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stopsB);
            rectFirepowerTeamB.setFill(lgB);

            side = 0;
        }
    }
}
