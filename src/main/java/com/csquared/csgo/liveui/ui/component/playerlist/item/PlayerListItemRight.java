package com.csquared.csgo.liveui.ui.component.playerlist.item;

import com.csquared.csgo.liveui.ui.component.Side;
import com.csquared.csgo.liveui.ui.component.utilityfield.UtilityField;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

@SuppressWarnings("unused")
public class PlayerListItemRight extends PlayerListItem {

    public PlayerListItemRight() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerListItemRight.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        super.init();
        utilityField.setAlignment(UtilityField.Alignment.LEFT);
    }

    @Override
    public void switchSide(Side side) {

    }

    @Override
    void onDead() {
        TranslateTransition anim1 = new TranslateTransition(Duration.millis(500), rectBackground);
        anim1.setFromX(0.0);
        anim1.setToX(100.0);
        anim1.play();
        TranslateTransition anim2 = new TranslateTransition(Duration.millis(500), imgAvatar);
        anim2.setFromX(0.0);
        anim2.setToX(100.0);
        anim2.play();
    }

    @Override
    void onRespawn() {
        TranslateTransition anim1 = new TranslateTransition(Duration.millis(500), rectBackground);
        anim1.setFromX(100.0);
        anim1.setToX(0.0);
        anim1.play();
        TranslateTransition anim2 = new TranslateTransition(Duration.millis(500), imgAvatar);
        anim2.setFromX(100.0);
        anim2.setToX(0.0);
        anim2.play();
    }

    @Override
    void onHealthBarTimerActive(Rectangle rect, int health) {
        int x = (int) ((100 - health) / 100.0 * 350);
        if (rect.getLayoutX() > x) {
            if (rect.getLayoutX() - x > 10) {
                Platform.runLater(() -> rect.setLayoutX(rect.getLayoutX() - 10));
            } else {
                Platform.runLater(() -> rect.setLayoutX(x));
            }
        } else if (rect.getLayoutX() < x) {
            if (x - rect.getLayoutX() > 10) {
                Platform.runLater(() -> rect.setLayoutX(rect.getLayoutX() + 10));
            } else {
                Platform.runLater(() -> rect.setLayoutX(x));
            }
        }
    }

    private boolean debugVal = true;
    @FXML
    public void debug() {
        if (debugVal) {
            setHealth(0);
            debugVal = false;
        } else {
            setHealth(100);
            debugVal = true;
        }
    }
}
