package com.csquared.csgo.liveui.ui.component.playerlist.item;

import com.csquared.csgo.liveui.ui.UIHelper;
import com.csquared.csgo.liveui.ui.component.Side;
import com.csquared.csgo.liveui.ui.component.utilityfield.UtilityField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import uk.oczadly.karl.csgsi.state.PlayerState;
import uk.oczadly.karl.csgsi.state.PlayerState.WeaponDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("unused")
public abstract class PlayerListItem extends Pane {
    @FXML protected Pane paneRoot;
    @FXML private ImageView imgWeapon, imgArmor;
    @FXML protected ImageView imgAvatar;
    @FXML protected Rectangle rectBackground, rectHealth, rectSelected;
    @FXML private Label lbPlayerName, lbHealth, lbKill, lbDeath;
    @FXML protected UtilityField utilityField;

    private int health = 100;

    protected void init() {
        rectSelected.setVisible(false);

        final Rectangle clip = new Rectangle();
        paneRoot.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            clip.setHeight(newValue.getHeight());
            clip.setWidth(newValue.getWidth());
        });
        paneRoot.setClip(clip);

        new Timer().schedule(healthBarTimerTask, 0, 20);
    }

    public void setPlayer() {

    }

    public void select() {
        rectSelected.setVisible(true);
    }

    public void unselect() {
        rectSelected.setVisible(false);
    }

    public void setHealth(int health) {
        lbHealth.setText(Integer.toString(health));
        onHealthChange(health);
    }

    public void setArmor(boolean hasHelmet, int armor) {

    }

    public void setInventory(List<WeaponDetails> inventory) {
        utilityField.setUtilities(inventory);
    }

    abstract void switchSide(Side side);

    // TODO: alive judgement when first set
    private boolean alive = true;
    public void onHealthChange(int health) {
        this.health = health;
        if (alive && health == 0) {
            alive = false;
            UIHelper.invisible(imgWeapon, utilityField, imgArmor, lbHealth);
            imgAvatar.setEffect(new ColorAdjust(0, -1, -0.3, 0));
            onDead();
        } else if (!alive && health > 0) {
            alive = true;
            UIHelper.visible(imgWeapon, utilityField, imgArmor, lbHealth);
            imgAvatar.setEffect(null);
            onRespawn();
        }
    }

    abstract void onDead();
    abstract void onRespawn();

    abstract void onHealthBarTimerActive(Rectangle rect, int health);

    private final TimerTask healthBarTimerTask = new TimerTask() {
        @Override
        public void run() {
            onHealthBarTimerActive(rectHealth, health);
        }
    };
}
